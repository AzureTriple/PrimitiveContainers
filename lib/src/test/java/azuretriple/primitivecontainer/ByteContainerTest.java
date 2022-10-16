package azuretriple.primitivecontainer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ByteContainerTest
{
    @Test
    void testData()
    {
        final FixedByteList l = new FixedByteList(2);
        l.add((byte)1);
        assertArrayEquals(new byte[] {1,0},l.data());
    }
    
    @Test
    void testArray()
    {
        final FixedByteList l = new FixedByteList(2);
        l.add((byte)1);
        assertArrayEquals(new byte[] {1},l.array());
    }
}