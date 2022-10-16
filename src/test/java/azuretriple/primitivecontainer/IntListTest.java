package azuretriple.primitivecontainer;

import azuretriple.assertionutil.AssertionUtil;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class IntListTest
{
    @Test
    void testGet()
    {
        final Class<?>[] pair = AssertionUtil.getAssertionPair(FixedIntList.class);
        try
        {
            {
                final Object l = pair[0].
                    getDeclaredConstructor(int.class).
                    newInstance(2);
                pair[0].getMethod("add",int.class).invoke(l,0);
                pair[0].getMethod("set",int.class,int.class).invoke(l,0,1);
                final Method m = pair[0].getMethod("get",int.class);
                assertEquals(1,(int)m.invoke(l,0));
                assertThrows(InvocationTargetException.class,() -> m.invoke(l,1));
            }
            {
                final Object l = pair[1].
                    getDeclaredConstructor(int.class).
                    newInstance(2);
                pair[1].getMethod("add",int.class).invoke(l,0);
                pair[1].getMethod("set",int.class,int.class).invoke(l,0,1);
                final Method m = pair[1].getMethod("get",int.class);
                assertEquals(1,(int)m.invoke(l,0));
                // Dummy call to satisfy the coverage report for assertions.
                m.invoke(l,1);
            }
        }
        catch(NoSuchMethodException|InstantiationException|IllegalAccessException|InvocationTargetException e) {fail(e);}
    }
    
    @Test
    void testSet()
    {
        final Class<?>[] pair = AssertionUtil.getAssertionPair(FixedIntList.class);
        try
        {
            {
                final Object l = pair[0].
                    getDeclaredConstructor(int.class).
                    newInstance(2);
                pair[0].getMethod("add",int.class).invoke(l,0);
                final Method m = pair[0].getMethod("set",int.class,int.class);
                m.invoke(l,0,1);
                assertEquals(1,(int)pair[0].getMethod("get",int.class).invoke(l,0));
                assertThrows(InvocationTargetException.class,() -> m.invoke(l,1,0));
            }
            {
                final Object l = pair[1].
                    getDeclaredConstructor(int.class).
                    newInstance(2);
                pair[1].getMethod("add",int.class).invoke(l,1);
                final Method m = pair[1].getMethod("set",int.class,int.class);
                m.invoke(l,0,1);
                assertEquals(1,(int)pair[1].getMethod("get",int.class).invoke(l,0));
                // Dummy call to satisfy the coverage report for assertions.
                m.invoke(l,1,0);
            }
        }
        catch(NoSuchMethodException|InstantiationException|IllegalAccessException|InvocationTargetException e) {fail(e);}
    }
    
    @Test
    void testAdd()
    {
        final FixedIntList l = new FixedIntList(4);
        l.add(new int[] {0,1,2});
        assertEquals(3,l.size);
        l.add(3);
        assertEquals(4,l.size);
        assertArrayEquals(new int[] {0,1,2,3},l.data());
    }
    
    @Test
    void testInsert()
    {
        final FixedIntList l = new FixedIntList(4);
        l.add(3);
        l.insert(0,new int[] {1,2});
        assertEquals(3,l.size);
        l.insert(0,0);
        assertEquals(4,l.size);
        assertArrayEquals(new int[] {0,1,2,3},l.data());
    }
    
    @Test
    void testPop()
    {
        final FixedIntList l = new FixedIntList(new int[] {1,2},2);
        assertEquals(2,l.pop());
        assertEquals(1,l.pop());
    }
    
    @Test
    void testExtract()
    {
        final FixedIntList l = new FixedIntList(new int[] {0,1,2},3);
        assertEquals(1,l.extract(1));
        assertArrayEquals(new int[] {0,2},l.array());
    }
}