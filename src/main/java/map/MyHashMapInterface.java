package map;

import exceptions.GetException;
import exceptions.PutException;

public interface MyHashMapInterface {

    public long put(int key, long value) throws PutException;

    public long get(int key) throws GetException;

    public int size();

}
