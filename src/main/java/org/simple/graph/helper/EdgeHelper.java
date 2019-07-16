package org.simple.graph.helper;

import org.simple.graph.model.AbstractGraph;
import org.simple.graph.model.Edge;
import org.simple.graph.model.Vertex;

import java.util.LinkedList;

public class EdgeHelper {

    private EdgeHelper() {
    }

    /**
     * Create path from list of vertex(Like from -> to)
     * presented by linked list of edges
     * @param graph AbstractGraph
     * @param vertexInOrder LinkedList<Vertex>
     * @return LinkedList<Edge>
     */

    public static LinkedList<Edge> findEdgesByVertex(AbstractGraph graph, LinkedList<Vertex> vertexInOrder){

        LinkedList<Edge> result = new LinkedList();
        for(int i=0; i < vertexInOrder.size()-1; i++){

            Vertex currentVertex = vertexInOrder.get(i);
            Vertex nextVertex = vertexInOrder.get(i+1);
            result.add(graph.getEdgeList()
                    .stream()
                    .filter(edge -> edge.getSource().getId().equals(currentVertex.getId()) && edge.getDestination().getId().equals(nextVertex.getId()))
                    .findAny().orElse(null)
            );
        }
        return result;
    }
}
