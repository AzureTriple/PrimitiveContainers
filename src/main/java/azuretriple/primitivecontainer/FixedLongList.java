package azuretriple.primitivecontainer;

/** A list backed by a fixed-size array containing {@code long}s. */
public class FixedLongList extends FixedList implements LongList
{
    /**
     * @param work Backing array.
     * @param initialSize Initial size of the list.
     */
    public FixedLongList(final long[] work,final int initialSize) {super(work,initialSize);}
    /** Equivalent to {@code new FixedLongList(work,0)}. */
    public FixedLongList(final long[] work) {this(work,0);}
    /** @param size Size of the backing array. */
    public FixedLongList(final int size) {this(new long[size]);}
    /** Creates a copy of the argument. */
    public FixedLongList(final LongList other) {super((List)other);}
    
    @Override public boolean equals(final Object other) {return other instanceof final LongList o && equals(o);}
    @Override public FixedLongList clone() {return new FixedLongList(this);}
}