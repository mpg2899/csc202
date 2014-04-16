//----------------------------------------------------------------------------
// BinarySearchTree.java          by Dale/Joyce/Weems                Chapter 8
//
// Defines all constructs for a reference-based BST
//----------------------------------------------------------------------------

package assignment3;

import assignment2.LLNode;
import assignment2.MazePath;
import assignment3.queues.*;
import assignment3.stacks.*;
import support.BSTNode;      

public class BinarySearchTree<T extends Comparable<T>> 
             implements BSTInterface<T>
{
  protected BSTNode<T> root;      // reference to the root of this BST

  boolean found;   // used by remove
  
  // for traversals
  protected LinkedUnbndQueue<T> inOrderQueue;    // queue of info
  protected LinkedUnbndQueue<T> preOrderQueue;   // queue of info
  protected LinkedUnbndQueue<T> postOrderQueue;  // queue of info

  public BinarySearchTree()
  // Creates an empty BST object.
  {
    root = null;
  }

  public boolean isEmpty()
  // Returns true if this BST is empty; otherwise, returns false.
  {
    return (root == null);
  }

  private int recSize(BSTNode<T> tree)
  // Returns the number of elements in tree.
  {
    if (tree == null)    
      return 0;
    else
      return recSize(tree.getLeft()) + recSize(tree.getRight()) + 1;
  }

  public int size()
  // Returns the number of elements in this BST.
  {
    return recSize(root);
  }

  public int size2()
  // Returns the number of elements in this BST.
  {
    int count = 0;
    if (root != null)
    {
      LinkedStack<BSTNode<T>> hold = new LinkedStack<BSTNode<T>>();
      BSTNode<T> currNode;
      hold.push(root);
      while (!hold.isEmpty())
      {
        currNode = hold.top();
        hold.pop();
        count++;
        if (currNode.getLeft() != null)
          hold.push(currNode.getLeft());
        if (currNode.getRight() != null)
          hold.push(currNode.getRight());
      }
    }
    return count;
  }

  
  // Non recursive function.
  private boolean recContains(T element, BSTNode<T> tree) {
	  if (tree == null)
	      return false;       // element is not found
	  LinkedStack myStack = new LinkedStack<LLNode<T>>();
	  BSTNode<T> myTree = tree;
	  
	  int found = 0;
	  int empty = 0;
	  int waspopped = 0;
	  while (empty == 0) {
		  if (element.compareTo(myTree.getInfo()) < 0) {
			  if (myTree.getLeft() != null) {
				  //myStack.push(myTree);
				  myTree = myTree.getLeft();
				 // waspopped = 0;
			  }
			  //else if (!myStack.isEmpty()) {
			  else {
				  empty = 1;
				  //myTree = (BSTNode<T>) myStack.top();
				  //myStack.pop();
				  //waspopped = 1;
			  }
		  }
		  else if (element.compareTo(myTree.getInfo()) > 0) {
			  if (myTree.getRight() != null) {
				  //myStack.push(myTree);
				  myTree = myTree.getRight();
			  }
			  else {
				  empty = 1;
			  }
		  }
		  else return true;
	  }
	  return false;
  }

  public boolean contains (T element)
  // Returns true if this BST contains an element e such that 
  // e.compareTo(element) == 0; otherwise, returns false.
  {
    return recContains(element, root);
  }
  
  
  // Non recursive function.
  private T recGet(T element, BSTNode<T> tree)
  // Returns an element e from tree such that e.compareTo(element) == 0;
  // if no such element exists, returns null.
  {
	  if (tree == null)
	      return null;       // element is not found

	  BSTNode<T> myTree = tree;
	  int empty = 0;

	  while (empty == 0) {
		  if (element.compareTo(myTree.getInfo()) < 0) {
			  if (myTree.getLeft() != null) {

				  myTree = myTree.getLeft();
			  }
			  else {
				  empty = 1;
			  }
		  }
		  else if (element.compareTo(myTree.getInfo()) > 0) {
			  if (myTree.getRight() != null) {
				  myTree = myTree.getRight();
			  }
			  else {
				  empty = 1;
			  }
		  }
		  else return myTree.getInfo();
	  }
	  return null;
  }

  public T get(T element)
  // Returns an element e from this BST such that e.compareTo(element) == 0;
  // if no such element exists, returns null.
  {
    return recGet(element, root);
  }
  

  
 // Non Recursive Function
  private BSTNode<T> recAdd(T element, BSTNode<T> tree)
  // Adds element to tree; tree retains its BST property.
  {
	BSTNode<T> myTree = tree;
	boolean inserted = false;
    if (myTree == null) {
      // Empty tree
      tree = new BSTNode<T>(element);
    }
    else {
    	
    	while (inserted == false) {
    		if (element.compareTo(myTree.getInfo()) <= 0) { //left
    			if (myTree.getLeft() == null) { // no left leaf
    				myTree.setLeft(new BSTNode<T>(element));
    				inserted = true;
    			}
    			else { // existing left leaf
    				myTree = myTree.getLeft();
    			}
    		}
    		else { // right
    			if (myTree.getRight() == null) { // no left leaf
    				myTree.setRight(new BSTNode<T>(element));
    				inserted = true;
    			}
    			else { // existing right leaf
    				myTree = myTree.getRight();
    			}
    		}
    	}
    }

    return tree;
  }

  public void add (T element)
  // Adds element to this BST. The tree retains its BST property.
  {
    root = recAdd(element, root);
  }
  

  private T getPredecessor(BSTNode<T> tree)
  // Returns the information held in the rightmost node in tree
  {
    while (tree.getRight() != null)
      tree = tree.getRight();
    return tree.getInfo();
  }

  private BSTNode<T> removeNode(BSTNode<T> tree)
  // Removes the information at the node referenced by tree.
  // The user's data in the node referenced by tree is no
  // longer in the tree.  If tree is a leaf node or has only
  // a non-null child pointer, the node pointed to by tree is
  // removed; otherwise, the user's data is replaced by its
  // logical predecessor and the predecessor's node is removed.
  {
    T data;

    if (tree.getLeft() == null)
      return tree.getRight();
    else if (tree.getRight() == null) 
      return tree.getLeft();
    else
    {
      data = getPredecessor(tree.getLeft());
      tree.setInfo(data);
      tree.setLeft(recRemove(data, tree.getLeft()));  
      return tree;
    }
  }

  private BSTNode<T> recRemove(T element, BSTNode<T> tree)
  // Removes an element e from tree such that e.compareTo(element) == 0
  // and returns true; if no such element exists, returns false. 
  {
    if (tree == null)
      found = false;
    else if (element.compareTo(tree.getInfo()) < 0)
      tree.setLeft(recRemove(element, tree.getLeft()));
    else if (element.compareTo(tree.getInfo()) > 0)
      tree.setRight(recRemove(element, tree.getRight()));
    else  
    {
      tree = removeNode(tree);
      found = true;
	 }
    return tree;
  }

  public boolean remove (T element)
  // Removes an element e from this BST such that e.compareTo(element) == 0
  // and returns true; if no such element exists, returns false. 
  {
    root = recRemove(element, root);
    return found;
  }

  
  // Non recursive function
  private void inOrder(BSTNode<T> tree)
  // Initializes inOrderQueue with tree elements in inOrder order.
  {

    BSTNode<T> myTree = tree;
    LinkedStack myStack = new LinkedStack<LLNode<BSTNode<T>>>();
    int mypopped = 0;
    int written = 0;
    int fromright = 0;
    if (myTree != null) {
    	int isempty = 0;
    	while (isempty == 0) {
    		System.out.println(myTree.getInfo());
    		// There is something on the left, and we haven't popped.
    		if (myTree.getLeft() != null && mypopped == 0) {
    			myStack.push(myTree);
    			myTree = myTree.getLeft();
    			System.out.println("Going left");
    		}
    		// There is nothing on the left, and haven't written tree.
    		else if ((myTree.getLeft() == null && written == 0) || (myTree.getLeft() != null && mypopped == 1 && written == 0)) {
    			inOrderQueue.enqueue(myTree.getInfo());
    			written = 1;
    			mypopped = 0;
    			System.out.println("queuing " + myTree.getInfo());
    		}
    		// There is something on the right, and we have written tree.
    		if (myTree.getRight() != null && written == 1 && mypopped == 0) {
    			
    			System.out.println("Going Right");
    			myStack.push(myTree);
    			myTree = myTree.getRight();
    			written = 0;
    			mypopped = 0;
    		}
    		// There is nothing on the right, and we have written tree.
    		else if ((myTree.getRight() == null && written == 1) || (myTree.getRight() != null && mypopped == 1)) {
    			System.out.println("There is nothing on the right, and we have written tree.");
    			// Anything left in stack?
    			if (myStack.isEmpty() == false) {
    				// Check to see if we just went right.
    				if (myTree.getInfo() == ((BSTNode<T>) myStack.top()).getRight().getInfo() || fromright == 1) {
    					System.out.println("We're popping, and we just came from the right.");
    					myStack.pop();
        				mypopped = 1;
        				written = 1;
        				fromright = 1;
    					if (myStack.isEmpty() == false) {
            				myTree = (BSTNode<T>) myStack.top();
        				}
    					else isempty = 1;
    				}
    				else { // We're popping, but we didn't just come from the right.
    					System.out.println("We're popping, but we didn't just come from the right.");
    					mypopped = 1;
    					written = 0;
    					myStack.pop();
    					fromright = 0;
    					if (myStack.isEmpty() == false) {
    					myTree = (BSTNode<T>) myStack.top();
    					}
    					else isempty = 1;
    					
    				}
    			
    			}
    			else isempty = 1;
    		}
    		
    	}
    }

  }
  
  // Non Recursive Function.
  private void preOrder(BSTNode<T> tree)
  // Initializes preOrderQueue with tree elements in preOrder order.
  {
	  // queue tree.
	  // go left.
	  // go right.
	    BSTNode<T> myTree = tree;
	    LinkedStack myStack = new LinkedStack<LLNode<BSTNode<T>>>();
	    int mypopped = 0;
	    int written = 0;
	    int fromright = 0;
	    if (myTree != null) {
	    	int isempty = 0;
	    	while (isempty == 0) {
	    		System.out.println(myTree.getInfo());
	    		// Haven't written, haven't popped.
	    		if (written == 0 && mypopped == 0) {
	    			//myStack.push(myTree);
	    			preOrderQueue.enqueue(myTree.getInfo());
	    			System.out.println("queuing " + myTree.getInfo());
	    			written = 1;
	    			myStack.push(myTree);
	    			
	    		}
	    		// We have written the current tree node to stack and queue.
	    	    if (written == 1) {
	    	    	// Never go left after popping.
	    			if (myTree.getLeft() != null  && mypopped == 0) {
	    				System.out.println("Going left");
	    				//myStack.push(myTree);
	    				written = 0;
	    				myTree = myTree.getLeft();
	    			}
	    			// Since we couldn't go left, let's go right.
	    			else if (myTree.getRight() != null) {
	    				System.out.println("Going Right");
	    				//myStack.push(myTree);
	    				fromright++;
	    				written = 0;
	    				myTree = myTree.getRight();
	    				mypopped = 0;
	    			}
	    			// Pop back to a node where we haven't gone right yet.
	    			else if (fromright >= 1) {
	    				while (fromright >= 0) {
	    					System.out.println("popping rights: " + fromright);
	    					if (myStack.isEmpty() == false) {
	    						myStack.pop();
	        				}
	    					if (myStack.isEmpty() == false) {
	        					myTree = (BSTNode<T>) myStack.top();
	        				}
	    					else isempty = 1;
	    					fromright--;
	    				}
	    				fromright = 0;
	    				mypopped = 1;
	    			}
	    			// We already went left and/or right, but now must pop again.
	    			else if (mypopped == 1) {
	    				myStack.pop();
	    				if (myStack.isEmpty() == false) {
        					myTree = (BSTNode<T>) myStack.top();
        				}
    					else isempty = 1;
	    			}
	    			// We are at a leaf node, let's pop back up a level.
	    			else if (mypopped == 0) {
	    				mypopped = 1;
	    				myStack.pop();
	    				if (myStack.isEmpty() == false) {
        					myTree = (BSTNode<T>) myStack.top();
        				}
    					else isempty = 1;
	    			}
	    			else isempty = 1;
	    		}

	    		else isempty = 1;
	       }
	    }
    
  }


  private void postOrder(BSTNode<T> tree)
  // Initializes postOrderQueue with tree elements in postOrder order.
  {
    if (tree != null)
    {
      postOrder(tree.getLeft());
      postOrder(tree.getRight());
      postOrderQueue.enqueue(tree.getInfo());
    }
  }

  private void postOrder2(BSTNode<T> tree)
  // Initializes postOrderQueue with tree elements in postOrder order.
  {
    	// go left
    	// go right
    	// write
	    BSTNode<T> myTree = tree;
	    LinkedStack myStack = new LinkedStack<LLNode<BSTNode<T>>>();
	    LinkedStack dirStack = new LinkedStack<LLNode<Integer>>();
	    int mypopped = 0;
	    int written = 0;
	    int popcontinue = 1;
	    if (myTree != null) {
	    	int isempty = 0;
	    	while (isempty == 0) {
    			if (myTree.getLeft() != null  && mypopped == 0) {
    				System.out.println("Going left");
    				myStack.push(myTree);
    				myTree = myTree.getLeft();
    				dirStack.push(1);
    			}
    			// Nothing on the left, let's go right.
    			else if (myTree.getRight() != null) {
    				System.out.println("Going right, node: " + myTree.getInfo());
    				myStack.push(myTree);
    				myTree = myTree.getRight();
    				dirStack.push(0);
    				// Allow going left again.
    				mypopped = 0;
    			}
    			// Nothing on the right, let's write and pop.
    			else {
    				
    				
    				// If the most recent direction was 1
    				// Then we must pop, and set mypopped to 1
    				// to indicate we cannot go left.
    				if (dirStack.isEmpty() == false) {
    					if (dirStack.top() == (Integer)1) {
    						postOrderQueue.enqueue(myTree.getInfo());
    						mypopped = 1;
    						dirStack.pop();
    						myStack.pop();
    						if (myStack.isEmpty() == false) {
    							myTree = (BSTNode<T>) myStack.top();
    						}
    						else isempty = 1;
    					}
    					popcontinue = 1;
    					while (!dirStack.isEmpty() && popcontinue == 1) {
    						postOrderQueue.enqueue(myTree.getInfo());
    						System.out.println("queuing: " + myTree.getInfo());
    						if (dirStack.top() == (Integer)0) {
    							mypopped = 1;
    							dirStack.pop();
    							myStack.pop();
    							
    							if (myStack.isEmpty() == false) {
    								myTree = (BSTNode<T>) myStack.top();
    								System.out.println("after pop: " + myTree.getInfo());
    							}
    							else isempty = 1;
    						}
    						else popcontinue = 0;
    					
    					}
    				}
    				else isempty = 1;
    			 
    			}
	    	}
	    }
    
  }
  public int reset(int orderType)
  // Initializes current position for an iteration through this BST
  // in orderType order. Returns current number of nodes in the BST.
  {
    int numNodes = size();

    if (orderType == INORDER)
    {
      inOrderQueue = new LinkedUnbndQueue<T>();
      inOrder(root);
    }
    else
    if (orderType == PREORDER)
    {
      preOrderQueue = new LinkedUnbndQueue<T>();
      preOrder(root);
    }
    if (orderType == POSTORDER)
    {
      postOrderQueue = new LinkedUnbndQueue<T>();
      postOrder2(root);
    }
    return numNodes;
  }

  public T getNext (int orderType)
  // Preconditions: The BST is not empty
  //                The BST has been reset for orderType
  //                The BST has not been modified since the most recent reset
  //                The end of orderType iteration has not been reached
  //
  // Returns the element at the current position on this BST for orderType
  // and advances the value of the current position based on the orderType. 
  {
    if (orderType == INORDER)
      return inOrderQueue.dequeue();
    else
    if (orderType == PREORDER)
      return preOrderQueue.dequeue();
    else
    if (orderType == POSTORDER)
      return postOrderQueue.dequeue();
    else return null;
  }
}