package azuretriple.primitivecontainer;

/** A list backed by a growable array containing {@code short}s. */
public class GrowableShortList extends GrowableList implements ShortList
{
    /**
     * @param work Backing array.
     * @param initialSize Initial size of the list.
     */
    public GrowableShortList(final short[] work,final int initialSize) {super(work,initialSize);}
    /** Equivalent to {@code new GrowableShortList(work,0)}. */
    public GrowableShortList(final short[] work) {this(work,0);}
    /** @param size Size of the backing array. */
    public GrowableShortList(final int size) {this(new short[size]);}
    /** Equivalent to {@code new GrowableShortList(1 << 4)}. */
    public GrowableShortList() {this(Growable.DEFAULT_SIZE);}
    /** Creates a copy of the argument. */
    public GrowableShortList(final ShortList other) {super((List)other);}

    @Override public boolean equals(final Object other) {return other instanceof final ShortList o && equals(o);}
    @Override public GrowableShortList clone() {return new GrowableShortList(this);}
}