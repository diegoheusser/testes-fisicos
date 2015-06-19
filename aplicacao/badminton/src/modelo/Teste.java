//Teste.java
package modelo;

import java.util.Date;

/**
 * @author Diego Heusser
 */
public interface Teste {

    public int getTemperatura();

    public void setTemperatura(Object temperatura);

    public String getSuperficie();

    public void setSuperficie(Object superficie);

    public Protocolo getProtocolo();

    public void setProtocolo(Protocolo protocolo);

    public Status getStatus();

    public void setStatus(Status status);

    public int getId();

    public void setId(int id);

    public Object getObjeto();

    public void setObjeto(Object distancia);

    public void setData(Object data) throws Exception;

    public Date getData();

    public void setHora(Object hora);

    public String getHora();

    public void setAtleta(Object atleta);

    public Atleta getAtleta();
    
    public String getButton();
    
    public void setButton(String button);
    
    public String getClassificacao();
    
    public void setClassificacao(String classificacao);
}
