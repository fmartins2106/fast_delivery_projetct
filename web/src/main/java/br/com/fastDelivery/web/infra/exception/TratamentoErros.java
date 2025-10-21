package br.com.fastDelivery.web.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class TratamentoErros {

    private record tratarErro(String campo, String mensagem){
        private tratarErro(FieldError fieldError){
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }

    private record ErroRespose(int status, String erro, String mensagem, LocalDateTime timeStamp){

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> tratarErro400(MethodArgumentNotValidException exception){
        var erro = exception.getFieldErrors().stream().map(tratarErro::new).toList();
        return ResponseEntity.badRequest().body(erro);
    }

    @ExceptionHandler(HttpMessageConversionException.class)
    public ResponseEntity<ErroRespose> tratarErro400JsonInvalido(HttpMessageConversionException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErroRespose(400, "BAD REQUEST", "Json inválido ou mal formatado.",
                        LocalDateTime.now()));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErroRespose> tratarErro400(MethodArgumentTypeMismatchException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErroRespose(400, "BAD REQUEST", "Paramentro inválido."+
                        exception.getMessage(), LocalDateTime.now()));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErroRespose> tratarErro401(BadCredentialsException exception){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ErroRespose(401, "Unauthorized", "Login ou senha inválido.",
                        LocalDateTime.now()));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErroRespose> tratarErro403(AccessDeniedException exception){
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ErroRespose(403, "Forbidden", "Acesso negado.",
                        LocalDateTime.now()));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErroRespose> tratarErro404(EntityNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErroRespose(400, "Not Found", exception.getMessage(),
                        LocalDateTime.now()));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErroRespose> tratarErro404(NoHandlerFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErroRespose(404, "Not Found","Rota não encontrada."+
                        exception.getRequestURL(), LocalDateTime.now()));
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErroRespose> tratarErro409(DataIntegrityViolationException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErroRespose(409, "Conflict", "Erro de violação de dados.",
                        LocalDateTime.now()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroRespose> tratarErro500(Exception exception){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErroRespose(500, "Internal Server Error",
                        "Erro inesperado"+ exception.getMessage(),
                        LocalDateTime.now()));
    }


}
