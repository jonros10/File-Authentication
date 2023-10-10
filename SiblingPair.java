//TODO: Complete java docs and code in missing spots.

/**
 * This class is used to store each element in the Authentication Pairs Route to Root (APRR).
 * Task 2: You are required to develop the constructor and the set and get methods of the leftSibling and rightSibling instance variables of this class.
 * @author Maha Shamseddine.
 * @param <X> Type of input of SiblingPair class
 */
public class SiblingPair<X> {
 /**
     * This variable is the left side node.
     */
	public X leftSibling;

 /**
     * This varibale is the right side node.
     */
	public X rightSibling;
    
    /**
     * This is the constructor for siblingpair.
     * @param leftS user given left node.
     * @param rightS user given right node.
     */
    public SiblingPair(X leftS, X rightS) {
        //throw IllegalArgumentException for invalid parameters
        if((leftS == null) || (rightS == null)){
            throw new IllegalArgumentException();
        }
        this.leftSibling = leftS;
        this.rightSibling = rightS;
    }

    /**
     * Default Constructor.
     */
    public SiblingPair(){
    	this.leftSibling = null;
        this.rightSibling = null;
    }
   
    /**
     * This method gets the left side node.
     * @return the left node of the pair.
     */
    public X getLeftSibling() {
        return this.leftSibling;
    }
    
    /**
     * This method gets the right side node.
     * @return the right node of the pair.
     */
    public X getRightSibling() {
        return this.rightSibling;
    }
}
