package modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Diego Heusser    
 */
public class Execucao {
    private int id;
    private Atleta atleta;
    private Protocolo protocolo;
    protected String hora;
    protected Date data;
    private int temperatura;
    private String superficie;
    private Status status;

    public Execucao() {
        data = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        hora = sdf.format(data);
    }
    
    public Protocolo getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(Protocolo protocolo) {
        this.protocolo = protocolo;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public Atleta getAtleta() {
        return atleta;
    }
    
    public void setAtleta(Object athlete) {
        this.atleta = (modelo.Atleta) athlete;
    }
    
    public String getHora() {
        return hora;
    }

    public void setHora(Object hora) {
        this.hora = (String) hora;
    }

    public Date getData() {
        return data;
    }
    
    public void setData(Date data) throws ParseException {
        this.data = data;
    }

    public int getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Object temperatura) {
        this.temperatura = (int) temperatura;
    }

    public String getSuperficie() {
        return superficie;
    }

    public void setSuperficie(Object superficie) {
        this.superficie = (String) superficie;
    }
    
    
}
