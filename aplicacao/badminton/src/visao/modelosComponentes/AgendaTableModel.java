//AgendaTableModel.java
package visao.modelosComponentes;

import modelo.Agenda;
import modelo.Protocolo;
import modelo.Atleta;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 * @author 5102011212
 */
public class AgendaTableModel extends AbstractTableModel {

    private List<Agenda> row;
    private String[] column = new String[]{"Data e Hora", "Local", "Atletas", "Protocolos", "ID", "Situação"};

    public AgendaTableModel() {
        this.row = new ArrayList<>();
    }

    public AgendaTableModel(List<Agenda> scheduling) {
        this.row = scheduling;
    }

    @Override
    public int getRowCount() {
        return this.row.size();
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
        boolean error = false;
        Agenda scheduling = new Agenda();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            scheduling = row.get(rowIndex);
        } catch (Exception e) {
            error = true;
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        if (!error) {
            switch (columnIndex) {
                case 0:
                    String date = df.format(scheduling.getData());
                    return date;
                case 1:
                    return scheduling.getLocal();
                case 2:
                    List<Atleta> athlete = scheduling.getAtleta();
                    String cellAthlete = "<html> ";
                    for (Atleta list : athlete) {
                        cellAthlete += list.getNome() + "<br>";
                    }
                    cellAthlete += " </html>";
                    return cellAthlete;
                case 3:
                    List<Protocolo> protocol = scheduling.getProtocolo();
                    String cellProtocol = "<html> ";
                    for (Protocolo list : protocol) {
                        cellProtocol += list.getNome() + "<br>";
                    }
                    cellProtocol += " </html>";
                    return cellProtocol;
                case 4:
                    return scheduling.getId();
                case 5:
                    return scheduling.getSituacao();
                default:
                    throw new IndexOutOfBoundsException("ColumnIndex out Bounds!");

            }
        } else {
            return "";
        }
    }

    public List<Agenda> getRow() {
        return row;
    }
}
