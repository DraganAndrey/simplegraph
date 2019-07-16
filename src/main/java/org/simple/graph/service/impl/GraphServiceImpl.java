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

    private boolean searchEnded = false;

    public GraphServiceImpl(AbstractGraph graph) {
        this.graph = graph;
    }

    @Override
    public List<Edge> searchShortestPath(Vertex source, Vertex destination) throws ObjectNotFoundException {
        if(Objects.isNull(source) || Objects.isNull(destination)){
            throw new ObjectNotFoundException("Source or destination doesn't exist");
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
     * Method visits vertex until reach requirement
     * @param vertex
     * @param searchable
     * @param path
     * @param visitedVertexIdList
     * @param comeByEdge
     */
    private void visitUntilSearchedVertex(Vertex vertex, Vertex searchable, List<Edge> path, List<String> visitedVertexIdList, Edge comeByEdge){

        if(searchEnded){
            return;
        }

        if(Objects.nonNull(comeByEdge)){
            path.add(comeByEdge);
        }
        if(vertex.getId().equals(searchable.getId())){
            return;
        }

        String currentVertexId = vertex.getId();
        List<Edge> edgeToVisit = graph.getEdgeList()
                .stream()
                .filter(edge -> edge.getSource().getId().equals(currentVertexId)
                        && !visitedVertexIdList.contains(edge.getDestination().getId()))
                .collect(Collectors.toList());

        visitedVertexIdList.add(currentVertexId);

        if(edgeToVisit.isEmpty()){
            path.remove(comeByEdge);
            return;
        }



        Edge endEdge = edgeToVisit
                .stream()
                .filter(edge -> edge.getDestination().getId().equals(searchable.getId()))
                .findAny().orElse(null);
        if(Objects.nonNull(endEdge)){
            path.add(endEdge);
            searchEnded = true;
            return;
        }

        edgeToVisit.forEach(edge -> {
            visitUntilSearchedVertex(edge.getDestination(), searchable,path,visitedVertexIdList,edge);
        });

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
