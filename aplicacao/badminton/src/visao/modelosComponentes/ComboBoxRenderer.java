//ComboBoxRenderer.java
package visao.modelosComponentes;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 * @author 5102011212
 */
public class ComboBoxRenderer extends JLabel
                              implements ListCellRenderer {

    public ComboBoxRenderer() {
        super();
        setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        setText(value.toString());
        if (isSelected) {
            setBackground(list.getSelectionBackground());
        } else {
            setBackground(((modelo.Protocolo) value).getCor());
        }
        return this;
    }
}
