import java.util.*;

/**
 * This is the class representing the complete MerkleTree.
 * 
 * @author Jonathan Rosales.
 */
public class MerkleTree {

	/**
	 * This variable sets up the root for the tree.
	 */
	public static MerkleTreeNode root;

	/**
	 * This variable represents the number of files.
	 */
	public int numberOfFiles;

	   /**
	 	* This variable sets up an arraylist of leaves.
		*/
    public ArrayList<MerkleTreeNode> leaves;

	/**
	 * This variable keeps track of the parents of the parents.
	*/
	private ArrayList<MerkleTreeNode> parents;

	/**
	 * This method sets up the MerkleTree from the user given string of files.
	 * @param files	user given string array of files.
	 * @return	MerkleTree with parents and leaves(files).
	 */
	public String constructMerkleTree(String[] files){
		//Task 3: You are required to develop the code for the constructMerkleTree method.
		//Running time complexity of this method: O(n) where n is the number of files (size of the files array)
		//You can assume that the size of the files will be given as 2^n
		//throw IllegalArgumentException for invalid parameters
		if (files == null){
			throw new IllegalArgumentException();	//If the files are null, throw IAE
		}

		this.leaves = new ArrayList<>(); //finish setting up the arraylist for leaves.
		numberOfFiles = files.length;	//update number of files with files.length.
		
		for(int i = 0; i < numberOfFiles;i++){			//Sets up the arraylist of leaves(files)
			MerkleTreeNode newNode = new MerkleTreeNode();
			newNode.setStr(files[i]);
			this.leaves.add(newNode);
		}

		ArrayList<MerkleTreeNode> parents1 = new ArrayList<>();
		for(int j = 0; j < numberOfFiles; j+=2){			//Sets up the parents for each leaf.
			MerkleTreeNode parentnode = new MerkleTreeNode();
			this.leaves.get(j).setParent(parentnode);
			this.leaves.get(j+1).setParent(parentnode);
			parentnode.setLeft(this.leaves.get(j));
			parentnode.setRight(this.leaves.get(j+1));
			parentnode.setStr(Hashing.cryptHash(this.leaves.get(j).getStr() + this.leaves.get(j+1).getStr())); 
			parents1.add(parentnode);
		}
		this.parents = new ArrayList<>();		//Sets up parents for each parent after until it reaches 2 to make up the root.
		while(parents1.size() != 2){
			for(int i = 0; i < parents1.size(); i+=2){
				MerkleTreeNode newNode = new MerkleTreeNode();
				parents1.get(i).setParent(newNode);
				parents1.get(i+1).setParent(newNode);
				newNode.setLeft(parents1.get(i));
				newNode.setRight(parents1.get(i+1));
				newNode.setStr(Hashing.cryptHash(parents1.get(i).getStr() + parents1.get(i+1).getStr())); 
				this.parents.add(newNode);
			}
			parents1 = this.parents;
			this.parents = new ArrayList<>(); 
		}
		root = new MerkleTreeNode();	//set up the final node (root) which will always have two kids and go from there.
		parents1.get(0).setParent(root);
		parents1.get(1).setParent(root);
		root.setLeft(parents1.get(0));
		root.setRight(parents1.get(1));
		root.setStr(Hashing.cryptHash(parents1.get(0).getStr() + parents1.get(1).getStr()));

		return root.getStr();	
	}

	/*     
		SiblingPair is a generic data structure defined in SiblingPair.java
	*/
	/**
	 * This method sets up an ArrayList with strings from the nodes from files to root.
	 * @param fileIndex	the user given index for the file.
	 * @return	the ArrayList with the string pairs.
	 */
	public ArrayList<SiblingPair<String>> sendAppr(int fileIndex){
		//Task 4: You are required to develop the code for the sendAppr method.
		//Running time complexity of this method: O(logn)
		//throw IllegalArgumentException for invalid parameters
		if(root == null){	//return null if there is no root (tree was not constructed).
			return null;
		}

		if((fileIndex < 0) || (fileIndex > (numberOfFiles-1))){
			throw new IllegalArgumentException();
		}
		ArrayList<SiblingPair<String>> siblings = new ArrayList<>(); 
		SiblingPair<String> newPair;
		if(fileIndex % 2 == 0){ 
			newPair = new SiblingPair<>(this.leaves.get(fileIndex).getStr(),this.leaves.get(fileIndex+1).getStr()); //get the leaves (if even).
			siblings.add(newPair);
		}
		else if(fileIndex % 2 != 0){
			newPair = new SiblingPair<>(this.leaves.get(fileIndex-1).getStr(),this.leaves.get(fileIndex).getStr()); //get the leaves (if odd).
			siblings.add(newPair);
		}
		MerkleTreeNode current = this.leaves.get(fileIndex).getParent().getParent();
		while(current != null){
			newPair = new SiblingPair<>(current.getLeft().getStr(),current.getRight().getStr()); //set up pairs while current is not root.
			siblings.add(newPair);
			current = current.getParent();
		}
		MerkleTreeNode nullNode = new MerkleTreeNode();
		nullNode.setStr("null"); //set up the (root, null) pair and since it can't be empty we add in a temporary "null" node.
		newPair = new SiblingPair<String>(root.getStr(), nullNode.getStr());
		siblings.add(newPair);

		return siblings;	//return list.
	}

	/**
	 * This method verifies if the root value given is equal to the root value in the aprr ArrayList.
	 * @param aprr	user given ArrayList.
	 * @param rootValue	user given root hashed string to compare in the aprr ArrayList.
	 * @return	true or false if it is the same value.
	 */
	public static boolean verifyIntegrity(ArrayList<SiblingPair<String>> aprr, String rootValue){
		//Task 5: You are required to develop the code for the verifyIntegrity method
		//Running time complexity of this method: O(logn)
		//throw IllegalArgumentException for invalid parameters
		if((aprr == null) || (rootValue == null)){	//check for valid parameters.
			throw new IllegalArgumentException();
		}
		for(int i = 0; i<aprr.size(); i++){	//Goes through the sibling arrayList to see if the final item is equal to the rootvalue given.
			if(aprr.get(i).getRightSibling() == "null"){
				if(aprr.get(i).getLeftSibling() == rootValue){
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * This method replaces the file with another one and then corrects the hash for the nodes up to the root.
	 * @param fileIndex the user given index in the file.
	 * @param updatedFile the user given "file".
	 * @return the root hashed string.
	 */
	public String replaceFile(int fileIndex, String updatedFile){
		//Task 6: You are required to develop the code for the replaceFile method.
		//Running time complexity of this method: O(logn)
		//throw IllegalArgumentException for invalid parameters
		if(root == null){	//return null if there is no root (tree was not constructed).
			return null;
		}

		if((fileIndex < 0) || (fileIndex > (numberOfFiles-1)) || (updatedFile == null)){ // check if valid parameters.
			throw new IllegalArgumentException();
		}

		this.leaves.get(fileIndex).setStr(updatedFile);	//gets the string of the index node.
		MerkleTreeNode current = this.leaves.get(fileIndex);
		while(current != root){	//traverse until it reaches the root and changes that.
			current.getParent().setStr(Hashing.cryptHash(current.getParent().getLeft().getStr() + current.getParent().getRight().getStr()));
			current = current.getParent();
		}
		return root.getStr();
	}
}
