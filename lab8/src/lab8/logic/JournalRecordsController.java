/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lab8.logic;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;
import javax.xml.bind.JAXBException;
import lab8.Helper;
import lab8.gui.JTableModel;

/**
 * Контроллер записей поставок.
 * Инкупсулирует в себе все, что связано с изменением журнала поставок, включая сохранение их на диске.
 * Использует дефолтный конструктор.
 * Содержит в себе ссылку на 
 */
public class JournalRecordsController {
    private RecordsStorage storage;
    private JTableModel tableModel;

    public JournalRecordsController() throws NoSuchMethodException {
        this.loadRecords();        
        this.tableModel = new JTableModel(this.storage.getAllRecords());
    }
    
    public void addRecord(String product, int count, Date date){
        JournalRecord record = new JournalRecord(product, date, count);
        this.storage.addRecord(record);
        this.saveToDisk();
        this.tableModel.fireTableDataChanged();
    }
    
    /**
     * Метод, отдающий гуевой части tableModel
     * @return 
     */
    public JTableModel getTableModel(){
        return this.tableModel;
    }
    
    /**
     * Подгружаем с диска нашу хранилку.
     * Проверяем есть ли файл. Если есть - грузим с него.
     * Если файла нет - создаем новую пустую хранилку.
     */
    private void loadRecords() {
        File file = new File(Helper.PATH_TO_FILE);
        if(file.exists()){
            try {
                this.storage = RecordsStorage.loadFromFile(file);
            } catch (JAXBException ex) {
                System.out.println("Error during loading from file!");
                ex.printStackTrace();
                this.storage = new RecordsStorage();
            }
        }
        else{
            this.storage = new RecordsStorage();
        }
    }
    
    /**
     * Сохраняем все на диск.
     * Проверяем есть ли файл. Если нет - создаем.
     * Когда файл точно есть -- отдаем его в качестве параметра для сохранения в хранилку.
     */
    private void saveToDisk(){
        File file = new File(Helper.PATH_TO_FILE);
        if(!file.exists()){
            try {
                Files.createFile(file.toPath());
            } catch (IOException ex) {
                System.out.println("Error during creating file on disk");
                ex.printStackTrace();
                return;
            }
        }
        try {
            this.storage.saveToFile(file);
        } catch (JAXBException ex) {
            System.out.println("Error during saving file on disk");
            ex.printStackTrace();
        }
    }
    
}
