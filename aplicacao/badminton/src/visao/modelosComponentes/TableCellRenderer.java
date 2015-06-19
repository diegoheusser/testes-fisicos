// TableCellRenderer.java
package visao.modelosComponentes;

import java.awt.Color;
import java.awt.Component;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import util.Coluna;

/**
 * @author Diego Heusser
 */
public class TableCellRenderer extends DefaultTableCellRenderer {

    private TableModelGeneric tableModel;

    public TableCellRenderer(TableModelGeneric tableModel) {
        this.tableModel = tableModel;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table,
            Object obj, boolean isSelected, boolean hasFocus, int linha, int coluna) {
        Component cell = super.getTableCellRendererComponent(
                table, obj, isSelected, hasFocus, linha, coluna);

        if (isSelected && coluna < 4) {
            cell.setBackground(table.getSelectionBackground());
        } else {
            cell.setBackground(table.getBackground());
            if (!tableModel.getLista().isEmpty()) {
                Object objeto = tableModel.getLista().get(linha);
                Class<?> classe = objeto.getClass();
                for (Method metodo : classe.getDeclaredMethods()) {
                    if (metodo.isAnnotationPresent(Coluna.class)) {
                        Coluna anotacao = metodo.getAnnotation(Coluna.class);
                        if (anotacao.nome().equals("Classificação") && anotacao.posicao() == coluna) {
                            try {
                                cell.setBackground(getCor((String) metodo.invoke(objeto)));
                            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                                Logger.getLogger(TableCellRenderer.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
            }
        }

        return cell;
    }

    private Color getCor(String c) {
        if (c.isEmpty()) {
            return Color.white;
        }
        return c.equals("Excelente") ? Color.blue
                : c.equals("Bom") ? Color.green
                : c.equals("Muito Bom") ? Color.green
                : c.equals("Acima da média") ? Color.yellow
                : c.equals("Médio") ? Color.yellow
                : c.equals("Abaixo da média") ? Color.yellow
                : c.equals("Ruim") ? Color.orange
                : Color.red;
    }

}
