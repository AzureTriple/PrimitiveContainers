package azuretriple.primitivecontainer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrowableDoubleQueueTest
{
    @Test
    void testInit()
    {
        {
            final double[] b = {0,1,2,3};
            {
                final GrowableDoubleQueue q = new GrowableDoubleQueue(b,0,1,GrowableQueue.NORMAL);
                assertSame(b,q.data());
                assertEquals(1,q.size());
                Assertions.assertEquals(GrowableQueue.NORMAL,q.state);
            }
            {
                final GrowableDoubleQueue q = new GrowableDoubleQueue(b);
                assertSame(b,q.data());
                assertEquals(0,q.size());
                Assertions.assertEquals(GrowableQueue.EMPTY,q.state);
            }
        }
        {
            final GrowableDoubleQueue q = new GrowableDoubleQueue(10);
            assertArrayEquals(new double[10],q.data());
            assertEquals(0,q.size());
            Assertions.assertEquals(GrowableQueue.EMPTY,q.state);
        }
        {
            final GrowableDoubleQueue q = new GrowableDoubleQueue();
            assertArrayEquals(new double[Growable.DEFAULT_SIZE],q.data());
            assertEquals(0,q.size());
            assertEquals(Growable.DEFAULT_SIZE,q.data().length);
        }
        {
            final GrowableDoubleQueue q  = new GrowableDoubleQueue(new double[] {1,0,0,0},0,1,GrowableQueue.NORMAL),
                                      q2 = new GrowableDoubleQueue(q);
            assertEquals(q,q2);
            q.push(2);
            q2.push(3);
            assertNotEquals(q,q2);
        }
    }
    
    @Test
    void testEquals()
    {
        final GrowableDoubleQueue q = new GrowableDoubleQueue();
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
        final GrowableDoubleQueue q = new GrowableDoubleQueue(new double[] {3,0,1,2},2,1,GrowableQueue.NORMAL);
        assertEquals(q,q.clone());
    }
}