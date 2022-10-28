package azuretriple.primitivecontainer;

import static java.lang.System.arraycopy;
import static java.lang.reflect.Array.getLength;

/** An abstract class of lists backed by a growable array. */
@SuppressWarnings("SuspiciousSystemArraycopy")
public abstract class GrowableList extends List
{
    GrowableList(final Object arr,final int size) {super(arr,size);}
    GrowableList(final List other) {super(other);}
    
    @Override
    public void clear()
    {
        super.clear();
        if(getLength(arr) > Growable.DEFAULT_SIZE) arr = arrInst(arr,Growable.DEFAULT_SIZE);
    }
    
    @Override
    void add(final Object v)
    {
        final int vl = getLength(v);
        if(vl == 0) return;
        {
            final int ll = getLength(arr);
            final byte n = Growable.nextPow2(vl,size,ll);
            if(n != (byte)0) arraycopy(arr,0,arr = arrInst(arr,ll << n),0,size);
        }
        arraycopy(v,0,arr,size,vl);
        size += vl;
    }
    @Override
    void add(final Object v,final int start,final int length)
    {
        if(length == 0) return;
        {
            final int ll = getLength(arr);
            final byte n = Growable.nextPow2(length,size,ll);
            if(n != (byte)0) arraycopy(arr,0,arr = arrInst(arr,ll << n),0,size);
        }
        arraycopy(v,start,arr,size,length);
        size += length;
    }
    @Override
    void addLogic()
    {
        final int ll = getLength(arr);
        if(size == ll) arraycopy(arr,0,arr = arrInst(arr,ll*2),0,ll);
    }
    @Override
    void insert(final int location,final Object v)
    {
        final int vl = getLength(v);
        if(vl == 0) return;
        final int ll = getLength(arr);
        {
            final byte n = Growable.nextPow2(vl,size,ll);
            if(n != (byte)0)
            {
                final Object next = arrInst(arr,ll << n);
                arraycopy(arr,0,next,0,location);
                arraycopy(arr,location,arr = next,location+vl,size-location);
            }
            else
                arraycopy(arr,location,arr,location+vl,size-location);
        }
        arraycopy(v,0,arr,location,vl);
        size += vl;
    }
    @Override
    void insert(final int location,final Object v,final int start,final int length)
    {
        if(length == 0) return;
        final int ll = getLength(arr);
        {
            final byte n = Growable.nextPow2(length,size,ll);
            if(n != (byte)0)
            {
                final Object next = arrInst(arr,ll << n);
                arraycopy(arr,0,next,0,location);
                arraycopy(arr,location,arr = next,location+length,size-location);
            }
            else
                arraycopy(arr,location,arr,location+length,size-location);
        }
        arraycopy(v,start,arr,location,length);
        size += length;
    }
    @Override
    void insertLogic(final int location)
    {
        final int ll = getLength(arr);
        final Object n;
        if(size == ll) arraycopy(arr,0,n = arrInst(arr,ll*2),0,ll);
        else n = arr;
        arraycopy(arr,location,arr = n,location+1,(size++)-location);
    }
    @Override
    public void delete()
    {
        super.delete();
        final int ll = getLength(arr);
        if(size < ll/4) arraycopy(arr,0,arr = arrInst(arr,ll/2),0,size);
    }
    @Override
    public void remove(final int location)
    {
        final int ll = getLength(arr);
        final Object n;
        if(--size < ll/4) arraycopy(arr,0,n = arrInst(arr,ll/2),0,location);
        else n = arr;
        arraycopy(arr,location+1,arr = n,location,size-location);
    }
    
    @Override public abstract GrowableList clone();
}