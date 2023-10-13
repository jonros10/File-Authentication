//This class is used to represent nodes in the Merkle Tree.
//Task 1: You are required to develop the constructor and the set and get methods of all the instance variables of this class.

/**
 * This class sets up the MerkleTree Nodes.
 * 
 * @author Jonathan Rosales.
 */
public class MerkleTreeNode{
	   /**
	 	* This variable sets up the parent node from the tree.
	 	*/
    private MerkleTreeNode parent;
 /**
	 * This variable sets up the left node of the parent.
	 */
	private MerkleTreeNode left;
	   /**
	 * This variable sets up the right node of the parent.
	 */
    private MerkleTreeNode right;
	   /**
	 * This variable would be the string within the node.
	 */
    private String str;

	/**
	 * Develop a default MerkleTreeNode constructor that initializes the instance variables to null.
	 */
	public MerkleTreeNode(){
		this.parent = null;
		this.left = null;
		this.right = null;
		this.str = "";
	}
	
	/**
	 * This method develops a MerkleTreeNode that initiates with user given variables.
	 * @param parent parent node in tree.
	 * @param left left node from parent.
	 * @param right right node from parent.
	 * @param str string variable given by user.
	 */
	public MerkleTreeNode(MerkleTreeNode parent,MerkleTreeNode left,MerkleTreeNode right,String str){
		this.parent = parent;
		this.left = left;
		this.right = right;
		this.str = str;
	}

	/**
	 * Develop the set and get methods for the instance variables parent, left, right and str.
	 * @return  parent node.
	 */
	public MerkleTreeNode getParent(){
		return this.parent;
	}

	/**
	 * This method gets the left node.
	 * @return left node.
	 */
	public MerkleTreeNode getLeft(){
		return this.left;
	}
	
	/**
	 * This method gets the right node.
	 * @return right node.
	 */
	public MerkleTreeNode getRight(){
		return this.right;
	}

	/**
	 * This method gets the string variable.
	 * @return the string.
	 */
	public String getStr(){
		return this.str;
	}
	
	/**
	 * This method sets the parent node.
	 * @param parent user given parent node.
	 */
	public void setParent(MerkleTreeNode parent){
		if(parent == null){
			throw new IllegalArgumentException();
		}
		this.parent = parent;
		//throw IllegalArgumentException for invalid parameters
	}
	
	/**
	 * This method sets the left node.
	 * @param left user given left node.
	 */
	public void setLeft(MerkleTreeNode left){
		if(left == null){
			throw new IllegalArgumentException();
		}
		this.left = left;
		//throw IllegalArgumentException for invalid parameters
	}

	/**
	 * This method sets the right node.
	 * @param right user given right node.
	 */
	public void setRight(MerkleTreeNode right){
		if(right == null){
			throw new IllegalArgumentException();
		}
		this.right = right;
		//throw IllegalArgumentException for invalid parameters
	}

	/**
	 * This method sets the string.
	 * @param str user given string.
	 */
	public void setStr(String str){
		if(str == null){
			throw new IllegalArgumentException();
		}
		this.str = str;
		//throw IllegalArgumentException for invalid parameters
	}        
        
}
