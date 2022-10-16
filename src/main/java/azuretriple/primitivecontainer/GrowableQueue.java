package azuretriple.primitivecontainer;

import static java.lang.System.arraycopy;
import static java.lang.reflect.Array.getLength;

/** An abstract class of queues backed by a growable array. */
@SuppressWarnings("SuspiciousSystemArraycopy")
public abstract class GrowableQueue extends Queue
{
    /** Constant representing this queue's current state. */
    public static final byte EMPTY = 0,NORMAL = 1,FULL = 2;
    
    byte state;
    
    GrowableQueue(final Object arr,final int start,final int end,final byte state)
    {
        super(arr,start,end);
        this.state = state;
        
        assert switch(state)
        {
            case EMPTY,FULL -> start == end % getLength(arr);
            case NORMAL     -> start != end % getLength(arr);
            default         -> false;
        };
    }
    GrowableQueue(final Queue other)
    {
        super(other);
        state = other instanceof final GrowableQueue o
            ? o.state
            : (((FixedQueue)other).empty? EMPTY : (end == getLength(arr)? FULL : NORMAL));
    }
    
    @Override
    public void clear()
    {
        start = end = 0;
        state = EMPTY;
        if(getLength(arr) > Growable.DEFAULT_SIZE) arr = arrInst(arr,Growable.DEFAULT_SIZE);
    }
    @Override
    public int size()
    {
        return switch(state)
        {
            case EMPTY         -> 0;
            case NORMAL        -> start > end? (getLength(arr) - start + end) : (end - start);
            default /* FULL */ -> getLength(arr);
        };
    }
    @Override public boolean empty() {return state == EMPTY;}
    
    @Override
    void push(final Object v)
    {
        final int vl = getLength(v);
        if(vl == 0) return;
        {
            final int ql = getLength(arr);
            final Object nq;
            final int r = size();
            if(ql - r == vl)
            {
                state = FULL;
                nq = arr;
            }
            else
            {
                state = NORMAL;
                final byte n = Growable.nextPow2(vl,r,ql);
                nq = n != 0? arrInst(arr,ql << n) : arr;
            }
            // Always re-order the existing data to avoid needing to check for the case where the
            // input wraps around. This also has the benefit of making future operations more
            // efficient until a series of push/pop operations makes the queue wrap around again.
            end = rearrange(arr,arr = nq,start,end);
            start = 0;
        }
        arraycopy(v,0,arr,end,vl);
        end += vl;
    }
    @Override
    void pushLogic()
    {
        int ql = getLength(arr);
        if(state == FULL)
        {
            end = rearrange(arr,arr = arrInst(arr,ql *= 2),start,end);
            start = 0;
        }
        else end %= ql;
        state = (end + 1) % ql == start? FULL : NORMAL;
    }
    @Override
    public void delete()
    {
        if(++start == end) state = EMPTY;
        else
        {
            // Could throw out-of-bounds, but that would mean a branch on a potential hot path.
            assert state != EMPTY;
            state = NORMAL;
        }
        final int ll = getLength(arr);
        start %= ll;
        if(size() < ll/4)
        {
            final Object n = arrInst(arr,ll/2);
            end = state == EMPTY? 0 : rearrange(arr,n,start,end);
            start = 0;
            arr = n;
        }
    }
    @Override
    Object arrayImpl()
    {
        final Object o = arrInst(arr,size());
        rearrange(arr,o,start,end);
        return o;
    }
    
    @Override public abstract GrowableQueue clone();
}