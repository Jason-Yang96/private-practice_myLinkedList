package myLinkedListEasy;

public class Main {
    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList<Integer>();
        System.out.println(list);
        list.add(0);
        System.out.println(list);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        System.out.println(list);
        list.get(9);
        System.out.println(list);
        list.get(0);
        System.out.println(list);
        list.get(2);
        System.out.println(list);

    }
}
