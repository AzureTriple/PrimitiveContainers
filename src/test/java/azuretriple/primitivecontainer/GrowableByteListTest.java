package azuretriple.primitivecontainer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrowableByteListTest
{
    @Test
    void testInit()
    {
        {
            final byte[] b = {0,1,2,3};
            {
                final GrowableByteList l = new GrowableByteList(b,2);
                assertSame(b,l.data());
                assertEquals(2,l.size());
                assertFalse(l.empty());
            }
            {
                final GrowableByteList l = new GrowableByteList(b);
                assertSame(b,l.data());
                assertEquals(0,l.size());
                assertTrue(l.empty());
            }
        }
        {
            final GrowableByteList l = new GrowableByteList(3);
            assertArrayEquals(new byte[3],l.data());
            assertEquals(0,l.size());
            assertTrue(l.empty());
        }
        {
            final GrowableByteList l = new GrowableByteList();
            assertArrayEquals(new byte[Growable.DEFAULT_SIZE],l.data());
            assertEquals(0,l.size());
            assertTrue(l.empty());
        }
        {
            final GrowableByteList l = new GrowableByteList(new byte[] {1,0,0,0},1),l2 = new GrowableByteList(l);
            assertEquals(l,l2);
            l.add((byte)2);
            l2.add((byte)3);
            assertNotEquals(l,l2);
        }
    }
    
    @Test
    void testEquals()
    {
        final GrowableByteList l = new GrowableByteList(1);
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
        final GrowableByteList l = new GrowableByteList(new byte[] {0,1,2,3},2);
        assertEquals(l,l.clone());
    }
}