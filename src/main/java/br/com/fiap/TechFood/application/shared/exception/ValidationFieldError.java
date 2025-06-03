package br.com.fiap.TechFood.application.shared.exception;

public record ValidationFieldError(String field, String message) {
}
