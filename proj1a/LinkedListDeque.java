public class LinkedListDeque<T> {

    private class Node {
        Node previous;
        Node next;
        T item;

        public Node(T item, Node previous, Node next) {
            this.item = item;
            this.previous = previous;
            this.next = next;
        }
    }

    private Node sentinel;

    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.previous = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public LinkedListDeque(T x) {
        sentinel = new Node(null, null, null);
        sentinel.next = new Node(x, sentinel, sentinel);
        sentinel.previous = sentinel.next;
        size = 1;
    }

    public void addFirst(T item) {
        sentinel.next = new Node(item, sentinel, sentinel.next);
        sentinel.next.next.previous = sentinel.next;
        size++;
    }

    public void addLast(T item) {
        sentinel.previous = new Node(item, sentinel.previous, sentinel);
        sentinel.previous.previous.next = sentinel.previous;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {

    }

    public T removeFirst() {
        if (!isEmpty()) {
            T item = sentinel.next.item;
            sentinel.next = sentinel.next.next;
            sentinel.next.previous = sentinel;
            size--;
            return item;
        }
        return null;
    }

    public T removeLast() {
        if (!isEmpty()) {
            T item = sentinel.previous.item;
            sentinel.previous = sentinel.previous.previous;
            sentinel.previous.next = sentinel;
            size--;
            return item;
        }
        return null;
    }

    public T get(int index) {
        if (index < size / 2) {
            Node p = sentinel.next;
            for (int i = 0; i < index; i++) {
                p = p.next;
            }
            return p.item;
        }
        Node p = sentinel.previous;
        for (int i = 0; i < size - index - 1; i++) {
            p = p.previous;
        }
        return p.item;
    }
}
