//ExecucaoCellRenderer.java
package visao.modelosComponentes;

import java.awt.Color;
import java.awt.Component;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * @author 5102011212
 */
public class ExecucaoCellRenderer extends DefaultTableCellRenderer {

    private List<modelo.Atleta> listaIntervalo;

    public ExecucaoCellRenderer(List<modelo.Atleta> listaIntervalo) {
        this.listaIntervalo = listaIntervalo;
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table,
            Object obj, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent(
                table, obj, isSelected, hasFocus, row, column);

        if (isSelected) {
            cell.setBackground(table.getSelectionBackground());
        } else {
            modelo.Atleta umAtleta = (modelo.Atleta) (table.getValueAt(row, 0));
            if (umAtleta != null) {
                cell.setBackground(umAtleta.getCor());
            } else {
                cell.setBackground(Color.white);
            }
        }

        return cell;
    }
}
