package org.simple.graph.exception;

import lombok.ToString;

/**
 * This exception will be thrown when edge or vertex is already exist in graph
 */
@ToString
public class ObjectExistsException extends Exception{
    public ObjectExistsException(String message) {
        super(message);
    }
}
