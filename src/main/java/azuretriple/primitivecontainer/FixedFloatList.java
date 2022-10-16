package azuretriple.primitivecontainer;

/** A list backed by a fixed-size array containing {@code float}s. */
public class FixedFloatList extends FixedList implements FloatList
{
    /**
     * @param work Backing array.
     * @param initialSize Initial size of the list.
     */
    public FixedFloatList(final float[] work,final int initialSize) {super(work,initialSize);}
    /** Equivalent to {@code new FixedFloatList(work,0)}. */
    public FixedFloatList(final float[] work) {this(work,0);}
    /** @param size Size of the backing array. */
    public FixedFloatList(final int size) {this(new float[size]);}
    /** Creates a copy of the argument. */
    public FixedFloatList(final FloatList other) {super((List)other);}
    
    @Override public boolean equals(final Object other) {return other instanceof final FloatList o && equals(o);}
    @Override public FixedFloatList clone() {return new FixedFloatList(this);}
}