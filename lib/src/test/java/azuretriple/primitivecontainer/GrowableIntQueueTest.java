package azuretriple.primitivecontainer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrowableIntQueueTest
{
    @Test
    void testInit()
    {
        {
            final int[] b = {0,1,2,3};
            {
                final GrowableIntQueue q = new GrowableIntQueue(b,0,1,GrowableQueue.NORMAL);
                assertSame(b,q.data());
                assertEquals(1,q.size());
                Assertions.assertEquals(GrowableQueue.NORMAL,q.state);
            }
            {
                final GrowableIntQueue q = new GrowableIntQueue(b);
                assertSame(b,q.data());
                assertEquals(0,q.size());
                Assertions.assertEquals(GrowableQueue.EMPTY,q.state);
            }
        }
        {
            final GrowableIntQueue q = new GrowableIntQueue(10);
            assertArrayEquals(new int[10],q.data());
            assertEquals(0,q.size());
            Assertions.assertEquals(GrowableQueue.EMPTY,q.state);
        }
        {
            final GrowableIntQueue q = new GrowableIntQueue();
            assertArrayEquals(new int[Growable.DEFAULT_SIZE],q.data());
            assertEquals(0,q.size());
            assertEquals(Growable.DEFAULT_SIZE,q.data().length);
        }
        {
            final GrowableIntQueue q  = new GrowableIntQueue(new int[] {1,0,0,0},0,1,GrowableQueue.NORMAL),
                                   q2 = new GrowableIntQueue(q);
            assertEquals(q,q2);
            q.push(2);
            q2.push(3);
            assertNotEquals(q,q2);
        }
    }
    
    @Test
    void testEquals()
    {
        final GrowableIntQueue q = new GrowableIntQueue();
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
        final GrowableIntQueue q = new GrowableIntQueue(new int[] {3,0,1,2},2,1,GrowableQueue.NORMAL);
        assertEquals(q,q.clone());
    }
}