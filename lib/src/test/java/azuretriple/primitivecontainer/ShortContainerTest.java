package azuretriple.primitivecontainer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShortContainerTest
{
    @Test
    void testData()
    {
        final FixedShortList l = new FixedShortList(2);
        l.add((short)1);
        assertArrayEquals(new short[] {1,0},l.data());
    }
    
    @Test
    void testArray()
    {
        final FixedShortList l = new FixedShortList(2);
        l.add((short)1);
        assertArrayEquals(new short[] {1},l.array());
    }
}