package azuretriple.primitivecontainer;

/** A list backed by a growable array containing {@code float}s. */
public class GrowableFloatList extends GrowableList implements FloatList
{
    /**
     * @param work Backing array.
     * @param initialSize Initial size of the list.
     */
    public GrowableFloatList(final float[] work,final int initialSize) {super(work,initialSize);}
    /** Equivalent to {@code new GrowableFloatList(work,0)}. */
    public GrowableFloatList(final float[] work) {this(work,0);}
    /** @param size Size of the backing array. */
    public GrowableFloatList(final int size) {this(new float[size]);}
    /** Equivalent to {@code new GrowableFloatList(1 << 4)}. */
    public GrowableFloatList() {this(Growable.DEFAULT_SIZE);}
    /** Creates a copy of the argument. */
    public GrowableFloatList(final FloatList other) {super((List)other);}

    @Override public boolean equals(final Object other) {return other instanceof final FloatList o && equals(o);}
    @Override public GrowableFloatList clone() {return new GrowableFloatList(this);}
}