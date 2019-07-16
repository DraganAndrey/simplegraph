package org.simple.graph.exception;

/**
 * Exception will be thrown when path between vertex doesn't exist
 */
public class PathNotFoundException extends Exception{

    public PathNotFoundException(String message) {
        super(message);
    }
}
