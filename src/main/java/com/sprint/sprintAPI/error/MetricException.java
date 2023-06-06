package com.sprint.sprintAPI.error;

public class MetricException extends RuntimeException {

    public MetricException() {
        super();
    }

    private MetricException(String message) {
        super(message);
    }

    public static MetricException metricNotFound() {
        return new MetricException("Métrique introuvable.");
    }

    public static MetricException invalidMetric() {
        return new MetricException("Métrique invalide.");
    }
    
}
