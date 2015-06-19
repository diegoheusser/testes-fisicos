//AgendaCellRenderer.java
package visao.modelosComponentes;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import visao.Agenda;

/**
 * @author 5102011212
 */
public class AgendaCellRenderer extends DefaultTableCellRenderer {

    private Agenda a;

    public AgendaCellRenderer(Agenda agenda) {
        this.a = agenda;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table,
            Object obj, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent(
                table, obj, isSelected, hasFocus, row, column);
        if (isSelected) {
            cell.setBackground(table.getSelectionBackground());
        } else {
            if (a.tbAgenda.getValueAt(row, 5).equals("Cancelado")) {
                cell.setBackground(Color.red);
            } else if (a.tbAgenda.getValueAt(row, 5).equals("Concluído")) {
                Color g = new Color(181, 230, 29);// cria um novo tom de verde
                cell.setBackground(g);
            } else {
                cell.setBackground(Color.white);
            }
        }
        return cell;
    }
}
