/*
 Program implementing the LinkedList Java Class 
 Data Members: 
    1. Node class having 2 members data and next which points to next node in Linked List. 
    2.
 Member methods:
    1. traverse()
 */
public class LinkedList {
    static class Node {
        int data;
        Node next;

        public Node() {
            this.next = null;
        }

        public Node(int d) {
            this.data = d;
            this.next = null;
        }
    }

    private class DeletionPointers {
        Node previousNode;
        Node cuurentNode;

        DeletionPointers(Node pN, Node cN) {
            this.previousNode = pN;
            this.cuurentNode = cN;
        }
    }

    Node start;

    LinkedList() {
        start = null;
    }

    private void traverse() {
        Node ptr = this.start;
        while (ptr != null) {
            System.out.print(ptr.data + " ");
            ptr = ptr.next;
        }
        System.out.println();
    }

    private boolean search(int item) {
        Node ptr = this.start;
        while (ptr != null) {
            if (ptr.data == item)
                return true;
            ptr = ptr.next;
        }
        return false;
    }

    private Node findPrecedingNode(int item) {
        if (this.start == null)
            return null;
        if (this.start.data >= item)
            return null;
        Node save = this.start;
        Node ptr = this.start.next;
        while (ptr != null) {
            if (item < ptr.data)
                return save;
            save = ptr;
            ptr = ptr.next;
        }
        return save;
    }

    private void insertAfter(Node location, int item) {
        Node newNode = new Node(item);
        if (location == null)
            this.start = newNode;
        else {
            newNode.next = location.next;
            location.next = newNode;
        }
    }

    private void insert(int item) {
        Node location = findPrecedingNode(item);
        insertAfter(location, item);
    }

    private DeletionPointers findSuccedingNode(int item) {
        if (this.start == null)
            return new DeletionPointers(null, null);
        if (this.start.data == item)
            return new DeletionPointers(null, start);
        Node save = this.start;
        Node ptr = this.start.next;
        while (ptr != null) {
            if (ptr.data == item)
                return new DeletionPointers(save, ptr);
            save = ptr;
            ptr = ptr.next;
        }
        return new DeletionPointers(save, null);
    }

    private void remove(int item) {
        DeletionPointers pointers = findSuccedingNode(item);
        if (pointers.cuurentNode == null)
            System.out.println("Not found");
        if (pointers.previousNode == null)
            this.start = this.start.next;
        else
            pointers.previousNode.next = pointers.cuurentNode.next;
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.insert(1);
        list.insert(3);
        list.insert(2);
        list.insert(5);
        list.insert(4);
        list.traverse();
        System.out.println(list.search(6));
        list.remove(5);
        list.traverse();
        System.out.println(list.search(1));
    }
}