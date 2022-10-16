package azuretriple.primitivecontainer;

/** A queue backed by a fixed-size array containing {@code int}s. */
public class FixedIntQueue extends FixedQueue implements IntQueue
{
    /**
     * @param work Backing array.
     * @param start Initial start index.
     * @param end Initial end index.
     * @param empty {@code true} if the queue is initially empty.
     */
    public FixedIntQueue(final int[] work,final int start,final int end,final boolean empty) {super(work,start,end,empty);}
    /** Equivalent to {@code new FixedIntQueue(work,0,0,true)}. */
    public FixedIntQueue(final int[] work) {this(work,0,0,true);}
    /** @param size Size of the backing array. */
    public FixedIntQueue(final int size) {this(new int[size]);}
    /** Creates a copy of the argument. */
    public FixedIntQueue(final IntQueue other) {super((Queue)other);}
    
    @Override public boolean equals(final Object other) {return other instanceof final IntQueue o && equals(o);}
    @Override public FixedIntQueue clone() {return new FixedIntQueue(this);}
}