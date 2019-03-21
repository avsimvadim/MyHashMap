package map;

import exceptions.GetException;
import org.junit.*;

public class MyHashMapTest {

    private MyHashMapInterface myHashMap;

    @Before
    public void setUp() throws Exception {
        myHashMap = new MyHashMap();
        for (int i = 0; i < 30; i++) {
            myHashMap.put(i * 3, i);
        }
    }

    @After
    public void tearDown() throws Exception {
        myHashMap = null;
    }

    @Test
    public void putTestSizeEquality() throws Exception{
        Assert.assertEquals(30,myHashMap.size());
    }

    @Test
    public void putTestNumberEquality() throws Exception{
        Assert.assertEquals(29,myHashMap.get(87));
    }

    @Test
    public void get() throws Exception{
        Assert.assertEquals(3, myHashMap.get(9));
    }

    @Test(expected = GetException.class)
    public void getException() throws Exception{
        myHashMap.get(-1);
    }

    @Test
    public void size() throws Exception{
        Assert.assertEquals(30,myHashMap.size());
    }
}