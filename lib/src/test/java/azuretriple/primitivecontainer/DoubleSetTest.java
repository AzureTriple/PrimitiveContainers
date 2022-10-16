package azuretriple.primitivecontainer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoubleSetTest
{
    @Test
    void testInit()
    {
        final DoubleSet s  = new DoubleSet(new OrderedDoubleList(new FixedDoubleList(1))),
                        s2 = new DoubleSet(s);
        assertEquals(s,s2);
        s.add(1);
        assertNotEquals(s,s2);
    }
    
    @Test
    void testAdd()
    {
        final DoubleSet s = new DoubleSet(new OrderedDoubleList(new FixedDoubleList(1)));
        assertEquals(0,s.add(1));
        assertEquals(-1,s.add(1));
    }
    
    @Test
    void testRemove()
    {
        final DoubleSet s = new DoubleSet(new OrderedDoubleList(new FixedDoubleList(1)));
        assertEquals(-1,s.remove(1));
        s.add(1);
        assertEquals(0,s.remove(1));
    }
    
    @Test
    void testContains()
    {
        final DoubleSet s = new DoubleSet(new OrderedDoubleList(new FixedDoubleList(1)));
        assertFalse(s.contains(1));
        s.add(1);
        assertTrue(s.contains(1));
    }
    
    @Test
    void testClone()
    {
        
        final DoubleSet s  = new DoubleSet(new OrderedDoubleList(new FixedDoubleList(1))),
                        s2 = s.clone();
        assertEquals(s,s2);
        s.add(1);
        assertNotEquals(s,s2);
    }
}