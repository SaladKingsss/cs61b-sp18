public class ArrayDeque<T> implements Deque<T> {

    private T[] items;
    private int nextFirst;
    private int nextLast;
    private int size;

    /* You may add any private helper classes or methods in ArrayDeque.
    java if you deem it necessary. */

    /* Creates an empty array deque. */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        nextFirst = 0;
        nextLast = 1;
        size = 0;
    }

    /* move the pointer. */
    private int plusOne(int x) {
        return (x + 1) % this.items.length;
    }

    /* move the pointer. */
    private int minusOne(int x) {
        return (x - 1 + this.items.length) % this.items.length;
    }

    /* resize the arrays and change their order.
     * what to do if num<size ? */
    private void resize(int num) {
        T[] a = (T[]) new Object[num]; //new way to do.
        int j = plusOne(this.nextFirst); //the bug exists here!
        for (int i = 0; i < this.size; i++) {
            a[i] = items[j];
            j = plusOne(j);
        }
        this.items = a;
        this.nextFirst = this.items.length - 1;
        this.nextLast = size;
        /*it is necessary to change nextfirst and nextlast. Think!*/
    }

    /*Adds an item of type T to the front of the deque.*/
    @Override
    public void addFirst(T item) {
        if (this.size == this.items.length) {
            resize(this.size * 2);
        }
        items[this.nextFirst] = item;
        this.nextFirst = minusOne(this.nextFirst);
        this.size += 1;
    }

    /* Adds an item of type T to the back of the deque. */
    @Override
    public void addLast(T item) {
        if (this.size == this.items.length) {
            resize(this.size * 2);
        }
        items[this.nextLast] = item;
        this.nextLast = plusOne(this.nextLast);
        this.size += 1;
    }

    /* Removes and returns the item at the front of the deque.
    If no such item exists, returns null. */
    @Override
    public T removeFirst() {
        if (this.size == 0) {
            return null;
        }
        this.nextFirst = plusOne(this.nextFirst);
        T ans = items[this.nextFirst];
        items[this.nextFirst] = null; //it is unnecessary.
        if (!isEmpty()) {
            this.size -= 1;
        }

        if (size < this.items.length / 2) {
            resize(this.items.length / 2); //num will not small than size
        }

        return ans;
    }

    @Override
    public T removeLast() {
        if (this.size == 0) {
            return null;
        }
        this.nextLast = minusOne((this.nextLast));
        T ans = items[this.nextLast];
        items[this.nextLast] = null; //it is unnecessary.
        if (!isEmpty()) {
            this.size -= 1;
        }

        if (size < this.items.length / 2) {
            resize(this.items.length / 2);
        }

        return ans;
    }

    /* Returns true if deque is empty, false otherwise. */
    @Override
    public boolean isEmpty() {
        return (this.size == 0);
    }

    /* Returns the number of items in the deque. */
    @Override
    public int size() {
        return this.size;
    }

    /* Prints the items in the deque from first to last, separated by a space. */
    @Override
    public void printDeque() {
        for (int i = this.nextFirst; i != nextLast; i = plusOne(i)) {
            System.out.print(items[i]);
            System.out.print(" ");
        }
    }

    /* Gets the item at the given index,
    where 0 is the front, 1 is the next item, and so forth.
     If no such item exists, returns null. Must not alter the deque! */
    @Override
    public T get(int index) {
        if (index < 0 || index >= this.size) {
            return null;
        } else {
            int num = this.nextFirst;
            for (int i = 0; i <= index; i++) {
                num = plusOne(num);
            }
            return items[num];
        }
    }
}
