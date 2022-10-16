package azuretriple.primitivecontainer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderedFloatListTest
{
    @Test
    void testInit()
    {
        final FixedFloatList l = new FixedFloatList(new float[] {0,1,2,3},2);
        final OrderedFloatList o = new OrderedFloatList(l);
        assertSame(l,o.data());
        assertEquals(o,new OrderedFloatList(o));
    }
    
    @Test
    void testFind()
    {
        final OrderedFloatList o = new OrderedFloatList(new FixedFloatList(new float[] {0,2,4,6},3));
        assertEquals(1,o.find((float)1));
        assertEquals(~1,o.find((float)2));
    }
    
    @Test
    void testAdd()
    {
        final FixedFloatList l = new FixedFloatList(new float[] {0,2,-1,-1},2);
        final OrderedFloatList o = new OrderedFloatList(l);
        assertEquals(1,o.add((float)1));
        assertArrayEquals(new float[] {0,1,2,-1},l.data());
        assertEquals(2,o.add((float)2));
        assertArrayEquals(new float[] {0,1,2,2},l.data());
    }
    
    @Test
    void testClone()
    {
        final OrderedFloatList o = new OrderedFloatList(new FixedFloatList(new float[] {0,1,2,3},2));
        assertEquals(o,o.clone());
    }
}