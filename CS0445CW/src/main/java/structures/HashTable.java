package structures;

import stores.CastCredit;
import stores.Company;
import utils.LoadData;

public class HashTable<K, V> {
    public Entry<K, V>[] table;
    private int capacity;
    private int size;
    private static final double LOAD_FACTOR_THRESHOLD = 0.7;

    public HashTable(int capacity) {
        this.capacity = capacity;
        table = new Entry[capacity];
        size = 0;
    }

    public void put(K key, V value) {
        int index = hash(key);
        if (table[index] != null) {
            Entry<K, V> entry = table[index];
            while (entry.next != null && !entry.key.equals(key)) {
                entry = entry.next;
            }
            
            entry.next = new Entry<>(key, value);
            size++;
            
        } else {
            table[index] = new Entry<>(key, value);
            size++;
        }

        if ((double) size / capacity >= LOAD_FACTOR_THRESHOLD) {
            rehash();
        }
    }
    
    public void remove(K key) {
        int index = hash(key);
        if (table[index] != null) {
            Entry<K, V> prev = null;
            Entry<K, V> current = table[index];
            while (current != null) {
                if (current.key.equals(key)) {
                    if (prev == null) {
                        table[index] = current.next;
                    } else {
                        prev.next = current.next;
                    }
                    size--;
                    return;
                }
                prev = current;
                current = current.next;
                return;
            }
        }
    }

    public V get(K key) {
        int index = hash(key);
        if (table[index] != null) {
            Entry<K, V> entry = table[index];
            while (entry != null) {
                if (entry.key.equals(key)) {
                    return entry.value;
                }
                entry = entry.next;
            }
        }
        return null;
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    private void rehash() {
        int newCapacity = capacity * 2;
        Entry<K, V>[] newTable = new Entry[newCapacity];

        for (Entry<K, V> entry : table) {
            while (entry != null) {
                int index = hash(entry.key);
                if (newTable[index] == null) {
                    newTable[index] = new Entry<>(entry.key, entry.value);
                } else {
                    Entry<K, V> current = newTable[index];
                    while (current.next != null) {
                        current = current.next;
                    }
                    current.next = new Entry<>(entry.key, entry.value);
                }
                entry = entry.next;
            }
        }

        table = newTable;
        capacity = newCapacity;
    }

    public int size() {
        return size;
    }

    public int[] traverseI(){
        int[] traverse = new int[size()];
        int index = 0;
        for (int i = 0; i < table.length; i++) {
            if(table[i] != null){
                traverse[index] = (int) table[i].value;
                index++;
            }
            if (index == size()) {
                break;
            }
        }
        return traverse;
    }


    public static class Entry<K, V> {
        public K key;
        public V value;
        public Entry<K, V> next;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }
}
