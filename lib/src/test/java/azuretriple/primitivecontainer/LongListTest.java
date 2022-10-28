package azuretriple.primitivecontainer;

import azuretriple.assertionutil.AssertionUtil;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class LongListTest
{
    @Test
    void testGet()
    {
        final Class<?>[] pair = AssertionUtil.getAssertionPair(FixedLongList.class);
        try
        {
            {
                final Object l = pair[0].
                    getDeclaredConstructor(int.class).
                    newInstance(2);
                pair[0].getMethod("add",long.class).invoke(l,(long)1);
                final Method m = pair[0].getMethod("get",int.class);
                assertEquals(1,(long)m.invoke(l,0));
                assertThrows(InvocationTargetException.class,() -> m.invoke(l,1));
            }
            {
                final Object l = pair[1].
                    getDeclaredConstructor(int.class).
                    newInstance(2);
                pair[1].getMethod("add",long.class).invoke(l,(long)1);
                final Method m = pair[1].getMethod("get",int.class);
                assertEquals(1,(long)m.invoke(l,0));
                // Dummy call to satisfy the coverage report for assertions.
                m.invoke(l,1);
            }
        }
        catch(NoSuchMethodException|InstantiationException|IllegalAccessException|InvocationTargetException e) {fail(e);}
    }
    
    @Test
    void testSet()
    {
        final Class<?>[] pair = AssertionUtil.getAssertionPair(FixedLongList.class);
        try
        {
            {
                final Object l = pair[0].
                    getDeclaredConstructor(int.class).
                    newInstance(2);
                pair[0].getMethod("add",long.class).invoke(l,0);
                final Method set = pair[0].getMethod("set",int.class,long.class);
                set.invoke(l,0,1);
                assertEquals(1,(long)pair[0].getMethod("get",int.class).invoke(l,0));
                assertThrows(InvocationTargetException.class,() -> set.invoke(l,1,0));
            }
            {
                final Object l = pair[1].
                    getDeclaredConstructor(int.class).
                    newInstance(2);
                pair[1].getMethod("add",long.class).invoke(l,0);
                final Method set = pair[1].getMethod("set",int.class,long.class);
                set.invoke(l,0,1);
                assertEquals(1,(long)pair[1].getMethod("get",int.class).invoke(l,0));
                // Dummy call to satisfy the coverage report for assertions.
                set.invoke(l,1,0);
            }
        }
        catch(NoSuchMethodException|InstantiationException|IllegalAccessException|InvocationTargetException e) {fail(e);}
    }
    
    @Test
    void testAdd()
    {
        final FixedLongList l = new FixedLongList(4);
        l.add(new long[] {0,1,2});
        assertEquals(3,l.size);
        l.add(3);
        assertEquals(4,l.size);
        assertArrayEquals(new long[] {0,1,2,3},l.data());
    }
    
    @Test
    void testAddRange()
    {
        final FixedLongList l = new FixedLongList(4);
        l.add(new long[] {-1,0,1,2,-1},1,3);
        assertEquals(3,l.size);
        l.add(3);
        assertEquals(4,l.size);
        assertArrayEquals(new long[] {0,1,2,3},l.data());
    }
    
    @Test
    void testInsert()
    {
        final FixedLongList l = new FixedLongList(4);
        l.add(3);
        l.insert(0,new long[] {1,2});
        assertEquals(3,l.size);
        l.insert(0,0);
        assertEquals(4,l.size);
        assertArrayEquals(new long[] {0,1,2,3},l.data());
    }
    
    @Test
    void testInsertRange()
    {
        final FixedLongList l = new FixedLongList(4);
        l.add(3);
        l.insert(0,new long[] {-1,1,2,-1},1,2);
        assertEquals(3,l.size);
        l.insert(0,0);
        assertEquals(4,l.size);
        assertArrayEquals(new long[] {0,1,2,3},l.data());
    }
    
    @Test
    void testPop()
    {
        final FixedLongList l = new FixedLongList(new long[] {1,2},2);
        assertEquals(2,l.pop());
        assertEquals(1,l.pop());
    }
    
    @Test
    void testExtract()
    {
        final FixedLongList l = new FixedLongList(new long[] {0,1,2},3);
        assertEquals(1,l.extract(1));
        assertArrayEquals(new long[] {0,2},l.array());
    }
}