package azuretriple.primitivecontainer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrowableIntListTest
{
    @Test
    void testInit()
    {
        {
            final int[] b = {0,1,2,3};
            {
                final GrowableIntList l = new GrowableIntList(b,2);
                assertSame(b,l.data());
                assertEquals(2,l.size());
                assertFalse(l.empty());
            }
            {
                final GrowableIntList l = new GrowableIntList(b);
                assertSame(b,l.data());
                assertEquals(0,l.size());
                assertTrue(l.empty());
            }
        }
        {
            final GrowableIntList l = new GrowableIntList(3);
            assertArrayEquals(new int[3],l.data());
            assertEquals(0,l.size());
            assertTrue(l.empty());
        }
        {
            final GrowableIntList l = new GrowableIntList();
            assertArrayEquals(new int[Growable.DEFAULT_SIZE],l.data());
            assertEquals(0,l.size());
            assertTrue(l.empty());
        }
        {
            final GrowableIntList l = new GrowableIntList(new int[] {1,0,0,0},1),l2 = new GrowableIntList(l);
            assertEquals(l,l2);
            l.add(2);
            l2.add(3);
            assertNotEquals(l,l2);
        }
    }
    
    @Test
    void testEquals()
    {
        final GrowableIntList l = new GrowableIntList(1);
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
        final GrowableIntList l = new GrowableIntList(new int[] {0,1,2,3},2);
        assertEquals(l,l.clone());
    }
}