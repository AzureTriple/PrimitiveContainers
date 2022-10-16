package azuretriple.primitivecontainer;

/** A queue backed by a growable array containing {@code double}s. */
public class GrowableDoubleQueue extends GrowableQueue implements DoubleQueue
{
    /**
     * @param work Backing array.
     * @param start Initial start index.
     * @param end Initial end index.
     * @param state {@link GrowableQueue#EMPTY}, {@link GrowableQueue#NORMAL}, or {@link GrowableQueue#FULL}.
     */
    public GrowableDoubleQueue(final double[] work,final int start,final int end,final byte state) {super(work,start,end,state);}
    /** Equivalent to {@code GrowableDoubleQueue(work,0,0,GrowableQueue.EMPTY)}. */
    public GrowableDoubleQueue(final double[] work) {this(work,0,0,EMPTY);}
    /** @param size Size of the backing array. */
    public GrowableDoubleQueue(final int size) {this(new double[size]);}
    /** Equivalent to {@code GrowableDoubleQueue(1 << 4)}. */
    public GrowableDoubleQueue() {this(Growable.DEFAULT_SIZE);}
    /** Creates a copy of the argument. */
    public GrowableDoubleQueue(final DoubleQueue other) {super((Queue)other);}
    
    @Override public boolean equals(final Object other) {return other instanceof final DoubleQueue o && equals(o);}
    @Override public GrowableDoubleQueue clone() {return new GrowableDoubleQueue(this);}
}