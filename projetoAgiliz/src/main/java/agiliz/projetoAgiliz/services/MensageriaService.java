package agiliz.projetoAgiliz.services;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class MensageriaService<T> {
    private String mensagemCliente;
    private String mensagemServidor;
    private T data;
    private Integer status;
    private List datas;

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

    public MensageriaService(String mensagemCliente, List<T> datas, int status) {
        this.mensagemCliente = mensagemCliente;
        this.datas = datas;
        this.status = status;
    }

    public MensageriaService(String mensagemCliente, T data) {
        this.mensagemCliente = mensagemCliente;
        this.data = data;
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
