package hash;

public class Hash<T> {
    
    private static final int DEFAULT_CAPACITY = 16;
    private int capacity;
    private T[] array;
    private boolean[] check;
    
    public Hash(){
        this.capacity = DEFAULT_CAPACITY;
        this.arraysInit();
    }   
    
    public Hash(int capacity){
        this.capacity = capacity;
        this.arraysInit();
    }
    
    private void arraysInit(){
        this.array = (T[]) new Object[this.capacity];
        this.check = new boolean[this.capacity];
        for (int i = 0; i < this.capacity; i++) {
            this.check[i] = true; //можно записать
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
            if (this.check[k]) {
                this.array[k] = obj;
                this.check[k] = false;
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
            if (!this.check[k]) {
                if (obj.equals(this.array[k])) return k;
            } else return -1;
        }
        return -1;
    }
    
    public void delete(T obj, int key){
        int a = h1(key);
        int b = h2(key);
        for (int i = 0; i < this.capacity; i++){
            int k = (a + i*b)%this.capacity;
            if (!this.check[k] && obj.equals(this.array[k])) {
                this.check[k] = true;
                break;
            }
        }
    }
    
}
