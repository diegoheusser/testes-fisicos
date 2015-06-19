// ShuttleRunTableModelOnline.java
// modelo de tabela para a execução online do teste shuttle run
package visao.modelosComponentes;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * @author Diego Heusser
 */
public class ShuttleRunTableModelOnline extends AbstractTableModel {

    private List<modelo.ShuttleRun> rows;
    private String[] columns = new String[]{"Nome do atleta", "Nível", "Tempo", "Ação"};
    private List<String> botao;

    public ShuttleRunTableModelOnline() {
        this.rows = new ArrayList<>();
        botao = new ArrayList<>();
        preenche();
    }

    public ShuttleRunTableModelOnline(List<modelo.ShuttleRun> rows) {
        this.rows = rows;
        botao = new ArrayList<>();
        preenche();
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
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rows.size() > 0) {
            switch (columnIndex) {
                case 0:
                    return rows.get(rowIndex).getAtleta();
                case 1:
                    return rows.get(rowIndex).getNivel();
                case 2:
                    return rows.get(rowIndex).getTempo();
                case 3:
                    return botao.get(rowIndex);
                default:
                    return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                rows.get(rowIndex).setAtleta((modelo.Atleta) aValue);
                break;
            case 1:
                rows.get(rowIndex).setNivel(Integer.parseInt((String) aValue));
                break;
            case 2:
                rows.get(rowIndex).setTempo((String) aValue);
                break;
            default:
                botao.set(rowIndex, (String) aValue);
                break;
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }


    public void remove(int rowIndex) {
        rows.remove(rowIndex);
        botao.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }


    private void preenche() {
        String a = "Finalizar";
        for (int i = 0; i < rows.size(); i++) {
            botao.add(a);
        }
    }

}
