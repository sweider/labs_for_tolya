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

/**
 *
 * Компонент JTable для своей работы требует класса-модели для таблицы, так как работает по 
 * MVC шаблону.
 * Данный класс и является реализацией этой необходимой модели.
 * Так как в нашей таблице мы не разрешаем редактировать данные (это противоречит здравому смыслу)
 * мы это явно здесь запрещаем для всех ячеек таблицы.
 * 
 */
public class JTableModel extends  AbstractTableModel {
    private final static int PRODUCT_INDEX = 0;
    private final static int DATE_INDEX = PRODUCT_INDEX + 1;
    private final static int COUNT_INDEX = DATE_INDEX + 1;

    /**
     * Собственно записи, которые мы будем отображать.
     * Передаются сюда из контроллера по ссылке, поэтому
     * при добавлении новых записей нужно будет только попросить табличку обновиться, 
     * без передачи дополнительно это записи.
     */
    private final List<JournalRecord> journalRecords;

    
    /**
     * Map для имен столбцов.
     */
    private Map<Integer, String> COLUMN_NAMES;
    
    /**
     * Маp для сохранненых методов.
     */
    private Map<Integer, Method> METHODS_MAP;

    /**
     * Конструктор, требующий список отображаемых записей.
     * @param journalRecords
     * @throws NoSuchMethodException 
     */
    public JTableModel(List<JournalRecord> journalRecords) throws NoSuchMethodException {
        this.journalRecords = journalRecords;
        this.initializeMappers();
    }

    /**
     * На самом деле самое сложное здесь -- это придумать, как повесить табличный метод
     * {@link #getValueAt(int, int)} на методы наших объектов-записей.
     * Для того, чтобы это сделать используется механизм Reflection.
     * Мы знаем как называются методы, возвращающие данные для каждого стоблца.
     * С помощью этого механизма мы их получаем из класса по имени --> entryClass.getDeclaredMethod("getProduct"));
     * и сохраняем под соответствующим индексом в мапе.
     * Когда придет запрос -- мы по индексу достаем метод, и исполняя его получаем необходимые значения.
     * Профит.
     * Инициализацией наших мапперов этот метод и занимается
     * @throws NoSuchMethodException 
     */
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

    /**
     * Кол-во строк таблицы соответсвует количеству записей в нашей хранилке, что и возвращаем.
     * @return 
     */
    @Override
    public int getRowCount() {
        return this.journalRecords.size();
    }

    @Override
    public int getColumnCount() {
        return this.COLUMN_NAMES.size();
    }

    /**
     * Именно для этого метода инициализировали Map. 
     * Надеюсь тут уже все очевидно.
     * @param columnIndex
     * @return 
     */
    @Override
    public String getColumnName(int columnIndex) {
        return this.COLUMN_NAMES.get(columnIndex);
    }

    /**
     * В нашем случае у нас все данные все равно будут представлены в виде строки, 
     * поэтому заморачиваться с тем, чтобы вернуть везде правильных класс не имеет смысла.
     * Integer в String преобразуется красиво сам, Product у нас и так строка, а дату мы отдаем уже подготовленной строкой.
     * @param columnIndex
     * @return 
     */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    /**
     * Нет, у нас нельзя ничего редактирвать.
     * @param rowIndex
     * @param columnIndex
     * @return 
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    /**
     * Именно про этот метод написано выше.
     * По rowIndex достаем из {@link #journalRecords} наш объект-запись.
     * По columnIndex достаем метод, который нужно вызвать.
     * Вызываем метод на нашем объекте и отдаем полученные данные
     * @param rowIndex
     * @param columnIndex
     * @return 
     */
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

    /**
     * Мы не разрешаем редактирование таблицы вообще и этот метод не должен вызываться.
     * Но на всякий случай мы его переопределим пустым.
     * @param aValue
     * @param rowIndex
     * @param columnIndex 
     */
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        //nothing. editing isn't allowed
    }
}
