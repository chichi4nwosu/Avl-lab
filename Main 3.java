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
