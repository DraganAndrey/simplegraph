package org.simple.graph;

import org.junit.Assert;
import org.junit.Test;
import org.simple.graph.model.Edge;
import org.simple.graph.model.UndirectedGraph;
import org.simple.graph.model.Vertex;
import org.simple.graph.service.GraphService;
import org.simple.graph.service.impl.GraphServiceImpl;

import java.util.List;

public class UnDirectedGraphTest {

    private UndirectedGraph createTestGraph() throws Exception {
        UndirectedGraph undirectedGraph = new UndirectedGraph();

        Vertex vertex1 = new Vertex("1","myVertex",1);
        Vertex vertex2 = new Vertex("2","myVertex",2);
        Vertex vertex3 = new Vertex("3","myVertex",3);
        Vertex vertex4 = new Vertex("4","myVertex",4);
        Vertex vertex5 = new Vertex("5","myVertex",5);
        Vertex vertex6 = new Vertex("6","myVertex",6);
        Vertex vertex7 = new Vertex("7","myVertex",7);
        Vertex vertex8 = new Vertex("8","myVertex",8);


        undirectedGraph.addVertex(vertex1);
        undirectedGraph.addVertex(vertex2);
        undirectedGraph.addVertex(vertex3);
        undirectedGraph.addVertex(vertex4);
        undirectedGraph.addVertex(vertex5);
        undirectedGraph.addVertex(vertex6);
        undirectedGraph.addVertex(vertex7);
        undirectedGraph.addVertex(vertex8);

        Edge e16 = new Edge("16",2,vertex1,vertex6);
        Edge e18 = new Edge("18",1,vertex1,vertex8);
        Edge e12 = new Edge("12",100,vertex1,vertex2);

        Edge e23 = new Edge("23",8,vertex2,vertex3);
        Edge e24 = new Edge("24",4,vertex2,vertex4);
        Edge e25 = new Edge("25",1,vertex2,vertex5);
        Edge e27 = new Edge("27",10,vertex2,vertex7);


        Edge e35 = new Edge("35",5,vertex3,vertex5);

        Edge e45 = new Edge("45",1,vertex4,vertex5);

        Edge e56 = new Edge("56",5,vertex5,vertex6);
        Edge e58 = new Edge("58",20,vertex5,vertex8);

        Edge e67 = new Edge("67",1,vertex6,vertex7);

        undirectedGraph.addEdge(e16);
        undirectedGraph.addEdge(e18);
        undirectedGraph.addEdge(e12);

        undirectedGraph.addEdge(e23);
        undirectedGraph.addEdge(e24);
        undirectedGraph.addEdge(e25);
        undirectedGraph.addEdge(e27);

        undirectedGraph.addEdge(e35);
        undirectedGraph.addEdge(e45);
        undirectedGraph.addEdge(e56);
        undirectedGraph.addEdge(e58);
        undirectedGraph.addEdge(e67);

        return undirectedGraph;
    }

    @Test
    public void testShortPath() throws Exception{
        UndirectedGraph graph = createTestGraph();
        GraphService graphService = new GraphServiceImpl(graph);

        Vertex vertex3 = graph.getVertexById("1");
        Vertex vertex6 = graph.getVertexById("2");

        List<Edge> result = graphService.searchShortestPath(vertex3,vertex6);
        Assert.assertEquals(3,result.size());

        int pathValue = result.stream().mapToInt(Edge::getValue).sum();
        Assert.assertEquals(8,pathValue);

    }
}
