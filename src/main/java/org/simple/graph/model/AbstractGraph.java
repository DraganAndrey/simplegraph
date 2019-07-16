package org.simple.graph.model;

import org.simple.graph.exception.ObjectExistsException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractGraph {

    protected List<Vertex> vertexList = new ArrayList<>();

    protected List<Edge> edgeList = new ArrayList<>();

    public void addVertex(Vertex vertex) throws Exception {
        if(Objects.nonNull(getVertexById(vertex.getId()))){
            throw new ObjectExistsException("Vertex with id " + vertex.getId() + " already exists");
        }
        this.vertexList.add(vertex);
    }

    public Vertex getVertexById(String id){
        return vertexList
                .stream()
                .filter(vertex-> vertex.getId().equals(id))
                .findAny()
                .orElse(null);
    }

    public void addEdge(Edge edge) throws ObjectExistsException {
        if(Objects.nonNull(getEdgeById(edge.getId()))){
            throw new ObjectExistsException("Edge with id " + edge.getId() + " already exists");
        }
        this.edgeList.add(edge);
    };

    public Edge getEdgeById(String id){
        return edgeList.stream()
                .filter(edge-> edge.getId().equals(id))
                .findAny()
                .orElse(null);
    }

    public void removeVertexById(String id){
        vertexList.removeIf(vertex -> vertex.getId().equals(id));
    }

    public List<Edge> getEdgeList() {
        return edgeList;
    }

    public List<Vertex> getVertexList() {
        return vertexList;
    }




}
