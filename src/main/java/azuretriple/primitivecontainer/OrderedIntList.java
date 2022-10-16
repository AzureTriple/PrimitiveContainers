package azuretriple.primitivecontainer;

/** A list of {@code int}s in ascending order. */
public class OrderedIntList extends OrderedList<IntList>
{
    /**
     * Creates an ordered int list using the argument to back this data structure. Ensure that
     * the argument is sorted or empty before use.
     */
    public OrderedIntList(final IntList list) {super(list);}
    /** Creates a copy of the argument. */
    public OrderedIntList(final OrderedIntList other) {this(other.data().clone());}
    
    /**
     * @return The index where the argument would be inserted. If the argument already exists in
     *         the list, the returned value is inverted (i.e. the value is negative and can be
     *         retrieved by using the {@code ~} operator).
     */
    public int find(final int v)
    {
        final int[] arr = list.data();
        int a = 0,b = ((List)list).size;
        while(a < b)
        {
            final int m = (a + b - 1) >>> 1;
            final int mv = arr[m];
            if(mv < v) a = m + 1;
            else if(mv > v) b = m;
            else return ~m;
        }
        return a;
    }
    /**
     * Inserts a value into the list.
     * @return The index where the value was inserted. 
     */
    public int add(final int v)
    {
        final int i;
        {
            final int j = find(v);
            i = j < 0? ~j : j;
        }
        list.insert(i,v);
        return i;
    }
    
    @Override public OrderedIntList clone() {return new OrderedIntList(this);}
}