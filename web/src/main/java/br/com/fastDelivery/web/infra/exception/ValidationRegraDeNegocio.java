package br.com.fastDelivery.web.infra.exception;

public class ValidationRegraDeNegocio extends IllegalArgumentException {
    public ValidationRegraDeNegocio(String message) {
        super(message);
    }
}
