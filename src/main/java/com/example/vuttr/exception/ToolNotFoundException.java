package com.example.vuttr.exception;

public class ToolNotFoundException extends RuntimeException {
    public ToolNotFoundException(){
        super("Tool not Found");
    }
}
