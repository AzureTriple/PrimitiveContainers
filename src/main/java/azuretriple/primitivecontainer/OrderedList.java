package azuretriple.primitivecontainer;

/** An abstract class of lists sorted in ascending order. */
public abstract class OrderedList<L> implements Cloneable
{
    final L list;
    
    OrderedList(final L list) {this.list = list;}
    
    /** @return The backing list. Changes to this list affect the ordered list. */
    public L data() {return list;}
    
    @Override public abstract OrderedList<L> clone();
    
    @Override
    public boolean equals(final Object other)
    {
        return this == other || (other instanceof final OrderedList<?> o && list.equals(o.list));
    }
}