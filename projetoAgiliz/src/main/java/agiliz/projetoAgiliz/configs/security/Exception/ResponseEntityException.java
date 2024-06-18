package agiliz.projetoAgiliz.configs.security.Exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ResponseEntityException extends ResponseStatusException{
    private Integer code;
    
    public ResponseEntityException(HttpStatusCode status, String reason, Integer code) {
        super(status, reason);
        this.code = code;
    }
    
}
