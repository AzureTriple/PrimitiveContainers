package azuretriple.primitivecontainer;

/** A set containing {@code double}s. */
public record DoubleSet(OrderedDoubleList data) implements Cloneable
{
    /** Creates a copy of the argument. */
    @SuppressWarnings("CopyConstructorMissesField")
    public DoubleSet(final DoubleSet other) {this(new OrderedDoubleList(other.data));}
    
    /**
     * Adds the value to the set.
     * @return The index in the backing list where the value was inserted, or the inverted
     *         index of the existing element if it already exists in the set.
     */
    public int add(final double v)
    {
        final int i = data.find(v);
        if(i >= 0) data.list.insert(i,v);
        return i;
    }
    /**
     * Removes the value from the set.
     * @return The index in the backing list where the value was removed, or the inverted
     *         index where the element would have been removed if it was not in the set.
     */
    public int remove(final double v)
    {
        final int i = ~data.find(v);
        if(i >= 0) data.list.remove(i);
        return i;
    }
    /** @return {@code true} iff the value exists in the set. */
    public boolean contains(final double v) {return data.find(v) < 0;}
    
    @SuppressWarnings("MethodDoesntCallSuperMethod")
    @Override public DoubleSet clone() {return new DoubleSet(this);}
}