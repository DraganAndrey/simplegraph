package org.simple.graph.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Vertex {

    private String id;

    /**
     *  Contains custom type, if required
     */
    private String type;

    /**
     * Contains custom value from user
     */
    private Object value;

}
