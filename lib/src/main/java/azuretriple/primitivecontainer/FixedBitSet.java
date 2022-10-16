package azuretriple.primitivecontainer;

import java.util.Arrays;

import static java.lang.System.arraycopy;

/** A bit set backed by a fixed-size array. */
public class FixedBitSet extends ArrayBitSet
{
    /** The backing array. */
    public final long[] set;
    
    /**
     * @param work Backing array.
     * @param clear {@code true} iff the set should be cleared.
     */
    public FixedBitSet(final long[] work,final boolean clear)
    {
        set = work;
        if(clear) Arrays.fill(set,0L);
    }
    /** Equivalent to {@code new FixedBitSet(work,true)}. */
    public FixedBitSet(final long[] work) {this(work,true);}
    /** @param size Minimum number of bits required. */
    public FixedBitSet(final int size) {this(new long[(size + 63)/64],false);}
    /** Creates a copy of the argument. */
    public FixedBitSet(final BitSet other)
    {
        if(other instanceof final ArrayBitSet o)
            arraycopy(o.data(),0,set = new long[o.data().length],0,set.length);
        else
        {
            final MapBitSet o = (MapBitSet)other;
            if(o.keys.data().empty()) set = new long[0];
            else
            {
                set = new long[o.keys.data().get(o.keys.data().size()-1)+1];
                final int limit;
                final int[] k;
                {
                    final IntList d = o.keys.data();
                    limit = d.size();
                    k = d.data();
                }
                final long[] v = o.values.data();
                for(int i = 0;i < limit;++i) set[k[i]] = v[i];
            }
        }
    }
    
    @Override public long[] data() {return set;}
    
    @Override void sizeLogic(final int major) {}
    
    @Override public void clear() {Arrays.fill(set,0L);}
    
    @Override public FixedBitSet clone() {return new FixedBitSet(this);}
}