package com.test;

import com.datastructors.AVLTree;

public class AVLTreeTest {
    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<>();
        tree.insert(15);
        tree.insert(12);
        tree.insert(19);
        tree.insert(10);
        tree.insert(8);
        tree.insert(20);
        tree.insert(22);
        tree.insert(5);
        tree.insert(9);
        tree.insert(2);
        tree.insert(25);
        tree.insert(21);
        tree.insert(30);
        tree.insert(4);
        tree.insert(26);
        tree.insert(15);
        tree.insert(6);
        tree.insert(1);
        tree.printInLevelOrder();
        tree.delete(6);
        tree.printInLevelOrder();
        tree.delete(5);
        // Now it forms LL case
        tree.printInLevelOrder();
        tree.insert(14);
        tree.printInLevelOrder();
        // Now it forms RR case
        tree.delete(9);
        tree.printInLevelOrder();
        tree.delete(1);
        tree.delete(10);
        tree.delete(14);
        tree.printInLevelOrder();
        // Now it forms LR case
        tree.delete(12);
        tree.printInLevelOrder();
        tree.delete(19);
        tree.delete(21);
        tree.delete(30);
        tree.printInLevelOrder();
        tree.delete(20);
        tree.printInLevelOrder();
    }
}
