// FabricaTeste.java
// classe que fabrica os modelos dos testes
package modelo;

/**
 * @author Diego Heusser
 */
public class FabricaTeste {

    public static modelo.Teste getTeste(util.Teste teste) {
        switch (teste.getValor()) {
            case 0:
                return new LancamentoDeBolasNaParede();
            case 1:
                return new RepeticaoDeAgachamento();
            case 2:
                return new Cegonha();
            case 3:
                return new MusculaturaDoCore();
            case 4:
                return new SaltoVertical();
            case 5:
                return new SaltoHorizontal();
            case 6:
                return new Hexagono();
            case 7:
                return new TempoDeReacaoRegua();
            case 8:
                return new SentarEAlcancar();
            case 9:
                return new FlexibilidadeDeOmbroEPunho();
            default:
                return null;
        }
    }
}
