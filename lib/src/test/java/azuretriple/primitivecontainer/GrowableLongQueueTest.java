package azuretriple.primitivecontainer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrowableLongQueueTest
{
    @Test
    void testInit()
    {
        {
            final long[] b = {0,1,2,3};
            {
                final GrowableLongQueue q = new GrowableLongQueue(b,0,1,GrowableQueue.NORMAL);
                assertSame(b,q.data());
                assertEquals(1,q.size());
                Assertions.assertEquals(GrowableQueue.NORMAL,q.state);
            }
            {
                final GrowableLongQueue q = new GrowableLongQueue(b);
                assertSame(b,q.data());
                assertEquals(0,q.size());
                Assertions.assertEquals(GrowableQueue.EMPTY,q.state);
            }
        }
        {
            final GrowableLongQueue q = new GrowableLongQueue(10);
            assertArrayEquals(new long[10],q.data());
            assertEquals(0,q.size());
            Assertions.assertEquals(GrowableQueue.EMPTY,q.state);
        }
        {
            final GrowableLongQueue q = new GrowableLongQueue();
            assertArrayEquals(new long[Growable.DEFAULT_SIZE],q.data());
            assertEquals(0,q.size());
            assertEquals(Growable.DEFAULT_SIZE,q.data().length);
        }
        {
            final GrowableLongQueue q  = new GrowableLongQueue(new long[] {1,0,0,0},0,1,GrowableQueue.NORMAL),
                                    q2 = new GrowableLongQueue(q);
            assertEquals(q,q2);
            q.push(2);
            q2.push(3);
            assertNotEquals(q,q2);
        }
    }
    
    @Test
    void testEquals()
    {
        final GrowableLongQueue q = new GrowableLongQueue();
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
        final GrowableLongQueue q = new GrowableLongQueue(new long[] {3,0,1,2},2,1,GrowableQueue.NORMAL);
        assertEquals(q,q.clone());
    }
}