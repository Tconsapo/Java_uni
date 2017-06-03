package hash;

import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;

public class HashTest {
    
    public HashTest() {
    }

    /**
     * Test of insert method, of class Hash.
     */
    @Test
    public void testInsert() {
        System.out.println("insert");
        String s = "asdasd";
        int key = 21;
        Hash instance = new Hash();
        int expResult = 5;
        int result = instance.insert(s, key);
        assertEquals(expResult, result);
    }

    /**
     * Test of search method, of class Hash.
     */
    @Test
    public void testSearch() {
        System.out.println("search");
        double obj = 21.5;
        int key = 15;
        Hash instance = new Hash();
        instance.insert(obj, key);
        int expResult = 15;
        int result = instance.search(obj, key);
        assertEquals(expResult, result);
    }

    /**
     * Test of delete method, of class Hash.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        int obj = 24;
        int key = 10;
        Hash instance = new Hash();
        instance.insert(obj, key);
        int expResult = -1;
        instance.delete(obj, key);
        int result = instance.search(obj, key);
        assertEquals(expResult, result);
    }

    /**
     * Test of entrySet method, of class Hash.
     */
    @Test
    public void testEntrySet() {
        System.out.println("entrySet");
        Hash instance = new Hash(3);
        instance.insert(12, 12);
        instance.insert(13, 11);
        instance.insert(14, 17);
        String expResult = "0 12 1 14 2 13 ";        
        Set result = instance.entrySet();
        String s = "";
        while (result.iterator().hasNext()){
            s += result.iterator().next().toString() + " ";
        }        
        assertEquals(expResult, s);
    }
    
    @Test
    public void testAll(){
        System.out.println("testAll");
        int[] expResult = new int[4];
        int[] result = new int[4];
        Hash instance = new Hash(20);
        expResult[0] = -1;
        instance.insert(12, 12);
        instance.delete(12, 12);
        expResult[1] = instance.insert(13, 12);
        expResult[2] = instance.insert(14, 12);
        expResult[3] = instance.insert(14, 12);
        result[0] = instance.search(12, 12);
        result[1] = instance.search(13, 12);
        result[2] = instance.search(14, 12);        
        //если не удалить - найдет первое вхождение, так как элементы одинаковы
        instance.delete(14, 12);
        result[3] = instance.search(14, 12);
        assertArrayEquals(expResult, result);
    }
}
