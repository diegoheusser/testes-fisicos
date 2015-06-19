//ShuttleRunTableModelOffline
package visao.modelosComponentes;

import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import modelo.Atleta;

/**
 * @author 5102011212
 */
public class ShuttleRunTableModelOffline extends AbstractTableModel {

    private List<modelo.ShuttleRun> rows;
    private List<String> buttons;
    private String[] columns = new String[]{"Nome do atleta", "Data", "Hora", "Nível", "Tempo"};

    public ShuttleRunTableModelOffline(List<modelo.ShuttleRun> rows) {
        this.rows = rows;
    }

    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columns[columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex > 0 && columnIndex < 5) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        boolean erro = false;
        Atleta athlete = null;
        try {
            athlete = rows.get(rowIndex).getAtleta();
        } catch (Exception e) {
            return erro = true;
        }
        if (!erro) {
            switch (columnIndex) {
                case 0:
                    return athlete;
                case 1:
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    return sdf.format(rows.get(rowIndex).getData());
                case 2:
                    return rows.get(rowIndex).getHora();
                case 3:
                    return rows.get(rowIndex).getNivel();
                case 4:
                    return rows.get(rowIndex).getTempo();
                default:
                    return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public void setValueAt(Object obj, int row, int column) {
        switch (column) {
            case 0:
                rows.get(row).setAtleta((modelo.Atleta) obj);
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                rows.get(row).setNivel(Integer.parseInt(
                        String.valueOf(obj).replaceAll(" ", "")));
                break;
            case 4:
                rows.get(row).setTempo((String) obj);
                break;
            default:
                break;
        }
    }

    public void remove(int rowIndex) {
        rows.remove(rowIndex);
        buttons.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

}
