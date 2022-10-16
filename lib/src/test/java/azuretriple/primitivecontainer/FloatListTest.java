package azuretriple.primitivecontainer;

import azuretriple.assertionutil.AssertionUtil;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class FloatListTest
{
    @Test
    void testGet()
    {
        final Class<?>[] pair = AssertionUtil.getAssertionPair(FixedFloatList.class);
        try
        {
            {
                final Object l = pair[0].
                    getDeclaredConstructor(int.class).
                    newInstance(2);
                pair[0].getMethod("add",float.class).invoke(l,(float)0);
                pair[0].getMethod("set",int.class,float.class).invoke(l,0,(float)1);
                final Method m = pair[0].getMethod("get",int.class);
                assertEquals((float)1,(float)m.invoke(l,0));
                assertThrows(InvocationTargetException.class,() -> m.invoke(l,1));
            }
            {
                final Object l = pair[1].
                    getDeclaredConstructor(int.class).
                    newInstance(2);
                pair[1].getMethod("add",float.class).invoke(l,(float)0);
                pair[1].getMethod("set",int.class,float.class).invoke(l,0,(float)1);
                final Method m = pair[1].getMethod("get",int.class);
                assertEquals((float)1,(float)m.invoke(l,0));
                // Dummy call to satisfy the coverage report for assertions.
                m.invoke(l,1);
            }
        }
        catch(NoSuchMethodException|InstantiationException|IllegalAccessException|InvocationTargetException e) {fail(e);}
    }
    
    @Test
    void testSet()
    {
        final Class<?>[] pair = AssertionUtil.getAssertionPair(FixedFloatList.class);
        try
        {
            {
                final Object l = pair[0].
                    getDeclaredConstructor(int.class).
                    newInstance(2);
                pair[0].getMethod("add",float.class).invoke(l,(float)0);
                final Method m = pair[0].getMethod("set",int.class,float.class);
                m.invoke(l,0,(float)1);
                assertEquals((float)1,(float)pair[0].getMethod("get",int.class).invoke(l,0));
                assertThrows(InvocationTargetException.class,() -> m.invoke(l,1,(float)0));
            }
            {
                final Object l = pair[1].
                    getDeclaredConstructor(int.class).
                    newInstance(2);
                pair[1].getMethod("add",float.class).invoke(l,(float)0);
                final Method m = pair[1].getMethod("set",int.class,float.class);
                m.invoke(l,0,(float)1);
                assertEquals((float)1,(float)pair[1].getMethod("get",int.class).invoke(l,0));
                // Dummy call to satisfy the coverage report for assertions.
                m.invoke(l,1,(float)0);
            }
        }
        catch(NoSuchMethodException|InstantiationException|IllegalAccessException|InvocationTargetException e) {fail(e);}
    }
    
    @Test
    void testAdd()
    {
        final FixedFloatList l = new FixedFloatList(4);
        l.add(new float[] {0,1,2});
        assertEquals(3,l.size);
        l.add((float)3);
        assertEquals(4,l.size);
        assertArrayEquals(new float[] {0,1,2,3},l.data());
    }
    
    @Test
    void testInsert()
    {
        final FixedFloatList l = new FixedFloatList(4);
        l.add((float)3);
        l.insert(0,new float[] {1,2});
        assertEquals(3,l.size);
        l.insert(0,(float)0);
        assertEquals(4,l.size);
        assertArrayEquals(new float[] {0,1,2,3},l.data());
    }
    
    @Test
    void testPop()
    {
        final FixedFloatList l = new FixedFloatList(new float[] {1,2},2);
        assertEquals((float)2,l.pop());
        assertEquals((float)1,l.pop());
    }
    
    @Test
    void testExtract()
    {
        final FixedFloatList l = new FixedFloatList(new float[] {0,1,2},3);
        assertEquals((float)1,l.extract(1));
        assertArrayEquals(new float[] {0,2},l.array());
    }
}