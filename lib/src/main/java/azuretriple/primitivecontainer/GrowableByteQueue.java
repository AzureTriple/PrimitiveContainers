package azuretriple.primitivecontainer;

/** A queue backed by a growable array containing {@code byte}s. */
public class GrowableByteQueue extends GrowableQueue implements ByteQueue
{
    /**
     * @param work Backing array.
     * @param start Initial start index.
     * @param end Initial end index.
     * @param state {@link GrowableQueue#EMPTY}, {@link GrowableQueue#NORMAL}, or {@link GrowableQueue#FULL}.
     */
    public GrowableByteQueue(final byte[] work,final int start,final int end,final byte state) {super(work,start,end,state);}
    /** Equivalent to {@code GrowableByteQueue(work,0,0,GrowableQueue.EMPTY)}. */
    public GrowableByteQueue(final byte[] work) {this(work,0,0,EMPTY);}
    /** @param size Size of the backing array. */
    public GrowableByteQueue(final int size) {this(new byte[size]);}
    /** Equivalent to {@code GrowableByteQueue(1 << 4)}. */
    public GrowableByteQueue() {this(Growable.DEFAULT_SIZE);}
    /** Creates a copy of the argument. */
    public GrowableByteQueue(final ByteQueue other) {super((Queue)other);}
    
    @Override public boolean equals(final Object other) {return other instanceof final ByteQueue o && equals(o);}
    @Override public GrowableByteQueue clone() {return new GrowableByteQueue(this);}
}