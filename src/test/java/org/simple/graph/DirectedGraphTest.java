package org.simple.graph;

import org.junit.Assert;
import org.junit.Test;
import org.simple.graph.model.DirectedGraph;
import org.simple.graph.model.Edge;
import org.simple.graph.model.Vertex;
import org.simple.graph.service.GraphService;
import org.simple.graph.service.impl.GraphServiceImpl;

import java.util.List;

public class DirectedGraphTest {


    private DirectedGraph createGraph() throws Exception {
        DirectedGraph directedGraph = new DirectedGraph();

        Vertex vertex1 = new Vertex("1","myVertex",1);
        Vertex vertex2 = new Vertex("2","myVertex",2);
        Vertex vertex3 = new Vertex("3","myVertex",3);
        Vertex vertex4 = new Vertex("4","myVertex",4);
        Vertex vertex5 = new Vertex("5","myVertex",5);
        Vertex vertex6 = new Vertex("6","myVertex",6);
        Vertex vertex7 = new Vertex("7","myVertex",7);
        Vertex vertex8 = new Vertex("8","myVertex",8);


        directedGraph.addVertex(vertex1);
        directedGraph.addVertex(vertex2);
        directedGraph.addVertex(vertex3);
        directedGraph.addVertex(vertex4);
        directedGraph.addVertex(vertex5);
        directedGraph.addVertex(vertex6);
        directedGraph.addVertex(vertex7);
        directedGraph.addVertex(vertex8);


        Edge e12 = new Edge("12",1,vertex1,vertex2);
        Edge e13 = new Edge("13",1,vertex1,vertex3);

        Edge e34 = new Edge("34",1,vertex3,vertex4);

        Edge e24 = new Edge("24",1,vertex2,vertex4);

        Edge e45 = new Edge("45",1,vertex4,vertex5);

        Edge e61 = new Edge("61",1,vertex6,vertex1);


        directedGraph.addEdge(e12);
        directedGraph.addEdge(e13);
        directedGraph.addEdge(e34);
        directedGraph.addEdge(e24);
        directedGraph.addEdge(e45);
        directedGraph.addEdge(e61);

        return directedGraph;
    }

    @Test
    public void testShortPath() throws Exception{
        DirectedGraph graph = createGraph();
        GraphService graphService = new GraphServiceImpl(graph);
        List<Edge> result = graphService.searchShortestPath(graph.getVertexById("6"),graph.getVertexById("5"));
        Assert.assertEquals(4,result.size());
        int pathValue = result.stream().mapToInt(Edge::getValue).sum();
        Assert.assertEquals(4,pathValue);
    }
}
