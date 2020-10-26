public class LinkedListDeque<T> {

    private class IntNode {
        public IntNode prev;
        public T item;
        public IntNode next;

        public IntNode(IntNode m, T i, IntNode n) {
            prev = m;
            item = i;
            next = n;
        }
    }

    private IntNode sentinel;
    private int size;

    /* Creates an empty linked list deque. */
    public LinkedListDeque() {
        size = 0;
        this.sentinel = new IntNode(null, null, null);//sentinel is also an IntNode!!
        this.sentinel.prev = this.sentinel;
        this.sentinel.next = this.sentinel;
        //here you need to do something to keep its types correct.
    }

    /* add and remove operations must not involve any looping or recursion.
    A single such operation must take “constant time”,
    i.e. execution time should not depend on the size of the deque. */
    public void addFirst(T item) {
        size += 1;
        this.sentinel.next = new IntNode(this.sentinel, item, this.sentinel.next);
        this.sentinel.next.next.prev = this.sentinel.next;
    }

    public void addLast(T item) {
        size += 1;
        this.sentinel.prev = new IntNode(this.sentinel.prev, item, this.sentinel);
        this.sentinel.prev.prev.next = this.sentinel.prev;
    }

    /* Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return this.sentinel.next == this.sentinel;
    }

    /* size must take constant time. */
    public int size() {
        return this.size;
    }

    /* Prints the items in the deque from first to last, separated by a space. */
    public void printDeque() {
        IntNode p = this.sentinel;
        p = p.next;
        while (p != null) {
            System.out.println(p.item);
            System.out.println(" ");
            p = p.next;
        }
    }

    /* Removes and returns the item at the front of the deque.
    If no such item exists, returns null. */
    public T removeFirst() {
        if (this.size == 0) {
            return null;
        } else {
            this.sentinel.next.next.prev = this.sentinel;
            this.sentinel.next = this.sentinel.next.next;
            size -= 1;
            return this.sentinel.next.item;
        }
    }

    /* Removes and returns the item at the back of the deque.
    If no such item exists, returns null. */
    public T removeLast() {
        if (this.size == 0) {
            return null;
        } else {
            this.sentinel.prev.prev.next = this.sentinel;
            this.sentinel.prev = this.sentinel.prev.prev;
            size -= 1;
            return this.sentinel.prev.item;
        }
    }

    /* Gets the item at the given index,
     where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque!
     * get must use iteration, not recursion.
     * sounds like i need to do some error handling.*/
    public T get(int index) {
        int cnt = 0;
        IntNode p = this.sentinel;

        if (index >= size || index < 0) {
            return null;
        } else {
            while (cnt <= index) {
                p = p.next;
                cnt++;
                //cnt starts from zero and now p and cnt both come to the first IntNode.
            }
            return p.item;
        }
    }//may have problems.


    /* Same as get, but uses recursion.
     * here is only one parameter, so you need another function(method).*/
    public T getRecursive(int index) {

        int cnt = 0;
        IntNode p = this.sentinel.next;

        if (index >= size || index < 0) {
            return null;
        } else {
            return getRecursiveTools(index, cnt, p);
            //return p and p will do recursion in the tools method.
        }
    }

    /* You may add any private helper classes or methods in LinkedListDeque.
    java if you deem it necessary. */
    private T getRecursiveTools(int index, int cnt, IntNode p) {
        if (index == cnt) {
            return p.item;
        } else {
            return getRecursiveTools(index, cnt + 1, p.next);
        }
    }
}