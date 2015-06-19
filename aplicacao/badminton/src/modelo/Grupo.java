//Grupo.java
package modelo;

import java.util.Date;

/**
 * @author Diego Heusser
 */
public class Grupo {
    private int id;
    private String nome;
    private int instituicaoID;
    private Date dataInicial, dataFinal;

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getInstituicaoID() {
        return instituicaoID;
    }

    public void setInstituicaoID(int instituicaoID) {
        this.instituicaoID = instituicaoID;
    }

    @Override
    public String toString() {
        return nome;
    }
    
}
