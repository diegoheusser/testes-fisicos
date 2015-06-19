//InstituicaoTableModel.java
package visao.modelosComponentes;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import modelo.Instituicao;

/**
 * @author 5102011212
 */
public class InstituicaoTableModel extends AbstractTableModel {

    private List<Instituicao> linhas;
    private String[] colunas = new String[]{
        "id", "CNPJ", "Nome", "Cidade", "Rua", "Telefone", "E-mail", "UF", "CEP"
    };

    public InstituicaoTableModel() {
        linhas = new ArrayList<>();
    }

    public InstituicaoTableModel(List<Instituicao> listaInstituicao) {
        linhas = new ArrayList<>(listaInstituicao);
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
        Instituicao instituicao = linhas.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return instituicao.getId();
            case 1:
                return instituicao.getCnpj();
            case 2:
                return instituicao.getNome();
            case 3:
                return instituicao.getCidade();
            case 4:
                return instituicao.getRua();
            case 5:
                return instituicao.getTelefone();
            case 6:
                return instituicao.getEmail();
            case 7:
                return instituicao.getUf();
            case 8:
                return instituicao.getCep();
            default:
                throw new IndexOutOfBoundsException("ColumnIndex out Bounds!");
        }

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Instituicao instituicao = linhas.get(rowIndex);

        switch (columnIndex) {
            case 0:
                instituicao.setId((int)aValue);
            case 1:
                instituicao.setCnpj(aValue.toString());
            case 2:
                instituicao.setNome(aValue.toString());
            case 3:
                instituicao.setCidade(aValue.toString());
            case 4:
                instituicao.setRua(aValue.toString());
            case 5:
                instituicao.setTelefone(aValue.toString());
            case 6:
                instituicao.setEmail(aValue.toString());
            case 7:
                instituicao.setUf(aValue.toString());
            case 8:
                instituicao.setCep(aValue.toString());
            default:
                throw new IndexOutOfBoundsException("ColumnIndex out Bounds!");
        }

    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public void addInstituicao(Instituicao n) {
        linhas.add(n);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void removeInstituicao(int rowIndex) {
        linhas.remove(rowIndex);

        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public void addListaInstituicao(List<Instituicao> instituicao) {
        int oldLength = getRowCount();
        linhas.addAll(instituicao);
        fireTableRowsInserted(oldLength, getRowCount() - 1);
    }

    public void limpar() {
        linhas.clear();

        fireTableDataChanged();
    }

    public boolean isEmpty() {
        return linhas.isEmpty();
    }

    public Instituicao getIntituicao(int index) {
        return linhas.get(index);
    }
}
