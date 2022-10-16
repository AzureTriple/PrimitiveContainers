package azuretriple.primitivecontainer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderedDoubleListTest
{
    @Test
    void testInit()
    {
        final FixedDoubleList l = new FixedDoubleList(new double[] {0,1,2,3},2);
        final OrderedDoubleList o = new OrderedDoubleList(l);
        assertSame(l,o.data());
        assertEquals(o,new OrderedDoubleList(o));
    }
    
    @Test
    void testFind()
    {
        final OrderedDoubleList o = new OrderedDoubleList(new FixedDoubleList(new double[] {0,2,4,6},3));
        assertEquals(1,o.find(1));
        assertEquals(~1,o.find(2));
    }
    
    @Test
    void testAdd()
    {
        final FixedDoubleList l = new FixedDoubleList(new double[] {0,2,-1,-1},2);
        final OrderedDoubleList o = new OrderedDoubleList(l);
        assertEquals(1,o.add(1));
        assertArrayEquals(new double[] {0,1,2,-1},l.data());
        assertEquals(2,o.add(2));
        assertArrayEquals(new double[] {0,1,2,2},l.data());
    }
    
    @Test
    void testClone()
    {
        final OrderedDoubleList o = new OrderedDoubleList(new FixedDoubleList(new double[] {0,1,2,3},2));
        assertEquals(o,o.clone());
    }
}