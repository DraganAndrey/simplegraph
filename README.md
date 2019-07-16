** Simple Project for work with graph **

Features:
1) 2 types of graphs are supported: "Directed" and "Undirected"
2) Each vertex could contain custom value;
3) Each Edge has value (It is usually known as weight)
4) Graph Service able to calculate path between vertexes

Limitation
1) Path searching doesn't work for edges with negatives and 0 values

Usage:

To Create Graph you can use:
    DirectedGraph directedGraph = new DirectedGraph();

Then create a few Vertexes

    Vertex vertex1 = new Vertex("1","myVertex",null);
    Vertex vertex2 = new Vertex("2","myVertex",null);

*If you need custom type and value for vertex put in instead "myVertex" and null

Then add edge between Vertexes:

    Edge edge = new Edge("edgeBetween12",1,vertex1,vertex2);


Of course add edge and vertexes in graph:

    directedGraph.addVertex(vertex1);
    directedGraph.addVertex(vertex2);
    directedGraph.addEdge(edge);


To calculate path create service by line:

    GraphService graphService = new GraphServiceImpl(graph);


Then to receive list of edges between vertexes use:

    List<Edge> result = graphService.searchShortestPath(vertex1,vertex2);


