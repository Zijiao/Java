/**
 * @author Zijiao Chen 
 *
 * class Deque
 * 
 * solution to Princeton Algo homework 2 on Coursera
 * 
 * for description see http://coursera.cs.princeton.edu/algs4/assignments/queues.html
 * 
 */

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    
    private class Node<Item> {
        // deque contains nodes
       private Item item;
       private Node<Item> prev;
       private Node<Item> next;
       
       public Node(Item item, Node<Item> prev, Node<Item> next) {
          this.item = item;
          this.prev = prev;
          this.next = next;
       }
       
       public Item getItem() {
           return this.item;
       }
    }
    
    private int length;
    private Node<Item> head;
    private Node<Item> tail;
    
    public Deque() {                        
       // construct an empty deque
        this.head = null;
        this.tail = null;
        this.length = 0;
        
        /* when length = 0, head(null) <--> tail(null), both null
         * when length = 1, head(item) <--> tail(null), put the only item into head, tail remains empty
         * when length >=2, head(item) ... <--> ... tail(item), regular case
         */
    }
    
    public boolean isEmpty() {                 
       // is the deque empty?
        return (this.length == 0);
    }
    
    public int size() {                       
       // return the number of items on the deque
       return this.length; 
    }
    
    public void addFirst(Item item) {          
       // insert the item at the front
        if (item == null) { throw new NullPointerException("No null items!"); }
        
        // three cases, a. length = 0; b. length = 1; c. length = 2
        if (this.length == 0 ) {
            this.head = new Node<Item>(item, null, this.tail);
        }
        else if (this.length == 1) {
            this.tail = this.head;
            this.head = new Node<Item>(item, null, this.tail);
        }
        else {
            Node<Item> temp = this.head;
            this.head = new Node<Item>(item, null, temp);
        }
        
        this.length += 1;
    }
    
    public void addLast(Item item) {          
       // insert the item at the end
        if (item == null) { throw new NullPointerException("No null items!"); }
        
        // three cases
        if (length == 0) {
            this.head = new Node<Item>(item, null, this.tail);
        }
        else if (length == 1) {
            this.tail = new Node<Item>(item, this.head, null);
        }
        else {
            Node<Item> temp = this.tail;
            this.tail = new Node<Item>(item, temp, null);
        }
        
        this.length += 1;
    }
    
    public Item removeFirst() {               
       // delete and return the item at the front
        if (this.length == 0) {throw new java.util.NoSuchElementException("deque is alraedy empty!"); }
        
        Item returnItem = this.head.getItem();
        // three cases
        if(this.length == 2) {
            this.head = this.tail;
            this.tail = null;
        }
        else if (this.length == 1) {
            this.head = null;
        }
        else {
            this.head = this.head.next;
        }
        
        this.length -= 1;
        return returnItem;
    }
    
    public Item removeLast() {                 
       // delete and return the item at the end
        if (this.length == 0) {throw new java.util.NoSuchElementException("deque is alraedy empty!"); }
        
        Item returnItem;
        
        if (this.length == 1) {
            returnItem = this.head.getItem();
            this.head = null;
        }
        else if (this.length == 2) {
            returnItem = this.tail.getItem();
            this.tail = null;
        }
        else {
            returnItem = this.tail.getItem();
            this.tail = this.tail.prev;
        }
        
        this.length -= 1;
        return returnItem;
    }
    
    public Iterator<Item> iterator() {        
       // return an iterator over items in order from front to end
        return new DequeIterator();
    }
            
    private class DequeIterator implements Iterator<Item> {
            
        private Node<Item> current = head;
            
        public void remove() { throw new UnsupportedOperationException(); }
            
        public boolean hasNext() {
            return current != null;
        }
            
        public Item next() {
            if (current == tail) {throw new java.util.NoSuchElementException("No more items!"); }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
        
    
    
    public static void main(String[] args) {
       Deque<Integer> deque = new Deque<Integer>();
       deque.addLast(1);
       deque.addLast(2);
       StdOut.printf("%d\n", deque.removeFirst());
    }

}