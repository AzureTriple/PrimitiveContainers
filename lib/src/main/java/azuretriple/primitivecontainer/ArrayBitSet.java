package azuretriple.primitivecontainer;

import static azuretriple.primitivecontainer.BitSet.major;
import static azuretriple.primitivecontainer.BitSet.mask;

/** A bit set backed by a {@code long} array. */
public abstract class ArrayBitSet implements BitSet
{
    /** @return The backing array. Changes to this array affect the set. */
    public abstract long[] data();
    abstract void sizeLogic(final int major);
    
    @Override
    public void set(final int v)
    {
        final int major = major(v);
        sizeLogic(major);
        data()[major] |= mask(v);
    }
    @Override
    public void unset(final int v)
    {
        final int major = major(v);
        if(major >= data().length) return;
        data()[major] &= ~mask(v);
    }
    @Override
    public boolean add(final int v)
    {
        final int major = major(v);
        sizeLogic(major);
        return data()[major] != (data()[major] |= mask(v));
    }
    @Override
    public boolean remove(final int v)
    {
        final int major = major(v);
        if(major >= data().length) return false;
        final long mask = mask(v);
        final boolean out = (data()[major] & mask) != 0L;
        data()[major] &= ~mask;
        return out;
    }
    @Override
    public boolean test(final int v)
    {
        final int major = major(v);
        return major < data().length && (data()[major] & mask(v)) != 0L;
    }
    
    @Override
    public boolean equals(final Object obj)
    {
        return obj instanceof final BitSet b && BitSet.equals(this,b);
    }
    
    @Override public abstract ArrayBitSet clone();
}