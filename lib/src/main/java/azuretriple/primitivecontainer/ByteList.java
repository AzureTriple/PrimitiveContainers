package azuretriple.primitivecontainer;

/** A list containing {@code byte}s. */
public interface ByteList extends ByteContainer
{
    /** @return {@code true} iff this list contains the same data as the argument. */
    default boolean equals(final ByteList other)
    {
        if(this == other) return true;
        final int s1 = ((List)this).size;
        if(s1 != ((List)other).size) return false;
        final byte[] d1 = data(),d2 = other.data();
        for(int i = 0;i < s1;++i) if(d1[i] != d2[i]) return false;
        return true;
    }
    
    /** @return The value at the location specified by the argument. */
    default byte get(final int location)
    {
        // Could throw out-of-bounds, but that would mean a branch on a potential hot path.
        assert location < ((List)this).size;
    
        return data()[location];
    }
    /** Sets the value at the specified location to the second argument. */
    default void set(final int location,final byte v)
    {
        // Could throw out-of-bounds, but that would mean a branch on a potential hot path.
        assert location < ((List)this).size;
        
        data()[location] = v;
    }
    
    /** Appends the values in the argument to the end of the list. */
    default void add(final byte[] v) {((List)this).add(v);}
    /**
     * Appends the values in the argument to the end of the list.
     *
     * @param start Starting position in the array.
     * @param length Number of elements to copy.
     */
    default void add(final byte[] v,final int start,final int length) {((List)this).add(v,start,length);}
    /** Appends the argument to the end of the list. */
    default void add(final byte v) {((List)this).addLogic(); data()[((List)this).size++] = v;}
    /** Inserts the specified values immediately before the specified location. */
    default void insert(final int location,final byte[] v) {((List)this).insert(location,v);}
    /**
     * Inserts the specified values immediately before the specified location.
     *
     * @param start Starting position in the array.
     * @param length Number of elements to copy.
     */
    default void insert(final int location,final byte[] v,final int start,final int length)
    {
        ((List)this).insert(location,v,start,length);
    }
    /** Inserts the specified value immediately before the specified location. */
    default void insert(final int location,final byte v) {((List)this).insertLogic(location); data()[location] = v;}
    /** Removes the last value from the list. */
    void delete();
    /** Removes the value at the specified location from the list. */
    void remove(final int location);
    /** Removes and returns the last value from the list. */
    default byte pop() {delete(); return data()[((List)this).size];}
    /** Removes and returns the value at the specified location from the list. */
    default byte extract(final int location) {final byte o = data()[location]; remove(location); return o;}
    
    @Override ByteList clone();
}