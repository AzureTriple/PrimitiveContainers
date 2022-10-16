package azuretriple.primitivecontainer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapBitSetTest
{
    @Test
    void testInit()
    {
        {
            final OrderedIntList k = new OrderedIntList(new FixedIntList(0));
            final FixedLongList v = new FixedLongList(0);
            MapBitSet s = new MapBitSet(k,v);
            assertSame(k,s.keys);
            assertSame(v,s.values);
            s = new MapBitSet(new IntToLongMap(new IntSet(k),v));
            assertSame(k,s.keys);
            assertSame(v,s.values);
        }
        {
            final MapBitSet s = new MapBitSet();
            assertTrue(s.keys.data() instanceof GrowableIntList);
            assertTrue(s.values instanceof GrowableLongList);
        }
        {
            final MapBitSet s = new MapBitSet();
            s.set(1);
            final MapBitSet s2 = new MapBitSet(s);
            assertEquals(s,s2);
            s2.unset(1);
            assertNotEquals(s,s2);
        }
        {
            final FixedBitSet f = new FixedBitSet(129);
            f.set(1);
            f.set(128);
            final MapBitSet m = new MapBitSet(f);
            //noinspection AssertBetweenInconvertibleTypes
            assertEquals(f,m);
            f.set(2);
            //noinspection AssertBetweenInconvertibleTypes
            assertNotEquals(f,m);
        }
        {
            final GrowableBitSet g = new GrowableBitSet(1);
            g.set(1);
            g.set(128);
            final MapBitSet m = new MapBitSet(g);
            //noinspection AssertBetweenInconvertibleTypes
            assertEquals(g,m);
            g.set(2);
            //noinspection AssertBetweenInconvertibleTypes
            assertNotEquals(g,m);
        }
    }
    
    @Test
    void testKeys()
    {
        final OrderedIntList k = new OrderedIntList(new FixedIntList(1));
        final MapBitSet m = new MapBitSet(k,new FixedLongList(1));
        assertSame(k,m.keys());
    }
    
    @Test
    void testValues()
    {
        final FixedLongList v = new FixedLongList(1);
        final MapBitSet m = new MapBitSet(new OrderedIntList(new FixedIntList(1)),v);
        assertSame(v,m.values());
    }
    
    @Test
    void testAsMap()
    {
        final OrderedIntList k = new OrderedIntList(new FixedIntList(1));
        final FixedLongList v = new FixedLongList(1);
        final IntToLongMap m = new MapBitSet(new IntToLongMap(new IntSet(k),v)).asMap();
        assertSame(m.keys().data(),k);
        assertSame(m.values(),v);
    }
    
    @Test
    void testSet()
    {
        final MapBitSet m = new MapBitSet();
        m.set(0);
        m.set(1);
        assertEquals(3L,m.values.data()[0]);
    }
    
    @Test
    void testUnset()
    {
        final MapBitSet m = new MapBitSet();
        m.set(0);
        m.set(1);
        m.set(32);
        m.unset(0);
        m.unset(1);
        m.unset(32);
        m.unset(64);
        assertTrue(m.keys.data().empty());
    }
    
    @Test
    void testAdd()
    {
        final MapBitSet m = new MapBitSet();
        assertTrue(m.add(0));
        assertTrue(m.add(1));
        assertFalse(m.add(1));
        assertTrue(m.add(64));
    }
    
    @Test
    void testRemove()
    {
        final MapBitSet m = new MapBitSet();
        assertFalse(m.remove(0));
        m.set(0);
        m.set(1);
        assertTrue(m.remove(0));
        assertFalse(m.remove(0));
        assertTrue(m.remove(1));
    }
    
    @Test
    void testTest()
    {
        final MapBitSet m = new MapBitSet();
        assertFalse(m.test(0));
        m.set(1);
        assertFalse(m.test(0));
        assertTrue(m.test(1));
    }
    
    @Test
    void testClear()
    {
        final MapBitSet m = new MapBitSet();
        m.set(0);
        m.clear();
        assertTrue(m.keys.data().empty());
        assertTrue(m.values.empty());
    }
    
    @Test
    void testClone()
    {
        final MapBitSet a = new MapBitSet();
        a.set(1);
        final MapBitSet b = a.clone();
        assertEquals(a,b);
        a.set(2);
        assertNotEquals(a,b);
    }
    
    @Test
    void testEquals()
    {
        assertNotEquals(new MapBitSet(),new Object());
        final MapBitSet A = new MapBitSet();
        assertEquals(A,A);
        final MapBitSet B = new MapBitSet();
        B.set(0);
        assertNotEquals(A,B);
    }
}