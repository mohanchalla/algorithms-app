package com.datastructors;

public class AVLTree<E extends Comparable<E>> {
    private Node<E> root;

    private static class Node<E> {
        E data;
        Node<E> left;
        Node<E> right;
        int height;
        Node(E data, Node<E> left, Node<E> right, int height) {
            this.data = data;
            this.left = left;
            this.right = right;
            this.height = height;
        }
    }

    private int height(Node<E> node) {
        if (node == null) {
            return -1;
        }
        return node.height;
    }

    private int max(int a, int b) {
        return a>b ? a : b;
    }

    private int getBalanceFactor(Node<E> root) {
        if (root == null) {
            return -1;
        }
        return height(root.left) - height(root.right);
    }

    public void insert(E data) {
        this.root = _insert(this.root, data);
    }

    private Node<E> _insert(Node<E> root, E data) {
        if (root == null) {
            return new Node<>(data, null, null, 0);
        }
        if (data.compareTo(root.data) < 0) {
            root.left = _insert(root.left, data);
        } else if (data.compareTo(root.data) > 0) {
            root.right = _insert(root.right, data);
        } else {
            // TO handle duplicate case.
            root.data = data;
        }

        // Always add plus 1 to include current root edge applies in all the height calculation places.
        root.height = 1 + max(height(root.left), height(root.right));
        int balanceFactor = getBalanceFactor(root);
        // In case of LL
        if (balanceFactor > 1 && data.compareTo(root.left.data) < 0) {
            return rightRotate(root);
        }
        // In case of RR
        if (balanceFactor < -1 && data.compareTo(root.right.data) > 0) {
            return leftRotate(root);
        }
        // In case of LR
        if (balanceFactor > 1 && data.compareTo(root.left.data) > 0) {
            root.left =  leftRotate(root.left);
            return rightRotate(root);
        }
        // In case of RL
        if (balanceFactor < -1 && data.compareTo(root.right.data) < 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }
        return root;
    }

    /**
     * This makes LL cases as balanced one
     * @param root
     * @return
     */
    private Node<E> rightRotate(Node<E> root) {
        Node<E> newRoot = root.left;
        root.left = newRoot.right;
        newRoot.right = root;
        // Updating heights of the rotated Nodes
        //always update the old root height first. Otherwise it will take the old height of root if first
        // calculate newRoot height which will gives wrong results.
        root.height = 1 + max(height(root.left), height(root.right));
        newRoot.height = 1 + max(height(newRoot.left), height(newRoot.right));

        return newRoot;
    }

    /**
     * This makes RR cases as balanced one
     * @param root
     * @return
     */
    private Node<E> leftRotate(Node<E> root) {
        Node<E> newRoot = root.right;
        root.right = newRoot.left;
        newRoot.left = root;
        // Updating heights of the rotated Nodes
        //always update the old root height first otherwise it will take the old height of old root if first calculate newRoot height which will gives wrong results.
        root.height = 1 + max(height(root.left), height(root.right));
        newRoot.height = 1 + max(height(newRoot.left), height(newRoot.right));

        return newRoot;
    }

    public void printInLevelOrder(){
        Node<E> root = this.root;
        if (root == null) {
            System.out.println("[]");
            return;
        }
        LinkedListQueue<Node<E>> queue = new LinkedListQueue<>();
        queue.push(root);
        StringBuilder builder = new StringBuilder("[");
        while (!queue.isEmpty()) {
            Node<E> node = queue.pop();
            if (node.left != null)
                queue.push(node.left);
            if (node.right != null)
                queue.push(node.right);
            //System.out.println(root.data);
            builder.append(node.data);
            builder.append(", ");
        }
        //builder.substring(0, builder.lastIndexOf(", "));
        System.out.println(builder.substring(0, builder.lastIndexOf(", ")) + "]");
    }

    public void delete(E data) {
        this.root = _delete(this.root, data);
    }

    private Node<E> _delete(Node<E> root, E data) {
        if (root == null) {
            return null;
        }
        if (data.compareTo(root.data) < 0) {
            root.left = _delete(root.left, data);
        } else if (data.compareTo(root.data) > 0) {
            root.right = _delete(root.right, data);
        } else {
            // I got you. Get ready to be deleted.
            // Case 1: if the node is a leaf node
            if (root.left == null && root.right == null) {
                root = null;
            }
            // Case 2: If the node has only one children either it may contain left child or right child
            // It has right sub tree and left is empty
            else if (root.left == null) {
                root = root.right;
            }
            // It has left sub tree and right is empty
            else if (root.right == null) {
                root = root.left;
            } else {
                // Case 3: Node with Two children
                // Find min element from right sub tree
                Node<E> minNode = _findMin(root.right);
                root.data = minNode.data;// replace the targeted node with min element
                // Now it is reduced to either case 1 or case 2.
                root.right = _delete(root.right, minNode.data);// now remove the duplicate element.
            }
        }

        // in the case of leaf node deletion it will be null or there is only one node to delete.
        if (root == null) {
            return root;
        }

        root.height = 1 + max(height(root.left), height(root.right));
        // Check the balance factor of this(current) node check whether it is imbalanced.
        int balanceFactor = getBalanceFactor(root);
        // In case of LL rotation
        if (balanceFactor > 1 && getBalanceFactor(root.left) > 0) {
            return rightRotate(root);
        }
        // In case of RR rotation
        if (balanceFactor < -1 && getBalanceFactor(root.right) < 0) {
            return leftRotate(root);
        }
        // In case of LR rotation
        if (balanceFactor > 1 && getBalanceFactor(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }
        // In case of RL rotation
        if (balanceFactor < -1 && getBalanceFactor(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }
        return root;
    }

    private Node<E> _findMin(Node<E> root) {
        if (root == null)
            return null;
        if (root.left == null) {
            return root;
        }
        return _findMin(root.left);
    }
}
