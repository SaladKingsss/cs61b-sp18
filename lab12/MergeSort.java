import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class MergeSort {
    /**
     * Removes and returns the smallest item that is in q1 or q2.
     * <p>
     * The method assumes that both q1 and q2 are in sorted order, with the smallest item first. At
     * most one of q1 or q2 can be empty (but both cannot be empty).
     *
     * @param q1 A Queue in sorted order from least to greatest.
     * @param q2 A Queue in sorted order from least to greatest.
     * @return The smallest item that is in q1 or q2.
     */
    private static <Item extends Comparable> Item getMin(
            Queue<Item> q1, Queue<Item> q2) {
        if (q1.isEmpty()) {
            return q2.dequeue();
        } else if (q2.isEmpty()) {
            return q1.dequeue();
        } else {
            // Peek at the minimum item in each queue (which will be at the front, since the
            // queues are sorted) to determine which is smaller.
            Comparable q1Min = q1.peek();
            Comparable q2Min = q2.peek();
            if (q1Min.compareTo(q2Min) <= 0) {
                // Make sure to call dequeue, so that the minimum item gets removed.
                return q1.dequeue();
            } else {
                return q2.dequeue();
            }
        }
    }

    /**
     * Returns a queue of queues that each contain one item from items.
     */
    private static <Item extends Comparable> Queue<Queue<Item>>
    makeSingleItemQueues(Queue<Item> items) {
        Queue<Queue<Item>> res = new Queue<>();
        for (Item item : items) {
            Queue<Item> q = new Queue<>();
            q.enqueue(item);
            res.enqueue(q);
        }
        return res; //that's why they call it result.
    }

    /**
     * Returns a new queue that contains the items in q1 and q2 in sorted order.
     * <p>
     * This method should take time linear in the total number of items in q1 and q2.  After
     * running this method, q1 and q2 will be empty, and all of their items will be in the
     * returned queue.
     *
     * @param q1 A Queue in sorted order from least to greatest.
     * @param q2 A Queue in sorted order from least to greatest.
     * @return A Queue containing all of the q1 and q2 in sorted order, from least to
     * greatest.
     */
    private static <Item extends Comparable> Queue<Item> mergeSortedQueues(
            Queue<Item> q1, Queue<Item> q2) {
        Queue<Item> res = new Queue<>();
        while (!q1.isEmpty() || !q2.isEmpty()) {
            res.enqueue(getMin(q1, q2));
        }
        return res;
    }

    /**
     * Returns a Queue that contains the given items sorted from least to greatest.
     * 1.split into 2 pieces
     * 2.mergesort each half
     * 3.merge the 2 sorted halves to form the final result.
     */
    public static <Item extends Comparable> Queue<Item> mergeSort(
            Queue<Item> items) {
        if (items.size() <= 1) {
            return items;
        }

        Queue<Item> q1 = new Queue<>();
        Queue<Item> q2 = new Queue<>();

        int mid = items.size() / 2;
        for (Item i : items) {
            if (mid > 0) {
                q1.enqueue(i);
            } else {
                q2.enqueue(i);
            }
            mid--;
        }

        Queue<Item> q1merged = mergeSort(q1);
        Queue<Item> q2merged = mergeSort(q2);

        items = mergeSortedQueues(q1merged, q2merged);
        return items;
    }

    public static void main(String[] args) {

        Queue<Integer> grades = new Queue<>();
        Queue<Integer> grades1;
        grades.enqueue(1);
        grades.enqueue(5);
        grades.enqueue(2);
        grades.enqueue(4);
        grades.enqueue(3);
        grades.enqueue(0);
        System.out.println("original queue: " + grades);
        grades1 = mergeSort(grades);
        System.out.println("sorted queue  : " + grades1);
        StdOut.println(grades.size() == grades1.size());


        Queue<String> students = new Queue<>();
        students.enqueue("Alice");
        students.enqueue("Vanessa");
        students.enqueue("Ethan");
        students.enqueue("David");
        students.enqueue("Jay");
        students.enqueue("Harry");
        System.out.println("original queue: " + students);
        Queue<String> students2 = MergeSort.mergeSort(students);
        System.out.println("sorted queue:   " + students2);
        System.out.println(students.size() == students2.size());


        Queue<Integer> ints = new Queue<>();
        ints.enqueue(10);
        ints.enqueue(5);
        ints.enqueue(6);
        ints.enqueue(4);
        ints.enqueue(1);
        ints.enqueue(7);
        ints.enqueue(9);
        ints.enqueue(3);
        System.out.println("original queue: " + ints);
        Queue<Integer> ints2 = MergeSort.mergeSort(ints);
        System.out.println("sorted queue:   " + ints2);
        System.out.println(ints.size() == ints2.size());

    }
}
