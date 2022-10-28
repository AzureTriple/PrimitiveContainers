package azuretriple.primitivecontainer;

import azuretriple.assertionutil.AssertionUtil;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class ShortListTest
{
    @Test
    void testGet()
    {
        final Class<?>[] pair = AssertionUtil.getAssertionPair(FixedShortList.class);
        try
        {
            {
                final Object l = pair[0].
                    getDeclaredConstructor(int.class).
                    newInstance(2);
                pair[0].getMethod("add",short.class).invoke(l,(short)1);
                final Method m = pair[0].getMethod("get",int.class);
                assertEquals((short)1,(short)m.invoke(l,0));
                assertThrows(InvocationTargetException.class,() -> m.invoke(l,(short)1));
            }
            {
                final Object l = pair[1].
                    getDeclaredConstructor(int.class).
                    newInstance(2);
                pair[1].getMethod("add",short.class).invoke(l,(short)1);
                final Method m = pair[1].getMethod("get",int.class);
                assertEquals((short)1,(short)m.invoke(l,0));
                // Dummy call to satisfy the coverage report for assertions.
                m.invoke(l,(short)1);
            }
        }
        catch(NoSuchMethodException|InstantiationException|IllegalAccessException|InvocationTargetException e) {fail(e);}
    }
    
    @Test
    void testSet()
    {
        final Class<?>[] pair = AssertionUtil.getAssertionPair(FixedShortList.class);
        try
        {
            {
                final Object l = pair[0].
                    getDeclaredConstructor(int.class).
                    newInstance(2);
                pair[0].getMethod("add",short.class).invoke(l,(short)0);
                final Method m = pair[0].getMethod("set",int.class,short.class);
                m.invoke(l,0,(short)1);
                assertEquals((short)1,(short)pair[0].getMethod("get",int.class).invoke(l,0));
                assertThrows(InvocationTargetException.class,() -> m.invoke(l,1,(short)0));
            }
            {
                final Object l = pair[1].
                    getDeclaredConstructor(int.class).
                    newInstance(2);
                pair[1].getMethod("add",short.class).invoke(l,(short)0);
                final Method m = pair[1].getMethod("set",int.class,short.class);
                m.invoke(l,0,(short)1);
                assertEquals((short)1,(short)pair[1].getMethod("get",int.class).invoke(l,0));
                // Dummy call to satisfy the coverage report for assertions.
                m.invoke(l,1,(short)0);
            }
        }
        catch(NoSuchMethodException|InstantiationException|IllegalAccessException|InvocationTargetException e) {fail(e);}
    }
    
    @Test
    void testAdd()
    {
        final FixedShortList l = new FixedShortList(4);
        l.add(new short[] {0,1,2});
        assertEquals(3,l.size);
        l.add((short)3);
        assertEquals(4,l.size);
        assertArrayEquals(new short[] {0,1,2,3},l.data());
    }
    
    @Test
    void testAddRange()
    {
        final FixedShortList l = new FixedShortList(4);
        l.add(new short[] {-1,0,1,2,-1},1,3);
        assertEquals(3,l.size);
        l.add((short)3);
        assertEquals(4,l.size);
        assertArrayEquals(new short[] {0,1,2,3},l.data());
    }
    
    @Test
    void testInsert()
    {
        final FixedShortList l = new FixedShortList(4);
        l.add((short)3);
        l.insert(0,new short[] {1,2});
        assertEquals(3,l.size);
        l.insert(0,(short)0);
        assertEquals(4,l.size);
        assertArrayEquals(new short[] {0,1,2,3},l.data());
    }
    
    @Test
    void testInsertRange()
    {
        final FixedShortList l = new FixedShortList(4);
        l.add((short)3);
        l.insert(0,new short[] {-1,1,2,-1},1,2);
        assertEquals(3,l.size);
        l.insert(0,(short)0);
        assertEquals(4,l.size);
        assertArrayEquals(new short[] {0,1,2,3},l.data());
    }
    
    @Test
    void testPop()
    {
        final FixedShortList l = new FixedShortList(new short[] {1,2},2);
        assertEquals((short)2,l.pop());
        assertEquals((short)1,l.pop());
    }
    
    @Test
    void testExtract()
    {
        final FixedShortList l = new FixedShortList(new short[] {0,1,2},3);
        assertEquals((short)1,l.extract(1));
        assertArrayEquals(new short[] {0,2},l.array());
    }
}