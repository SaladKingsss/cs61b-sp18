package synthesizer;

//simply provide a protected fillCount and capacity variable that all subclasses will inherit
public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T> {
    protected int fillCount;
    protected int capacity;

    public int capacity() {
        return capacity;
    }

    public int fillCount() {
        return fillCount;
    }
}
