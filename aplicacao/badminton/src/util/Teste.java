//Teste.java
//Enum para os protocolos
package util;

/**
 * @author Diego Heusser
 */
public enum Teste {
    LANÇAMENTO_DE_BOLAS_NA_PAREDE(0),
    REPETIÇÃO_DE_AGACHAMENTO(1),
    CEGONHA(2),
    MUSCULATURA_DO_CORE(3),
    SALTO_VERTICAL(4),
    SALTO_HORIZONTAL(5),
    HEXÁGONO(6),
    TEMPO_DE_REAÇÃO_RÉGUA(7),
    SENTAR_E_ALCAÇAR(8),
    FLEXIBILIDADE_DE_OMBRO_E_PUNHO(9);
    
    private final int valor;
    
    Teste(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
