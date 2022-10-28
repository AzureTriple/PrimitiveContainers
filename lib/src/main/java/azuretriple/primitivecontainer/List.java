package azuretriple.primitivecontainer;

import static java.lang.System.arraycopy;
import static java.lang.reflect.Array.getLength;

/**
 * An abstract class of containers structured as a list. Lists are implemented as a backing array and a size
 * which is efficient for sequential element access and operations on the end of the list. Insertions and
 * deletions on all elements excepting the last require a bulk copy.
 */
@SuppressWarnings("SuspiciousSystemArraycopy")
public abstract class List extends Container
{
    /** The current size of this list. */
    public int size;
    
    List(final Object arr,final int size) {super(arr); this.size = size; assert getLength(arr) >= size;}
    List(final List other)
    {
        super(arrInst(other.arr,getLength(other.arr)));
        arraycopy(other.arr,0,arr,0,size = other.size);
    }
    
    @Override public boolean empty() {return size == 0;}
    @Override public int size() {return size;}
    @Override public void clear() {size = 0;}
    
    abstract void add(final Object v);
    abstract void add(final Object v,final int start,final int length);
    abstract void addLogic();
    abstract void insert(final int location,final Object v);
    abstract void insert(final int location,final Object v,final int start,final int length);
    abstract void insertLogic(final int location);
    public void delete() {assert size > 0; --size;}
    public abstract void remove(final int location);
    
    @Override
    Object arrayImpl()
    {
        final Object o = arrInst(arr,size);
        arraycopy(arr,0,o,0,size);
        return o;
    }
    
    @Override public abstract List clone();
}