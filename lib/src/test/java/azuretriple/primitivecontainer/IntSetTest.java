package azuretriple.primitivecontainer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntSetTest
{
    @Test
    void testInit()
    {
        final IntSet s  = new IntSet(new OrderedIntList(new FixedIntList(1))),
                     s2 = new IntSet(s);
        assertEquals(s,s2);
        s.add(1);
        assertNotEquals(s,s2);
    }
    
    @Test
    void testAdd()
    {
        final IntSet s = new IntSet(new OrderedIntList(new FixedIntList(1)));
        assertEquals(0,s.add(1));
        assertEquals(-1,s.add(1));
    }
    
    @Test
    void testRemove()
    {
        final IntSet s = new IntSet(new OrderedIntList(new FixedIntList(1)));
        assertEquals(-1,s.remove(1));
        s.add(1);
        assertEquals(0,s.remove(1));
    }
    
    @Test
    void testContains()
    {
        final IntSet s = new IntSet(new OrderedIntList(new FixedIntList(1)));
        assertFalse(s.contains(1));
        s.add(1);
        assertTrue(s.contains(1));
    }
    
    @Test
    void testUnion()
    {
        {
            final IntSet a = new IntSet(new OrderedIntList(new FixedIntList(3))),
                         b = new IntSet(new OrderedIntList(new FixedIntList(2)));
            a.add(1);
            a.add(3);
            b.add(1);
            b.add(2);
            a.union(b);
            assertArrayEquals(new int[] {1,2,3},a.data().data().data());
            assertEquals(3,a.data().data().size());
        }
        {
            final IntSet a = new IntSet(new OrderedIntList(new FixedIntList(4))),
                         b = new IntSet(new OrderedIntList(new FixedIntList(3)));
            a.add(1);
            a.add(2);
            b.add(1);
            b.add(3);
            b.add(4);
            a.union(b);
            assertArrayEquals(new int[] {1,2,3,4},a.data().data().data());
            assertEquals(4,a.data().data().size());
        }
    }
    
    @Test
    void testIntersect()
    {
        {
            final IntSet a = new IntSet(new OrderedIntList(new FixedIntList(2))),
                         b = new IntSet(new OrderedIntList(new FixedIntList(2)));
            a.add(1);
            a.add(2);
            b.add(1);
            b.add(2);
            a.intersect(b);
            assertArrayEquals(new int[] {1,2},a.data().data().data());
            assertEquals(2,a.data().data().size());
        }
        {
            final IntSet a = new IntSet(new OrderedIntList(new FixedIntList(2))),
                         b = new IntSet(new OrderedIntList(new FixedIntList(2)));
            a.add(1);
            a.add(3);
            b.add(2);
            b.add(3);
            a.intersect(b);
            assertArrayEquals(new int[] {3,3},a.data().data().data());
            assertEquals(1,a.data().data().size());
        }
        {
            final IntSet a = new IntSet(new OrderedIntList(new FixedIntList(3))),
                         b = new IntSet(new OrderedIntList(new FixedIntList(2)));
            a.add(1);
            a.add(3);
            a.add(4);
            b.add(2);
            b.add(3);
            a.intersect(b);
            assertArrayEquals(new int[] {3,3,4},a.data().data().data());
            assertEquals(1,a.data().data().size());
        }
    }
    
    @Test
    void testSubtract()
    {
        {
            final IntSet a = new IntSet(new OrderedIntList(new FixedIntList(3))),
                         b = new IntSet(new OrderedIntList(new FixedIntList(2)));
            a.add(1);
            a.add(2);
            a.add(4);
            b.add(2);
            b.add(3);
            a.subtract(b);
            assertArrayEquals(new int[] {1,4,4},a.data().data().data());
            assertEquals(1,a.data().data().size());
        }
        {
            final IntSet a = new IntSet(new OrderedIntList(new FixedIntList(2))),
                         b = new IntSet(new OrderedIntList(new FixedIntList(3)));
            a.add(1);
            a.add(2);
            b.add(2);
            b.add(3);
            b.add(4);
            a.subtract(b);
            assertArrayEquals(new int[] {1,2},a.data().data().data());
            assertEquals(1,a.data().data().size());
        }
    }
    
    @Test
    void testClone()
    {
    
        final IntSet s  = new IntSet(new OrderedIntList(new FixedIntList(1))),
                     s2 = s.clone();
        assertEquals(s,s2);
        s.add(1);
        assertNotEquals(s,s2);
    }
}