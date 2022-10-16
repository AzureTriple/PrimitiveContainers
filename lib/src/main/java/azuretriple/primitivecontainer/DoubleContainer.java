package azuretriple.primitivecontainer;

/** A container which holds {@code double}s. */
public interface DoubleContainer extends PrimitiveContainer
{
    /** @return The backing array. Changes to this array affect the container. */
    default double[] data() {return (double[])((Container)this).arr;}
    /** @return A copy of all the values in this container in order. */
    default double[] array() {return (double[])((Container)this).arrayImpl();}
    
    @Override DoubleContainer clone();
}