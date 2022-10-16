package azuretriple.primitivecontainer;

/** A queue backed by a growable array containing {@code short}s. */
public class GrowableShortQueue extends GrowableQueue implements ShortQueue
{
    /**
     * @param work Backing array.
     * @param start Initial start index.
     * @param end Initial end index.
     * @param state {@link GrowableQueue#EMPTY}, {@link GrowableQueue#NORMAL}, or {@link GrowableQueue#FULL}.
     */
    public GrowableShortQueue(final short[] work,final int start,final int end,final byte state) {super(work,start,end,state);}
    /** Equivalent to {@code GrowableShortQueue(work,0,0,GrowableQueue.EMPTY)}. */
    public GrowableShortQueue(final short[] work) {this(work,0,0,EMPTY);}
    /** @param size Size of the backing array. */
    public GrowableShortQueue(final int size) {this(new short[size]);}
    /** Equivalent to {@code GrowableShortQueue(1 << 4)}. */
    public GrowableShortQueue() {this(Growable.DEFAULT_SIZE);}
    /** Creates a copy of the argument. */
    public GrowableShortQueue(final ShortQueue other) {super((Queue)other);}
    
    @Override public boolean equals(final Object other) {return other instanceof final ShortQueue o && equals(o);}
    @Override public GrowableShortQueue clone() {return new GrowableShortQueue(this);}
}