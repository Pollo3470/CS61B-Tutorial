public class ArrayDeque<T> implements Deque<T> {
    /*
     * index => (nextFirst + 1 + index) % array.length
     * */
    private int size;
    private int nextFirst;
    private int nextLast;
    private T[] array;

    private void resize(int capacity) {
        T[] newArray = (T[]) new Object[capacity];
        if ((array.length + nextLast - 1) % array.length > (nextFirst + 1) % array.length) {
            System.arraycopy(array, (nextFirst + 1) % array.length, newArray, 0, size);
        } else {
            System.arraycopy(array, nextFirst + 1, newArray, 0, array.length - nextFirst - 1);
            System.arraycopy(array, 0, newArray, array.length - nextFirst - 1, nextLast);
        }
        nextFirst = capacity - 1;
        nextLast = size;
        array = newArray;
    }

    public ArrayDeque() {
        array = (T[]) new Object[10];
        nextFirst = 9;
        nextLast = 0;
    }

    @Override
    public void addFirst(T item) {
        if (size == array.length) {
            resize(array.length * 2);
        }

        array[nextFirst] = item;
        nextFirst = (array.length + nextFirst - 1) % array.length;
        size += 1;
    }

    @Override
    public void addLast(T item) {
        if (size == array.length) {
            resize(size * 2);
        }

        array[nextLast] = item;
        nextLast = (nextLast + 1) % array.length;
        size += 1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < size - 1; i++) {
            System.out.print(array[(nextFirst + 1 + i) % array.length] + " ");
        }
        System.out.println(array[nextLast - 1]);
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        if (size == array.length / 4 && array.length > 10) {
            resize(size * 2);
        }

        T item = array[(nextFirst + 1) % array.length];
        array[(nextFirst + 1) % array.length] = null;
        nextFirst = (nextFirst + 1) % array.length;

        size -= 1;

        return item;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        if (size == array.length / 4 && array.length > 10) {
            resize(size * 2);
        }

        T item = array[(array.length + nextLast - 1) % array.length];
        array[(array.length + nextLast - 1) % array.length] = null;
        nextLast = (array.length + nextLast - 1) % array.length;

        size -= 1;

        return item;
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        return array[(nextFirst + 1 + index) % array.length];
    }
}
