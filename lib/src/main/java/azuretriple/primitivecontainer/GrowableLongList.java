package azuretriple.primitivecontainer;

/** A list backed by a growable array containing {@code long}s. */
public class GrowableLongList extends GrowableList implements LongList
{
    /**
     * @param work Backing array.
     * @param initialSize Initial size of the list.
     */
    public GrowableLongList(final long[] work,final int initialSize) {super(work,initialSize);}
    /** Equivalent to {@code new GrowableLongList(work,0)}. */
    public GrowableLongList(final long[] work) {this(work,0);}
    /** @param size Size of the backing array. */
    public GrowableLongList(final int size) {this(new long[size]);}
    /** Equivalent to {@code new GrowableLongList(1 << 4)}. */
    public GrowableLongList() {this(Growable.DEFAULT_SIZE);}
    /** Creates a copy of the argument. */
    public GrowableLongList(final LongList other) {super((List)other);}

    @Override public boolean equals(final Object other) {return other instanceof final LongList o && equals(o);}
    @Override public GrowableLongList clone() {return new GrowableLongList(this);}
}