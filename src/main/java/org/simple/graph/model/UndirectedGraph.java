package org.simple.graph.model;


import org.simple.graph.exception.ObjectExistsException;

public class UndirectedGraph extends AbstractGraph {

    /**
     * When we have edge like (1 -> 2) where are always edge (2 -> 1)
     * For that case we automatically create reverted way
     * @param edge
     * @throws Exception
     */
    @Override
    public void addEdge(Edge edge) throws ObjectExistsException {
        super.addEdge(edge);
        Edge revertedEdge = new Edge(
                "reverted" + edge.getId(),
                edge.getValue(),
                edge.getDestination(),
                edge.getSource()
        );
        this.edgeList.add(revertedEdge);
    }
}
