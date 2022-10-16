package azuretriple.primitivecontainer;

/** A container which holds {@code float}s. */
public interface FloatContainer extends PrimitiveContainer
{
    /** @return The backing array. Changes to this array affect the container. */
    default float[] data() {return (float[])((Container)this).arr;}
    /** @return A copy of all the values in this container in order. */
    default float[] array() {return (float[])((Container)this).arrayImpl();}
    
    @Override FloatContainer clone();
}