package myLinkedListDeveloped;

import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyLinkedList<E> implements Iterable<E> {

    // 유의 사항: data의 타입은 LinkedList를 생성할 때 정할 수 있는 제네릭으로 구현

    // 노드의 첫 부분을 가리키는 포인트
    private Node<E> head; // 이 친구들도 노드지만, 약간 이름표 같은 역할을 한다. 너 첫번째네 그럼 head
    // 노드의 마지막 부분을 가리키는 포인트. head를 할당한다? head와 같은 값을 참조한다.
    private Node<E> tail; // 너 마지막이네 그럼 tail. 어 아무것도 없네? tail = head
    // 노드의 개수
    private int size;

    // 생성자
    MyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public class IterableHelper implements Iterator<E>{
        private Node<E> index;

        IterableHelper() {
            this.index = head;
        }

        @Override
        public boolean hasNext() {
            return index != null;
        }
        @Override
        public E next(){
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E val = index.item;
            index = index.next;
            return val;
        }

        /*
        public Object printArr() {
            Object[] array = new Object[size];
            for (int i = 0; i <= size; i++) {
                array[i] = (E) next();
            }
            return array;
        }

         */
    }

    // 노드 클래스 정의(private static)
    private static class Node<E> {

        // 인스턴스 필드 정의
        public E item;

        public Node<E> next; //만약 private라면 무슨일이 생기지?
        // 노드 생성자
        Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }
    }
    // 노드 검색 메서드 정의(인덱스가 주어졌을 때 그 인덱스에 맞춰서 노드 탐색_처음부터) -> 노드 반환
    private Node<E> search(int index) {
        Node<E> n = head;
        for (int i = 0; i < index; i++) {
            n = n.next;
        }
        return n;
    }
    // 노드 추가 메서드

    // 노드 추가 메서드_가장 첫번째
    public void addFirst(E item) {
        // 순서에 따라서 head가 참조하는 값이 엉킬 수 있다... 초기에 추가 전 처음 값 백업
        // first에 head가 할당되는데, 만약에 head가 null이었다면 생성될 때 부터 next null
        Node<E> firstNode = head;
        Node<E> newNode = new Node<>(item, firstNode); //제네릭을 써주는 이유는? 그래야 jvm이 타입 추측 가능
        size++;
        head = newNode;
        if (firstNode == null) { // 백업했던 초기값이 null이었다면? 그 말은 초기 head가 null 이었다면? head와 tail은 공통 노드 참조
            tail = newNode; // 뭐 head라고 해도 좋지만, newNode라고 명시하는 것이 중요
        }
    }

    // 노드 추가 메서드_가장 마지막
    public void addLast(E item) {
        Node<E> lastNode = tail;
        Node<E> newNode = new Node<>(item, null);
        size++;
        tail = newNode;
//        lastNode.next = newNode; // 만약 초기값이었다면, 현재 tail 을 참조하고 있기에.. null.. 따라서 값도 없는데 새로운 노드 참조        tail = newNode;
        if (lastNode == null) {
            head = newNode;
        } else {
            lastNode.next = newNode;
        }
    }

    // 노드 추가 메서드_원하는 인덱스에
    public void add(int index, E item) {
        // index가 적절한 값인지 판단, 앞선 메서드 사용할 수 있는지 파악
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
//            return; // unreachable statement
        }
        if (index == 0) {
            addFirst(item);
            return;
        }
        if (index == size - 1) {
            addLast(item);
            return;
        }
        // 값 백업 필요
        Node<E> prevNode = search(index); //현재 추가하려는 위치의 노드 얻기
        Node<E> nextNode = prevNode.next;
        Node<E> newNode = new Node<>(item, nextNode); // 하드 코딩 형식... 변수로 지정해야 하나?
        size++; //일단 생성이 되었으니 사이즈는 늘려주기. (상태. 업데이트)
        prevNode.next = newNode;
    }

    // 노드 추가 메서드_그냥 숫자만 주어지고 추가(메서드 오버로드) 맨 뒤에 넣어 줌 -> boolean 반환
    public boolean add(E item) {
        addLast(item);
        return true;
    }

    /* Object Override: toSting() 메서드 정의(해당 클래스 출력하게 되면 string 배열 반환)
     * 추가 설명: 원래는 클래스 인스턴스를 출력할 때 실제 메모리 주소와 파일 내 아이디 값을 반환
     * 여기서는 인스턴스 출력 시 인스턴스가 가지고 있는 배열 값을 출력하도록 만듦
     */
    @Override
    public Iterator<E> iterator() {
        return new IterableHelper();
    }
    @Override
    public String toString() {
        if (head == null) {
            return "[]";
        }
        // 어떤 타입이 올지 모르니, 모든 클래스의 조상을 소환
        Object[] array = new Object[size]; //배열의 크기는 딱 리스트 크기만큼만
        //순회하면서 item 값만 배열에 넣은 뒤 배열을 String으로 변환하면 됨
        for (int i = 0; i <= size - 1; i++) {
            array[i] = search(i).item; // 여기서 형 변환 해줘야 하나?
        }
        return Arrays.toString(array);
    }
    // 노드 제거 메서드
    // 노드 제거 메서드_가장 첫번째
    public E removeFirst() {
        if (head == null) {
            throw new RuntimeException();
        }
        E returnItem = head.item; // 첫번째 노드의 데이터 백업(나중에 리턴)
        Node<E> firstNode = head.next; // 두번째 노드 백업
        head.item = null;
        head.next = null; // 참조형이기 때문에 참조하고 있는 주소의 값 변경 가능
        head = firstNode; // head 값 업데이트
        size--;
        /*
         * 만약에 삭제한 친구가 유일한 값이었다면? tail과 head 동일시.
         * 판단 조건은? head가 null 상태겠지.
         * 왜? 앞선 로직(addFirst참고)에 따르면, 유일 값인 경우 head와 tail이 동일 노드를 참조하고
         * 유일 값이 삭제된 순간 head의 다음은 null일테니
         */
        if (head == null) {
//            tail = head; // 이렇게 하면... 잘못 참조될 수도? 습관 들이기
            tail = null; // 명확하게 표현해 줄 것.
        }
        return returnItem;
    }
    // 노드 제거 메서드_가장 마지막
    public E removeLast(){
        if (tail == null) {
            throw new RuntimeException();
        }
        E returnItem = tail.item;
        Node<E> lastNode = search(size - 1);
        tail.item = null; // next는 원래 null이었으니까
        lastNode.next = null;
        size--;
        tail = lastNode;
        if (tail == null) {
            head = null;
        }
        return returnItem;
    }

    // 노드 제거 메서드_원하는 인덱스

    public E remove(int index){
        if (index < 0 || index >= size) { // 가독성을 위해서는 index >= size 로 만들어 줘야 하나
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            return removeFirst();
        }
        /*
         * 우선 데이터 백업 먼저. 삭제할 놈, 삭제할 것 이전 놈, 삭제할 것 다음 놈.
         */
        Node<E> prevNode = search(index - 1);
        Node<E> delNode = prevNode.next;
        E returnItem = delNode.item;
        prevNode.next = delNode.next; // 뭐 선언해서 메모리 용량 늘릴 필요 있나 그냥 참조시켜

        delNode.item = null;
        delNode.next = null;
        size--;

        return returnItem;
    }

    // 노드 제거 메서드_원하는 값을 가진 노드

    // 인덱스에 맞는 노드 반환

    // 특정 인덱스에 값 변경하기
}
