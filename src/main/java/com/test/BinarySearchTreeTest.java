package com.test;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTreeTest {
    public static void main(String[] args) {
/*
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.add(12);
        tree.add(8);
        tree.add(7);
        tree.add(1);
        tree.add(15);
        tree.add(17);
        tree.add(20);
        tree.add(9);
        tree.add(11);
        tree.add(22);
        tree.add(13);
        System.out.println(tree.findMin());
        System.out.println(tree.findMax());
*/
        //tree.remove(1); // removing leaf nodes
        //tree.remove(10); runs O(logn) if item is not available
        //tree.remove(11);
        //tree.remove(17); // removing from right subtree with one child
        // tree.remove(7); // removing from left subtree with one child
        //tree.remove(15); // removing from right subtre with 2 children
        // tree.remove(8); // removing from left subtree with 2 children
         //tree.remove(12); // removing root node from tree
/*
        System.out.println(tree.height());
        tree.levelOrder();
*/

        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.add(6);
        tree.add(3);
        tree.add(8);
        //tree.add(4);
        tree.add(1);
        tree.add(5);
        tree.add(9);
        tree.traverseTree(BinarySearchTree.TraversalType.InORDER);
        System.out.println();
        //tree.makeLeftNodeEmpty();
        tree.makeRightSkewedTree();
        tree.traverseTree(BinarySearchTree.TraversalType.InORDER);
        //System.out.println();
        //List<Integer> sumsPath = new ArrayList<>();
        //tree.rootLeafSumExists(25, sumsPath);
        //System.out.println(sumsPath);
    }
}
