package com.android.alesta.omnidle;
import java.util.ArrayList;


public class Main{
    public static void main(String[] args) {

        ArrayList<ArrayList<String>> array = new ArrayList<>();

        ArrayList<String> innerList1 = new ArrayList<>();
        innerList1.add("a");
        innerList1.add("b");
        innerList1.add("a");

        ArrayList<String> innerList2 = new ArrayList<>();
        innerList2.add("a");
        innerList2.add("a");
        innerList2.add("a");

        array.add(innerList1);
        array.add(innerList2);

        CircularLinkedList<ArrayList<String>> circularList = new CircularLinkedList<>();

        for (ArrayList<String> innerList : array) {
            circularList.insertToEnd(innerList);
        }

        System.out.println("Circular Linked List of ArrayLists:");
        circularList.display();

        Omnidle game = new Omnidle();
        game.initializeTheGame(circularList);
    }
}