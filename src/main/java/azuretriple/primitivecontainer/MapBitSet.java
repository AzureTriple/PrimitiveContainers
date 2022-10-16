package azuretriple.primitivecontainer;

import static azuretriple.primitivecontainer.BitSet.major;
import static azuretriple.primitivecontainer.BitSet.mask;

/** A bit set backed by a map structure. */
public class MapBitSet implements BitSet
{
    final OrderedIntList keys;
    final LongList values;
    
    /**
     * @param keys The backing list holding the keys used to index the buckets. Changes to this list affect the set.
     * @param values The backing list holding the buckets. Changes to this list affect the set.
     *
     * @apiNote This function is made public only for performance reasons. Consider using
     *          {@linkplain MapBitSet#MapBitSet(IntToLongMap)} when debugging to ensure that assertions can catch
     *          potential bugs.
     */
    public MapBitSet(final OrderedIntList keys,final LongList values) {this.keys = keys; this.values = values;}
    /** Creates a map bit set from the data in the specified map. */
    public MapBitSet(final IntToLongMap map) {this(map.keys().data(),map.values());}
    /** Equivalent to {@code new MapBitSet(new IntToLongMap(new IntSet(new OrderedIntList(new GrowableIntList())),new GrowableLongList()))}. */
    public MapBitSet() {this(new OrderedIntList(new GrowableIntList()),new GrowableLongList());}
    /** Creates a copy of the argument. */
    public MapBitSet(final BitSet other)
    {
        if(other instanceof final MapBitSet o) {keys = o.keys.clone(); values = o.values.clone();}
        else
        {
            final IntList k = new GrowableIntList();
            values = new GrowableLongList();
            final long[] data = ((ArrayBitSet)other).data();
            final int limit = other instanceof final GrowableBitSet o? o.highest+1 : data.length;
            for(int i = 0;i < limit;++i)
                if(data[i] != 0L)
                {
                    k.add(i);
                    values.add(data[i]);
                }
            keys = new OrderedIntList(k);
        }
    }
    
    /** @return The backing list holding the keys used to index the buckets. Changes to this list affect the set. */
    public OrderedIntList keys() {return keys;}
    /** @return The backing list holding the buckets. Changes to this list affect the set. */
    public LongList values() {return values;}
    /**
     * @return The {@linkplain MapBitSet#keys()} and {@linkplain MapBitSet#values()} in the form of a
     *         {@linkplain IntToLongMap}. Changes to this map affect the set.
     */
    public IntToLongMap asMap() {return new IntToLongMap(new IntSet(keys),values);}
    
    @Override
    public void set(final int v)
    {
        final int major = major(v);
        final long mask = mask(v);
        final int idx = keys.find(major);
        if(idx < 0) values.data()[~idx] |= mask;
        else
        {
            keys.data().insert(idx,major);
            values.insert(idx,mask);
        }
    }
    @Override
    public void unset(final int v)
    {
        final int idx = ~keys.find(major(v));
        if(idx >= 0 && (values.data()[idx] &= ~mask(v)) == 0L)
        {
            keys.data().remove(idx);
            values.remove(idx);
        }
    }
    @Override
    public boolean add(final int v)
    {
        final int major = major(v),idx = keys.find(major);
        if(idx >= 0)
        {
            keys.data().insert(idx,major);
            values.insert(idx,mask(v));
            return true;
        }
        return values.data()[~idx] != (values.data()[~idx] |= mask(v));
    }
    @Override
    public boolean remove(final int v)
    {
        final int idx = ~keys.find(major(v));
        if(idx < 0) return false;
        final long l = values.data()[idx];
        if((values.data()[idx] &= ~mask(v)) == 0L)
        {
            keys.data().remove(idx);
            values.remove(idx);
            return true;
        }
        return values.data()[idx] != l;
    }
    @Override
    public boolean test(final int v)
    {
        final int idx = keys.find(major(v));
        return idx < 0 && (values.data()[~idx] & mask(v)) != 0L;
    }
    @Override public void clear() {keys.data().clear(); values.clear();}
    
    @SuppressWarnings("MethodDoesntCallSuperMethod") @Override public MapBitSet clone() {return new MapBitSet(this);}
    
    @Override
    public boolean equals(final Object obj)
    {
        return obj instanceof final BitSet b && BitSet.equals(this,b);
    }
}