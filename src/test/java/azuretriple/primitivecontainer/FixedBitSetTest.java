package azuretriple.primitivecontainer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FixedBitSetTest
{
    @Test
    void testInit()
    {
        {
            final long[] work = {1L};
            {
                final FixedBitSet f = new FixedBitSet(work,false);
                assertSame(work,f.set);
                assertArrayEquals(new long[]{1L},work);
            }
            {
                final FixedBitSet f = new FixedBitSet(work,true);
                assertSame(work,f.set);
                assertArrayEquals(new long[]{0L},work);
            }
            work[0] = 1L;
            {
                final FixedBitSet f = new FixedBitSet(work);
                assertSame(work,f.set);
                assertArrayEquals(new long[]{0L},work);
            }
        }
        {
            assertArrayEquals(new long[1],new FixedBitSet(1).set);
            assertArrayEquals(new long[1],new FixedBitSet(64).set);
            assertArrayEquals(new long[2],new FixedBitSet(65).set);
        }
        {
            final FixedBitSet f = new FixedBitSet(1);
            f.set(0);
            assertEquals(f,new FixedBitSet(f));
        }
        {
            final MapBitSet m = new MapBitSet();
            assertEquals(0,new FixedBitSet(m).set.length);
            m.set(0);
            //noinspection AssertBetweenInconvertibleTypes
            assertEquals(m,new FixedBitSet(m));
        }
    }
    
    @Test
    void testData()
    {
        final FixedBitSet f = new FixedBitSet(1);
        assertSame(f.set,f.data());
    }
    
    @Test void testSizeLogic() {assertDoesNotThrow(() -> new FixedBitSet(1).sizeLogic(0));}
    
    @Test
    void testClear()
    {
        final FixedBitSet f = new FixedBitSet(1);
        f.set(0);
        f.clear();
        assertArrayEquals(new long[1],f.data());
    }
    
    @Test
    void testClone()
    {
        final FixedBitSet f  = new FixedBitSet(1),
                          f2 = f.clone();
        assertEquals(f,f2);
        f.set(0);
        f2.set(1);
        assertNotEquals(f,f2);
    }
}