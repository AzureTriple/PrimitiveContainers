package azuretriple.primitivecontainer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntContainerTest
{
    @Test
    void testData()
    {
        final FixedIntList l = new FixedIntList(2);
        l.add(1);
        assertArrayEquals(new int[] {1,0},l.data());
    }
    
    @Test
    void testArray()
    {
        final FixedIntList l = new FixedIntList(2);
        l.add(1);
        assertArrayEquals(new int[] {1},l.array());
    }
}