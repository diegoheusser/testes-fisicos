//Protocolo.java
package modelo;

import java.awt.Color;
import java.util.Objects;

/**
 * @author Diego Heusser
 */
public class Protocolo {
    private String tipo, nome;
    private int id;
    private Color cor;

    public Protocolo(){
        cor = Color.green;
    }
    
    public Color getCor() {
        return cor;
    }

    public void setCor(Color cor) {
        this.cor = cor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return nome;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Protocolo other = (Protocolo) obj;
        if (!Objects.equals(this.tipo, other.tipo)) {
            return false;
        }
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
}
