package azuretriple.primitivecontainer;

import azuretriple.assertionutil.AssertionUtil;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class LongToDoubleMapTest
{
    @Test
    void testInit()
    {
        final Class<?>[] pair = AssertionUtil.getAssertionPair(LongToDoubleMap.class),
                         ksPair = AssertionUtil.getAssertionPair(LongSet.class),
                         kolPair = AssertionUtil.getAssertionPair(OrderedLongList.class),
                         kflPair = AssertionUtil.getAssertionPair(FixedLongList.class),
                         vflPair = AssertionUtil.getAssertionPair(FixedDoubleList.class),
                         klPair = AssertionUtil.getAssertionPair(LongList.class),
                         vlPair = AssertionUtil.getAssertionPair(DoubleList.class);
        try
        {
            {
                final Object k = ksPair[0].getDeclaredConstructor(kolPair[0]).
                                           newInstance
                                           (
                                               kolPair[0].getDeclaredConstructor(klPair[0]).
                                                          newInstance
                                                          (
                                                              kflPair[0].getDeclaredConstructor(int.class).
                                                                         newInstance(1)
                                                          )
                                           );
                final Object v = vflPair[0].getDeclaredConstructor(int.class).newInstance(1);
                final Constructor<?> init = pair[0].getDeclaredConstructor(ksPair[0],vlPair[0]);
                {
                    final Method keys = pair[0].getDeclaredMethod("keys"),values = pair[0].getDeclaredMethod("values");
                    final Object o = init.newInstance(k,v);
                    assertSame(k,keys.invoke(o));
                    assertSame(v,values.invoke(o));
                    assertEquals(o,pair[0].getDeclaredConstructor(pair[0]).newInstance(o));
                }
                ksPair[0].getDeclaredMethod("add",long.class).invoke(k,(long)0);
                assertThrows(InvocationTargetException.class,() -> init.newInstance(k,v));
            }
            {
                final Object k = ksPair[1].getDeclaredConstructor(kolPair[1]).
                                           newInstance
                                           (
                                               kolPair[1].getDeclaredConstructor(klPair[1]).
                                                          newInstance
                                                          (
                                                              kflPair[1].getDeclaredConstructor(int.class).
                                                                         newInstance(1)
                                                          )
                                           );
                final Object v = vflPair[1].getDeclaredConstructor(int.class).newInstance(1);
                final Constructor<?> init = pair[1].getDeclaredConstructor(ksPair[1],vlPair[1]);
                {
                    final Method keys = pair[1].getDeclaredMethod("keys"),values = pair[1].getDeclaredMethod("values");
                    final Object o = init.newInstance(k,v);
                    assertSame(k,keys.invoke(o));
                    assertSame(v,values.invoke(o));
                    assertEquals(o,pair[1].getDeclaredConstructor(pair[1]).newInstance(o));
                }
                ksPair[1].getDeclaredMethod("add",long.class).invoke(k,(long)0);
                // Dummy call to satisfy the coverage report for assertions.
                init.newInstance(k,v);
            }
        }
        catch(NoSuchMethodException|InstantiationException|IllegalAccessException|InvocationTargetException e) {fail(e);}
    }

    @Test
    void testInsert()
    {
        final LongToDoubleMap m = new LongToDoubleMap(new LongSet(new OrderedLongList(new FixedLongList(1))),new FixedDoubleList(1));
        m.insert(1,2);
        assertTrue(m.keys().contains(1));
        assertEquals(2,m.get(1));
    }

    @Test
    void testPut()
    {
        final LongToDoubleMap m = new LongToDoubleMap(new LongSet(new OrderedLongList(new FixedLongList(1))),new FixedDoubleList(1));
        m.put(1,2);
        assertTrue(m.keys().contains(1));
        assertEquals(2,m.get(1));
        m.put(1,3);
        assertTrue(m.keys().contains(1));
        assertEquals(3,m.get(1));
        m.put(1,3);
        assertTrue(m.keys().contains(1));
        assertEquals(3,m.get(1));
    }

    @Test
    void testAdd()
    {
        final LongToDoubleMap m = new LongToDoubleMap(new LongSet(new OrderedLongList(new FixedLongList(1))),new FixedDoubleList(1));
        assertTrue(m.add(1,2));
        assertTrue(m.keys().contains(1));
        assertEquals(2,m.get(1));
        assertTrue(m.add(1,3));
        assertEquals(3,m.get(1));
        assertFalse(m.add(1,3));
    }

    @Test
    void testGet()
    {
        final LongToDoubleMap m = new LongToDoubleMap(new LongSet(new OrderedLongList(new FixedLongList(1))),new FixedDoubleList(1));
        m.insert(1,2);
        assertEquals(2,m.get(1));
    }

    @Test
    void testRemove()
    {
        final LongToDoubleMap m = new LongToDoubleMap(new LongSet(new OrderedLongList(new FixedLongList(1))),new FixedDoubleList(1));
        assertFalse(m.remove(1));
        m.insert(1,2);
        assertTrue(m.remove(1));
    }

    @Test
    void testDelete()
    {
        final LongToDoubleMap m = new LongToDoubleMap(new LongSet(new OrderedLongList(new FixedLongList(1))),new FixedDoubleList(1));
        m.insert(1,2);
        m.delete(1);
        assertFalse(m.keys().contains(1));
    }

    @Test
    void testExtract()
    {
        final LongToDoubleMap m = new LongToDoubleMap(new LongSet(new OrderedLongList(new FixedLongList(1))),new FixedDoubleList(1));
        m.insert(1,2);
        assertEquals(2,m.extract(1));
    }

    @Test
    void testClone()
    {
        final LongToDoubleMap m = new LongToDoubleMap(new LongSet(new OrderedLongList(new FixedLongList(1))),new FixedDoubleList(1)),
                            m2 = m.clone();
        assertEquals(m,m2);
        m.insert(1,2);
        assertNotEquals(m,m2);
    }

    @Test
    void testKeys()
    {
        final LongSet k = new LongSet(new OrderedLongList(new FixedLongList(1)));
        assertSame(k,new LongToDoubleMap(k,new FixedDoubleList(1)).keys());
    }

    @Test
    void testValues()
    {
        final DoubleList v = new FixedDoubleList(1);
        assertSame(v,new LongToDoubleMap(new LongSet(new OrderedLongList(new FixedLongList(1))),v).values());
    }
}