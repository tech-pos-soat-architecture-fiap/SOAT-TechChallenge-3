package br.com.fiap.TechFood.infrastructure.result;

public enum OperationStatus {
    SUCCESS(true),
    RESOURCE_ALREADY_EXISTS(false),
    RESOURCE_NOT_FOUND(false);

    private final boolean success;

    OperationStatus(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }
}
