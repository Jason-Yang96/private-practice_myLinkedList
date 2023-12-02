package myLinkedListEasy;

import java.util.Arrays;

public class MyLinkedList<E> { //제네릭.
    // 유의 사항: data의 타입은 LinkedList를 생성할 때 정할 수 있는 제네릭으로 구현

    // my linked list 인스턴스 필드
    // 노드의 첫 부분을 가리키는 포인트
    private Node <E> head;
    // 노드의 마지막 부분을 가리키는 포인트
    private Node <E> tail;
    // 노드의 개수
    private int size;

    // 생성자
    MyLinkedList(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // 노드 클래스 정의(private static). class 빼먹지 말기
    private static class Node<E> {
        // 노드 클래스 인스턴스 필드
        private E item;
        private Node <E> next;

        // 노드 클래스 생성자
        Node(E item, Node <E> next){
            this.item = item;
            this.next = next;
        }
    }
    // 노드 검색 메서드 정의(인덱스가 주어졌을 때 그 인덱스에 맞춰서 노드 탐색) -> 노드 반환
    private Node<E> search(int index){ //노드 클래스 안에 정의되지 않도록 조심해야 함.
        Node<E> n = head;
        for (int i = 0; i < index; i++) {
//            if (n.next == null) {
//                break;
//            } 아직 이건 고민 안해줘도 되려나?
            n = n.next;
        }
        return n;
    }

    // 노드 추가 메서드
    // 노드 추가 메서드_가장 마지막
    public void add(E item){
        if (size == 0){
            Node<E> newNode = new Node<>(item, null);
            size++;
            head = newNode;
            tail = newNode;
            return;
        }
        if (size > 0) {
            Node<E> newNode = new Node<>(item, null);
            Node<E> lastNode = search(size - 1);
            size++;
            lastNode.next = newNode;
            tail = newNode;
            return;
        }
    }

    // Object Override: toSting() 메서드 정의(해당 클래스 출력하게 되면 string 배열 반환)
    // 추가 설명: 원래는 클래스 인스턴스를 출력할 때 실제 메모리 주소와 파일 내 아이디 값을 반환
    // 여기서는 인스턴스 출력 시 인스턴스가 가지고 있는 배열 값을 출력하도록 만듦
    @Override
    public String toString(){

        if (size == 0) {
            return "[]";
        }
        Object[] arr = new Object[size];
        int index = 0;
        Node<E> n = head;
        while (n != null) {
            arr[index] = (E) n.item; // 형 변환 필요
            index++;
            n = n.next;
        }
        return Arrays.toString(arr);
    }

    // 노드 제거 메서드
    // 노드 제거 메서드_원하는 인덱스
    public void get(int index) {
        if ( index > size - 1) {
            return;
        }
        if (size == 1 || index ==0) {
            Node<E> target = search(index);
            head = null;
            tail = null;
            target.next = null;
            target.item = null;
            size--;
            return;
        }
        // scope 밖에서 이미 targetNode라는 것이 정의가 된 상태. 일회성인데 이름 달리 해야 함...
        if (index == size - 1) {
            Node<E> target = search(index);
            Node<E> prev = search(index - 1);
            prev.next = null;
            tail = prev;
            target.next = null;
            target.item = null;
            size--;
            return;
        }
        Node<E> prevNode = search(index-1);
        Node<E> targetNode = search(index);
        prevNode.next = targetNode.next;
        targetNode.next = null;
        targetNode.item = null;
        size--;
        return;
    }
}
