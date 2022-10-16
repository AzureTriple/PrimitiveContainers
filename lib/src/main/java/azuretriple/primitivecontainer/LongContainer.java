package azuretriple.primitivecontainer;

/** A container which holds {@code long}s. */
public interface LongContainer extends PrimitiveContainer
{
    /** @return The backing array. Changes to this array affect the container. */
    default long[] data() {return (long[])((Container)this).arr;}
    /** @return A copy of all the values in this container in order. */
    default long[] array() {return (long[])((Container)this).arrayImpl();}
    
    @Override LongContainer clone();
}