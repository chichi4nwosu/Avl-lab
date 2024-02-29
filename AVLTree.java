import java.util.*;

public class AVLTree<E extends Comparable<E>> extends SearchTree<E> {

  // Method to add data to the AVL tree
    public boolean add(E data) {
        return insert(data);
    }

    public boolean insert(E data) {
      // This method should use a recursive helper.  It 
        // the tree that results from inserting data 
        // into t. It has a similar structure to add from BST.  It
        // starts by asking if tree is empty, and if so it sets the
        // a new leaf node as the root. If tree is not empty you can compare
        // data to the current node's data and take action based on this comparison.
        // When you create a new leaf node you should 
      // increment the tree's size variable. When you return from a 
        // recursive call that updates one of t's children
        // (e.g. curr.leftChild = insertHelper(data, curr.left))
      // you should check the AVL property and possibly 
        // call updateHeight(), and then recompute curr's height.
      int size = getSize();// get og size
        overallRoot = insertHelper(data, (AVLNode<E>) overallRoot);
        return size< getSize();// get size now
    }

  // Recursive helper method to insert data into the AVL tree
    private AVLNode<E> insertHelper(E data, AVLNode<E> curr) {
        if (curr == null) {// If the current node is null
            curr = new AVLNode<>(data);// Create a new AVL node with the data
      size++; // Increment the size of the tree
      } else if (data.compareTo(curr.data) < 0) { // If data is less than the current node's data
          curr.left = insertHelper(data, (AVLNode<E>) curr.left); // Recursively insert into the left subtree
      } else {
          curr.right = insertHelper(data, (AVLNode<E>) curr.right); // Recursively insert into the right subtree
      }
      updateHeight(curr); // Update the height of the current node
      return balance(curr); // Balance the current 
    }

    private void updateHeight(AVLNode<E> curr) {
          if (curr != null) { // If the current node is not null
              int leftHeight = (curr.left == null) ? -1 : curr.left.height; // Get the height of the left subtree
              int rightHeight = (curr.right == null) ? -1 : curr.right.height; // Get the height of the right subtree
              curr.height = 1 + Math.max(leftHeight, rightHeight); // Update the height of the current node
          }
        }
    }

    private AVLNode<E> balance(AVLNode<E> curr) {
      if (curr == null) return null; // If the current node is null, return null
      if (curr.bf() < -1) { // If the balance factor is less than -1
          if (((AVLNode<E>) curr.left).bf() > 0) { // If the balance factor of the left child is greater than 0
              curr.left = rotateLeft((AVLNode<E>) curr.left); // Rotate left
          }
          curr = rotateRight(curr); // Rotate right
      } else if (curr.bf() > 1) { // If the balance factor is greater than 1
          if (((AVLNode<E>) curr.right).bf() < 0) { // If the balance factor of the right child is less than 0
              curr.right = rotateRight((AVLNode<E>) curr.right); // Rotate right
          }
          curr = rotateLeft(curr); // Rotate left
      }
      return curr; // Return the balanced node
    }
  // TODO: IMPLEMENT THE FOLLOWING METHODS BASED ON THE JAVADOC COMMENTS

  /**
   * Perform a single rotation to the right of a tree rooted at the current node.
   * Consider the following illustrations (called on the node A):
   *
   *        A       =>     B
   *       / \      =>    / \
   *      B   T3    =>  T1   A
   *     / \        =>      / \
   *   T1   T2      =>    T2   T3
   *
   * Note that A's original parent (if it exists) will need to become B's new
   * parent. 
   *
   * @return The new root of this subtree (node B).
   */

    public AVLNode<E> rotateRight(AVLNode<E> oldRoot) {
      // TODO: Implement this method.  Return the new root B.
      // Do not forget to change A's parent (if it exists) to be
      // aware of B as the new root by returning the new root and setting the
      // parent's pointer when we call rotateRight(node).


      AVLNode<E> newRoot = (AVLNode<E>) oldRoot.left; // Set the new root to the left child of the old root
      oldRoot.left = newRoot.right; // Update the left child of the old root
      newRoot.right = oldRoot; // Set the right child of the new root to the old root
      updateHeight(oldRoot); // Update the height of the old root
      updateHeight(newRoot); // Update the height of the new root
      return newRoot; // Return the new root
    }
  /**
   * Perform a single rotation to the left of a tree rooted at the current node.
   * Consider the following illustrations (called on the node A):
   *
   *      A         =>       B
   *     / \        =>      / \
   *   T1   B       =>     A   T3
   *       / \      =>    / \
   *     T2   T3    =>  T1   T2
   *
   * Note that A's original parent (if it exists) will need to become B's new
   * parent. 
   *
   * @return The new root of this subtree (node B).
   */


    public AVLNode<E> rotateLeft(AVLNode<E> oldRoot) {
      // TODO: Implement this method. Return the new root B. 
      // Do not forget to change A's parent (if it exists) to be
      // aware of B as the new root by returning the new root and setting the
      // parent's pointer when we call rotateRight(node).

      AVLNode<E> newRoot = (AVLNode<E>) oldRoot.right; // Set the new root to the right child of the old root
      oldRoot.right = newRoot.left; // Update the right child of the old root
      newRoot.left = oldRoot; // Set the left child of the new root to the old root
      updateHeight(oldRoot); // Update the height of the old root
      updateHeight(newRoot); // Update the height of the new root
      return newRoot; // Return the new root
    }
  // WAIT TO WORK ON THIS!!!

    public boolean remove(E data) {
      //iterate through the tree to find the data. If never found return false
      //else, point to left and right subtree then insert them in appropriate places

      //Iterate through

int initialSize = getSize(); // Get the initial size of the tree
overallRoot = removeHelper(data, (AVLNode<E>) overallRoot); // Remove the data using the recursive helper method
return getSize() < initialSize; // Return true if the size of the tree decreased after removal
    }

    private AVLNode<E> removeHelper(E data, AVLNode<E> curr) {
      //Steps:
      //1. Find the node to remove.
      //a. Keep track of parent.
      //b. Use compareTo and find note without recursion
      //c. If never found (temp.left is null or temp.right is null), return false
      //2. Save right and left subtree of removed node
      //3. Insert left and right subtrees onto parent
      //a. iterate until leaf on both sides then input on left for less than or right for greater than


      if (curr == null) return null; // If the current node is null, return null
      if (data.compareTo(curr.data) < 0) { // If data is less than the current node's data
          curr.left = removeHelper(data, (AVLNode<E>) curr.left); // Recursively remove from the left subtree
      } else if (data.compareTo(curr.data) > 0) { // If data is greater than the current node's data
          curr.right = removeHelper(data, (AVLNode<E>) curr.right); // Recursively remove from the right subtree
      } else { // Node to be removed found
          if (curr.left == null || curr.right == null) { // If the node has 0 or 1 child
              curr = (curr.left == null) ? (AVLNode<E>) curr.right : (AVLNode<E>) curr.left; // Set the current node to its non-null child
              size--; // Decrement the size of the tree
          } else { // Node has 2 children
              AVLNode<E> successor = findSuccessor((AVLNode<E>) curr.right); // Find the successor node
              curr.data = successor.data; // Replace the current node's data with the successor's data
              curr.right = removeHelper(successor.data, (AVLNode<E>) curr.right); // Remove the successor
          }
      }
      return balance(curr); // Balance the current node
    }

    private AVLNode<E> findSuccessor(AVLNode<E> node) {
      while (node.left != null) { // Traverse to the leftmost node
        node = (AVLNode<E>) node.left;
      }
      return node; // Return the leftmost node
    }
  // This will help you with debugging. It prints the keys
   // on each level of the tree.

    public void treePrinter() {
        for (int level = 0; level < ((AVLNode<E>) overallRoot).height(); level++) {
            System.out.printf("Level %d: ", level);
            printLevel(level, (AVLNode<E>) overallRoot);
            System.out.println();
        }
    }

    public void printLevel(int level, AVLNode<E> t) {
        if (t != null) {
            if (level == 0)
                System.out.printf("%s ", t.data);
            else {
                printLevel(level - 1, (AVLNode<E>) t.left);
                printLevel(level - 1, (AVLNode<E>) t.right);
            }
        }
    }
}
