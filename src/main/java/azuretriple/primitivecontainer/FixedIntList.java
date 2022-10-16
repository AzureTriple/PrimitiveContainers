package azuretriple.primitivecontainer;

/** A list backed by a fixed-size array containing {@code int}s. */
public class FixedIntList extends FixedList implements IntList
{
    /**
     * @param work Backing array.
     * @param initialSize Initial size of the list.
     */
    public FixedIntList(final int[] work,final int initialSize) {super(work,initialSize);}
    /** Equivalent to {@code new FixedIntList(work,0)}. */
    public FixedIntList(final int[] work) {this(work,0);}
    /** @param size Size of the backing array. */
    public FixedIntList(final int size) {this(new int[size]);}
    /** Creates a copy of the argument. */
    public FixedIntList(final IntList other) {super((List)other);}
    
    @Override public boolean equals(final Object other) {return other instanceof final IntList o && equals(o);}
    @Override public FixedIntList clone() {return new FixedIntList(this);}
}