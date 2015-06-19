//ListaRenderer.java
package visao.modelosComponentes;

import java.awt.Color;
import java.awt.Component;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import visao.Execucao;

/**
 * @author 5102011212
 */
public class ListaRenderer extends JLabel implements ListCellRenderer {

    private Execucao e;
    private List<Object> listaIntervalo;
    private List<Object> todos;

    public ListaRenderer(Execucao e, List<Object> todos, List<Object> listaIntervalo) {
        this.e = e;
        this.todos = todos;
        this.listaIntervalo = listaIntervalo;
        setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        setText(value.toString());
        int cont = 0;
        for (Object object : todos) {
            if (listaIntervalo != null) {
                if (index == cont) {
                    if (estaNoIntervalo(object)) {
                        setBackground(Color.YELLOW);
                    } else {
                        setBackground(Color.WHITE);
                    }
                }
            } else {
                setBackground(Color.WHITE);
            }
            if (isSelected){
                setBackground(Color.LIGHT_GRAY);
            }
            cont++;
        }
        return this;
    }

    private boolean estaNoIntervalo(Object o) {
        for (Object intervalo : listaIntervalo) {
            if (intervalo.equals(o)) {
                return true;
            }
        }
        return false;
    }
}
