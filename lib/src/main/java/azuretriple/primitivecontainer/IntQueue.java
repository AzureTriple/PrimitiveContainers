package azuretriple.primitivecontainer;

/** A queue containing {@code int}s. */
public interface IntQueue extends IntContainer
{
    /** @return {@code true} iff this queue contains the same data as the argument. */
    default boolean equals(final IntQueue other)
    {
        if(this == other) return true;
        final int s1 = size();
        if(s1 != other.size()) return false;
        final int l1 = data().length,l2 = other.data().length;
        for(int i = 0,a = ((Queue)this).start,b = ((Queue)other).start;i != s1;++i,a = (a+1)%l1,b = (b+1)%l2)
            if(data()[a] != other.data()[b])
                return false;
        return true;
    }
    
    /** Appends the values in the argument to the queue. */
    default void push(final int[] v) {((Queue)this).push(v);}
    /**
     * Appends the values in the argument to the queue.
     *
     * @param start Starting position in the array.
     * @param length Number of elements to copy.
     */
    default void push(final int[] v,final int start,final int length) {((Queue)this).push(v,start,length);}
    /** Appends the value to the queue. */
    default void push(final int v) {((Queue)this).pushLogic(); data()[((Queue)this).end++] = v;}
    /** Removes the value at the front of the queue. */
    void delete();
    /** Removes and returns the value from the front of the queue. */
    default int pop() {final int o = data()[((Queue)this).start]; delete(); return o;}
    /** @return The value at the front (i.e. least recently pushed value) of the queue. */
    default int front() {assert !empty(); return data()[((Queue)this).start];}
    /** @return The value at the back (i.e. most recently pushed value) of the queue. */
    default int back() {assert !empty(); return data()[((Queue)this).end-1];}
    
    @Override IntQueue clone();
}