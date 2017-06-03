package hash;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;

public class Hash<T> extends AbstractMap{
    
    private static final int DEFAULT_CAPACITY = 16;
    private int capacity;
    private T[] array;
    private boolean[] canInsert;
    
    public Hash(){
        this(DEFAULT_CAPACITY);
        this.arraysInit();
    }   
    
    public Hash(int capacity){
        this.capacity = capacity;
        this.arraysInit();
    }
    
    private void arraysInit(){
        this.array = (T[]) new Object[this.capacity];
        this.canInsert = new boolean[this.capacity];
        for (int i = 0; i < this.capacity; i++) {
            this.canInsert[i] = true; //можно записать
        }
    }    
    
    private int h1(int key){
        return key%this.capacity;
    }
    
    private int h2(int key){
        return 1 + key%(this.capacity - 1);
    }    

    public int insert(T obj, int key){
        int a = h1(key);
        int b = h2(key);
        for (int i = 0; i < this.capacity; i++){
            int k = (a + i*b)%this.capacity;
            if (this.canInsert[k]) {
                this.array[k] = obj;
                this.canInsert[k] = false;
                return k;
            }
        }
        return -1;
    }
    
    public int search(T obj, int key){
        int a = h1(key);
        int b = h2(key);
        for (int i = 0; i < this.capacity; i++){
            int k = (a + i*b)%this.capacity;
            if (!this.canInsert[k]) {
                if (obj.equals(this.array[k])) return k;
            } //else return -1;
        }
        return -1;
    }
    
    public void delete(T obj, int key){
        int a = h1(key);
        int b = h2(key);
        for (int i = 0; i < this.capacity; i++){
            int k = (a + i*b)%this.capacity;
            if (!this.canInsert[k] && obj.equals(this.array[k])) {
                this.canInsert[k] = true;
                break;
            }
        }
    }
    
    
    @Override
    public AbstractSet entrySet() {
        AbstractSet<Entry<Integer, T>> set = new SetImpl();
        return set;
    }
    
    private class SetImpl extends AbstractSet<Entry<Integer, T>>{
        
        private Iterator<Entry<Integer, T>> it = new IteratorImpl();
        
        @Override
        public Iterator<Entry<Integer, T>> iterator() {
            return it;
        }

        @Override
        public int size() {
            return array.length;
        }
        
    }
    
    private class IteratorImpl implements Iterator<Entry<Integer, T>>{       
        
        private int index = -1;

        public IteratorImpl() {
        }
        
        @Override
        public boolean hasNext() {
            return index+1 < array.length;
        }

        @Override
        public EntryImpl next() {
            if (hasNext()){
                return new EntryImpl(++index, array[index]);
            }
            return null;
        }
        
    }

    private class EntryImpl implements Entry<Integer, T> {

        public EntryImpl() {
        }
        private int key;
        private T obj;        

        private EntryImpl(int k, T v) {
            this.key = k;
            this.obj = v;
        }

        @Override
        public Integer getKey() {
            return key;
        }

        @Override
        public T getValue() {
            return obj;
        }

        @Override
        public T setValue(T value) {
            return obj = value;
        }
        
        @Override
        public String toString(){
            return Integer.toString(key) + " " + obj.toString();
        }
    }
}
