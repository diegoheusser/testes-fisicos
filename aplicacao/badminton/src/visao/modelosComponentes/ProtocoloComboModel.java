//ProtocoloComboModel.java
package visao.modelosComponentes;

import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 * @author 5102011212
 */
public class ProtocoloComboModel extends AbstractListModel implements ComboBoxModel {

    private List<modelo.Protocolo> itens;
    private modelo.Protocolo umProtocolo;

    public ProtocoloComboModel(List<modelo.Protocolo> itens) {
        this.itens = itens;
        umProtocolo = new modelo.Protocolo();
    }

    @Override
    public int getSize() {
        return itens.size();
    }

    @Override
    public Object getElementAt(int index) {
        return itens.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        umProtocolo = (modelo.Protocolo) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return umProtocolo;
    }
    

    public void update(modelo.Protocolo novo) {
        int index = 0;
        for (modelo.Protocolo protocolo : itens) {
            if (protocolo != null) {
                if (protocolo.getId() == novo.getId()) {
                    itens.set(index, novo);
                    fireContentsChanged(novo, index, index);
                }
            }
            index++;
        }
    }
}
