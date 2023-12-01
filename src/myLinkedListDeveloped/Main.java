package myLinkedListDeveloped;

public class Main {
    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<Integer>();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        System.out.println(list);
        list.removeLast();
        System.out.println(list);
        list.removeFirst();
        System.out.println(list);
        list.remove(3);
        System.out.println(list);


        for (Integer item:list)
              {
                  System.out.println(item);
        }
        /*
         * list 자체를 순회할 수 있도록 해야 하나?
         * 그러면 어떻게 해야 하지? 반환 값을 받아서
         * for(int data: linkedList) {
            sout(data);
        } -> 하나씩 출력이 되야 한다. 그걸 구현.
         */

    }
}
