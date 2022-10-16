package azuretriple.primitivecontainer;

/** A queue backed by a fixed-size array containing {@code byte}s. */
public class FixedByteQueue extends FixedQueue implements ByteQueue
{
    /**
     * @param work Backing array.
     * @param start Initial start index.
     * @param end Initial end index.
     * @param empty {@code true} if the queue is initially empty.
     */
    public FixedByteQueue(final byte[] work,final int start,final int end,final boolean empty) {super(work,start,end,empty);}
    /** Equivalent to {@code new FixedByteQueue(work,0,0,true)}. */
    public FixedByteQueue(final byte[] work) {this(work,0,0,true);}
    /** @param size Size of the backing array. */
    public FixedByteQueue(final int size) {this(new byte[size]);}
    /** Creates a copy of the argument. */
    public FixedByteQueue(final ByteQueue other) {super((Queue)other);}
    
    @Override public boolean equals(final Object other) {return other instanceof final ByteQueue o && equals(o);}
    @Override public FixedByteQueue clone() {return new FixedByteQueue(this);}
}