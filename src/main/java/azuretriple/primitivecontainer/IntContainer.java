package azuretriple.primitivecontainer;

/** A container which holds {@code int}s. */
public interface IntContainer extends PrimitiveContainer
{
    /** @return The backing array. Changes to this array affect the container. */
    default int[] data() {return (int[])((Container)this).arr;}
    /** @return A copy of all the values in this container in order. */
    default int[] array() {return (int[])((Container)this).arrayImpl();}
    
    @Override IntContainer clone();
}