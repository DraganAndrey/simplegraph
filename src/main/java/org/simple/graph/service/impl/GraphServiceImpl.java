package org.simple.graph.service.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.simple.graph.exception.ObjectNotFoundException;
import org.simple.graph.exception.PathNotFoundException;
import org.simple.graph.helper.EdgeHelper;
import org.simple.graph.model.AbstractGraph;
import org.simple.graph.model.Edge;
import org.simple.graph.model.Vertex;
import org.simple.graph.service.GraphService;

import java.util.*;
import java.util.stream.Collectors;

public class GraphServiceImpl implements GraphService {

    private AbstractGraph graph;

    public GraphServiceImpl(AbstractGraph graph) {
        this.graph = graph;
    }

    @Override
    public List<Edge> searchShortestPath(Vertex source, Vertex destination) throws ObjectNotFoundException {
        if(Objects.isNull(source) || Objects.isNull(destination)){
            throw new ObjectNotFoundException("Source or destination doesn't exist");
        }

        if(graph.getEdgeList().stream().anyMatch(edge -> edge.getValue() <=0)){
            throw new RuntimeException("Found edges with 0 or less value, calculation cancelled");
        }

        List<Vertex> notVisited = new ArrayList(graph.getVertexList());
        Map<Vertex,DijkstraData> tracker = new HashMap<>();
        tracker.put(source,new DijkstraData(null,0));

        while(true){

            Vertex toOpen = null;
            Integer minimalValue = Integer.MAX_VALUE;
            for(Vertex notVisitedVertex : notVisited){
                if(tracker.containsKey(notVisitedVertex) && tracker.get(notVisitedVertex).getValue() < minimalValue){
                    toOpen = notVisitedVertex;
                    minimalValue = tracker.get(notVisitedVertex).getValue();
                }
            }
            if(Objects.isNull(toOpen) || toOpen.getId().equals(destination.getId())){
                break;
            }
            String toOpenId = toOpen.getId();
            List<Edge> edgesWhereToOpenIsSource = graph
                    .getEdgeList()
                    .stream()
                    .filter(edge -> edge.getSource().getId().equals(toOpenId))
                    .collect(Collectors.toList());

            for(Edge edge : edgesWhereToOpenIsSource){
                Integer currentValue = tracker.get(toOpen).getValue() + edge.getValue();
                Vertex nextNode = edge.getDestination();
                if(!tracker.containsKey(nextNode) || tracker.get(nextNode).getValue() > currentValue){
                    tracker.put(nextNode,new DijkstraData(toOpen,currentValue));
                }
            }

            notVisited.remove(toOpen);
        }

        LinkedList<Vertex> shortWayByVertex = new LinkedList<>();
        buildShortWayByVertex(destination,shortWayByVertex,tracker);

        return EdgeHelper.findEdgesByVertex(graph,shortWayByVertex);

    }

    /**
     * Restore path based on the calculated DijkstraData
     * @param destination
     * @param vertexList
     * @param tracker
     */
    private void buildShortWayByVertex(Vertex destination, LinkedList vertexList, Map<Vertex,DijkstraData> tracker){
        vertexList.addFirst(destination);
        if(Objects.isNull(tracker.get(destination).getParentVertex())){
            return;
        }
        buildShortWayByVertex(tracker.get(destination).getParentVertex(),vertexList,tracker);
    }

    /**
     * Class contains information
     * about shortest path to to vertex
     */
    @Data
    @AllArgsConstructor
    class DijkstraData{
        Vertex parentVertex;
        Integer value;
    }

}
