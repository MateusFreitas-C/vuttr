package com.example.vuttr.exception;

public class ToolAlreadyExistsException extends RuntimeException {
    public ToolAlreadyExistsException(){
        super("Tool alreeady exists.");
    }
}
