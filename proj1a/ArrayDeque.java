public class ArrayDeque<T> {

    private T[] items;
    private int nextFirst;
    private int nextLast;
    private int size;

    /* You may add any private helper classes or methods in ArrayDeque.java if you deem it necessary. */

    /* Creates an empty array deque. */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        nextFirst = 0;
        nextLast = 1;
        size = 0;
    }

    /* move the pointer. */
    public int PlusOne(int x) {
        if (x != this.items.length) {
            return x + 1;
        } else {
            return 0;
        }
    }

    /* move the pointer. */
    public int MinusOne(int x) {
        if (x != 0) {
            return x - 1;
        } else {
            return this.items.length;
        }
    }

    /* resize the arrays and change their order.
     * what to do if num<size ? */
    public void resize(int num) {
        T[] a = (T[]) new Object[num];//new way to do.
        int j = this.nextFirst;
        for (int i = 0; i < this.size; i++) {
            a[i] = items[j];
            j = PlusOne(j);
        }
        this.items = a;
        this.nextFirst = this.items.length - 1;
        this.nextLast = size;
        /*it is necessary to change nextfirst and nextlast. Think!*/
    }

    /*Adds an item of type T to the front of the deque.*/
    public void addFirst(T item) {
        if (this.size == this.items.length) {
            resize(this.size * 2);
        }
        items[this.nextFirst] = item;
        this.nextFirst = this.MinusOne(this.nextFirst);
        this.size += 1;
    }

    /* Adds an item of type T to the back of the deque. */
    public void addLast(T item) {
        if (this.size == this.items.length) {
            resize(this.size * 2);
        }
        items[this.nextLast] = item;
        this.nextLast = PlusOne(this.nextLast);
        this.size += 1;
    }

    /* Removes and returns the item at the front of the deque. If no such item exists, returns null. */
    public T removeFirst() {
        this.nextFirst = PlusOne(this.nextFirst);
        this.size -= 1;
        if (size < this.items.length / 2) {
            resize(this.items.length / 2);//num will not small than size
        }
        return items[PlusOne(this.nextFirst)];
    }

    public T removeLast() {
        this.nextLast = MinusOne((this.nextLast));
        this.size -= 1;
        if (size < this.items.length / 2) {
            resize(this.items.length / 2);
        }
        return items[MinusOne(this.nextLast)];
    }

    /* Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        if (this.size == 0) {
            return true;
        } else {
            return false;
        }
    }

    /* Returns the number of items in the deque. */
    public int size() {
        return this.size;
    }

    /* Prints the items in the deque from first to last, separated by a space. */
    public void printDeque() {
        for (int i = this.nextFirst; i != nextLast; i = PlusOne(i)) {
            System.out.print(items[i]);
            System.out.print(" ");
        }
    }

    /* Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     If no such item exists, returns null. Must not alter the deque! */
    public T get(int index) {
        if (index < 0 || index > this.size) {
            return null;
        } else {
            int num = this.nextFirst;
            for (int i = 0; i <= index; i++) {
                num = PlusOne(num);
            }
            return items[num];
        }
    }
}