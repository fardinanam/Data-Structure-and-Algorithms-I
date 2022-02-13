import java.util.LinkedList;
import java.util.List;

public class MyStack<T> {
    private final List<T> container;
    private int top;

    public MyStack() {
        container = new LinkedList<>();
        top = -1;
    }

    public void push(T item) {
        container.add(item);
        top++;
    }

    public T pop() {
        if(isEmpty()) {
            System.out.println("The stack is empty");
            return null;
        }
        return container.remove(top--);
    }

    public T peek() {
        if(!isEmpty()) {
            return container.get(top);
        } else {
            System.out.println("The stack is empty");
            return null;
        }
    }

    public int getTop() {
        return top;
    }

    public boolean isEmpty() {
        if(top == -1) {
            return true;
        }
        return false;
    }

//    public static void main(String[] args) {
//        MyStack<Integer> checkStack = new MyStack<>();
//
//        for(int i=0; i<10; i++) {
//            checkStack.push(i * 2);
//        }
//        System.out.println(checkStack.getTop());
//        System.out.println(checkStack.isEmpty());
//        while (!checkStack.isEmpty()) {
//            System.out.println(checkStack.pop());
//        }
//        System.out.println(checkStack.pop());
//        System.out.println(checkStack.getTop());
//        System.out.println(checkStack.isEmpty());
//    }
}
