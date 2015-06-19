//SaltoHorizontal.java
//Classe modelo do teste Salto horizontal
package modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import util.Coluna;
import util.Montador;

/**
 * @author Diego Heusser
 */
public class SaltoHorizontal implements Teste {

    private Atleta atleta;
    private int distancia;
    private Date data;
    private String hora;
    private String superficie;
    private int temperatura;
    private Protocolo protocolo;
    private Status status;
    private int id;
    private String classificacao;

    public SaltoHorizontal() {
        data = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        hora = sdf.format(data);
        classificacao = "";
    }

    @Override
    @Montador(coluna = 2)
    public void setData(Object data) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        this.data = sdf.parse(String.valueOf(data));
    }

    @Override
    @Coluna(nome = "Data", posicao = 2, editavel = true)
    public Date getData() {
        return data;
    }

    @Override
    @Montador(coluna = 1)
    public void setHora(Object hora) {
        this.hora = (String) hora;
    }

    @Override
    @Coluna(nome = "Hora", posicao = 1, editavel = true)
    public String getHora() {
        return hora;
    }

    @Override
    @Montador(coluna = 0)
    public void setAtleta(Object atleta) {
        this.atleta = (Atleta) atleta;
    }

    @Override
    @Coluna(nome = "Atleta", posicao = 0, editavel = false)
    public Atleta getAtleta() {
        return atleta;
    }

    @Override
    @Coluna(nome = "Distância (Cm)", posicao = 3, editavel = true)
    public Object getObjeto() {
        return distancia;
    }

    @Override
    @Montador(coluna = 3)
    public void setObjeto(Object objeto) {
        int o = Integer.parseInt(new util.Regex().toInt(
                String.valueOf(objeto)));
        this.distancia = o;
        setClassificacao(String.valueOf(o));
    }

    @Override
    public int getTemperatura() {
        return temperatura;
    }

    @Override
    public void setTemperatura(Object temperatura) {
        this.temperatura = Integer.parseInt(
                String.valueOf(temperatura));
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
                getAtleta().getGenero().equals("Masculino") ? (
                    n > 250 ? "Excelente" :
                    n > 240 ? "Bom" :
                    n > 230 ? "Acima da média" :
                    n > 220 ? "Médio" :
                    n > 210 ? "Abaixo da média" :
                    n > 190 ? "Ruim" :
                    "Muito Ruim"
                ):(
                    n > 200 ? "Excelente" :
                    n > 190 ? "Bom" :
                    n > 180 ? "Acima da média" :
                    n > 170 ? "Médio" :
                    n > 160 ? "Abaixo da média" :
                    n > 140 ? "Ruim" :
                    "Muito Ruim"
                );
    }
}