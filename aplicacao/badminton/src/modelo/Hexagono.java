//Hexagono.java
//Classe modelo do teste: Héxagono
package modelo;

import java.text.SimpleDateFormat;
import java.util.Date;
import util.Coluna;
import util.Montador;

/**
 * @author Diego Heusser
 */
public class Hexagono implements Teste {

    private Atleta atleta;
    private int objeto;
    private Date data;
    private String hora;
    private String superficie;
    private int temperatura;
    private Protocolo protocolo;
    private Status status;
    private int id;
    private String button;

    public Hexagono() {
        data = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        hora = sdf.format(data);
        button = "Iniciar";
    }

    @Override
    public int getTemperatura() {
        return temperatura;
    }

    @Override
    public void setTemperatura(Object temperatura) {
        this.temperatura = Integer.parseInt(String.valueOf(temperatura));
    }

    @Override
    public String getSuperficie() {
        return superficie;
    }

    @Override
    public void setSuperficie(Object superficie) {
        this.superficie = (String) superficie;
    }

    @Override
    public Protocolo getProtocolo() {
        return protocolo;
    }

    @Override
    public void setProtocolo(Protocolo protocolo) {
        this.protocolo = protocolo;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    @Coluna(nome="Tempo (segundos)",posicao=3,editavel=true)
    public Object getObjeto() {
        return objeto;
    }

    @Override
    @Montador(coluna=3)
    public void setObjeto(Object distancia) {
        this.objeto = Integer.parseInt(new util.Regex().toInt(
                String.valueOf(distancia)));
    }

    @Override
    @Montador(coluna=2)
    public void setData(Object data) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        this.data = sdf.parse(String.valueOf(data));
    }

    @Override
    @Coluna(nome="Data",posicao=2,editavel=true)
    public Date getData() {
        return data;
    }

    @Override
    @Montador(coluna=1)
    public void setHora(Object hora) {
        this.hora = (String) hora;
    }

    @Override
    @Coluna(nome="Hora",posicao=1,editavel=true)
    public String getHora() {
        return hora;
    }

    @Override
    @Montador(coluna=0)
    public void setAtleta(Object atleta) {
        this.atleta = (Atleta) atleta;
    }

    @Override
    @Coluna(nome="Alteta",posicao=0,editavel=false)
    public Atleta getAtleta() {
        return atleta;
    }
    @Override
    @Coluna(nome="Ação", posicao=4,editavel=false)
    public String getButton() {
        return button;
    }
    @Override
    @Montador(coluna=4)
    public void setButton(String button) {
        this.button = button;
    }

    @Override
    public String getClassificacao() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setClassificacao(String classificacao) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
