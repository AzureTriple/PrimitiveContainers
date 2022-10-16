package azuretriple.primitivecontainer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FixedFloatListTest
{
    @Test
    void testInit()
    {
        {
            final float[] b = {0,1,2,3};
            {
                final FixedFloatList l = new FixedFloatList(b,2);
                assertSame(b,l.data());
                assertEquals(2,l.size());
                assertFalse(l.empty());
            }
            {
                final FixedFloatList l = new FixedFloatList(b);
                assertSame(b,l.data());
                assertEquals(0,l.size());
                assertTrue(l.empty());
            }
        }
        {
            final FixedFloatList l = new FixedFloatList(3);
            assertArrayEquals(new float[3],l.data());
            assertEquals(0,l.size());
            assertTrue(l.empty());
        }
        {
            final FixedFloatList l = new FixedFloatList(new float[] {1,0,0,0},1),l2 = new FixedFloatList(l);
            assertEquals(l,l2);
            l.add((float)2);
            l2.add((float)3);
            assertNotEquals(l,l2);
        }
    }
    
    @Test
    void testEquals()
    {
        final FixedFloatList l = new FixedFloatList(1);
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
        final FixedFloatList l = new FixedFloatList(new float[] {0,1,2,3},2);
        assertEquals(l,l.clone());
    }
}