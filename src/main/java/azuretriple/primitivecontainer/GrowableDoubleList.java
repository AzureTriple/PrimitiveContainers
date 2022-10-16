package azuretriple.primitivecontainer;

/** A list backed by a growable array containing {@code double}s. */
public class GrowableDoubleList extends GrowableList implements DoubleList
{
    /**
     * @param work Backing array.
     * @param initialSize Initial size of the list.
     */
    public GrowableDoubleList(final double[] work,final int initialSize) {super(work,initialSize);}
    /** Equivalent to {@code new GrowableDoubleList(work,0)}. */
    public GrowableDoubleList(final double[] work) {this(work,0);}
    /** @param size Size of the backing array. */
    public GrowableDoubleList(final int size) {this(new double[size]);}
    /** Equivalent to {@code new GrowableDoubleList(1 << 4)}. */
    public GrowableDoubleList() {this(Growable.DEFAULT_SIZE);}
    /** Creates a copy of the argument. */
    public GrowableDoubleList(final DoubleList other) {super((List)other);}

    @Override public boolean equals(final Object other) {return other instanceof final DoubleList o && equals(o);}
    @Override public GrowableDoubleList clone() {return new GrowableDoubleList(this);}
}