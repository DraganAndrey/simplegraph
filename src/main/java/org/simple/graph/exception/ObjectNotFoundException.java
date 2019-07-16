package org.simple.graph.exception;

import lombok.ToString;

/**
 * Will be thrown when object in graph not found
 */
@ToString
public class ObjectNotFoundException extends Exception {
    public ObjectNotFoundException(String message) {
        super(message);
    }
}
