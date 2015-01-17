/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab8.gui;

import lab8.logic.JournalRecord;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author alex
 */
public class JTableModel extends  AbstractTableModel implements TableModel {
    private final static int PRODUCT_INDEX = 0;
    private final static int DATE_INDEX = PRODUCT_INDEX + 1;
    private final static int COUNT_INDEX = DATE_INDEX + 1;

    private final List<JournalRecord> journalRecords;

    private Map<Integer, String> COLUMN_NAMES;
    private Map<Integer, Method> METHODS_MAP;

    public JTableModel(List<JournalRecord> journalRecords) throws NoSuchMethodException {
        this.journalRecords = journalRecords;
        this.initializeMappers();
    }

    private void initializeMappers() throws NoSuchMethodException {
        Class entryClass = JournalRecord.class;
        this.COLUMN_NAMES = new HashMap<>();
        this.METHODS_MAP = new HashMap<>();

        this.COLUMN_NAMES.put(PRODUCT_INDEX, "Product");
        this.METHODS_MAP.put(PRODUCT_INDEX, entryClass.getDeclaredMethod("getProduct"));

        this.COLUMN_NAMES.put(DATE_INDEX, "Date");
        this.METHODS_MAP.put(DATE_INDEX, entryClass.getDeclaredMethod("getFormattedDate"));

        this.COLUMN_NAMES.put(COUNT_INDEX, "Count");
        this.METHODS_MAP.put(COUNT_INDEX, entryClass.getDeclaredMethod("getCount"));

    }

    @Override
    public int getRowCount() {
        return this.journalRecords.size();
    }

    @Override
    public int getColumnCount() {
        return this.COLUMN_NAMES.size();
    }

    @Override
    public String getColumnName(int columnIndex) {
        return this.COLUMN_NAMES.get(columnIndex);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = null;
        try {
            JournalRecord record = this.journalRecords.get(rowIndex);
            value = this.METHODS_MAP.get(columnIndex).invoke(record);
        } 
        catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            System.out.println("Error during invoke reflected method!");
        }
        return value;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        //nothing. editing isn't allowed
    }
}
