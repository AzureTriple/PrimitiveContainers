package azuretriple.primitivecontainer;

/** A queue backed by a growable array containing {@code int}s. */
public class GrowableIntQueue extends GrowableQueue implements IntQueue
{
    /**
     * @param work Backing array.
     * @param start Initial start index.
     * @param end Initial end index.
     * @param state {@link GrowableQueue#EMPTY}, {@link GrowableQueue#NORMAL}, or {@link GrowableQueue#FULL}.
     */
    public GrowableIntQueue(final int[] work,final int start,final int end,final byte state) {super(work,start,end,state);}
    /** Equivalent to {@code GrowableIntQueue(work,0,0,GrowableQueue.EMPTY)}. */
    public GrowableIntQueue(final int[] work) {this(work,0,0,EMPTY);}
    /** @param size Size of the backing array. */
    public GrowableIntQueue(final int size) {this(new int[size]);}
    /** Equivalent to {@code GrowableIntQueue(1 << 4)}. */
    public GrowableIntQueue() {this(Growable.DEFAULT_SIZE);}
    /** Creates a copy of the argument. */
    public GrowableIntQueue(final IntQueue other) {super((Queue)other);}
    
    @Override public boolean equals(final Object other) {return other instanceof final IntQueue o && equals(o);}
    @Override public GrowableIntQueue clone() {return new GrowableIntQueue(this);}
}