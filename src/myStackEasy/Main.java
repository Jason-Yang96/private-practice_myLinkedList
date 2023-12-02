package myStackEasy;

public class Main {
    public static void main(String[] args) {
        MyStack<String> stack = new MyStack<>();
        stack.push("jason");
        stack.push("cheer up");
        stack.push("you can do it");
        System.out.println(stack);

        stack.pop();
        stack.pop();
        System.out.println(stack);
    }
}
