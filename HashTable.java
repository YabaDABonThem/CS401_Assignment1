import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class HashTableEntry<K, V> {
    private K key;
    private V value;

    public HashTableEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}

class HashTable<K, V> {
    private int size;
    private List<LinkedList<HashTableEntry<K, V>>> table;

    public HashTable() {
        size = 10;
        table = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            table.add(new LinkedList<>());
        }
    }

    public int getListSize(K key) {
        int index = hash(key);
        LinkedList<HashTableEntry<K, V>> bucket = table.get(index);

        for (HashTableEntry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                return bucket.size();  // Return the size of the bucket the key is in, since it's in a linked list this works
            }
        }

        return 0;  // Key not found or list is empty
    }

    private int hash(K key) {
        int hashCode = key.hashCode(); // use the built in hashCode method (too lazy for my own hash function)
        int index = hashCode % size;
        return index >= 0 ? index : index + size; // make sure that the hash code cannot be negative
    }

    public void put(K key, V value) {
        int index = hash(key);
        LinkedList<HashTableEntry<K, V>> bucket = table.get(index);

        for (HashTableEntry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                entry = new HashTableEntry<>(key, value);  // Update existing pair
                return;
            }
        }

        bucket.add(new HashTableEntry<>(key, value));  // Add new pair to the ht
    }

    public V get(K key) {
        int index = hash(key);
        LinkedList<HashTableEntry<K, V>> bucket = table.get(index);

        for (HashTableEntry<K, V> entry : bucket) { // loop through every bucket for the entry
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }

        return null;  // Key not found
    }
}