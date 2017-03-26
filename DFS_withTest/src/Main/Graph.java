package Main;

import java.util.Map;
import java.util.Set;

public interface Graph {

    interface Vertex {
        String getName();
    }

    interface Edge {
        int getWeight();
        Vertex getEnd();
    }

    Set<Vertex> getVertices();

    default Set<Vertex> getNeighbors(Vertex v) {
        return getConnections(v).keySet();
    }

    Map<Vertex, Edge> getConnections(Vertex v);
}
