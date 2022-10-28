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
    void testUnion()
    {
        {
            final FloatSet a = new FloatSet(new OrderedFloatList(new FixedFloatList(3))),
                           b = new FloatSet(new OrderedFloatList(new FixedFloatList(2)));
            a.add((float)1);
            a.add((float)3);
            b.add((float)1);
            b.add((float)2);
            a.union(b);
            assertArrayEquals(new float[] {1,2,3},a.data().data().data());
            assertEquals(3,a.data().data().size());
        }
        {
            final FloatSet a = new FloatSet(new OrderedFloatList(new FixedFloatList(4))),
                           b = new FloatSet(new OrderedFloatList(new FixedFloatList(3)));
            a.add((float)1);
            a.add((float)2);
            b.add((float)1);
            b.add((float)3);
            b.add((float)4);
            a.union(b);
            assertArrayEquals(new float[] {1,2,3,4},a.data().data().data());
            assertEquals(4,a.data().data().size());
        }
    }
    
    @Test
    void testIntersect()
    {
        {
            final FloatSet a = new FloatSet(new OrderedFloatList(new FixedFloatList(2))),
                           b = new FloatSet(new OrderedFloatList(new FixedFloatList(2)));
            a.add((float)1);
            a.add((float)2);
            b.add((float)1);
            b.add((float)2);
            a.intersect(b);
            assertArrayEquals(new float[] {1,2},a.data().data().data());
            assertEquals(2,a.data().data().size());
        }
        {
            final FloatSet a = new FloatSet(new OrderedFloatList(new FixedFloatList(2))),
                           b = new FloatSet(new OrderedFloatList(new FixedFloatList(2)));
            a.add((float)1);
            a.add((float)3);
            b.add((float)2);
            b.add((float)3);
            a.intersect(b);
            assertArrayEquals(new float[] {3,3},a.data().data().data());
            assertEquals(1,a.data().data().size());
        }
        {
            final FloatSet a = new FloatSet(new OrderedFloatList(new FixedFloatList(3))),
                           b = new FloatSet(new OrderedFloatList(new FixedFloatList(2)));
            a.add((float)1);
            a.add((float)3);
            a.add((float)4);
            b.add((float)2);
            b.add((float)3);
            a.intersect(b);
            assertArrayEquals(new float[] {3,3,4},a.data().data().data());
            assertEquals(1,a.data().data().size());
        }
    }
    
    @Test
    void testSubtract()
    {
        {
            final FloatSet a = new FloatSet(new OrderedFloatList(new FixedFloatList(3))),
                           b = new FloatSet(new OrderedFloatList(new FixedFloatList(2)));
            a.add((float)1);
            a.add((float)2);
            a.add((float)4);
            b.add((float)2);
            b.add((float)3);
            a.subtract(b);
            assertArrayEquals(new float[] {1,4,4},a.data().data().data());
            assertEquals(1,a.data().data().size());
        }
        {
            final FloatSet a = new FloatSet(new OrderedFloatList(new FixedFloatList(2))),
                           b = new FloatSet(new OrderedFloatList(new FixedFloatList(3)));
            a.add((float)1);
            a.add((float)2);
            b.add((float)2);
            b.add((float)3);
            b.add((float)4);
            a.subtract(b);
            assertArrayEquals(new float[] {1,2},a.data().data().data());
            assertEquals(1,a.data().data().size());
        }
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