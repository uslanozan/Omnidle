package com.android.alesta.omnidle;

class Node<T> {
    public T data;
    public Node<T> next;
    public boolean isEmpty;

    public Node(T data) {
        this.data = data;
        this.next = null;
        this.isEmpty=true;
    }

    public String toString() {
        return String.valueOf(data);
    }
}