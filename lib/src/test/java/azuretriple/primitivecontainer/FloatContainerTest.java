package azuretriple.primitivecontainer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FloatContainerTest
{
    @Test
    void testData()
    {
        final FixedFloatList l = new FixedFloatList(2);
        l.add((float)1);
        assertArrayEquals(new float[] {1,0},l.data());
    }
    
    @Test
    void testArray()
    {
        final FixedFloatList l = new FixedFloatList(2);
        l.add((float)1);
        assertArrayEquals(new float[] {1},l.array());
    }
}