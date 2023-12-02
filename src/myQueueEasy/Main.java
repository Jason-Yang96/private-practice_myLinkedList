package myQueueEasy;

public class Main {
    /*
    여기서 클래스 메서드 혹은 클래스 필드를 작성해줘도 된다. 아래에 위 혹은 아래 모두 작성 가능하다
    마치 자바스크립트 호이스팅 되는 것처럼 적용이 된다.
    밑에 psvm은 결국 main이라는 이름을 가진 메서드 인것.
    궁금한 건 클래스를 실행하는데 왜 메서드가 실행되지? 뭐 한 것도 없잖아?
    */
    public static void main(String[] args) {
        MyQueue<String> myQueue = new MyQueue<>();
        myQueue.enqueue("love you");
        myQueue.dequeue();
    }
}
