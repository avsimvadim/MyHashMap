package map;

import exceptions.PutException;

public class MyHashMap implements MyHashMapInterface {

    private int capacity;
    private final float loadFactor;
    private int size = 0;
    private int hashCode = 0;
    private Long[][] buckets;

    public MyHashMap(){
        this.capacity = 16;
        this.loadFactor = 0.5f;
        buckets = new Long[16][2];
    }

    public MyHashMap(int capacity){
        // TODO: 3/21/2019 capacity < 1
        this.capacity = capacity;
        this.loadFactor = 0.5f;
        buckets = new Long[capacity][2];
    }

    public MyHashMap(int capacity, float loadFactor) {
        // TODO: 3/21/2019  capacity < 1 loadfactor <=0
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        buckets = new Long[capacity][2];
    }

    public long put(int key, long value) throws PutException {
        hashCode = hash(key);
        int position = hashCode % capacity;

        if (buckets[position][0] == null){
            buckets[position][0] = (long)key;
            buckets[position][1] = value;
            size++;
            rehashCheck();
            return value;
        }

        if (buckets[position][0] != null){
            if (buckets[position][0].equals(key)){
                long oldValue = buckets[position][1];
                buckets[position][1] = value;
                size++;
                rehashCheck();
                return oldValue;
            } else {
                int hashCode2 = hashcode2Result(key);

                if (7 - (key % 7) != 0){
                    hashCode2 = 7 - (key % 7);
                } else {
                    hashCode2 = (key % capacity) + 1;
                }

                for (int i = 0; i < capacity; i++) {
                    position = (hashCode + i * hashCode2) % capacity;

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

    public long get(int key) throws PutException{
        hashCode = hash(key);
        int position = hashCode % capacity;

        if (buckets[position][0] == key){
            return buckets[position][1];
        } else {
            int hashCode2 = hashcode2Result(key);

            for (int i = 0; i < capacity; i++) {
                position = (hashCode + i * hashCode2) % capacity;
                if (buckets[position][0].equals(key)) {
                    return buckets[position][1];
                }
            }
            throw new PutException("could not find the key");
        }
    }

    public int size() {
        return size;
    }

    private int hash(int number){
        return number;
    }

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

    private int hashcode2Result(int key){
        if (7 - (key % 7) != 0) {
            return  7 - (key % 7);
        }
        else {
            return (key % capacity) + 1;
        }
    }

}
