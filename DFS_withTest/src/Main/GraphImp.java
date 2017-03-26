package Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GraphImp implements Graph{
    
    private Set<Vertex> vertices;
    private Map<Vertex, LinkedHashSet<EdgeImp>> connections;
    
    public GraphImp(){
        vertices = new LinkedHashSet<>();
        connections = new HashMap<>();
    }
    
    public void readGraph(){
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            try {
                System.out.print("From ");
                String from = in.readLine();
                System.out.print("To ");
                String to = in.readLine();
                System.out.print("For ");
                int cost = Integer.valueOf(in.readLine());
                Vertex fromVertex = this.addVertex(from);
                Vertex toVertex = this.addVertex(to);
                this.addConnection(fromVertex, toVertex, cost);
                System.out.print("Enter something if done: ");
                String str = in.readLine();
                if (!str.equals("")) break;
            } catch (IOException ex) {
                Logger.getLogger(GraphImp.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Wrong Value");
            }
        }
    }
    
    class EdgeImp implements Edge{
        private Vertex begin;
        private Vertex end;
        private int weight;
        
        EdgeImp(Vertex begin, Vertex end, int weight){
            this.begin = begin;
            this.end = end;
            this.weight = weight;
        }
        
        @Override
        public String toString(){
            String s = "from " + begin.getName() + " to " + end.getName() + " for " + weight;
            return s;
        }
        
        @Override
        public Vertex getEnd(){
            return this.end;
        }
        
        @Override
        public int getWeight() {
            return weight;
        }
    }
    
    public class VertexImp implements Vertex{
        private String name;
        
        public VertexImp(String name){
            this.name = name;
        }
        
        @Override
        public String getName(){
            return name;
        }
    }
    
    public void addVertex(Vertex v){
        vertices.add(v);
    }
    
    public Vertex addVertex(String name){
        Vertex v = new VertexImp(name);
        vertices.add(v);
        return v;
    }
    
    public void addConnection(Vertex begin, Vertex end, int weight){
        Edge edge = new EdgeImp(begin, end, weight);
        LinkedHashSet x = connections.get(begin); 
        if (x == null){
           x = new LinkedHashSet();
        }
        x.add(edge);
        connections.put(begin, x);
    }
    
    @Override
    public Set<Vertex> getVertices() {
        return vertices;
    }
    
    @Override
    public Map<Vertex, Edge> getConnections(Vertex v) {
        HashMap<Vertex, Edge> m = new HashMap<>();
        Set s = connections.get(v);
        if (s == null){
            return null;
        }
        Iterator it = s.iterator();
        while (it.hasNext()){
            EdgeImp e = (EdgeImp) it.next();
            m.put(e.getEnd(), e);
        }
        return m;
    }
    
    private void doFind(Vertex begin, Vertex end, HashMap<String, Integer> map){
        if (!begin.getName().equals(end.getName()) 
            && this.getConnections(begin) != null){
            Set<Map.Entry<Vertex, Edge>> s;
            s = this.getConnections(begin).entrySet();
            s.stream().forEach((e) -> {
                if (map.containsKey(e.getValue().getEnd().getName())){
                    if (map.get(e.getValue().getEnd().getName()).compareTo
                                (e.getValue().getWeight() + map.get(begin.getName())) > 0){
                                    map.replace(e.getValue().getEnd().getName(),
                                                e.getValue().getWeight() 
                                                + map.get(begin.getName()));
                        
                        doFind(e.getValue().getEnd(), end, map);
                    }
                } else {
                    map.put(e.getValue().getEnd().getName(),
                            e.getValue().getWeight() 
                            + map.get(begin.getName()));
                    
                    doFind(e.getValue().getEnd(), end, map);
                }
            });
        }
    }
    
    public void setWay(){
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Way From ");
            String begin = in.readLine();
            System.out.print("Way To ");
            String end = in.readLine();
            System.out.println(this.findWay(begin, end));
        } catch (IOException ex) {
            Logger.getLogger(GraphImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int findWay(String begin, String end){
        Vertex beginVertex = null;
        Vertex endVertex = null;
        Set<Vertex> s = this.getVertices();
        for (Vertex v : s){
            if (v.getName().equals(begin)){
                beginVertex = v;
            }
            if (v.getName().equals(end)){
                endVertex = v;
            }
        }
        return findWay(beginVertex, endVertex);
    }
    
    public int findWay(Vertex begin, Vertex end){
        if (begin == null || end == null){
            return Integer.MAX_VALUE;
        }
        HashMap<String, Integer> map = new HashMap<>();
        Set<Vertex> s = this.getVertices();
        for (Vertex x : s){
            map.put(x.getName(), Integer.MAX_VALUE);
        }
        map.replace(begin.getName(), 0);
        doFind(begin, end, map);
        return map.get(end.getName());
    }
}
