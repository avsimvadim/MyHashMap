package map;

import exceptions.GetException;
import exceptions.PutException;

public class MyHashMap implements MyHashMapInterface {

    private int capacity; //size of the bucket
    private final float loadFactor; // load factor is 0.5 and can not be changed
    private int size = 0; //  size of filled buckets
    private int hashCode;
    private Long[][] buckets; // storage of data

    public MyHashMap(){
        this.capacity = 16;
        this.loadFactor = 0.5f;
        this.hashCode = 0;
        buckets = new Long[16][2];
    }

    //implemented algorithm with double hashing
    // method inserts a key and a value id bucket is null
    // if keys are the same, method changes old value for a new one
    public long put(int key, long value) throws PutException {
        hashCode = hash(key);
        int position = hashCode % capacity;

        // if bucket is empty
        if (buckets[position][0] == null){
            buckets[position][0] = (long)key;
            buckets[position][1] = value;
            size++;
            rehashCheck();
            return value;
        }

        //if bucket is not empty
        if (buckets[position][0] != null){

            // if key in the bucket and given key are the same
            if (buckets[position][0].equals(key)){
                long oldValue = buckets[position][1];
                buckets[position][1] = value;
                size++;
                rehashCheck();
                return oldValue;

            } else { // if key in the bucket and given key are different
                int hashCode2 = hashcode2Result(key);

                for (int i = 0; i < capacity; i++) {
                    position = (hashCode + i * hashCode2) % capacity;

                    // if bucket for key is empty
                    // otherwise if key in the bucket and given key are the same
                    // put value in a relevant bucket
                    if (buckets[position][0] == null){
                        buckets[position][0] = (long)key;
                        buckets[position][1] = value;
                        size++;
                        rehashCheck();
                        return value;
                    } else if(buckets[position][0].equals(key)){
                        long oldValue = buckets[position][1];
                        buckets[position][1] = value;
                        size++;
                        rehashCheck();
                        return oldValue;
                    }

                }
                throw new PutException("could not put value into the map");
            }
        }
        throw new PutException("could not put value into the map");
    }

    // method to find a value
    public long get(int key) throws GetException{
        hashCode = hash(key);
        int position = hashCode % capacity;

        //check, if there is not such key
        if (position < 0 || position > size - 1){
            throw new GetException("wrong key");
        }

        // returns value of a key
        if (buckets[position][0] == key){
            return buckets[position][1];
        } else {// search for a key after second hashing
            int hashCode2 = hashcode2Result(key);

            for (int i = 0; i < capacity; i++) {
                position = (hashCode + i * hashCode2) % capacity;
                if (buckets[position][0].equals(key)) {
                    return buckets[position][1];
                }
            }
            throw new GetException("could not find the key");
        }
    }

    // returns amount of the occupied buckets
    public int size() {
        return size;
    }

    // calculates hashcode
    private int hash(int number){
        return number;
    }

    //rehashing if a number of filled buckets divided by capacity, is bigger than load factor
    private void rehashCheck(){
        if (size / capacity > loadFactor) {
            size = 0;
            Long[][] oldBuckets = buckets;
            capacity = capacity * 2;
            buckets = new Long[capacity][2];

            for (int i = 0; i < capacity / 2; i++) {
                if (oldBuckets[i][0] != null){
                    try {
                        put(oldBuckets[i][0].intValue(), oldBuckets[i][1]);
                    } catch (PutException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

    }

    // second hashing
    private int hashcode2Result(int key){
        if (7 - (key % 7) != 0) {
            return  7 - (key % 7);
        }
        else {
            return (key % capacity) + 1;
        }
    }

}
