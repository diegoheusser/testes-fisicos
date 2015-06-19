// Regex.java
// Classe com expressões regulares
package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Diego Heusser
 */
public class Regex {
    Pattern vtoCm;
    Pattern vtoInteger;
    public Regex() {
        vtoCm = Pattern.compile("[^0123456789.]");
        vtoInteger = Pattern.compile("[^-0123456789]");
    }

    public String toCm(String cm) {
        Matcher query = this.vtoCm.matcher(cm.replaceFirst(",", "."));
        return query.replaceAll("");
    }
    
    public String toInt(String integer) {
        Matcher query = this.vtoInteger.matcher(integer);
        return query.replaceAll("");
    }
    
    public int toMilesegundos (String ml){
        ml = toCm(ml);
        double m = Double.parseDouble(ml);
        m = m/100;
        return (int) (1000*(Math.sqrt(((2*m)/9.8))));
    }
}
