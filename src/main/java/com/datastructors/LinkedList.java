package com.datastructors;

import java.util.EmptyStackException;
import java.util.HashSet;
import java.util.Stack;

public class LinkedList<E> {
    private Node head;
    private int size;

    private static class Node<E> {
        E data;
        Node next;

        public Node(E data) {
            this.data = data;
            this.next = null;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    public boolean add(E data) {
        boolean isAdded = false;
        if (this.head == null) {
            this.head = new Node(data);
            isAdded = true;
        } else {
            Node node = this.head;
            while (node.next != null) {
                node = node.next;
            }
            node.next = new Node(data);
            isAdded = true;
        }
        size++;
        return isAdded;
    }

    public void addFirst(E data) {
        Node<E> newNode  = new Node<E>(data);
        newNode.next = this.head;
        this.head = newNode;
        size++;
    }

    public E removeFirst() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Underflow Error!");
        }
        Node<E> node = this.head;
        this.head = node.next;
        size--;
        return node.data;
    }

    public void printItems() {
        printItems(this.head);
    }

    private void printItems(Node node) {
        while (node != null) {
            System.out.print(node.data + "->");
            node = node.next;
        }
    }

    public E getFirst() {
        Node<E> node = this.head;
        if (head == null) {
            throw new EmptyStackException();
        }
        return node.data;
    }

    public void reverse() {
        Node prev = null, current = this.head, next;
        while (current != null) {
            System.out.println(head.next);
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        this.head = prev;
    }

    private void _reverse(Node node) {
        Node prev = null, current = node, next;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        node = prev;
    }

    //using stack
    public boolean isPalindrome() {
        if (this.head != null) {
            Node node = this.head;
            Stack<E> stack = new Stack<E>();
            while (node != null) {
                stack.push((E) node.data);
                node = node.next;
            }
            System.out.println(stack);
            node = this.head;
            while (node != null) {
                if (node.data != stack.pop()) {
                    return false;
                }
                node = node.next;
            }
            return true;
        }
        return false;
    }

    //using Method of Gees for Geeks https://www.geeksforgeeks.org/function-to-check-if-a-singly-linked-list-is-palindrome/
    public boolean _isPalindrome() {
        boolean res = false;
        if (this.head == null) {
            return res;
        }
        Node fastPointer = this.head, slowPointer = this.head, prevoiusNodeOfSlowPtr = null, middleNode = null, secondHalf = null;
        //finding middle node
        while (fastPointer != null && fastPointer.next != null) {
            fastPointer = fastPointer.next.next;
            prevoiusNodeOfSlowPtr = slowPointer;
            slowPointer = slowPointer.next;
        }

        // If it is not null then there is odd string is passed Ex: MADAM
        if (fastPointer != null) {
            middleNode = slowPointer;
            slowPointer = slowPointer.next;
        }
        secondHalf = slowPointer;
        prevoiusNodeOfSlowPtr.next = null;
        // Reverse the second half
        _reverse(secondHalf);
        res = compareList(prevoiusNodeOfSlowPtr, secondHalf);
        _reverse(secondHalf);
        // To Handle Odd cases
        if (middleNode != null) {
            prevoiusNodeOfSlowPtr.next = middleNode;
            middleNode.next = secondHalf;
        } else {
            prevoiusNodeOfSlowPtr.next = secondHalf;
        }
        return res;
    }

    private boolean compareList(Node node1, Node node2) {
        Node temp1 = node1;
        Node temp2 = node2;
        while (temp1 != null && temp2 != null) {
            if (temp1.data == temp2.data) {
                temp1 = temp1.next;
                temp2 = temp2.next;
            } else {
                return false;
            }
        }
        // if both are null means then every data of node1 is matching with node2.
        if (temp1 == null && temp2 == null) {
            return true;
        }
        return false;
    }

    // for a sorted list
    public void removeDuplicates() {
        Node current = this.head;
        if (this.head == null) {
            return;
        }
        while (current.next != null) {
            if (current.data == current.next.data) {
                //next = current.next.next;
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        System.out.println(current.data);
    }

    /**
     * This method removes the duplicates from unsorted list
     */
    public void _removeDuplicates() {
        Node ptr1 = this.head, ptr2 = null;
        while (ptr1 != null) {
            ptr2 = ptr1;
            while (ptr2.next != null) {
                if (ptr1.data == ptr2.next.data) {
                    ptr2.next = ptr2.next.next;
                } else {
                    ptr2 = ptr2.next;
                }
            }
            ptr1 = ptr1.next;
        }
    }

    /**
     * This method uses hash set to store remove duplicates in the list.
     */
    public void removeDuplicatesHash() {
        Node node = this.head, prevNode = null;
        HashSet<E> set = new HashSet<E>();
        while (node != null) {
            if (set.contains(node.data)) {
                prevNode.next = node.next;
            } else {
                prevNode = node;
                set.add((E) node.data);
            }
            node = node.next;
        }
    }

    public boolean detectLoop() {
        if (this.head != null) {
            Node fastPointer = this.head;
            Node slowPointer = this.head;
            while (fastPointer != null && fastPointer.next != null) {
                fastPointer = fastPointer.next.next;
                slowPointer = slowPointer.next;
                if (slowPointer == fastPointer) {
                    System.out.println("loop detected at data:: " + fastPointer.data);
                    return true;
                }
            }
        }
        return false;
    }

    public int detectLoopLength() {
        if (this.head != null) {
            Node fastPointer = this.head;
            Node slowPointer = this.head;
            while (fastPointer != null && fastPointer.next != null) {
                fastPointer = fastPointer.next.next;
                slowPointer = slowPointer.next;
                if (slowPointer == fastPointer) {
                    return detectLength(slowPointer);
                }
            }
        }
        return 0;
    }

    private int detectLength(Node slowPointer) {
        int count = 1;
        Node temp = slowPointer;
        while (temp.next != slowPointer) {
            count++;
            temp = temp.next;
        }
        return count;
    }

    // all the values in the linked list should be distinct
    public void swapLinkedList(int x, int y) {
        Node prevX = null, currentX = this.head;
        while (currentX != null) {
            if ((Integer) currentX.data == x) {
                break;
            }
            prevX = currentX;
            currentX = currentX.next;
        }

        Node prevY = null, currentY = this.head;
        while (currentY != null) {
            if ((Integer) currentY.data == y) {
                break;
            }
            prevY = currentY;
            currentY = currentY.next;
        }

        if (currentX == null || currentY == null)
            return;

        //If X is not the head
        if (prevX != null) {
            prevX.next = currentY;
        } else {
            this.head = currentY;
        }

        if (prevY != null) {
            prevY.next = currentX;
        } else {
            this.head = currentX;
        }

        Node temp = currentX.next;
        currentX.next = currentY.next;
        currentY.next = temp;
    }

    public void segregateEvenOddValues() {
        Node evenStart = null, evenEnd = null, oddStart = null, oddEnd = null, currentNode = this.head;
        while (currentNode != null) {
            if ((Integer) currentNode.data % 2 == 0) {
                if (evenStart == null) {
                    evenStart = currentNode;
                    evenEnd = evenStart;
                } else {
                    evenEnd.next = currentNode;
                    evenEnd = evenEnd.next;
                }
            } else {
                if (oddStart == null) {
                    oddStart = currentNode;
                    oddEnd = oddStart;
                } else {
                    oddEnd.next = currentNode;
                    oddEnd = oddEnd.next;
                }
            }
            currentNode = currentNode.next;
        }

        if (evenStart == null || oddStart == null) {
            return;
        }
        evenEnd.next = oddStart;
        oddEnd.next = null;
    }

    public void moveLastToFirstNode() {
        Node secondLast = null, last = this.head;
        while (last.next != null) {
            secondLast = last;
            last = last.next;
        }
        secondLast.next = null;
        last.next = this.head;
        this.head = last;
    }

    public void detectIntersectionPoint(LinkedList<Integer> list1, LinkedList<Integer> list2) {
        if (list1 == null || list2 == null) {
            return;
        }
        Node list1CurrentNode = list1.head;
        while (list1CurrentNode != null) {
            Node list2CurrentNode = list2.head;
            while (list2CurrentNode != null) {
                System.out.println("dfsfsdfsdf");
                if (list1CurrentNode.data == list2CurrentNode.data) {
                    System.out.println("Intersection is detected!" + list2CurrentNode.data);
                    return;
                }
                list2CurrentNode = list2CurrentNode.next;
            }
            list1CurrentNode = list1CurrentNode.next;
        }
    }

    public LinkedList intersectionForSortedList(LinkedList b) {
        Node a = this.head;
        Node node = detectIntersectionForSorted(a, b.head);
        LinkedList list = new LinkedList();
        list.head = node;
        return list;
    }

    /**
     * This method detects the intersection for sorted list with current list.
     *
     * @param a
     * @param b
     */
    private Node detectIntersectionForSorted(Node a, Node b) {
        if (a == null || b == null)
            return null;

        if ((Integer)a.data < (Integer)b.data)
            return detectIntersectionForSorted(a.next, b);
        if ((Integer)a.data > (Integer)b.data)
            return detectIntersectionForSorted(a, b.next);

        // When it comes here a.data == b.data
        Node temp = new Node(a.data);
        temp.next = detectIntersectionForSorted(a.next, b.next);
        return temp;
    }

    public void push(Node dummy, int data) {
        Node newNode = new Node(data);
        newNode.next = dummy;
        dummy = newNode;
    }

    // Detecting the intersection using Mthod 3 https://www.geeksforgeeks.org/write-a-function-to-get-the-intersection-point-of-two-linked-lists/
    public void _detectIntersectionPoint(LinkedList<Integer> list2) {
        Node node1 = this.head, node2 = list2.head;
        if (node1 == null || node2 == null) {
            return;
        }
        int c1 = getListSize(node1);
        int c2 = getListSize(node2);
        int d = c1 - c2;

        if (d > 0) {
            for (int i = 0; i < d; i++) {
                node1 = node1.next;
            }
        } else {
            for (int i = 0; i < d; i++) {
                node2 = node2.next;
            }
        }

        while (node1 != null && node2 != null) {
            System.out.println("dfsfsfd");
            if (node1.data == node2.data) {
                System.out.println("Intersection is detected!" + node1.data);
                return;
            }
            node1 = node1.next;
            node2 = node2.next;
        }
    }

    private int getListSize(Node node) {
        int count = 0;
        while (node != null) {
            count++;
            node = node.next;
        }
        return count;
    }

    public boolean isEmpty() {
        return this.head == null;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        if (this.head == null) {
            return "";
        }
        StringBuffer buffer = new StringBuffer();
        Node node = this.head;
        while (node != null) {
            buffer.append(node.data + "->");
            node = node.next;
        }
        return buffer.substring(0, buffer.lastIndexOf("->"));
    }
}
