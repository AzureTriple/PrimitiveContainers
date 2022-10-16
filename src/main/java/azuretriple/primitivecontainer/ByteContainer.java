package azuretriple.primitivecontainer;

/** A container which holds {@code byte}s. */
public interface ByteContainer extends PrimitiveContainer
{
    /** @return The backing array. Changes to this array affect the container. */
    default byte[] data() {return (byte[])((Container)this).arr;}
    /** @return A copy of all the values in this container in order. */
    default byte[] array() {return (byte[])((Container)this).arrayImpl();}
    
    @Override ByteContainer clone();
}