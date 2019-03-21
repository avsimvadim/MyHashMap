package map;

import org.junit.*;

import static org.junit.Assert.*;

public class MyHashMapTest {

    private MyHashMapInterface myHashMap;

    @Test
    public void putTestSizeEquality() throws Exception{
        myHashMap = new MyHashMap();
        for (int i = 0; i < 30; i++) {
            myHashMap.put(i * 3, i);
        }
        Assert.assertEquals(30,myHashMap.size());
    }

    @Test
    public void putTestNumberEquality() throws Exception{
        myHashMap = new MyHashMap();
        for (int i = 0; i < 27; i++) {
            myHashMap.put(i * 5, i);
        }
        Assert.assertEquals(26,myHashMap.get(130));
    }

    @Test
    public void putTestNumber1Equality() throws Exception{
        myHashMap = new MyHashMap();
        myHashMap.put(Integer.MAX_VALUE * 3, 1);

    }

    @Test
    public void get() throws Exception{
    }

    @Test
    public void size() throws Exception{
        myHashMap = new MyHashMap();
        for (int i = 0; i < 20; i++) {
            myHashMap.put(i, 1);
        }
        Assert.assertEquals(20,myHashMap.size());
    }
}