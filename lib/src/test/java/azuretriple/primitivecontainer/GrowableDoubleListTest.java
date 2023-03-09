package azuretriple.primitivecontainer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrowableDoubleListTest
{
    @Test
    void testInit()
    {
        {
            final double[] b = {0,1,2,3};
            {
                final GrowableDoubleList l = new GrowableDoubleList(b,2);
                assertSame(b,l.data());
                assertEquals(2,l.size());
                assertFalse(l.empty());
            }
            {
                final GrowableDoubleList l = new GrowableDoubleList(b);
                assertSame(b,l.data());
                assertEquals(0,l.size());
                assertTrue(l.empty());
            }
        }
        {
            final GrowableDoubleList l = new GrowableDoubleList(3);
            assertArrayEquals(new double[3],l.data());
            assertEquals(0,l.size());
            assertTrue(l.empty());
        }
        {
            final GrowableDoubleList l = new GrowableDoubleList();
            assertArrayEquals(new double[Growable.DEFAULT_SIZE],l.data());
            assertEquals(0,l.size());
            assertTrue(l.empty());
        }
        {
            final GrowableDoubleList l = new GrowableDoubleList(new double[] {1,0,0,0},1),l2 = new GrowableDoubleList(l);
            assertEquals(l,l2);
            l.add(2);
            l2.add(3);
            assertNotEquals(l,l2);
        }
    }
    
    @Test
    void testEquals()
    {
        final GrowableDoubleList l = new GrowableDoubleList(1);
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
        final GrowableDoubleList l = new GrowableDoubleList(new double[] {0,1,2,3},2);
        assertEquals(l,l.clone());
    }
    
    @Test
    void testPop()
    {
        final GrowableDoubleList l = new GrowableDoubleList(16);
        l.add(1.D);
        assertEquals(1.D,l.pop());
    }
}