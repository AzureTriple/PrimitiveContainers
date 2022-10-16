package azuretriple.primitivecontainer;

/** A data structure which maps {@code long}s to {@code byte}s. */
public record LongToByteMap(LongSet keys,ByteList values) implements Cloneable
{
    public LongToByteMap {assert keys.data().list.size() == values.size();}
    /** Creates a copy of the argument. */
    @SuppressWarnings("CopyConstructorMissesField")
    public LongToByteMap(final LongToByteMap other) {this(new LongSet(other.keys),other.values.clone());}

    /** Inserts a key value pair. This method does not check for duplicate keys. */
    public void insert(final long key,final byte value) {values.insert(keys.add(key),value);}
    /** Maps the key to the value, overwriting the value at the previous location. */
    public void put(final long key,final byte value)
    {
        final int i = keys.data().find(key);
        if(i < 0) values.data()[~i] = value;
        else
        {
            keys.data().data().insert(i,key);
            values.insert(i,value);
        }
    }
    /**
     * Maps the key to the value, overwriting the value at the previous location.
     * @return {@code true} iff the map changed as a result of calling this method.
     */
    public boolean add(final long key,final byte value)
    {
        final int i = keys.data().find(key);
        if(i < 0)
        {
            final byte v = values.data()[~i];
            if(v != value)
            {
                values.data()[~i] = value;
                return true;
            }
            return false;
        }
        keys.data().data().insert(i,key);
        values.insert(i,value);
        return true;
    }
    /** @return The value mapped to the key. This method does not check if the key exists. */
    public byte get(final long key) {return values.get(~keys.data().find(key));}
    /** Removes the value mapped to the specified key. This method does not check if the key exists. */
    public void delete(final long key) {values.remove(keys.remove(key));}
    /**
     * Removes the value at the specified key.
     * @return {@code true} iff the map changed as a result of calling this method.
     */
    public boolean remove(final long key)
    {
        final int i = keys.remove(key);
        if(i < 0) return false;
        values.remove(i);
        return true;
    }
    /** Removes and returns the value at the specified key. This method does not check if the key exists. */
    public byte extract(final long key) {return values.extract(keys.remove(key));}

    @SuppressWarnings("MethodDoesntCallSuperMethod")
    @Override public LongToByteMap clone() {return new LongToByteMap(this);}
}