package azuretriple.primitivecontainer;

/** A queue backed by a fixed-size array containing {@code short}s. */
public class FixedShortQueue extends FixedQueue implements ShortQueue
{
    /**
     * @param work Backing array.
     * @param start Initial start index.
     * @param end Initial end index.
     * @param empty {@code true} if the queue is initially empty.
     */
    public FixedShortQueue(final short[] work,final int start,final int end,final boolean empty) {super(work,start,end,empty);}
    /** Equivalent to {@code new FixedShortQueue(work,0,0,true)}. */
    public FixedShortQueue(final short[] work) {this(work,0,0,true);}
    /** @param size Size of the backing array. */
    public FixedShortQueue(final int size) {this(new short[size]);}
    /** Creates a copy of the argument. */
    public FixedShortQueue(final ShortQueue other) {super((Queue)other);}
    
    @Override public boolean equals(final Object other) {return other instanceof final ShortQueue o && equals(o);}
    @Override public FixedShortQueue clone() {return new FixedShortQueue(this);}
}