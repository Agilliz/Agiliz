package agiliz.projetoAgiliz.services;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@AllArgsConstructor
@Service
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MensageriaService<T> {
    private String mensagemCliente;
    private String mensagemServidor;
    private T data;
    private Integer status;
    private List datas;

    public MensageriaService<T> mensagemCliente(String mensagemCliente) {
        setMensagemCliente(mensagemCliente);
        return this;
    }

    public MensageriaService<T> mensagemServidor(String mensagemServidor) {
        setMensagemServidor(mensagemServidor);
        return this;
    }

    public MensageriaService<T> data(T data) {
        setData(data);
        return this;
    }

    public MensageriaService<T> status(Integer status) {
        setStatus(status);
        return this;
    }

    public MensageriaService(String mensagemCliente, String mensagemServidor, T data, Integer status) {
        this.mensagemCliente = mensagemCliente;
        this.mensagemServidor = mensagemServidor;
        this.data = data;
        this.status = status;
    }

    public MensageriaService(String mensagemCliente, T data, Integer status) {
        this.mensagemCliente = mensagemCliente;
        this.data = data;
        this.status = status;
    }

    public MensageriaService(String mensagemCliente) {
        this.mensagemCliente = mensagemCliente;
    }

    public MensageriaService(String mensagemCliente, List<T> datas, int status) {
        this.mensagemCliente = mensagemCliente;
        this.datas = datas;
        this.status = status;
    }

    public MensageriaService(String mensagemCliente, T data) {
        this.mensagemCliente = mensagemCliente;
        this.data = data;
    }

    public MensageriaService(String mensagemCliente, Integer status) {
        this.mensagemCliente = mensagemCliente;
        this.status = status;
    }

    public MensageriaService(String mensagemCliente, String mensagemServidor, Integer status) {
        this.mensagemCliente = mensagemCliente;
        this.mensagemServidor = mensagemServidor;
        this.status = status;
    }

    public MensageriaService( T data, Integer status){
        this.data = data;
        this.status = status;
    }
}
