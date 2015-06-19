// CampoExcecao.java
package controle;

/**
 * @author Diego Heusser
 */
public class CampoExcecao extends Exception{

    public CampoExcecao(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public CampoExcecao(String string) {
        super(string);
    }
}
