package azuretriple.primitivecontainer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoubleContainerTest
{
    @Test
    void testData()
    {
        final FixedDoubleList l = new FixedDoubleList(2);
        l.add(1);
        assertArrayEquals(new double[] {1,0},l.data());
    }
    
    @Test
    void testArray()
    {
        final FixedDoubleList l = new FixedDoubleList(2);
        l.add(1);
        assertArrayEquals(new double[] {1},l.array());
    }
}