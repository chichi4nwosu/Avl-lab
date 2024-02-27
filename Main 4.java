public class AVLTree<E extends Comparable<E>> extends SearchTree<E> {

public boolean add(E data) {
    return insert(data);
}

public boolean insert(E data) {
    overallRoot = insertHelper(data, (AVLNode<E>)overallRoot);
    return true;
}

private AVLNode<E> insertHelper(E data, AVLNode<E> curr) {
    if (curr == null) {
        curr = new AVLNode<>(data);
        size++;
    } else if (data.compareTo(curr.data) < 0) {
        curr.left = insertHelper(data, curr.left);
    } else {
        curr.right = insertHelper(data, curr.right);
    }
    updateHeight(curr);
    return balance(curr);
}

private AVLNode<E> balance(AVLNode<E> curr) {
    if (curr.bf() < -1) {
        if (curr.left.bf() > 0) {
            curr.left = rotateLeft(curr.left);
        }
        curr = rotateRight(curr);
    } else if (curr.bf() > 1) {
        if (curr.right.bf() < 0) {
            curr.right = rotateRight(curr.right);
        }
        curr = rotateLeft(curr);
    }
    return curr;
}

public AVLNode<E> rotateRight(AVLNode<E> oldRoot) {
    AVLNode<E> newRoot = oldRoot.left;
    oldRoot.left = newRoot.right;
    newRoot.right = oldRoot;
    updateHeight(oldRoot);
    updateHeight(newRoot);
    return newRoot;
}

public AVLNode<E> rotateLeft(AVLNode<E> oldRoot) {
    AVLNode<E> newRoot = oldRoot.right;
    oldRoot.right = newRoot.left;
    newRoot.left = oldRoot;
    updateHeight(oldRoot);
    updateHeight(newRoot);
    return newRoot;
}
}
public boolean remove(E data) {
    int initialSize = size;
    overallRoot = removeHelper(data, (AVLNode<E>) overallRoot);
    return size < initialSize;
}

private AVLNode<E> removeHelper(E data, AVLNode<E> curr) {
    if (curr == null) {
        return null; // Data not found, return null
    }
    if (data.compareTo(curr.data) < 0) {
        curr.left = removeHelper(data, curr.left); // Recursively remove from the left subtree
    } else if (data.compareTo(curr.data) > 0) {
        curr.right = removeHelper(data, curr.right); // Recursively remove from the right subtree
    } else {
        // Node to be removed found
        if (curr.left == null || curr.right == null) {
            // Node has 0 or 1 child
            curr = (curr.left == null) ? curr.right : curr.left;
            size--;
        } else {
            // Node has 2 children
            AVLNode<E> successor = findSuccessor(curr.right);
            curr.data = successor.data;
            curr.right = removeHelper(successor.data, curr.right); // Remove the successor
        }
    }
    // Rebalance and update height
    return balance(curr);
}

private AVLNode<E> findSuccessor(AVLNode<E> node) {
    while (node.left != null) {
        node = node.left;
    }
    return node;
}
