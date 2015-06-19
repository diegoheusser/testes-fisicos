//ExecucaoTableModel.java
package visao.modelosComponentes;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import modelo.Atleta;

/**
 * * @author 5102011212
 */
public class ExecucaoTableModel extends AbstractTableModel {

    private List<Atleta> row;
    private String[] column = new String[]{"Atletas", ""};

    public ExecucaoTableModel(){
        this.row = new ArrayList<>();
    }
    
    public ExecucaoTableModel(List<Atleta> athlete) {
        this.row = athlete;
    }

    @Override
    public int getRowCount() {
        return row.size();
    }

    @Override
    public int getColumnCount() {
        return column.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return column[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Atleta athlete = null;
        if (rowIndex < row.size()) {
            try {
                if (rowIndex != -1) {
                    athlete = row.get(rowIndex);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
                e.printStackTrace();
            }
        }
        switch (columnIndex) {
            case 0:
                if (athlete != null) {
                    return athlete;
                } else {
                    return null;
                }
            case 1:
                if (athlete != null && athlete.getTempo() != null) {
                    return athlete.getTempo();
                } else {
                    return null;
                }
        }
        return null;
    }

    public void addAtleta(Atleta n) {
        row.add(n);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void updateAtleta(modelo.Atleta novo) {
        int index = 0;
        for (modelo.Atleta atleta : row) {
            if (atleta != null) {
                if (atleta.getId() == novo.getId()) {
                    row.set(index, novo);
                    fireTableRowsUpdated(index, index);
                }
            }
            index++;
        }
    }

    public int indexRow(modelo.Atleta n) {
        int index = 0;
        for (modelo.Atleta atleta : row) {
            if (atleta.equals(n)) {
                return index;
            }
            index++;
        }
        return index;
    }

    public boolean complete() {
        boolean b = true;
        for (modelo.Atleta umAtleta : row) {
            if (umAtleta != null) {
                if (umAtleta.getCor() == Color.white) {
                    b = false;
                }
            }
        }
        return b;
    }
}
