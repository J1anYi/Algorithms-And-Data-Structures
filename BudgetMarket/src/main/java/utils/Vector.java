package utils;

/**
 * It's a simple implementation of Vector class.
 * This is an imitation of
 * java.util.ArrayList implementation of add, delete and check,
 * and has a simple expansion mechanism
 * @param <E>
 */
public class Vector<E> {
    private Object[] elementData;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public Vector() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    public Vector(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
        elementData = new Object[initialCapacity];
    }

    /**
     * This method is used to add an element to the end of the vector
     * first, check if the capacity is enough, if not, expand the capacity
     * @param e
     */
    public void add(E e) {
        ensureCapacity();
        elementData[size++] = e;
    }

    public E get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (E) elementData[index];
    }


    /**
     * This method is used to remove the element at the specified position in this vector.
     * When the element is removed, the elements behind it will be moved forward
     * Using System.arraycopy() method to move the elements behind it
     * @param index
     */
    public void remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        }
        elementData[--size] = null; // clear to let GC do its work
    }

    /**
     * This method is used to expand the capacity of the vector
     * when the capacity is not enough, the capacity will be doubled
     * ArrayList uses this method to expand the capacity but the expansion factor is 1.5
     * And mine expansion factor is 2
     */
    private void ensureCapacity() {
        if (size == elementData.length) {
            int newIncreasedCapacity = elementData.length * 2;
            elementData = java.util.Arrays.copyOf(elementData, newIncreasedCapacity);
        }
    }

    public int size() {
        return size;
    }

    public void clear() {
        for (int i = 0; i < size; i++)
            elementData[i] = null;
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < size; i++) {
            sb.append(elementData[i].toString());
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append(']');
        return sb.toString();
    }
}
