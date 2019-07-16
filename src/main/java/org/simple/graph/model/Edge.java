package org.simple.graph.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Edge {

    private String id;

    private Integer value;

    private Vertex source;

    private Vertex destination;
}
