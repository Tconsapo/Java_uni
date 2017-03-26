package Tests;

import Main.GraphImp;
import org.junit.Assert;

public class Test {
    
    private GraphImp g;
    private GraphImp.VertexImp a;
    private GraphImp.VertexImp b;
    private GraphImp.VertexImp c;
    private GraphImp.VertexImp d;
    private GraphImp.VertexImp e;
    private GraphImp.VertexImp f;
    
    public Test() {   
    }
    
    private void init(){
        g = new GraphImp();
        a = g.new VertexImp("a");
        b = g.new VertexImp("b");
        c = g.new VertexImp("c");
        d = g.new VertexImp("d");
        e = g.new VertexImp("e");
        f = g.new VertexImp("f");
        g.addVertex(a);
        g.addVertex(b);
        g.addVertex(c);
        g.addVertex(d);
        g.addVertex(e);
        g.addVertex(f);
        g.addConnection(a, b, 3);
        g.addConnection(a, c, 2);
        g.addConnection(b, d, 7);
        g.addConnection(b, a, 3);
        g.addConnection(c, a, 2);
        g.addConnection(c, e, 1);
        g.addConnection(d, b, 7);
        g.addConnection(d, f, 1);
        g.addConnection(e, c, 1);
        g.addConnection(e, f, 10);
        g.addConnection(f, d, 1);
        g.addConnection(f, e, 10);
    }   
    
    @org.junit.Test
    public void AllWays(){
        init();
        String[] names = {"a","b","c","d","e","f"};
        int[] weights =    { 0, 3,  2, 10,  3, 11,
                             3, 0,  5,  7,  6,  8,
                             2, 5,  0, 12,  1, 11,
                            10, 7, 12,  0, 11,  1,
                             3, 6,  1, 11,  0, 10,
                            11, 8, 11,  1, 10,  0};
        int it = 0;
        for (int i = 0; i < 6; i++){
            for (int j = 0; j < 6; j++){
                String str = "From " + names[i] + " to " + names[j] + ":";
                Assert.assertEquals(str, weights[it], g.findWay(names[i], names[j]));
                it++;
            }
        }
        
    }
}
