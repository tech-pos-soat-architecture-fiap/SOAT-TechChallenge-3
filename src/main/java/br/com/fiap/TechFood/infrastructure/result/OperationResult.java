package br.com.fiap.TechFood.infrastructure.result;

import br.com.fiap.TechFood.infrastructure.exception.ErrorResponse;
import org.springframework.http.HttpStatus;

public record OperationResult<T>(OperationStatus status, T data, String message) {

    public static <T> OperationResult<T> success(T data, String message) {
        return new OperationResult<>(OperationStatus.SUCCESS, data, message);
    }

    public static <T> OperationResult<T> failure(OperationStatus failureStatus, String message) {
        return new OperationResult<>(failureStatus, null, message);
    }

    public boolean isFailure() {
        return !status.isSuccess();
    }

    public ErrorResponse toErrorResponse(String path) {
        HttpStatus httpStatus = mapToHttpStatusError(status);
        return ErrorResponse.of(httpStatus.value(), httpStatus.getReasonPhrase(), message, path);
    }

    public HttpStatus httpStatus() {
        return mapToHttpStatusError(status);
    }

    private HttpStatus mapToHttpStatusError(OperationStatus status) {
        return switch (status) {
            case RESOURCE_ALREADY_EXISTS -> HttpStatus.CONFLICT;
            case RESOURCE_NOT_FOUND -> HttpStatus.NOT_FOUND;
            default -> HttpStatus.INTERNAL_SERVER_ERROR;
        };
    }
}
