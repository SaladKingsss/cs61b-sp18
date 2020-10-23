public class LinkedListDeque<T> {

    public class IntNode {
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
        sentinel = new IntNode(null, (T)"63", null);//sentinel is also an IntNode!!
        //here you need to do something to keep its types correct.
    }

    /* add and remove operations must not involve any looping or recursion.
    A single such operation must take “constant time”, i.e. execution time should not depend on the size of the deque. */
    public void addFirst(T item) {
        size += 1;
        this.sentinel.next = new IntNode(this.sentinel, item, this.sentinel.next);
    }

    public void addLast(T item) {
        size += 1;
        this.sentinel.prev = new IntNode(this.sentinel.prev, item, this.sentinel);
    }

    /* Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        if (this.sentinel.next != null) {
            return false;
        } else {
            return true;
        }
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

    /* Removes and returns the item at the front of the deque. If no such item exists, returns null. */
    public T removeFirst() {
        size -= 1;
        this.sentinel.next = this.sentinel.next.next;
        if (this.sentinel.next != null) {
            return this.sentinel.next.item;
        } else {
            return null;
        }
    }

    /* Removes and returns the item at the back of the deque. If no such item exists, returns null. */
    public T removeLast() {
        size -= 1;
        this.sentinel.prev = this.sentinel.prev.prev;
        if (this.sentinel.prev != null) {
            return this.sentinel.prev.item;
        } else {
            return null;
        }
    }

    /* Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
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
                cnt++;//cnt starts from zero and now p and cnt both come to the first IntNode.
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
            return getRecursiveTools(index, cnt, p);//return p and p will do recursion in the tools method.
        }
    }

    /* You may add any private helper classes or methods in LinkedListDeque.java if you deem it necessary. */
    public T getRecursiveTools(int index, int cnt, IntNode p) {
        if (index == cnt) {
            return p.item;
        } else {
            return getRecursiveTools(index, cnt + 1, p.next);
        }
    }
}