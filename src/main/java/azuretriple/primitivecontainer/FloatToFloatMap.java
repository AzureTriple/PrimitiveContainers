package azuretriple.primitivecontainer;

/** A data structure which maps {@code float}s to {@code float}s. */
public record FloatToFloatMap(FloatSet keys,FloatList values) implements Cloneable
{
    public FloatToFloatMap {assert keys.data().list.size() == values.size();}
    /** Creates a copy of the argument. */
    @SuppressWarnings("CopyConstructorMissesField")
    public FloatToFloatMap(final FloatToFloatMap other) {this(new FloatSet(other.keys),other.values.clone());}

    /** Inserts a key value pair. This method does not check for duplicate keys. */
    public void insert(final float key,final float value) {values.insert(keys.add(key),value);}
    /** Maps the key to the value, overwriting the value at the previous location. */
    public void put(final float key,final float value)
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
    public boolean add(final float key,final float value)
    {
        final int i = keys.data().find(key);
        if(i < 0)
        {
            final float v = values.data()[~i];
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
    public float get(final float key) {return values.get(~keys.data().find(key));}
    /** Removes the value mapped to the specified key. This method does not check if the key exists. */
    public void delete(final float key) {values.remove(keys.remove(key));}
    /**
     * Removes the value at the specified key.
     * @return {@code true} iff the map changed as a result of calling this method.
     */
    public boolean remove(final float key)
    {
        final int i = keys.remove(key);
        if(i < 0) return false;
        values.remove(i);
        return true;
    }
    /** Removes and returns the value at the specified key. This method does not check if the key exists. */
    public float extract(final float key) {return values.extract(keys.remove(key));}

    @SuppressWarnings("MethodDoesntCallSuperMethod")
    @Override public FloatToFloatMap clone() {return new FloatToFloatMap(this);}
}