public class ArrayDeque<T> {
    private int size;
    private int capacity;
    private T[] array;

    private void expand() {
        capacity *= 2;
        T[] newArray = (T[]) new Object[capacity];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    private void shrink() {
        capacity /= 2;
        T[] newArray = (T[]) new Object[capacity];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    public ArrayDeque() {
        array = (T[]) new Object[10];
        capacity = 10;
        size = 0;
    }

    public void addFirst(T item) {
        if (size == capacity) {
            expand();
        }
        System.arraycopy(array, 0, array, 1, size);
        array[0] = item;
        size += 1;
    }

    public void addLast(T item) {
        if (size == capacity) {
            expand();
        }

        array[size] = item;
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
            System.out.print(array[i] + " ");
        }
        System.out.println(array[size - 1]);
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        if (size == capacity / 4 && capacity > 10) {
            shrink();
        }
        size -= 1;
        T item = array[0];
        System.arraycopy(array, 1, array, 0, size);
        return item;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        if (size == capacity / 4 && capacity > 10) {
            shrink();
        }
        size -= 1;
        T item = array[size];
        array[size] = null;
        return item;
    }

    public T get(int index) {
        return array[index];
    }
}
