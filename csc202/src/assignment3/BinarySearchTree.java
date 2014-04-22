//----------------------------------------------------------------------------
// BinarySearchTree.java          by Dale/Joyce/Weems                Chapter 8
//
// Defines all constructs for a reference-based BST
//----------------------------------------------------------------------------

// Modified by Michael Gugino
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

    boolean isempty = false;
    boolean popped = false;
    if (myTree == null) isempty = true;
    String lastWrite = null;
    String currentValue = null;
    
    if (myTree == null) {
      isempty = true;
    }

    while (!isempty) {
    	if (!popped) {
    		// go left
	    	if (myTree.getLeft() != null) {
	    		System.out.println("Going Left, Tree is now " + (String) myTree.getInfo());
	    		myStack.push(myTree);
	    		myTree = myTree.getLeft();
	    	}
	    	else {
	    		System.out.println("writing queue");
	    		inOrderQueue.enqueue(myTree.getInfo());
	    		lastWrite = (String) myTree.getInfo();
	    		// go right
	    		 if (myTree.getRight() != null) {
	 	    		myStack.push(myTree);
	 	    		myTree = myTree.getRight();
	 	    	}
	 	    	else {
		    		if (myStack.isEmpty()) {
		    			isempty = true;
		    		}
		    		else {
		    			myTree = (BSTNode<T>) myStack.top();
		    			myStack.pop();
		    			popped = true;
		    		}
		    	}
	    	}

	    	

    	}
	      if (popped) {
			currentValue = (String) myTree.getInfo();
			if (lastWrite.compareTo(currentValue) < 0) {
				inOrderQueue.enqueue(myTree.getInfo());
				if (myTree.getRight() != null) {
		    		myStack.push(myTree);
		    		myTree = myTree.getRight();
		    		popped = false;
	    		}
	    		else {
		    		if (myStack.isEmpty()) {
		    			isempty = true;
		    		}
		    		else {
		    			myTree = (BSTNode<T>) myStack.top();
		    			myStack.pop();
		    			popped = true;
		    		}
	    		}
			}
			else if (lastWrite.compareTo(currentValue) > 0) {
	    		if (myStack.isEmpty()) {
	    			isempty = true;
	    		}
	    		else {
	    			myTree = (BSTNode<T>) myStack.top();
	    			myStack.pop();
	    			popped = true;
	    		}
			}
	      }
    }
  }
  // Non Recursive Function.
  private void preOrder(BSTNode<T> tree) {
	    BSTNode<T> myTree = tree;
	    LinkedStack myStack = new LinkedStack<LLNode<BSTNode<T>>>();
	    boolean isempty = false;
	    boolean popped = false;
	    if (myTree == null) isempty = true;
	    String lastWrite = null;
	    String currentValue = null;
	    while (!isempty) {
	    	
	    	// if not popped
	    	if (!popped) {
	    		preOrderQueue.enqueue(myTree.getInfo());
	    		lastWrite = (String) myTree.getInfo();
	    		// go left
		    	if (myTree.getLeft() != null) {
		    		myStack.push(myTree);
		    		myTree = myTree.getLeft();
		    	}
	    		// go right
		    	else if (myTree.getRight() != null) {
		    		myStack.push(myTree);
		    		myTree = myTree.getRight();
		    	}
		    	else {
		    		if (myStack.isEmpty()) {
		    			isempty = true;
		    		}
		    		else {
		    			myTree = (BSTNode<T>) myStack.top();
		    			myStack.pop();
		    			popped = true;
		    		}
		    	}
	    	}
	      if (popped) {
  			currentValue = (String) myTree.getInfo();
  			if (lastWrite.compareTo(currentValue) < 0) {
  				if (myTree.getRight() != null) {
		    		myStack.push(myTree);
		    		myTree = myTree.getRight();
		    		popped = false;
	    		}
	    		else {
		    		if (myStack.isEmpty()) {
		    			isempty = true;
		    		}
		    		else {
		    			myTree = (BSTNode<T>) myStack.top();
		    			myStack.pop();
		    			popped = true;
		    		}
	    		}
  			}
  			else if (lastWrite.compareTo(currentValue) > 0) {
	    		if (myStack.isEmpty()) {
	    			isempty = true;
	    		}
	    		else {
	    			myTree = (BSTNode<T>) myStack.top();
	    			myStack.pop();
	    			popped = true;
	    		}
  			}
	      }

	    }
	    
  }
  
  // Non Recursive Function.
  



  private void postOrder(BSTNode<T> tree)
  // Initializes postOrderQueue with tree elements in postOrder order.
  {
    	// go left
    	// go right
    	// write
	    BSTNode<T> myTree = tree;
	    LinkedStack myStack = new LinkedStack<LLNode<BSTNode<T>>>();
	    boolean popped = false;
	    String lastWrite = null;
	    String currentValue = null;
	    boolean isempty = false;
	    boolean writeReset = false;
	    if (myTree != null) {
	    	while (!isempty) {
	    		
	    		// if not popped
	    		if (!popped) {
	    			// push tree to stack, go left
		    		if (myTree.getLeft() != null) {
		    			System.out.println("Pushing " + (String) myTree.getInfo() + " to stack");
		    			myStack.push(myTree);
		    			myTree = myTree.getLeft();
		    			System.out.println("Going Left, Tree is now " + (String) myTree.getInfo());
		    			//writeReset = true;
		    		}
		    		
	    			// push tree to stack, go right
		    		else if (myTree.getRight() != null) {
		    			System.out.println("Pushing " + (String) myTree.getInfo() + " to stack");
		    			myStack.push(myTree);
		    			myTree = myTree.getRight();
		    			System.out.println("Going Right, Tree is now " + (String) myTree.getInfo());
		    		}
	    			// else, write, pop.
		    		
		    		else {
		    			System.out.println("No pops, There doesn't appear to be a left or right leaf");
		    			postOrderQueue.enqueue(myTree.getInfo());
		    			lastWrite =  (String) myTree.getInfo();
		    			System.out.println("No pops, Pushing " + (String) myTree.getInfo() + " to queue");
		    			popped = true;
		    			// Check to make sure that the first element wasn't the only one.
		    			if (myStack.isEmpty() == true) {
		    				isempty = true;
		    				System.out.println("Empty Stack found");
		    			}
		    			else {
	
			    				myTree = (BSTNode<T>) myStack.top();
			    				myStack.pop();
			    				System.out.println("Popping, Tree is now " + (String) myTree.getInfo());
		    			}
		    		}
	    			
	    		}

	    		
	    		// if popped
	    		else if (popped) {
	    			// .compareTo(myTree.getInfo()) < 0
	    			// if last write < tree, go right, unset popped.
	    			currentValue = (String) myTree.getInfo();
	    			if (lastWrite.compareTo(currentValue) < 0) {
	    				popped = false;
	    				if (myTree.getRight() != null) {
			    			System.out.println("Pushing " + (String) myTree.getInfo() + " to stack");
			    			myStack.push(myTree);
			    			myTree = myTree.getRight();
			    			System.out.println("Going Right, Tree is now " + (String) myTree.getInfo());
			    		}
	    				else {
			    			if (myStack.isEmpty() == true) {
			    				isempty = true;
			    				System.out.println("Empty Stack found");
			    			}
			    			else {
			    				postOrderQueue.enqueue(myTree.getInfo());
			    				lastWrite =  (String) myTree.getInfo();
			    				myTree = (BSTNode<T>) myStack.top();
			    				myStack.pop();
			    			}
	    				}
	    			}
	    			// if last write > tree, write, pop again.
	    			if (lastWrite.compareTo(currentValue) > 0) {
	    				postOrderQueue.enqueue(myTree.getInfo());
	    				lastWrite =  (String) myTree.getInfo();
		    			if (myStack.isEmpty() == true) {
		    				isempty = true;
		    				System.out.println("Empty Stack found");
		    			}
		    			else {
		    				myTree = (BSTNode<T>) myStack.top();
		    				myStack.pop();
		    			}
	    			}
	    			

	    		}

	    		
	    	    // check for stack for empty to terminate the while clause.

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
      postOrder(root);
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