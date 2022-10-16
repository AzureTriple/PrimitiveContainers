package azuretriple.primitivecontainer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FloatSetTest
{
    @Test
    void testInit()
    {
        final FloatSet s  = new FloatSet(new OrderedFloatList(new FixedFloatList(1))),
                       s2 = new FloatSet(s);
        assertEquals(s,s2);
        s.add((float)1);
        assertNotEquals(s,s2);
    }
    
    @Test
    void testAdd()
    {
        final FloatSet s = new FloatSet(new OrderedFloatList(new FixedFloatList(1)));
        assertEquals(0,s.add((float)1));
        assertEquals(-1,s.add((float)1));
    }
    
    @Test
    void testRemove()
    {
        final FloatSet s = new FloatSet(new OrderedFloatList(new FixedFloatList(1)));
        assertEquals(-1,s.remove((float)1));
        s.add((float)1);
        assertEquals(0,s.remove((float)1));
    }
    
    @Test
    void testContains()
    {
        final FloatSet s = new FloatSet(new OrderedFloatList(new FixedFloatList(1)));
        assertFalse(s.contains((float)1));
        s.add((float)1);
        assertTrue(s.contains((float)1));
    }
    
    @Test
    void testClone()
    {
    
        final FloatSet s  = new FloatSet(new OrderedFloatList(new FixedFloatList(1))),
                       s2 = s.clone();
        assertEquals(s,s2);
        s.add((float)1);
        assertNotEquals(s,s2);
    }
}