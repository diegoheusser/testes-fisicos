//AtletaTableModel.java
package visao.modelosComponentes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import modelo.Atleta;

/**
 * @author 5102011212
 */
public class AtletaTableModel extends AbstractTableModel {

    private List<Atleta> linhas;
    private String[] colunas = new String[]{
        "Codigo", "Nome", "Estatura", "Massa Corporal", "Tempo de Pratica", 
        "Tempo de pratica por semana", "Nível", "Data Nascimento", "Genero", 
        "Lateralidade", "Envergadura", "E-mail", "instituicao", "histórico"
    };

    public AtletaTableModel() {
        linhas = new ArrayList<>();
    }

    public AtletaTableModel(List<Atleta> listaAtleta) {
        linhas = new ArrayList<>(listaAtleta);
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
        Atleta atleta = linhas.get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                return atleta.getId();
            case 1:
                return atleta.getNome();
            case 2:
                return atleta.getEstatura();
            case 3:
                return atleta.getMassaCorporal();
            case 4:
                return atleta.getPratica();
            case 5:
                return atleta.getPraticaSemana();
            case 6:
                return atleta.getNivel();
            case 7:    
                return (new SimpleDateFormat("dd/MM/yyyy")).format(atleta.getDataNascimento());
            case 8:
                return atleta.getGenero();
            case 9:
                return atleta.getLateralidade();
            case 10:
                return atleta.getEnvergadura();
            case 11:
                return atleta.getEmail();
            case 12:
                return atleta.getInstituicao();
            case 13:
                return atleta.getHistorico();
            default:
                throw new IndexOutOfBoundsException("ColumnIndex out Bounds!");
        }

    }

    
    public void setValueAt(Atleta aValue, int rowIndex, int columnIndex) {
        Atleta atleta = linhas.get(rowIndex);

        switch (columnIndex) {
            case 0:
                atleta.setId(aValue.getId());
            case 1:
                atleta.setNome(aValue.getNome());
            case 2:
                atleta.setEstatura(aValue.getEstatura());
            case 3:
                atleta.setMassaCorporal(aValue.getMassaCorporal());
            case 4:
                atleta.setPratica(aValue.getPratica());
            case 5:
                atleta.setPraticaSemana(aValue.getPraticaSemana());
            case 6:
                atleta.setNivel(aValue.getNivel());
            case 7:
                atleta.setDataNascimento(aValue.getDataNascimento());
            case 8:
                atleta.setGenero(aValue.getGenero());
            case 9:
                atleta.setLateralidade(aValue.getLateralidade());
            case 10:
                atleta.setEnvergadura(aValue.getEnvergadura());
            case 11:
                atleta.setEmail(aValue.getEmail());
            case 12:
                atleta.setInstituicao(aValue.getInstituicao());
            case 13:
                atleta.setHistorico(aValue.getHistorico());
            default:
                throw new IndexOutOfBoundsException("ColumnIndex out Bounds!");
        }

    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public void addAtleta(Atleta n) {
        linhas.add(n);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void removeAtleta(int rowIndex) {
        linhas.remove(rowIndex);

        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public void addListaAtleta(List<Atleta> atleta) {
        int oldLength = getRowCount();
        linhas.addAll(atleta);
        fireTableRowsInserted(oldLength, getRowCount() - 1);
    }

    public void limpar() {
        linhas.clear();

        fireTableDataChanged();
    }

    public boolean isEmpty() {
        return linhas.isEmpty();
    }

    public Atleta getAtleta(int index) {
        return linhas.get(index);
    }
}
