//LancamentoDeBolasNaParede.java
//Classe model odo teste: Lançamento de bolas na parede
package modelo;

import java.text.SimpleDateFormat;
import java.util.Date;
import util.Coluna;
import util.Montador;

/**
 * @author Diego Heusser
 */
public class LancamentoDeBolasNaParede implements Teste {

    private Atleta atleta;
    private int objeto;
    private Date data;
    private String hora;
    private String superficie;
    private int temperatura;
    private Protocolo protocolo;
    private Status status;
    private int id;
    private String classificacao;

    public LancamentoDeBolasNaParede() {
        data = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        hora = sdf.format(data);
        classificacao = "";
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
    @Coluna(nome="Lances (em 30 segundos)",posicao=3,editavel=true)
    public Object getObjeto() {
        return objeto;
    }

    @Override
    @Montador(coluna=3)
    public void setObjeto(Object distancia) {
        int o = Integer.parseInt(new util.Regex().toInt(
                    String.valueOf(distancia)));
        this.objeto = o;
        setClassificacao(String.valueOf(o));
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
    @Coluna(nome="Atleta",posicao=0,editavel=false)
    public Atleta getAtleta() {
        return atleta;
    }

    @Override
    public String getButton() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setButton(String button) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @Coluna(nome = "Classificação", posicao = 4, editavel = false)
    public String getClassificacao() {
        return classificacao;
    }

    @Override
    @Montador(coluna = 4)
    public void setClassificacao(String classificacao) {
        int n = Integer.parseInt(classificacao);
        this.classificacao = 
                n > 35 ? "Excelente" :
                n > 29 ? "Bom" :
                n > 19 ? "Médio" :
                n > 14 ? "Ruim" :
                "Muito Ruim";
    }
}