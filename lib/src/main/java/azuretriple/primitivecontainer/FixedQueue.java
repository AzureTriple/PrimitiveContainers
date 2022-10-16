package azuretriple.primitivecontainer;

import static java.lang.System.arraycopy;
import static java.lang.reflect.Array.getLength;

/** An abstract class of queues backed by a fixed-size array. */
@SuppressWarnings("SuspiciousSystemArraycopy")
public abstract class FixedQueue extends Queue
{
    boolean empty;
    
    FixedQueue(final Object arr,final int start,final int end,final boolean empty)
    {
        super(arr,start,end);
        this.empty = empty;
        
        assert !empty || (start == end);
    }
    FixedQueue(final Queue other)
    {
        super(other);
        empty = other.empty();
    }
    
    @Override public void clear() {start = end = 0; empty = true;}
    @Override public int size() {return !empty? start < end? end - start : (getLength(arr) - start + end) : 0;}
    @Override public boolean empty() {return empty;}
    
    @Override
    void push(final Object v)
    {
        final int vl = getLength(v);
        if(vl == 0) return;
        arraycopy(v,0,arr,end = empty? 0 : rearrange(arr,start,end),vl);
        start = 0;
        end += vl;
        empty = false;
    }
    @Override
    void pushLogic()
    {
        end %= getLength(arr);
        // Could throw out-of-bounds, but that would mean a branch on a potential hot path.
        assert empty || end != start;
        empty = false;
    }
    @Override
    public void delete()
    {
        empty = ++start == end;
        start %= getLength(arr);
    }
    @Override
    Object arrayImpl()
    {
        final Object o = arrInst(arr,size());
        rearrange(arr,o,start,end);
        return o;
    }
    
    @Override public abstract FixedQueue clone();
}