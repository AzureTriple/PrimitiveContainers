package azuretriple.primitivecontainer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrowableShortListTest
{
    @Test
    void testInit()
    {
        {
            final short[] b = {0,1,2,3};
            {
                final GrowableShortList l = new GrowableShortList(b,2);
                assertSame(b,l.data());
                assertEquals(2,l.size());
                assertFalse(l.empty());
            }
            {
                final GrowableShortList l = new GrowableShortList(b);
                assertSame(b,l.data());
                assertEquals(0,l.size());
                assertTrue(l.empty());
            }
        }
        {
            final GrowableShortList l = new GrowableShortList(3);
            assertArrayEquals(new short[3],l.data());
            assertEquals(0,l.size());
            assertTrue(l.empty());
        }
        {
            final GrowableShortList l = new GrowableShortList();
            assertArrayEquals(new short[Growable.DEFAULT_SIZE],l.data());
            assertEquals(0,l.size());
            assertTrue(l.empty());
        }
        {
            final GrowableShortList l = new GrowableShortList(new short[] {1,0,0,0},1),l2 = new GrowableShortList(l);
            assertEquals(l,l2);
            l.add((short)2);
            l2.add((short)3);
            assertNotEquals(l,l2);
        }
    }
    
    @Test
    void testEquals()
    {
        final GrowableShortList l = new GrowableShortList(1);
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
        final GrowableShortList l = new GrowableShortList(new short[] {0,1,2,3},2);
        assertEquals(l,l.clone());
    }
    
    @Test
    void testPop()
    {
        final GrowableShortList l = new GrowableShortList(16);
        l.add((short)1);
        assertEquals((short)1,l.pop());
    }
}