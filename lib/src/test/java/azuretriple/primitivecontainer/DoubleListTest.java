package azuretriple.primitivecontainer;

import azuretriple.assertionutil.AssertionUtil;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class DoubleListTest
{
    @Test
    void testGet()
    {
        final Class<?>[] pair = AssertionUtil.getAssertionPair(FixedDoubleList.class);
        try
        {
            {
                final Object l = pair[0].
                    getDeclaredConstructor(int.class).
                    newInstance(2);
                pair[0].getMethod("add",double.class).invoke(l,1);
                final Method m = pair[0].getMethod("get",int.class);
                assertEquals(1,(double)m.invoke(l,0));
                assertThrows(InvocationTargetException.class,() -> m.invoke(l,1));
            }
            {
                final Object l = pair[1].
                    getDeclaredConstructor(int.class).
                    newInstance(2);
                pair[1].getMethod("add",double.class).invoke(l,1);
                final Method m = pair[1].getMethod("get",int.class);
                assertEquals(1,(double)m.invoke(l,0));
                // Dummy call to satisfy the coverage report for assertions.
                m.invoke(l,1);
            }
        }
        catch(NoSuchMethodException|InstantiationException|IllegalAccessException|InvocationTargetException e) {fail(e);}
    }
    
    @Test
    void testSet()
    {
        final Class<?>[] pair = AssertionUtil.getAssertionPair(FixedDoubleList.class);
        try
        {
            {
                final Object l = pair[0].
                    getDeclaredConstructor(int.class).
                    newInstance(2);
                pair[0].getMethod("add",double.class).invoke(l,(double)0);
                final Method m = pair[0].getMethod("set",int.class,double.class);
                m.invoke(l,0,(double)1);
                assertEquals(1,(double)pair[0].getMethod("get",int.class).invoke(l,0));
                assertThrows(InvocationTargetException.class,() -> m.invoke(l,1,(double)0));
            }
            {
                final Object l = pair[1].
                    getDeclaredConstructor(int.class).
                    newInstance(2);
                pair[1].getMethod("add",double.class).invoke(l,(double)0);
                final Method m = pair[1].getMethod("set",int.class,double.class);
                m.invoke(l,0,(double)1);
                assertEquals(1,(double)pair[1].getMethod("get",int.class).invoke(l,0));
                // Dummy call to satisfy the coverage report for assertions.
                m.invoke(l,1,(double)0);
            }
        }
        catch(NoSuchMethodException|InstantiationException|IllegalAccessException|InvocationTargetException e) {fail(e);}
    }
    
    @Test
    void testAdd()
    {
        final FixedDoubleList l = new FixedDoubleList(4);
        l.add(new double[] {0,1,2});
        assertEquals(3,l.size);
        l.add(3);
        assertEquals(4,l.size);
        assertArrayEquals(new double[] {0,1,2,3},l.data());
    }
    
    @Test
    void testAddRange()
    {
        final FixedDoubleList l = new FixedDoubleList(4);
        l.add(new double[] {-1,0,1,2,-1},1,3);
        assertEquals(3,l.size);
        l.add(3);
        assertEquals(4,l.size);
        assertArrayEquals(new double[] {0,1,2,3},l.data());
    }
    
    @Test
    void testInsert()
    {
        final FixedDoubleList l = new FixedDoubleList(4);
        l.add(3);
        l.insert(0,new double[] {1,2});
        assertEquals(3,l.size);
        l.insert(0,0);
        assertEquals(4,l.size);
        assertArrayEquals(new double[] {0,1,2,3},l.data());
    }
    
    @Test
    void testInsertRange()
    {
        final FixedDoubleList l = new FixedDoubleList(4);
        l.add(3);
        l.insert(0,new double[] {-1,1,2,-1},1,2);
        assertEquals(3,l.size);
        l.insert(0,0);
        assertEquals(4,l.size);
        assertArrayEquals(new double[] {0,1,2,3},l.data());
    }
    
    @Test
    void testPop()
    {
        final FixedDoubleList l = new FixedDoubleList(new double[] {1,2},2);
        assertEquals(2,l.pop());
        assertEquals(1,l.pop());
    }
    
    @Test
    void testExtract()
    {
        final FixedDoubleList l = new FixedDoubleList(new double[] {0,1,2},3);
        assertEquals(1,l.extract(1));
        assertArrayEquals(new double[] {0,2},l.array());
    }
}