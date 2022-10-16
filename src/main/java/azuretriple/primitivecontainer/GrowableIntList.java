package azuretriple.primitivecontainer;

/** A list backed by a growable array containing {@code int}s. */
public class GrowableIntList extends GrowableList implements IntList
{
    /**
     * @param work Backing array.
     * @param initialSize Initial size of the list.
     */
    public GrowableIntList(final int[] work,final int initialSize) {super(work,initialSize);}
    /** Equivalent to {@code new GrowableIntList(work,0)}. */
    public GrowableIntList(final int[] work) {this(work,0);}
    /** @param size Size of the backing array. */
    public GrowableIntList(final int size) {this(new int[size]);}
    /** Equivalent to {@code new GrowableIntList(1 << 4)}. */
    public GrowableIntList() {this(Growable.DEFAULT_SIZE);}
    /** Creates a copy of the argument. */
    public GrowableIntList(final IntList other) {super((List)other);}

    @Override public boolean equals(final Object other) {return other instanceof final IntList o && equals(o);}
    @Override public GrowableIntList clone() {return new GrowableIntList(this);}
}