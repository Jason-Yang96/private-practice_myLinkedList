package myQueueEasy;

import java.util.ArrayList;
import java.util.Arrays;

public class MyQueue<T> {
    /*
         유의 사항: data의 타입은 MyQueue를 생성할 때 정할 수 있는 제네릭으로 구현

         노드의 첫 부분을 가리키는 포인트
         노드의 마지막 부분을 가리키는 포인트
         노드의 개수

         생성자

         노드 클래스 정의(private static)

         노드 검색 메서드 정의(인덱스가 주어졌을 때 그 인덱스에 맞춰서 노드 탐색) -> 노드 반환
         * 이거 찾을 필요 있나? 찾는 값 반환할 때는 필요하겠구나.

         노드 추가 메서드
         노드 추가 메서드_가장 첫번째
         노드 추가 메서드_가장 마지막
         노드 추가 메서드_원하는 인덱스에
         노드 추가 메서드_반복적으로 값을 추가할 때 -> boolean 반환

         Object Override: toSting() 메서드 정의(해당 클래스 출력하게 되면 string 배열 반환)
         추가 설명: 원래는 클래스 인스턴스를 출력할 때 실제 메모리 주소와 파일 내 아이디 값을 반환
         여기서는 인스턴스 출력 시 인스턴스가 가지고 있는 배열 값을 출력하도록 만듦

         인덱스에 맞는 노드 반환

         특정 인덱스에 값 변경하기

         노드 제거 메서드
         노드 제거 메서드_가장 첫번째
         노드 제거 메서드_가장 마지막
         노드 제거 메서드_원하는 인덱스
         노드 제거 메서드_원하는 값을 가진 노드
     */
    private ArrayList<T> queue = new ArrayList<>();
    public boolean enqueue(T item) {
        this.queue.add(item);
        return true;
    }
    public T dequeue(){
        if (this.queue.isEmpty()) {
            return null;
        }
        return this.queue.remove(0);
    }

    @Override
    public String toString() {
        int queueSize = this.queue.size();
        Object[] queueElements = new Object[queueSize];
        if (queueSize == 0) {
            return "[]";
        }
        for (int i = 0; i < queueSize; i++) {
            queueElements[i] = this.queue.get(i);
        }
        return Arrays.toString(queueElements);

    }
}
