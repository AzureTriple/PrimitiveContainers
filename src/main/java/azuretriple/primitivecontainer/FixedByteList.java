package azuretriple.primitivecontainer;

/** A list backed by a fixed-size array containing {@code byte}s. */
public class FixedByteList extends FixedList implements ByteList
{
    /**
     * @param work Backing array.
     * @param initialSize Initial size of the list.
     */
    public FixedByteList(final byte[] work,final int initialSize) {super(work,initialSize);}
    /** Equivalent to {@code new FixedByteList(work,0)}. */
    public FixedByteList(final byte[] work) {this(work,0);}
    /** @param size Size of the backing array. */
    public FixedByteList(final int size) {this(new byte[size]);}
    /** Creates a copy of the argument. */
    public FixedByteList(final ByteList other) {super((List)other);}
    
    @Override public boolean equals(final Object other) {return other instanceof final ByteList o && equals(o);}
    @Override public FixedByteList clone() {return new FixedByteList(this);}
}