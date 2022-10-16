package azuretriple.primitivecontainer;

/** An interface for containers which hold primitive values. */
public interface PrimitiveContainer extends Cloneable
{
    /** @return The size of this container. */
    int size();
    /** @return {@code true} iff this container is empty. */
    boolean empty();
    /** Removes all values in this container. */
    void clear();
    
    PrimitiveContainer clone();
}