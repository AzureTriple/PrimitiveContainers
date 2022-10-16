package azuretriple.primitivecontainer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LongContainerTest
{
    @Test
    void testData()
    {
        final FixedLongList l = new FixedLongList(2);
        l.add(1);
        assertArrayEquals(new long[] {1,0},l.data());
    }
    
    @Test
    void testArray()
    {
        final FixedLongList l = new FixedLongList(2);
        l.add(1);
        assertArrayEquals(new long[] {1},l.array());
    }
}