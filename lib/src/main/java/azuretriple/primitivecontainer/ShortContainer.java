package azuretriple.primitivecontainer;

/** A container which holds {@code short}s. */
public interface ShortContainer extends PrimitiveContainer
{
    /** @return The backing array. Changes to this array affect the container. */
    default short[] data() {return (short[])((Container)this).arr;}
    /** @return A copy of all the values in this container in order. */
    default short[] array() {return (short[])((Container)this).arrayImpl();}
    
    @Override ShortContainer clone();
}