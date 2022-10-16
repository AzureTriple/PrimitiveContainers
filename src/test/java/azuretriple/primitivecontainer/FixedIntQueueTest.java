package azuretriple.primitivecontainer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FixedIntQueueTest
{
    @Test
    void testInit()
    {
        {
            final int[] b = {0,1,2,3};
            {
                final FixedIntQueue q = new FixedIntQueue(b,0,2,false);
                assertSame(b,q.data());
                assertEquals(2,q.size());
                assertFalse(q.empty());
            }
            {
                final FixedIntQueue q = new FixedIntQueue(b);
                assertSame(b,q.data());
                assertEquals(0,q.size());
                assertTrue(q.empty());
            }
        }
        {
            final FixedIntQueue q = new FixedIntQueue(10);
            assertEquals(0,q.size());
            assertTrue(q.empty());
            assertArrayEquals(new int[10],q.data());
        }
        {
            final FixedIntQueue q  = new FixedIntQueue(new int[] {1,0,0,0},0,1,false),
                q2 = new FixedIntQueue(q);
            assertEquals(q,q2);
            q.push(2);
            q2.push(3);
            assertNotEquals(q,q2);
        }
    }
    
    @Test
    void testEquals()
    {
        final FixedIntQueue q = new FixedIntQueue(1);
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
        final FixedIntQueue q = new FixedIntQueue(new int[] {3,0,1,2},2,1,false);
        assertEquals(q,q.clone());
    }
}