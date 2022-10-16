package azuretriple.primitivecontainer;

/** A queue backed by a fixed-size array containing {@code float}s. */
public class FixedFloatQueue extends FixedQueue implements FloatQueue
{
    /**
     * @param work Backing array.
     * @param start Initial start index.
     * @param end Initial end index.
     * @param empty {@code true} if the queue is initially empty.
     */
    public FixedFloatQueue(final float[] work,final int start,final int end,final boolean empty) {super(work,start,end,empty);}
    /** Equivalent to {@code new FixedFloatQueue(work,0,0,true)}. */
    public FixedFloatQueue(final float[] work) {this(work,0,0,true);}
    /** @param size Size of the backing array. */
    public FixedFloatQueue(final int size) {this(new float[size]);}
    /** Creates a copy of the argument. */
    public FixedFloatQueue(final FloatQueue other) {super((Queue)other);}
    
    @Override public boolean equals(final Object other) {return other instanceof final FloatQueue o && equals(o);}
    @Override public FixedFloatQueue clone() {return new FixedFloatQueue(this);}
}