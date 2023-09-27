public class ArrayDeque<T> {
    /*
     * index => (nextFirst + 1 + index) % array.length
     * */
    private int size;
    // 从数组最后一位开始
    private int nextFirst;
    // 从数组第一位开始
    private int nextLast;
    private T[] array;

    private void resize(int capacity) {
        T[] newArray = (T[]) new Object[capacity];

        if (nextFirst < array.length - 1) {
            System.arraycopy(array, nextFirst + 1, newArray, 0, array.length - nextFirst - 1);
        }
        System.arraycopy(array, 0, newArray, array.length - nextFirst - 1, nextLast);
        nextFirst = capacity - 1;
        nextLast = size;
        array = newArray;
    }

    public ArrayDeque() {
        array = (T[]) new Object[10];
        nextFirst = 9;
        nextLast = 0;
    }

    public void addFirst(T item) {
        if (nextFirst == nextLast) {
            resize(array.length * 2);
        }

        array[nextFirst] = item;
        nextFirst = (array.length + nextFirst - 1) % array.length;
        size += 1;
    }

    public void addLast(T item) {
        if (nextFirst == nextLast) {
            resize(size * 2);
        }

        array[nextLast] = item;
        nextLast = (nextLast + 1) % array.length;
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
            System.out.print(array[(nextFirst + 1 + i) % array.length] + " ");
        }
        System.out.println(array[nextLast - 1]);
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        size -= 1;

        if (size == array.length / 4 && array.length > 10) {
            resize(size * 2);
        }

        T item = array[(nextFirst + 1) % array.length];
        array[(nextFirst + 1) % array.length] = null;
        nextFirst = (nextFirst + 1) % array.length;

        return item;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        size -= 1;

        if (size == array.length / 4 && array.length > 10) {
            resize(size * 2);
        }

        T item = array[(array.length + nextLast - 1) % array.length];
        array[(array.length + nextLast - 1) % array.length] = null;
        nextLast = (array.length + nextLast - 1) % array.length;

        return item;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        return array[(nextFirst + 1 + index) % array.length];
    }
}
