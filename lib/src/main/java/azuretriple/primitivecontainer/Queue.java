package azuretriple.primitivecontainer;

import static java.lang.System.arraycopy;
import static java.lang.reflect.Array.getLength;

/**
 * An abstract class of containers structured as a queue. Queues are implemented as a circular buffer which
 * is efficient for adding elements to the back and removing elements from the front.
 */
@SuppressWarnings("SuspiciousSystemArraycopy")
public abstract class Queue extends Container
{
    static int rearrange(final Object arr,final int start,final int end)
    {
        if(start == 0) return end;
        final int e;
        if(start < end) arraycopy(arr,start,arr,0,e = end-start);
        else
        {
            final int ll = getLength(arr),el = ll-start;
            e = end + el;
            final int a,b,c;
            if(end > el) {a = start; c = (b = el)^end;}
            else a = b = c = 0;
            final Object o = arrInst(arr,c^end);
            arraycopy(arr,a      , o ,0   ,c^end);
            arraycopy(arr,a^start,arr,b   ,c^el );
            arraycopy( o ,0      ,arr,b^el,c^end);
        }
        return e;
    }
    static int rearrange(final Object src,final Object dst,final int start,final int end)
    {
        if(start == 0)
        {
            arraycopy(src,0,dst,0,end);
            return end;
        }
        final int e;
        if(start < end)
            arraycopy(src,start,dst,0,e = end-start);
        else
        {
            final int ll = getLength(src)-start;
            arraycopy(src,start,dst,0,ll);
            arraycopy(src,0,dst,ll,end);
            e = ll + end;
        }
        return e;
    }
    
    int start,end;
    
    Queue(final Object arr,final int start,final int end)
    {
        super(arr);
        this.start = start;
        this.end = end;
        
        assert 0 <= end && end <= getLength(arr) && 0 <= start && start < getLength(arr);
    }
    Queue(final Queue other)
    {
        super(arrInst(other.arr,getLength(other.arr)));
        start = 0;
        end = rearrange(other.arr,arr,other.start,other.end);
    }
    
    /** Adjusts the argument to this queue's internal indices. */
    public int offset(final int idx) {return (start+idx)%getLength(arr);}
    
    abstract void push(final Object arr);
    abstract void pushLogic();
    public abstract void delete();
    
    @Override public abstract Queue clone();
}