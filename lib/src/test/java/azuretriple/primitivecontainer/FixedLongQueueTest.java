package azuretriple.primitivecontainer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FixedLongQueueTest
{
    @Test
    void testInit()
    {
        {
            final long[] b = {0,1,2,3};
            {
                final FixedLongQueue q = new FixedLongQueue(b,0,2,false);
                assertSame(b,q.data());
                assertEquals(2,q.size());
                assertFalse(q.empty());
            }
            {
                final FixedLongQueue q = new FixedLongQueue(b);
                assertSame(b,q.data());
                assertEquals(0,q.size());
                assertTrue(q.empty());
            }
        }
        {
            final FixedLongQueue q = new FixedLongQueue(10);
            assertEquals(0,q.size());
            assertTrue(q.empty());
            assertArrayEquals(new long[10],q.data());
        }
        {
            final FixedLongQueue q  = new FixedLongQueue(new long[] {1,0,0,0},0,1,false),
                q2 = new FixedLongQueue(q);
            assertEquals(q,q2);
            q.push(2);
            q2.push(3);
            assertNotEquals(q,q2);
        }
    }
    
    @Test
    void testEquals()
    {
        final FixedLongQueue q = new FixedLongQueue(1);
        {
            final boolean b = q.equals(new Object());
            assertFalse(b);
        }
        {
            //noinspection EqualsWithItself
            final boolean b = q.equals(q);
            assertTrue(b);
        }
    }
    
    @Test
    void testClone()
    {
        final FixedLongQueue q = new FixedLongQueue(new long[] {3,0,1,2},2,1,false);
        assertEquals(q,q.clone());
    }
}