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
    
    /** Adds all elements not present in this set from the argument. */
    public void union(final DoubleSet other)
    {
        final DoubleList a = data.list;
        final double[] b = other.data.list.data();
        final int bl = other.data.list.size();
        int bi = 0;
        for(int ai = 0;ai < a.size() && bi < bl;++ai)
            if(a.data()[ai] >= b[bi])
            {
                if(a.data()[ai] > b[bi]) a.insert(ai,b[bi]);
                ++bi;
            }
        a.add(b,bi,bl-bi);
    }
    /** Removes all elements in this set not present in the argument. */
    public void intersect(final DoubleSet other)
    {
        final double[] a = data.list.data(),b = other.data.list.data();
        final int al = data.list.size(),bl = other.data.list.size();
        int ai = 0;
        {
            final int min = Math.min(al,bl);
            while(ai < min && a[ai] == b[ai]) ++ai;
        }
        int bi = ai,ci = ai;
        while(ai < al && bi < bl)
            if(a[ai] < b[bi]) ++ai;
            else
            {
                if(a[ai] == b[bi]) a[ci++] = a[ai++];
                ++bi;
            }
        ((List)data.list).size = ci;
    }
    /** Removes all elements in this set present in the other set. */
    public void subtract(final DoubleSet other)
    {
        final double[] a = data.list.data(),b = other.data.list.data();
        final int al = data.list.size(),bl = other.data.list.size();
        int ai = 0,bi = 0,ci = 0;
        while(ai < al && bi < bl)
            if(a[ai] == b[bi]) {++ai; ++bi;}
            else if(a[ai] < b[bi]) a[ci++] = a[ai++];
            else ++bi;
        System.arraycopy(a,ai,a,ci,al-ai);
        ((List)data.list).size = ci;
    }
    
    @SuppressWarnings("MethodDoesntCallSuperMethod")
    @Override public DoubleSet clone() {return new DoubleSet(this);}
}