import java.util.LinkedList;
import java.util.ArrayList;

class MyHash<K, V> {
	// implement a hash table using an array of linkedlists
	// the hash funtion is: hashing = input % prime, where prime is set in construction
	// using seperate chaining to deal with hash collisions
    // type K must support method hashCode() since it serves as the key in <k, v> pairs

	private ArrayList<LinkedList<V>> array;
	int prime;
	int size; // size is number of <k, v> in the hash table, can be larger than prime, when there are collisions

	public MyHash(int prime) {
        this.prime = prime;
        array = new ArrayList<LinkedList<V>>(prime);
        for (int i = 0; i < prime; i ++) {
        	array.add(new LinkedList<V>());
        }
        this.size = 0;
	}

	public MyHash() {
        // for convenience of test, the default prime number is 7

		this(7);
	} 

	public boolean isEmpty() {
        return this.size == 0;
	}

	public int size() {
		return this.size;
	}

	public void put(K key, V value) {
        this.array.get(key.hashCode() % this.prime).add(value);
        this.size ++;
	}

	public LinkedList<V> get(K key) {
		return this.array.get(key.hashCode() % this.prime);
	}

	public static void main(String[] args) {
	    MyHash hash = new MyHash();
	    hash.put(1, "a");
	    hash.put(1, "l");
	    System.out.println(hash.get(1));
	    System.out.println(hash.get(2));
	}
}









