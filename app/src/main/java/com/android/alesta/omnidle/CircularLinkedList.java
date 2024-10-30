package com.android.alesta.omnidle;

class CircularLinkedList<T> {
    public  Node<T> head;

    public void insertToEnd(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
            head.next = head;
        } else {
            Node<T> iterator = head;
            while (iterator.next != head) {
                iterator = iterator.next;
            }
            iterator.next = newNode;
            newNode.next = head;
        }
    }
    public void insertToEnd(T data,String color) {
        Node<T> newNode = new Node<>(data);
        newNode.color=color;
        if (head == null) {
            head = newNode;
            head.next = head;
        } else {
            Node<T> iterator = head;
            while (iterator.next != head) {
                iterator = iterator.next;
            }
            iterator.next = newNode;
            newNode.next = head;
        }
    }

    public void display() {
        if (head == null) return;
        Node<T> iterator = head;
        do {
            System.out.print(iterator.data + " ");
            iterator = iterator.next;
        } while (iterator != head);
        System.out.println();
    }

    public int countNodes() {
        if (head == null) return 0;
        int count = 0;
        Node<T> iterator = head;
        do {
            count++;
            iterator = iterator.next;
        } while (iterator != head);
        return count;
    }

    public Node<T> getNode(int index) {
        if (head == null || index < 0) return null;
        Node<T> iterator = head;
        for (int i = 0; i < index; i++) {
            iterator = iterator.next;
            if (iterator == head) break;
        }
        return iterator;
    }
}