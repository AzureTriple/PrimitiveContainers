package azuretriple.primitivecontainer;

/** A queue backed by a growable array containing {@code long}s. */
public class GrowableLongQueue extends GrowableQueue implements LongQueue
{
    /**
     * @param work Backing array.
     * @param start Initial start index.
     * @param end Initial end index.
     * @param state {@link GrowableQueue#EMPTY}, {@link GrowableQueue#NORMAL}, or {@link GrowableQueue#FULL}.
     */
    public GrowableLongQueue(final long[] work,final int start,final int end,final byte state) {super(work,start,end,state);}
    /** Equivalent to {@code GrowableLongQueue(work,0,0,GrowableQueue.EMPTY)}. */
    public GrowableLongQueue(final long[] work) {this(work,0,0,EMPTY);}
    /** @param size Size of the backing array. */
    public GrowableLongQueue(final int size) {this(new long[size]);}
    /** Equivalent to {@code GrowableLongQueue(1 << 4)}. */
    public GrowableLongQueue() {this(Growable.DEFAULT_SIZE);}
    /** Creates a copy of the argument. */
    public GrowableLongQueue(final LongQueue other) {super((Queue)other);}
    
    @Override public boolean equals(final Object other) {return other instanceof final LongQueue o && equals(o);}
    @Override public GrowableLongQueue clone() {return new GrowableLongQueue(this);}
}