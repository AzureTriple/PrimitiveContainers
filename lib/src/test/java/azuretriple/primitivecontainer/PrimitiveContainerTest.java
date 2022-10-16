package azuretriple.primitivecontainer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrimitiveContainerTest
{
    static final int DEFAULT_SIZE;
    static
    {
        try {DEFAULT_SIZE = Class.forName("azuretriple.primitivecontainer.Growable").getDeclaredField("DEFAULT_SIZE").getInt(null);}
        catch(final Throwable e) {throw new AssertionError(e);}
    }
    
    @Test
    void testSize()
    {
        assertEquals(0,new FixedByteList(8).size());
        {
            final FixedByteQueue q = new FixedByteQueue(4);
            
            // empty
            assertEquals(0,q.size());
            
            // !wrapped
            q.push((byte)0);
            assertEquals(1,q.size());
            
            // wrapped
            q.pop();
            q.push((byte)1);
            q.pop();
            q.push((byte)2);
            q.pop();
            q.push((byte)3);
            q.push((byte)4);
            assertEquals(2,q.size());
        }
        {
            final GrowableByteQueue q = new GrowableByteQueue(3);
            
            // empty
            assertEquals(0,q.size());
            
            // !wrapped
            q.push((byte)0);
            assertEquals(1,q.size());
            
            // wrapped
            q.pop();
            q.push((byte)1);
            q.pop();
            q.push((byte)2);
            q.push((byte)3);
            assertEquals(2,q.size());
            q.push((byte)4);
            assertEquals(3,q.size());
        }
    }
    
    @Test
    void testEmpty()
    {
        {
            final FixedByteList l = new FixedByteList(1);
            assertTrue(l.empty());
            l.add((byte)0);
            assertFalse(l.empty());
        }
        {
            final FixedByteQueue q = new FixedByteQueue(1);
            assertTrue(q.empty());
            q.push((byte)0);
            assertFalse(q.empty());
        }
        {
            final GrowableByteQueue q = new GrowableByteQueue(1);
            assertTrue(q.empty());
            q.push((byte)0);
            assertFalse(q.empty());
        }
    }
    
    @Test
    void testClear()
    {
        {
            final GrowableByteList l = new GrowableByteList(DEFAULT_SIZE*2);
            assertEquals(DEFAULT_SIZE*2,l.data().length);
            l.add((byte)0);
            l.clear();
            assertTrue(l.empty());
            assertEquals(DEFAULT_SIZE,l.data().length);
            
            // resize branch
            l.clear();
            assertTrue(l.empty());
            assertEquals(DEFAULT_SIZE,l.data().length);
        }
        {
            final FixedByteQueue q = new FixedByteQueue(1);
            q.push((byte)0);
            q.clear();
            assertTrue(q.empty());
        }
        {
            final GrowableByteQueue q = new GrowableByteQueue(DEFAULT_SIZE*2);
            assertEquals(DEFAULT_SIZE*2,q.data().length);
            q.push((byte)0);
            q.clear();
            assertTrue(q.empty());
            assertEquals(DEFAULT_SIZE,q.data().length);
    
            // resize branch
            q.clear();
            assertTrue(q.empty());
            assertEquals(DEFAULT_SIZE,q.data().length);
        }
    }
    
    static void clone(final PrimitiveContainer c)
    {
        final PrimitiveContainer o = c.clone();
        assertEquals(c,o);
        o.clear();
        assertNotEquals(c,o);
    }
    @Test
    void testClone()
    {
        clone(new FixedByteList(new byte[1],1));
        clone(new FixedByteQueue(new byte[1],0,1,false));
        {
            final GrowableByteList l = new GrowableByteList(1);
            l.add((byte)0);
            clone(l);
        }
        {
            final GrowableByteQueue q = new GrowableByteQueue(1);
            q.push((byte)0);
            clone(q);
        }
    
        clone(new FixedShortList(new short[1],1));
        clone(new FixedShortQueue(new short[1],0,1,false));
        {
            final GrowableShortList l = new GrowableShortList(1);
            l.add((short)0);
            clone(l);
        }
        {
            final GrowableShortQueue q = new GrowableShortQueue(1);
            q.push((short)0);
            clone(q);
        }
    
        clone(new FixedIntList(new int[1],1));
        clone(new FixedIntQueue(new int[1],0,1,false));
        {
            final GrowableIntList l = new GrowableIntList(1);
            l.add(0);
            clone(l);
        }
        {
            final GrowableIntQueue q = new GrowableIntQueue(1);
            q.push(0);
            clone(q);
        }
    
        clone(new FixedLongList(new long[1],1));
        clone(new FixedLongQueue(new long[1],0,1,false));
        {
            final GrowableLongList l = new GrowableLongList(1);
            l.add(0);
            clone(l);
        }
        {
            final GrowableLongQueue q = new GrowableLongQueue(1);
            q.push(0);
            clone(q);
        }
    
        clone(new FixedFloatList(new float[1],1));
        clone(new FixedFloatQueue(new float[1],0,1,false));
        {
            final GrowableFloatList l = new GrowableFloatList(1);
            l.add(0);
            clone(l);
        }
        {
            final GrowableFloatQueue q = new GrowableFloatQueue(1);
            q.push(0);
            clone(q);
        }
        
        clone(new FixedDoubleList(new double[1],1));
        clone(new FixedDoubleQueue(new double[1],0,1,false));
        {
            final GrowableDoubleList l = new GrowableDoubleList(1);
            l.add(0);
            clone(l);
        }
        {
            final GrowableDoubleQueue q = new GrowableDoubleQueue(1);
            q.push(0);
            clone(q);
        }
    }
}