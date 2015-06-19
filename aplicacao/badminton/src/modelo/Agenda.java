package modelo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Diego Heusser
 */
public class Agenda {
    private int id;
    private Date data;
    private String local, situacao;
    private List<Atleta> atleta;
    private List<Protocolo> protocolo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   
    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public List<Atleta> getAtleta() {
        return atleta;
    }

    public void setAtleta(List<Atleta> atleta) {
        this.atleta = atleta;
    }

    public List<Protocolo> getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(List<Protocolo> protocolo) {
        this.protocolo = protocolo;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return String.valueOf(sdf.format(data));
    }
    
    
}
