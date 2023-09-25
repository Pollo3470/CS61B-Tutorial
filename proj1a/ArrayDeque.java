public class ArrayDeque<T> {
    private int size;
    private int nextFirst;
    private int nextLast;
    private T[] array;

    private void resize(int capacity) {
        T[] newArray = (T[]) new Object[capacity];
        System.arraycopy(array, nextFirst + 1, newArray, capacity / 2 - size / 2, size);
        nextFirst = capacity / 2 - size / 2 - 1;
        nextLast = nextFirst + size + 1;
        array = newArray;
    }

    public ArrayDeque() {
        array = (T[]) new Object[10];
        nextFirst = 4;
        nextLast = 5;
    }

    public void addFirst(T item) {
        if (nextFirst < 0) {
            resize(array.length * 2);
        }

        array[nextFirst] = item;
        nextFirst -= 1;
        size += 1;
    }

    public void addLast(T item) {
        if (nextLast >= array.length) {
            resize(size * 2);
        }

        array[nextLast] = item;
        nextLast += 1;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = 0; i < size - 1; i++) {
            System.out.print(array[nextFirst + 1 + i] + " ");
        }
        System.out.println(array[nextLast - 1]);
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        if (size == array.length / 4 && array.length > 10) {
            resize(size * 2);
        }

        T item = array[nextFirst + 1];
        array[nextFirst + 1] = null;
        nextFirst += 1;
        size -= 1;

        return item;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        if (size == array.length / 4 && array.length > 10) {
            resize(size * 2);
        }

        T item = array[nextLast - 1];
        array[nextLast - 1] = null;
        nextLast -= 1;
        size -= 1;

        return item;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        return array[nextFirst + 1 + index];
    }
}
