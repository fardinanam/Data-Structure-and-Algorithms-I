import java.util.LinkedList;
import java.util.List;

public class MyQueue<T> {
    private int head;
    private int tail;
    private List<T> container;

    public MyQueue() {
        container = new LinkedList<>();
        head = tail = -1;
    }

    public void enQueue(T item) {
        container.add(item);
        if(head == -1) {
            head++;
        }
        tail++;
    }

    public T deQueue() {
        if(isEmpty()) {
            System.out.println("Queue is empty");
            return null;
        }

        head++;
        return container.remove(0);
    }

    public T peek() {
        if(!isEmpty()) {
            return container.get(0);
        } else {
            System.out.println("Queue is empty");
            return null;
        }
    }

    public boolean contains(T item) {
        return container.contains(item);
    }

    public boolean isEmpty() {
        if(head > tail || head < 0 || tail < 0) {
            return true;
        }
        return false;
    }
}
