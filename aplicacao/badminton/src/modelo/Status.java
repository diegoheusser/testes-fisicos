//Status.java''
package modelo;

/**
 * @author Diego Heusser
 */
public enum Status {

    NÃO_CONCLUIDO(0), CONCLUIDO(1);
    private final int valor;

    Status(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }


}
