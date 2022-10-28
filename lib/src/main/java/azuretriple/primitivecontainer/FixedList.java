package azuretriple.primitivecontainer;

import static java.lang.System.arraycopy;
import static java.lang.reflect.Array.getLength;

/** An abstract class of lists backed by a fixed-size array. */
@SuppressWarnings("SuspiciousSystemArraycopy")
public abstract class FixedList extends List
{
    FixedList(final Object arr,final int size) {super(arr,size);}
    FixedList(final List other) {super(other);}
    
    @Override
    void add(final Object v)
    {
        final int vl = getLength(v);
        arraycopy(v,0,arr,size,vl);
        size += vl;
    }
    @Override
    void add(final Object v,final int start,final int length)
    {
        arraycopy(v,start,arr,size,length);
        size += length;
    }
    @Override void addLogic() {}
    @Override
    void insert(final int location,final Object v)
    {
        final int vl = getLength(v);
        arraycopy(arr,location,arr,location+vl,size-location);
        arraycopy(v,0,arr,location,vl);
        size += vl;
    }
    @Override
    void insert(final int location,final Object v,final int start,final int length)
    {
        arraycopy(arr,location,arr,location+length,size-location);
        arraycopy(v,start,arr,location,length);
        size += length;
    }
    @Override void insertLogic(final int location) {arraycopy(arr,location,arr,location+1,(size++)-location);}
    @Override public void remove(final int location) {arraycopy(arr,location+1,arr,location,(--size)-location);}
    
    @Override public abstract FixedList clone();
}