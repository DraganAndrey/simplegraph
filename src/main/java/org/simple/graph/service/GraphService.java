package org.simple.graph.service;

import org.simple.graph.exception.ObjectNotFoundException;
import org.simple.graph.exception.PathNotFoundException;
import org.simple.graph.model.Edge;
import org.simple.graph.model.Vertex;

import java.util.List;

public interface GraphService {

    /**
     * Search shortest path from one Vertex to another
     * Based on simple Dijkstra algorithm
     * @param source
     * @param destination
     * @throws ObjectNotFoundException
     * @return List<Edge>
     */
    List<Edge> searchShortestPath(Vertex source, Vertex destination) throws ObjectNotFoundException;
}
