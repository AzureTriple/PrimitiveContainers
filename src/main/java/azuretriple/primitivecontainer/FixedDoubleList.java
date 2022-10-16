package azuretriple.primitivecontainer;

/** A list backed by a fixed-size array containing {@code double}s. */
public class FixedDoubleList extends FixedList implements DoubleList
{
    /**
     * @param work Backing array.
     * @param initialSize Initial size of the list.
     */
    public FixedDoubleList(final double[] work,final int initialSize) {super(work,initialSize);}
    /** Equivalent to {@code new FixedDoubleList(work,0)}. */
    public FixedDoubleList(final double[] work) {this(work,0);}
    /** @param size Size of the backing array. */
    public FixedDoubleList(final int size) {this(new double[size]);}
    /** Creates a copy of the argument. */
    public FixedDoubleList(final DoubleList other) {super((List)other);}
    
    @Override public boolean equals(final Object other) {return other instanceof final DoubleList o && equals(o);}
    @Override public FixedDoubleList clone() {return new FixedDoubleList(this);}
}