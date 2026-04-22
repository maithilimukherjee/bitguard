package com.tina.bitguard.model;

public class RequestDTO {

    private String message;
    private double errorProbability;

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public double getErrorProbability() {
        return errorProbability;
    }
    public void setErrorProbability(double errorProbability) {
        this.errorProbability = errorProbability;   
    }
    
}
