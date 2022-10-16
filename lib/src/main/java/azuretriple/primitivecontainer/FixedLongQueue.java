package azuretriple.primitivecontainer;

/** A queue backed by a fixed-size array containing {@code long}s. */
public class FixedLongQueue extends FixedQueue implements LongQueue
{
    /**
     * @param work Backing array.
     * @param start Initial start index.
     * @param end Initial end index.
     * @param empty {@code true} if the queue is initially empty.
     */
    public FixedLongQueue(final long[] work,final int start,final int end,final boolean empty) {super(work,start,end,empty);}
    /** Equivalent to {@code new FixedLongQueue(work,0,0,true)}. */
    public FixedLongQueue(final long[] work) {this(work,0,0,true);}
    /** @param size Size of the backing array. */
    public FixedLongQueue(final int size) {this(new long[size]);}
    /** Creates a copy of the argument. */
    public FixedLongQueue(final LongQueue other) {super((Queue)other);}
    
    @Override public boolean equals(final Object other) {return other instanceof final LongQueue o && equals(o);}
    @Override public FixedLongQueue clone() {return new FixedLongQueue(this);}
}