package azuretriple.primitivecontainer;

/** A list backed by a growable array containing {@code byte}s. */
public class GrowableByteList extends GrowableList implements ByteList
{
    /**
     * @param work Backing array.
     * @param initialSize Initial size of the list.
     */
    public GrowableByteList(final byte[] work,final int initialSize) {super(work,initialSize);}
    /** Equivalent to {@code new GrowableByteList(work,0)}. */
    public GrowableByteList(final byte[] work) {this(work,0);}
    /** @param size Size of the backing array. */
    public GrowableByteList(final int size) {this(new byte[size]);}
    /** Equivalent to {@code new GrowableByteList(1 << 4)}. */
    public GrowableByteList() {this(Growable.DEFAULT_SIZE);}
    /** Creates a copy of the argument. */
    public GrowableByteList(final ByteList other) {super((List)other);}

    @Override public boolean equals(final Object other) {return other instanceof final ByteList o && equals(o);}
    @Override public GrowableByteList clone() {return new GrowableByteList(this);}
}