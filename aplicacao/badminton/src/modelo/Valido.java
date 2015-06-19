//Valido.java
package modelo;

/**
 * @author Diego Heusser
 */
public enum Valido {

    INVALIDO(0), VALIDO(1);
    private final int valor;

    Valido(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
