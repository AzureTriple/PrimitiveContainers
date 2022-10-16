package azuretriple.primitivecontainer;

/** A list backed by a fixed-size array containing {@code short}s. */
public class FixedShortList extends FixedList implements ShortList
{
    /**
     * @param work Backing array.
     * @param initialSize Initial size of the list.
     */
    public FixedShortList(final short[] work,final int initialSize) {super(work,initialSize);}
    /** Equivalent to {@code new FixedShortList(work,0)}. */
    public FixedShortList(final short[] work) {this(work,0);}
    /** @param size Size of the backing array. */
    public FixedShortList(final int size) {this(new short[size]);}
    /** Creates a copy of the argument. */
    public FixedShortList(final ShortList other) {super((List)other);}
    
    @Override public boolean equals(final Object other) {return other instanceof final ShortList o && equals(o);}
    @Override public FixedShortList clone() {return new FixedShortList(this);}
}