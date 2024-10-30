package com.android.alesta.omnidle;

class Node<T> {
    public T data;
    public Node<T> next;
    public String color;

    public Node(T data) {
        this.data = data;
        this.next = null;
        this.color = "w";
    }

    public String toString() {
        return String.valueOf(data);
    }
}