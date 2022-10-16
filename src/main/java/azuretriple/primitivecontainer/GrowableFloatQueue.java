package azuretriple.primitivecontainer;

/** A queue backed by a growable array containing {@code float}s. */
public class GrowableFloatQueue extends GrowableQueue implements FloatQueue
{
    /**
     * @param work Backing array.
     * @param start Initial start index.
     * @param end Initial end index.
     * @param state {@link GrowableQueue#EMPTY}, {@link GrowableQueue#NORMAL}, or {@link GrowableQueue#FULL}.
     */
    public GrowableFloatQueue(final float[] work,final int start,final int end,final byte state) {super(work,start,end,state);}
    /** Equivalent to {@code GrowableFloatQueue(work,0,0,GrowableQueue.EMPTY)}. */
    public GrowableFloatQueue(final float[] work) {this(work,0,0,EMPTY);}
    /** @param size Size of the backing array. */
    public GrowableFloatQueue(final int size) {this(new float[size]);}
    /** Equivalent to {@code GrowableFloatQueue(1 << 4)}. */
    public GrowableFloatQueue() {this(Growable.DEFAULT_SIZE);}
    /** Creates a copy of the argument. */
    public GrowableFloatQueue(final FloatQueue other) {super((Queue)other);}
    
    @Override public boolean equals(final Object other) {return other instanceof final FloatQueue o && equals(o);}
    @Override public GrowableFloatQueue clone() {return new GrowableFloatQueue(this);}
}