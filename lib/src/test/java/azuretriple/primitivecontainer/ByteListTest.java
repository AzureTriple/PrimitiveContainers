package azuretriple.primitivecontainer;

import azuretriple.assertionutil.AssertionUtil;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class ByteListTest
{
    @Test
    void testGet()
    {
        final Class<?>[] pair = AssertionUtil.getAssertionPair(FixedByteList.class);
        try
        {
            {
                final Object l = pair[0].
                    getDeclaredConstructor(int.class).
                    newInstance(2);
                pair[0].getMethod("add",byte.class).invoke(l,(byte)1);
                final Method m = pair[0].getMethod("get",int.class);
                assertEquals((byte)1,(byte)m.invoke(l,0));
                assertThrows(InvocationTargetException.class,() -> m.invoke(l,1));
            }
            {
                final Object l = pair[1].
                    getDeclaredConstructor(int.class).
                    newInstance(2);
                pair[1].getMethod("add",byte.class).invoke(l,(byte)1);
                final Method m = pair[1].getMethod("get",int.class);
                assertEquals((byte)1,(byte)m.invoke(l,0));
                // Dummy call to satisfy the coverage report for assertions.
                m.invoke(l,1);
            }
        }
        catch(NoSuchMethodException|InstantiationException|IllegalAccessException|InvocationTargetException e) {fail(e);}
    }
    
    @Test
    void testSet()
    {
        final Class<?>[] pair = AssertionUtil.getAssertionPair(FixedByteList.class);
        try
        {
            {
                final Object l = pair[0].
                    getDeclaredConstructor(int.class).
                    newInstance(2);
                pair[0].getMethod("add",byte.class).invoke(l,(byte)0);
                final Method m = pair[0].getMethod("set",int.class,byte.class);
                m.invoke(l,0,(byte)1);
                assertEquals((byte)1,(byte)pair[0].getMethod("get",int.class).invoke(l,0));
                assertThrows(InvocationTargetException.class,() -> m.invoke(l,1,(byte)0));
            }
            {
                final Object l = pair[1].
                    getDeclaredConstructor(int.class).
                    newInstance(2);
                pair[1].getMethod("add",byte.class).invoke(l,(byte)0);
                final Method m = pair[1].getMethod("set",int.class,byte.class);
                m.invoke(l,0,(byte)1);
                assertEquals((byte)1,(byte)pair[1].getMethod("get",int.class).invoke(l,0));
                // Dummy call to satisfy the coverage report for assertions.
                m.invoke(l,1,(byte)0);
            }
        }
        catch(NoSuchMethodException|InstantiationException|IllegalAccessException|InvocationTargetException e) {fail(e);}
    }
    
    @Test
    void testAdd()
    {
        final FixedByteList l = new FixedByteList(4);
        l.add(new byte[] {0,1,2});
        assertEquals(3,l.size);
        l.add((byte)3);
        assertEquals(4,l.size);
        assertArrayEquals(new byte[] {0,1,2,3},l.data());
    }
    
    @Test
    void testAddRange()
    {
        final FixedByteList l = new FixedByteList(4);
        l.add(new byte[] {-1,0,1,2,-1},1,3);
        assertEquals(3,l.size);
        l.add((byte)3);
        assertEquals(4,l.size);
        assertArrayEquals(new byte[] {0,1,2,3},l.data());
    }
    
    @Test
    void testInsert()
    {
        final FixedByteList l = new FixedByteList(4);
        l.add((byte)3);
        l.insert(0,new byte[] {1,2});
        assertEquals(3,l.size);
        l.insert(0,(byte)0);
        assertEquals(4,l.size);
        assertArrayEquals(new byte[] {0,1,2,3},l.data());
    }
    
    @Test
    void testInsertRange()
    {
        final FixedByteList l = new FixedByteList(4);
        l.add((byte)3);
        l.insert(0,new byte[] {-1,1,2,-1},1,2);
        assertEquals(3,l.size);
        l.insert(0,(byte)0);
        assertEquals(4,l.size);
        assertArrayEquals(new byte[] {0,1,2,3},l.data());
    }
    
    @Test
    void testPop()
    {
        final FixedByteList l = new FixedByteList(new byte[] {1,2},2);
        assertEquals((byte)2,l.pop());
        assertEquals((byte)1,l.pop());
    }
    
    @Test
    void testExtract()
    {
        final FixedByteList l = new FixedByteList(new byte[] {0,1,2},3);
        assertEquals((byte)1,l.extract(1));
        assertArrayEquals(new byte[] {0,2},l.array());
    }
}