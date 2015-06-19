// TableModelGeneric.java
package visao.modelosComponentes;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import modelo.Teste;
import util.Coluna;
import util.Montador;

/**
 * @author Diego Heusser
 */
public class TableModelGeneric extends AbstractTableModel {

    private List<Teste> lista;

    public TableModelGeneric(List<Teste> lista) {
        this.lista = lista;
    }

    public List<Teste> getLista() {
        return lista;
    }

    public void setLista(List<Teste> lista) {
        this.lista = lista;
    }

    public void clear() {
        lista = new ArrayList<>();
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        if (lista.isEmpty()) {
            return 0;
        }
        Object objeto = lista.get(0);
        Class<?> classe = objeto.getClass();

        int colunas = 0;
        for (Method metodo : classe.getDeclaredMethods()) {
            if (metodo.isAnnotationPresent(Coluna.class)) {
                colunas++;
            }
        }
        return colunas;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        try {
            Object objeto = lista.get(linha);
            Class<?> classe = objeto.getClass();
            for (Method metodo : classe.getDeclaredMethods()) {
                if (metodo.isAnnotationPresent(Coluna.class)) {
                    Coluna anotacao = metodo.getAnnotation(Coluna.class);
                    if (anotacao.posicao() == coluna) {
                        if (metodo.getReturnType() == Date.class) {
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                            return sdf.format(metodo.invoke(objeto));
                        } else if (anotacao.nome().equals("Ação")) {
                            return metodo.invoke(objeto);
                        }
                        return metodo.invoke(objeto);
                    }
                }
            }
            return null;
        } catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public void setValueAt(Object obj, int linha, int coluna) {
        try {
            Object objeto = lista.get(linha);
            Class<?> classe = objeto.getClass();
            for (Method metodo : classe.getDeclaredMethods()) {
                if (metodo.isAnnotationPresent(Montador.class)) {
                    Montador anotacao = metodo.getAnnotation(Montador.class);
                    if (anotacao.coluna() == coluna) {
                        if (anotacao.coluna() == 4) {
                            lista.get(linha).setButton(String.valueOf(obj));
                        } else {
                            metodo.invoke(objeto, obj);
                        }
                    }
                }
            }
            fireTableCellUpdated(linha, coluna);
            fireTableCellUpdated(linha, coluna+1);
        } catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public boolean isCellEditable(int linha, int coluna) {
        Object objeto = lista.get(linha);
        Class<?> classe = objeto.getClass();
        for (Method metodo : classe.getDeclaredMethods()) {
            if (metodo.isAnnotationPresent(Coluna.class)) {
                Coluna anotacao = metodo.getAnnotation(Coluna.class);
                if (anotacao.posicao() == coluna) {
                    return anotacao.editavel();
                }
            }
        }
        return false;
    }

    @Override
    public String getColumnName(int coluna) {
        Object objeto = lista.get(0);
        Class<?> classe = objeto.getClass();
        for (Method metodo : classe.getDeclaredMethods()) {
            if (metodo.isAnnotationPresent(Coluna.class)) {
                Coluna anotacao = metodo.getAnnotation(Coluna.class);
                if (anotacao.posicao() == coluna) {
                    return anotacao.nome();
                }
            }
        }
        return null;
    }
    
}
