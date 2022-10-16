package azuretriple.primitivecontainer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FixedFloatQueueTest
{
    @Test
    void testInit()
    {
        {
            final float[] b = {0,1,2,3};
            {
                final FixedFloatQueue q = new FixedFloatQueue(b,0,2,false);
                assertSame(b,q.data());
                assertEquals(2,q.size());
                assertFalse(q.empty());
            }
            {
                final FixedFloatQueue q = new FixedFloatQueue(b);
                assertSame(b,q.data());
                assertEquals(0,q.size());
                assertTrue(q.empty());
            }
        }
        {
            final FixedFloatQueue q = new FixedFloatQueue(10);
            assertEquals(0,q.size());
            assertTrue(q.empty());
            assertArrayEquals(new float[10],q.data());
        }
        {
            final FixedFloatQueue q  = new FixedFloatQueue(new float[] {1,0,0,0},0,1,false),
                q2 = new FixedFloatQueue(q);
            assertEquals(q,q2);
            q.push((float)2);
            q2.push((float)3);
            assertNotEquals(q,q2);
        }
    }
    
    @Test
    void testEquals()
    {
        final FixedFloatQueue q = new FixedFloatQueue(1);
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
        final FixedFloatQueue q = new FixedFloatQueue(new float[] {3,0,1,2},2,1,false);
        assertEquals(q,q.clone());
    }
}