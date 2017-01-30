/**
 * @author Zijiao Chen 
 *
 * class RandomizedQueue
 * 
 * solution to Princeton Algo homework 2 on Coursera
 * 
 * for description see http://coursera.cs.princeton.edu/algs4/assignments/queues.html
 * 
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    
    private int length;
    private Item[] queue;
    
    public RandomizedQueue() {                
        // construct an empty randomized queue
        this.length = 0;
        this.queue = (Item[]) new Object[10];
    }
    
    public boolean isEmpty() {                
       // is the queue empty?
        return this.length == 0;
    }
    
    public int size() {                       
       // return the number of items on the queue
        return this.length;
    }
    
    public void enqueue(Item item) {          
       // add the item, resize (inflate) if necessary 
        if (item == null) { throw new NullPointerException("No null items!"); }
        
        queue[length] = item;
        length += 1;
        
        if (queue.length == length) {
            this.resize((int)Math.round(1.5*length));
        }
    }
    
    public Item dequeue() {                   
       // delete and return a random item, resize (deflate) if necessary
        if (this.length == 0) { throw new java.util.NoSuchElementException("queue is already empty!"); }
        
        int index = StdRandom.uniform(this.length);
        Item returnItem = queue[index];
        queue[index] = queue[this.length - 1]; // just fill the empty position with the last item
        this.length -= 1;
        
        if(queue.length >= 2 * length) {
            this.resize((int)Math.round(0.67 * queue.length));
        }
        
        return returnItem;
    }
    
    public Item sample() {                    
       // return (but do not delete) a random item
        if (this.length == 0) { throw new java.util.NoSuchElementException("The queue is already empty!"); }
        int index = StdRandom.uniform(length);
        return queue[index];
    }
    
    private void resize(int new_size) {      
        // resize this.queue to length of new_size
        // after resize the queue should be compact (no null between items)
        // inflate when full by 1.5, deflate when half of size by 0.67 
        
        Item[] temp = (Item[]) new Object[new_size];
        for (int i = 0; i < this.length; i++) {
            temp[i] = queue[i];
        }
        this.queue = temp;        
    }
    
    public Iterator<Item> iterator() {        
       // return an independent iterator over items in random order
        return new RandomizedQueueIterator();
    }
        
        private class RandomizedQueueIterator implements Iterator<Item> {
            
            int index;
            Item[] tempArray = (Item[]) new Object[length];
            
            public RandomizedQueueIterator() {
                // first copy the original queue to a temporary new one
                for (int i = 0; i <length;i++) {
                   tempArray[i] = queue[i];
                }
                // then shuffle the temporary queue
                for (int i = 0; i < length;i++) {
                    int tempIndex = StdRandom.uniform(length);
                    Item tempItem = tempArray[i];
                    tempArray[i] = tempArray[tempIndex];
                    tempArray[tempIndex] = tempItem;
                }
            }
            
            public boolean hasNext() {
                return index < length - 1;
            }
            
            public Item next() {
                if (index > length - 1) { throw new java.util.NoSuchElementException(); }
                
                return tempArray[index++];
            }
            
            public void remove() { throw new UnsupportedOperationException(); }
         
    }
    
    public static void main(String[] args) {  
       // unit testing
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        for (int i=0;i<10;i++) { queue.enqueue(i); }
        for (int i=0;i<10;i++) { 
            StdOut.printf("succesfully deque %d\n", queue.dequeue());
        }
        
    }
}