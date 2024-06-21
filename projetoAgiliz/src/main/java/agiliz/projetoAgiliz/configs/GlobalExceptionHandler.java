package agiliz.projetoAgiliz.configs;

import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import agiliz.projetoAgiliz.configs.security.Exception.ResponseEntityException;
import agiliz.projetoAgiliz.services.MensageriaService;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<MensageriaService> handleMalformedJwtException(MalformedJwtException ex) {
        String message = "Token inválido ou mal formatado";
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new MensageriaService<>(message, 401));
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<MensageriaService> handleSignatureException(SignatureException ex){
        String message = "Token inválido ou mal formatado, tente se logar novamente ou entre em contato com nossa equipe técnica";

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
        .body(new MensageriaService<>(message, 401));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<MensageriaService> handleHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException ex) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new MensageriaService<>(ex.getMessage(), 400));
    }

    @ExceptionHandler(ResponseEntityException.class)
    public ResponseEntity<MensageriaService> handlResponseEntityException(ResponseEntityException ex) {
        return ResponseEntity.status(HttpStatus.valueOf(ex.getCode()))
                .body(new MensageriaService<>(ex.getReason(), ex.getCode()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<MensageriaService> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException ex) {
        String message = ex.getMessage();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new MensageriaService<>(message, 400));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MensageriaService<Map<String, String>>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex, HttpServletRequest request) {
        var errors = new HashMap<String, String>();
        String operation = determineOperation(request.getMethod());

        for (FieldError e : ex.getBindingResult().getFieldErrors()) {
            errors.put(e.getField(), e.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new MensageriaService<>(String.format("Ocorreu um erro ao efetuar a operação %s", operation),
                errors, 400));
    }

    private String determineOperation(String httpMethod) {
        switch (httpMethod) {
            case "POST":
                return "cadastro";
            case "PUT":
            case "PATCH":
                return "atualização";
            default:
                return "Operação desconhecida";
        }
    }
}
