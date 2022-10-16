package azuretriple.primitivecontainer;

/** A set which stores bit-packed boolean values. */
public interface BitSet extends Cloneable
{
    static int major(final int v) {return v >>> 6;}
    static long mask(final int v) {return 1L << (v & ((1 << 6) - 1));}
    
    static boolean equals(final BitSet a,final BitSet b)
    {
        if(a == b) return true;
        final boolean f1 = a instanceof ArrayBitSet,
                      f2 = b instanceof ArrayBitSet;
        if(f1 && f2) // Both sets are backed by an array.
        {
            final boolean g1 = a instanceof GrowableBitSet,
                          g2 = b instanceof GrowableBitSet;
            // The logic can be simplified for two growable bit sets since their exact sizes are tracked.
            if(g1 && g2)
            {
                final GrowableBitSet ga = (GrowableBitSet)a,
                                     gb = (GrowableBitSet)b;
                // If the index of the highest non-zero values of both sets differ, then the sets are
                // obviously not equal.
                if(ga.highest != gb.highest) return false;
                final long[] da = ga.set,db = gb.set;
                // Iterate through both sets, returning false if any values differ.
                for(int i = 0;i <= ga.highest;++i) if(da[i] != db[i]) return false;
                return true;
            }
            // Get the data and lengths such that (||*a|| < ||*b||).
            final int la,lb;
            final long[] da,db;
            {
                final ArrayBitSet a1 = (ArrayBitSet)a,
                                  b1 = (ArrayBitSet)b;
                final int al = g1? ((GrowableBitSet)a1).highest + 1 : a1.data().length,
                          bl = g2? ((GrowableBitSet)b1).highest + 1 : b1.data().length;
                if(al < bl)
                {
                    // If the larger set is growable, that guarantees there is a value outside the smaller
                    // set since the growable set tracks its size to the last value.
                    if(g2) return false;
                    la = al;
                    lb = bl;
                    da = a1.data();
                    db = b1.data();
                }
                else
                {
                    // ^^^ ditto
                    if(g1 && al != bl) return false;
                    la = bl;
                    lb = al;
                    da = b1.data();
                    db = a1.data();
                }
            }
            int bi = 0;
            // Iterate through the smaller set, returning false if the corresponding value in the larger
            // set does not match.
            for(int ai = 0;ai < la;++ai,++bi) if(da[ai] != db[bi]) return false;
            // Iterate through the remainder of the larger set, returning false if any values are non-zero.
            while(bi < lb) if(db[bi++] != 0L) return false;
            return true;
        }
        if(f1 == f2) // Both sets are backed by a map.
        {
            final MapBitSet ma = (MapBitSet)a,mb = (MapBitSet)b;
            return ma.keys.equals(mb.keys) && ma.values.equals(mb.values);
        }
        final ArrayBitSet sa;
        final MapBitSet sb;
        if(f1)
        {
            sa = (ArrayBitSet)a;
            sb = (MapBitSet)b;
        }
        else
        {
            sa = (ArrayBitSet)b;
            sb = (MapBitSet)a;
        }
        final IntList k = sb.keys.data();
        final long[] data = sa.data();
        final int al = sa instanceof final GrowableBitSet g? g.highest+1 : data.length,
                  bl = k.size();
        // If 'sb' is empty, ensure that 'sa' is too.
        if(bl == 0) {for(int i = 0;i < al;++i) if(data[i] != 0L) return false;}
        else
        {
            final int[] kd = k.data();
            // There is guaranteed to be at least one value outside 'sa' if size of its array is less than the
            // last key in 'sb'.
            if(al < kd[bl-1]+1) return false;
            final long[] v = sb.values.data();
            int ai = 0;
            for(int bi = 0;bi < bl;++bi)
            {
                // Iterate through all values in 'sa' between the previous and current key of 'sb', returning if
                // any are non-zero.
                while(ai < kd[bi]) if(data[ai++] != 0L) return false;
                // The value at the current key in both sets must match.
                if(data[ai++] != v[bi]) return false;
            }
            // All remaining values in 'sa' must be zero.
            while(ai < al) if(data[ai++] != 0L) return false;
        }
        return true;
    }
    
    /** Sets the bit at the position specified by the argument. */
    void set(final int v);
    /** Clears the bit at the position specified by the argument. */
    void unset(final int v);
    /**
     * Sets the bit at the position specified by the argument.
     * @return {@code true} iff the set changed as a result of calling this function.
     */
    boolean add(final int v);
    /**
     * Clears the bit at the position specified by the argument.
     * @return {@code true} iff the set changed as a result of calling this function.
     */
    boolean remove(final int v);
    /** @return {@code true} iff the bit at the position specified by the argument is set. */
    boolean test(final int v);
    /** Clears all bits in this set. */
    void clear();
    
    BitSet clone();
}