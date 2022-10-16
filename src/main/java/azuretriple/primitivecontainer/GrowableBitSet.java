package azuretriple.primitivecontainer;

import java.util.Arrays;

import static java.lang.System.arraycopy;

/** A bit set backed by a growable array. */
public class GrowableBitSet extends ArrayBitSet
{
    static int nextHighest(final long[] set,final int start)
    {
        int highest;
        //noinspection StatementWithEmptyBody
        for(highest = start;highest-- != 0 && set[highest] == 0L;);
        return highest;
    }
    
    long[] set;
    int highest;
    
    /**
     * @param work Backing array.
     * @param clear {@code true} iff the set should be cleared.
     */
    public GrowableBitSet(final long[] work,final boolean clear)
    {
        set = work;
        if(clear)
        {
            Arrays.fill(set,0L);
            highest = -1;
        }
        else highest = nextHighest(set,set.length);
    }
    /** Equivalent to {@code new GrowableBitSet(work,true)}. */
    public GrowableBitSet(final long[] work) {this(work,true);}
    /** @param size Minimum number of bits required. */
    public GrowableBitSet(final int size) {this(new long[(size + 63)/64],false);}
    /** Equivalent to {@code new GrowableBitSet(1 << 4)}. */
    public GrowableBitSet() {this(Growable.DEFAULT_SIZE);}
    /** Creates a copy of the argument. */
    public GrowableBitSet(final BitSet other)
    {
        if(other instanceof final ArrayBitSet o)
        {
            arraycopy(o.data(),0,set = new long[o.data().length],0,set.length);
            if(other instanceof final GrowableBitSet o2) highest = o2.highest;
            else highest = nextHighest(set,set.length);
        }
        else
        {
            final MapBitSet o = (MapBitSet)other;
            if(o.keys.data().empty())
            {
                set = new long[Growable.DEFAULT_SIZE];
                highest = -1;
            }
            else
            {
                final int s = o.keys.data().size();
                set = new long[1 << Growable.nextPow2(highest = o.keys.data().get(s-1))];
                final int[] k = o.keys.data().data();
                final long[] v = o.values.data();
                for(int i = 0;i < s;++i) set[k[i]] = v[i];
            }
        }
    }
    
    @Override public long[] data() {return set;}
    
    @Override
    void sizeLogic(final int major)
    {
        if(major >= highest && (highest = major) >= set.length)
        {
            final int l = set.length;
            arraycopy(set,0,set = new long[1 << Growable.nextPow2(highest+1)],0,l);
        }
    }
    
    @Override
    public void unset(final int v)
    {
        super.unset(v);
        if(highest != -1 && set[highest] == 0L)
        {
            final int n = 1 << Growable.nextPow2((highest = nextHighest(set,highest))+1);
            if(n < set.length / 4) arraycopy(set,0,set = new long[n],0,highest+1);
        }
    }
    
    @Override public void clear() {set = new long[1]; highest = -1;}
    
    @Override public GrowableBitSet clone() {return new GrowableBitSet(this);}
}