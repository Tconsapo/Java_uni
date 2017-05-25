package hash;

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
    
    @Test
    public void testAll(){
        int[] expResult = {5,-1,12};
        int[] result = new int[3];
        Hash instance = new Hash(20);
        instance.insert(12, 12);
        instance.insert(13, 12);
        instance.delete(12, 12);
        instance.insert(14, 12);
        result[0] = instance.search(13, 12);
        result[1] = instance.search(12, 12);
        result[2] = instance.search(14, 12);
        assertArrayEquals(expResult, result);
    }
}
