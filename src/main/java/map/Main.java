package map;

import exceptions.PutException;

public class Main {
    public static void main(String[] args) {
        MyHashMap myHashMap = new MyHashMap();
        int size = 17;
        for (int i = 0; i < size; i++) {
            try {
                myHashMap.put(i * 39, i);
            } catch (PutException e) {
                e.printStackTrace();
            }
        }
        System.out.println(myHashMap.size());

        for (int i = 0; i < size; i++) {
            try {
                System.out.println(myHashMap.get(i * 39));
            } catch (PutException e) {
                e.printStackTrace();
            }
        }
    }
}
