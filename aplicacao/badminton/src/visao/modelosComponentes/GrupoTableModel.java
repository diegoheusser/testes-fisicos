//GrupoTableModel.java
package visao.modelosComponentes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import modelo.Grupo;
import modelo.Periodo;

/**
 *
 * @author 5102011212
 */
public class GrupoTableModel extends AbstractTableModel {

    private List<Grupo> linhas;
    private List<Periodo> linhasP;
    private String[] colunas = new String[]{
        "ID", "Grupo", "Instituição", "Data de Inicio", "Data de Fim", "grupoAtletaID"
    };

    public GrupoTableModel() {
        linhas = new ArrayList<>();
        linhasP = new ArrayList<>();
    }

    public GrupoTableModel(List<Grupo> listaGrupo, List<Periodo> listaGrupoAtleta) {
        linhas = new ArrayList<>(listaGrupo);
        linhasP = new ArrayList<>(listaGrupoAtleta);
    }

    @Override
    public int getRowCount() {
        return linhas.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return colunas[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Grupo grupo = linhas.get(rowIndex);
        boolean erro = false;
        Periodo grupoP = null;
        try {
            grupoP = linhasP.get(rowIndex);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            erro = true;
        }

        if (!erro) {
            switch (columnIndex) {
                case 0:
                    return grupo.getId();
                case 1:
                    return grupo.getNome();
                case 2:
                    return grupo.getInstituicaoID();
                case 3:
                    return (new SimpleDateFormat("dd/MM/yyyy")).format(grupo.getDataInicial());
                case 4:
                    return (new SimpleDateFormat("dd/MM/yyyy")).format(grupo.getDataFinal());
                case 5:
                    return grupoP.getPeriodoId();
                default:
                    throw new IndexOutOfBoundsException("ColumnIndex out Bounds!");
            }
        } else {
            return "";
        }

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Grupo grupo = linhas.get(rowIndex);
        Periodo grupoP = linhasP.get(rowIndex);
        switch (columnIndex) {
            case 0:
                grupo.setId((int) aValue);
            case 1:
                grupo.setNome(aValue.toString());
            case 2:
                grupo.setInstituicaoID((int) aValue);
            case 3:
                grupo.setDataInicial((Date) aValue);
            case 4:
                grupo.setDataFinal((Date) aValue);
            case 5:
                grupoP.setPeriodoId((int) aValue);
            default:
                throw new IndexOutOfBoundsException("ColumnIndex out Bounds!");
        }

    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public void addGrupo(Grupo n) {
        linhas.add(n);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void removeGrupo(int rowIndex) {
        linhas.remove(rowIndex);

        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public void addListaGrupo(List<Grupo> grupo) {
        int oldLength = getRowCount();
        linhas.addAll(grupo);
        fireTableRowsInserted(oldLength, getRowCount() - 1);
    }

    public void limpar() {
        linhas.clear();
        linhasP.clear();
        fireTableDataChanged();
    }

    public boolean isEmpty() {
        return linhas.isEmpty();
    }

    public Grupo getGrupo(int index) {
        return linhas.get(index);
    }
}
