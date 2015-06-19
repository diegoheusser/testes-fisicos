/**
 * @author Diego Heusser
 */
public class Principal {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                controle.MenuPrincipal crt = new controle.MenuPrincipal();
                crt.executar();
            }
        });
    }
}
