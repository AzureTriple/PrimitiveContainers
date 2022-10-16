package azuretriple.primitivecontainer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayBitSetTest
{
    @Test
    void testSet()
    {
        final FixedBitSet s = new FixedBitSet(1);
        assertFalse(s.test(0));
        s.set(0);
        assertTrue(s.test(0));
    }
    
    @Test
    void testUnset()
    {
        final FixedBitSet s = new FixedBitSet(1);
        s.set(0);
        assertTrue(s.test(0));
        s.unset(0);
        assertFalse(s.test(0));
        assertDoesNotThrow(() -> s.unset(64));
    }
    
    @Test
    void testAdd()
    {
        final FixedBitSet s = new FixedBitSet(1);
        assertTrue(s.add(0));
        assertFalse(s.add(0));
    }
    
    @Test
    void testRemove()
    {
        final FixedBitSet s = new FixedBitSet(1);
        s.set(0);
        assertTrue(s.remove(0));
        assertFalse(s.remove(0));
        assertFalse(s.remove(64));
    }
    
    @Test
    void testTest()
    {
        final FixedBitSet s = new FixedBitSet(1);
        assertFalse(s.test(0));
        s.set(0);
        assertTrue(s.test(0));
        assertFalse(s.test(64));
    }
    
    @Test
    void testEquals()
    {
        assertNotEquals(new FixedBitSet(1),new Object());
        final FixedBitSet A = new FixedBitSet(1);
        assertEquals(A,A);
        final FixedBitSet B = new FixedBitSet(1);
        B.set(0);
        assertNotEquals(A,B);
    }
}