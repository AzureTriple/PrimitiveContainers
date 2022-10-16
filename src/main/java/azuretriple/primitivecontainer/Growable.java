package azuretriple.primitivecontainer;

final class Growable
{
    private Growable() {}
    
    static final int DEFAULT_SIZE = 1 << 4;
    
    /** @return The next power of two >= {@code m}. */
    static byte nextPow2(final int m)
    {
        final byte pow2 = (byte)((byte)31 - (byte)Integer.numberOfLeadingZeros(m));
        return pow2 != (byte)Integer.numberOfTrailingZeros(m)? (byte)(pow2 + (byte)1) : pow2;
    }
    /**
     * @param inSize Size of the input.
     * @param currentSize Size of the data structure.
     * @param arrLength Length of the backing array
     * @return The exponent of the minimum power of two multiples of {@code arrLength} to accommodate
     *         {@code inSize} more elements (i.e. if the returned value {@code r} is greater than
     *         {@code 0}, a new array should be created with size {@code (arrLength << r)}).
     */
    static byte nextPow2(final int inSize,final int currentSize,final int arrLength)
    {
        // (inSize == 0) indicates a flaw in the calling algorithm; it should have returned before
        // this function was called.
        assert inSize > 0 && currentSize >= 0 && arrLength > 0;
        // Return the exponent of the next power of two >= 'm' where
        // 'm' = # of 'arrLength' arrays needed to satisfy the required size.
        return nextPow2((inSize + currentSize + arrLength - 1) / arrLength);
    }
}