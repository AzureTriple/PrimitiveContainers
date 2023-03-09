package azuretriple.primitivecontainer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrowableFloatListTest
{
    @Test
    void testInit()
    {
        {
            final float[] b = {0,1,2,3};
            {
                final GrowableFloatList l = new GrowableFloatList(b,2);
                assertSame(b,l.data());
                assertEquals(2,l.size());
                assertFalse(l.empty());
            }
            {
                final GrowableFloatList l = new GrowableFloatList(b);
                assertSame(b,l.data());
                assertEquals(0,l.size());
                assertTrue(l.empty());
            }
        }
        {
            final GrowableFloatList l = new GrowableFloatList(3);
            assertArrayEquals(new float[3],l.data());
            assertEquals(0,l.size());
            assertTrue(l.empty());
        }
        {
            final GrowableFloatList l = new GrowableFloatList();
            assertArrayEquals(new float[Growable.DEFAULT_SIZE],l.data());
            assertEquals(0,l.size());
            assertTrue(l.empty());
        }
        {
            final GrowableFloatList l = new GrowableFloatList(new float[] {1,0,0,0},1),l2 = new GrowableFloatList(l);
            assertEquals(l,l2);
            l.add((float)2);
            l2.add((float)3);
            assertNotEquals(l,l2);
        }
    }
    
    @Test
    void testEquals()
    {
        final GrowableFloatList l = new GrowableFloatList(1);
        {
            final boolean b = l.equals(new Object());
            assertFalse(b);
        }
        {
            //noinspection EqualsWithItself
            final boolean b = l.equals(l);
            assertTrue(b);
        }
    }
    
    @Test
    void testClone()
    {
        final GrowableFloatList l = new GrowableFloatList(new float[] {0,1,2,3},2);
        assertEquals(l,l.clone());
    }
    
    @Test
    void testPop()
    {
        final GrowableFloatList l = new GrowableFloatList(16);
        l.add(1.F);
        assertEquals(1.F,l.pop());
    }
}