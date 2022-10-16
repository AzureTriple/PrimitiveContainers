package azuretriple.primitivecontainer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FixedIntListTest
{
    @Test
    void testInit()
    {
        {
            final int[] b = {0,1,2,3};
            {
                final FixedIntList l = new FixedIntList(b,2);
                assertSame(b,l.data());
                assertEquals(2,l.size());
                assertFalse(l.empty());
            }
            {
                final FixedIntList l = new FixedIntList(b);
                assertSame(b,l.data());
                assertEquals(0,l.size());
                assertTrue(l.empty());
            }
        }
        {
            final FixedIntList l = new FixedIntList(3);
            assertArrayEquals(new int[3],l.data());
            assertEquals(0,l.size());
            assertTrue(l.empty());
        }
        {
            final FixedIntList l = new FixedIntList(new int[] {1,0,0,0},1),l2 = new FixedIntList(l);
            assertEquals(l,l2);
            l.add(2);
            l2.add(3);
            assertNotEquals(l,l2);
        }
    }
    
    @Test
    void testEquals()
    {
        final FixedIntList l = new FixedIntList(1);
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
        final FixedIntList l = new FixedIntList(new int[] {0,1,2,3},2);
        assertEquals(l,l.clone());
    }
}