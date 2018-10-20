package com.test;

import java.util.List;

public class BinarySearchTree<E extends Comparable<E>> {
    private BSTNode<E> root;

    private static class BSTNode<E> {
        E data;
        BSTNode<E> left;
        BSTNode<E> right;
        public BSTNode(E data) {
            this.data = data;
        }
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    private BSTNode<E> _insert(BSTNode<E> node, E data) {
        if (node == null) {
            node = newNode(data);
        } else if (compareData(data, node.data) <= -1 || compareData(data, node.data) == 0) {
            node.left = _insert(node.left, data);
        } else {
            node.right = _insert(node.right, data);
        }
        return node;
    }

    private int compareData(E data1, E data2) {
        return ((Comparable)data1).compareTo(data2);
    }

    public void add(E data) {
        if (!(data instanceof Comparable)) {
            throw new ClassCastException("can't cast it to comparable type!");
        }
        this.root = _insert(this.root, data);
    }

    private BSTNode<E> newNode(E data) {
        BSTNode<E> newNode = new BSTNode<>(data);
        newNode.left = newNode.right = null;
        return newNode;
    }

    public boolean search(E searchKey) {
        if (searchKey == null)
            return false;
        return _search(this.root, searchKey);
    }

    private boolean _search(BSTNode<E> node, E searchKey) {
        if (node == null)
            return false;
        else if(compareData(searchKey, node.data) == 0)
            return true;
        else if (compareData(searchKey, node.data) == -1)
            return _search(node.left, searchKey);
        else
            return _search(node.right, searchKey);
    }

    private BSTNode<E> _findMin(BSTNode<E> node) {
        if (node == null)
            return null;
        else if (node.left == null)
            return node;
        return _findMin(node.left);
    }

    public E findMin() {
        BSTNode<E> min = _findMin(this.root);
        return min != null ? min.data : null;
        /*
        if (this.root == null) {
            throw new RuntimeException("Tree is empty!");
        }
        BSTNode<E> node =  this.root;
        while (node.left  != null) {
            node = node.left;
        }
        return node.data;
*/
    }

    private BSTNode<E> _finMax(BSTNode<E> node) {
        if (node == null)
            return null;
        else if (node.right == null)
            return node;
        return _finMax(node.right);
    }

    public E findMax() {
        BSTNode<E> max = _finMax(this.root);
        return max != null ? max.data : null;
        /*
        if (this.root == null) {
            throw new RuntimeException("Tree is empty!");
        }
        BSTNode<E> node =  this.root;
        while (node.right  != null) {
            node = node.right;
        }
        return node.data;
*/
    }

    /**
     * This method calculate no of edges in the longest path from root node to leaf node. This height is nothing but
     * maximum depth of a binary tree.
     * @param root
     * @return
     */
    private int _height(BSTNode<E> root) {
        if (root == null)
            return -1; // If you want calucate for no of edges in longest path then return zero here.
        int lefSubHeight =  _height(root.left);
        int rightSubHeight = _height(root.right);
        return Math.max(lefSubHeight, rightSubHeight) + 1; // consider the leaf node height as one
    }

    public int height() {
        return _height(this.root);
    }

    /**
     * This method performs search operation using loops.
     * @param searchKey
     * @return
     */
    public boolean searchItem(E searchKey) {
        if (searchKey == null)
            return false;
        BSTNode<E> node = this.root;
        while (node != null) {
            if (compareData(searchKey, node.data) == 0) {
                return true;
            } else if(compareData(searchKey, node.data) == -1) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return false;
    }

    public void remove(E data) {
        _remove(this.root, data);
    }

    private BSTNode<E> _remove(BSTNode<E> root, E data) {
        if (root == null)
            return root;
        else if (compareData(data, root.data) == -1) {
            root.left = _remove(root.left, data);
        } else if (compareData(data, root.data) == 1) {
            root.right = _remove(root.right, data);
        } else { //Wohoo.. I found you get ready to delete...
            //Case1: no child
            if (root.left == null && root.right == null) {
                root = null;
            }
            // Case 2: 1 child
            else if (root.left == null) {
                root = root.right;
            } else if (root.right == null) {
                root = root.left;
            }
            //case 3: 2 children
            else {
                // find minimum from right sub tree
                BSTNode<E> min = _findMin(root.right);
                //copy min value to targeted node(to be deleted node),
                // then there will be duplicates we need to remove duplicates
                root.data = min.data;
                // min value from right sub tree will not have any left child because if there is any then that will be minimum value.
                // min value may contain right subtree now I may be reduced my case to either 1 or 2.
                // Note: now there will be a duplicate with min value so we need to remove that
                root.right = _remove(root.right, min.data);
            }
        }
        return root;
    }

    public void levelOrder() {
        if (this.root == null) {
            System.out.println("[]");
            return;
        }
        LinkedListQueue<BSTNode<E>> queue = new LinkedListQueue<>();
        queue.push(this.root);
        while (!queue.isEmpty()) {
            BSTNode<E> current = queue.pop();// A node in the queue can be called as discovered node.
            System.out.print(current.data+ " ");
            if (current.left != null)
                queue.push(current.left);
            if (current.right != null)
                queue.push(current.right);
        }
    }

    private void walkThroughTreePreOrder(BSTNode<E> root) {
        if (root == null)
            return;
        System.out.print(root.data + " "); // here we are visiting the node in this we are using it for printing.
        walkThroughTreePreOrder(root.left);
        walkThroughTreePreOrder(root.right);
    }

    private void walkThroughTreeInOrder(BSTNode<E> root) {
        if (root == null) return;
        walkThroughTreeInOrder(root.left);
        System.out.print(root.data + " ");
        walkThroughTreeInOrder(root.right);
    }

    private void walkThroughPostOrder(BSTNode<E> root) {
        if (root == null) return;
        walkThroughPostOrder(root.left);
        walkThroughPostOrder(root.right);
        System.out.print(root.data + " ");
    }

    public void traverseTree(TraversalType traversalType) {
        switch (traversalType) {
            case PREORDER:
                walkThroughTreePreOrder(this.root);
                break;
            case InORDER:
                walkThroughTreeInOrder(this.root);
                break;
            case POSTORDER:
                walkThroughPostOrder(this.root);
                break;
        }
    }

    public enum TraversalType {
        PREORDER, InORDER, POSTORDER;
    }

    public void rootLeafSumExists(int sum, List<Integer> sumPaths) {
        rootLeafSumExistsInternal((BSTNode<Integer>)this.root, sum, sumPaths);
    }

    public void makeLeftNodeEmpty() {
        root.left = _makeLeftNodeEmpty(root);
    }

    private BSTNode<E> _makeLeftNodeEmpty(BSTNode<E> root) {
        if (root == null) {
            return null;
        }
        root.left = _makeLeftNodeEmpty(root.left);
        return root.left;
    }

    private boolean rootLeafSumExistsInternal(BSTNode<Integer> root, int sum, List<Integer> sumPaths) {
        if (root == null)
            return false;
        if (root.left == null && root.right == null){
            if (root.data == sum) {
                sumPaths.add(root.data);
                return true;
            }
        }
        if (rootLeafSumExistsInternal(root.left, sum - root.data, sumPaths)) {
            sumPaths.add(root.data);
            return true;
        }
        if (rootLeafSumExistsInternal(root.right, sum - root.data, sumPaths)) {
            sumPaths.add(root.data);
            return true;
        }
        return false;
    }

    public int sum() {
        return sumInternal((BSTNode<Integer>)root);
    }

    public int sumInternal(BSTNode<Integer> root) {
        if (root == null) {
            return 0;
        }
        int sum = root.data;
        sum = sum + sumInternal(root.left);
        sum = sum + sumInternal(root.right);
        return sum;
    }

    public void makeRightSkewedTree() {
        root.left = _makeRightSkewedTree(root.left, root);
    }

    private BSTNode<E> _makeRightSkewedTree(BSTNode<E> leftNode, BSTNode<E> root) {
        if (root == null || leftNode == null) {
            return null;
        }
        leftNode.left = _makeRightSkewedTree(leftNode.left, root);
        leftNode.right = _makeRightSkewedTree(leftNode.right, root);
        BSTNode<E> temp = root.right;
        root.right = leftNode;
        leftNode.right = temp;
        return leftNode.left;
    }
}
