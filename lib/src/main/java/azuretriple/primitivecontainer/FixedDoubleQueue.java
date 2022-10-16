package azuretriple.primitivecontainer;

/** A queue backed by a fixed-size array containing {@code double}s. */
public class FixedDoubleQueue extends FixedQueue implements DoubleQueue
{
    /**
     * @param work Backing array.
     * @param start Initial start index.
     * @param end Initial end index.
     * @param empty {@code true} if the queue is initially empty.
     */
    public FixedDoubleQueue(final double[] work,final int start,final int end,final boolean empty) {super(work,start,end,empty);}
    /** Equivalent to {@code new FixedDoubleQueue(work,0,0,true)}. */
    public FixedDoubleQueue(final double[] work) {this(work,0,0,true);}
    /** @param size Size of the backing array. */
    public FixedDoubleQueue(final int size) {this(new double[size]);}
    /** Creates a copy of the argument. */
    public FixedDoubleQueue(final DoubleQueue other) {super((Queue)other);}
    
    @Override public boolean equals(final Object other) {return other instanceof final DoubleQueue o && equals(o);}
    @Override public FixedDoubleQueue clone() {return new FixedDoubleQueue(this);}
}