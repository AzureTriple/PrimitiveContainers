package azuretriple.primitivecontainer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrowableListTest
{
    @Test void testInit() {assertDoesNotThrow(() -> new GrowableByteList(new GrowableByteList(new byte[0],0)));}
    
    @Test
    void testClear()
    {
        final GrowableByteList l = new GrowableByteList(32);
        l.clear();
        assertEquals(Growable.DEFAULT_SIZE,l.data().length);
    }
    
    @Test
    void testAdd()
    {
        final GrowableByteList l = new GrowableByteList(4);
        l.add(new byte[0]);
        assertTrue(l.empty());
        l.add(new byte[] {0,1,2,3,4});
        assertEquals(5,l.size());
        assertArrayEquals(new byte[] {0,1,2,3,4,0,0,0},l.data());
        l.add(new byte[] {5,6});
        assertEquals(7,l.size());
        assertArrayEquals(new byte[] {0,1,2,3,4,5,6,0},l.data());
    }
    
    @Test
    void testAddRange()
    {
        final GrowableByteList l = new GrowableByteList(4);
        l.add(new byte[] {-1,-1},1,0);
        assertTrue(l.empty());
        l.add(new byte[] {-1,0,1,2,3,4,-1},1,5);
        assertEquals(5,l.size());
        assertArrayEquals(new byte[] {0,1,2,3,4,0,0,0},l.data());
        l.add(new byte[] {-1,5,6,-1},1,2);
        assertEquals(7,l.size());
        assertArrayEquals(new byte[] {0,1,2,3,4,5,6,0},l.data());
    }
    
    @Test
    void testAddLogic()
    {
        final GrowableByteList l = new GrowableByteList(new byte[1],1);
        l.addLogic();
        assertEquals(2,l.data().length);
        l.addLogic();
        assertEquals(2,l.data().length);
    }
    
    @Test
    void testInsert()
    {
        final GrowableByteList l = new GrowableByteList(new byte[] {0,5},2);
        l.insert(1,new byte[0]);
        assertEquals(2,l.size());
        assertArrayEquals(new byte[] {0,5},l.data());
        l.insert(1,new byte[] {1,2,3});
        assertEquals(5,l.size());
        assertArrayEquals(new byte[] {0,1,2,3,5,0,0,0},l.data());
        l.insert(4,new byte[] {4});
        assertEquals(6,l.size());
        assertArrayEquals(new byte[] {0,1,2,3,4,5,0,0},l.data());
    }
    
    @Test
    void testInsertRange()
    {
        final GrowableByteList l = new GrowableByteList(new byte[] {0,5},2);
        l.insert(1,new byte[] {-1,-1},1,0);
        assertEquals(2,l.size());
        assertArrayEquals(new byte[] {0,5},l.data());
        l.insert(1,new byte[] {-1,1,2,3,-1},1,3);
        assertEquals(5,l.size());
        assertArrayEquals(new byte[] {0,1,2,3,5,0,0,0},l.data());
        l.insert(4,new byte[] {4},0,1);
        assertEquals(6,l.size());
        assertArrayEquals(new byte[] {0,1,2,3,4,5,0,0},l.data());
    }
    
    @Test
    void testInsertLogic()
    {
        {
            final GrowableByteList l = new GrowableByteList(1);
            l.insertLogic(0);
            assertEquals(1,l.size());
            assertEquals(1,l.data().length);
        }
        {
            final GrowableByteList l = new GrowableByteList(new byte[] {0,2},2);
            l.insertLogic(1);
            assertEquals(3,l.size());
            assertArrayEquals(new byte[] {0,2,2,0},l.data());
        }
    }
    
    @Test
    void testDelete()
    {
        {
            final GrowableByteList l = new GrowableByteList(new byte[]{1,2,0,0,0,0,0,0},2);
            l.delete();
            assertEquals(4,l.data().length);
        }
        {
            final GrowableByteList l = new GrowableByteList(new byte[] {1,2,3,0},3);
            l.delete();
            assertEquals(4,l.data().length);
        }
    }
    
    @Test
    void testRemove()
    {
        {
            final GrowableByteList l = new GrowableByteList(new byte[]{1,2,0,0,0,0,0,0},2);
            l.remove(0);
            assertEquals(4,l.data().length);
            assertEquals((byte)2,l.get(0));
        }
        {
            final GrowableByteList l = new GrowableByteList(new byte[] {1,2,3,0},3);
            l.remove(1);
            assertEquals(4,l.data().length);
            assertEquals((byte)1,l.get(0));
            assertEquals((byte)3,l.get(1));
        }
    }
}