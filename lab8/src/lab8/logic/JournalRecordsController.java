/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lab8.logic;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import javax.swing.JFrame;
import javax.xml.bind.JAXBException;

/**
 *
 * @author alex
 */
public class JournalRecordsController {
    private final static String PATH_TO_FILE = Paths.get("").toAbsolutePath().toString() + File.separator + "records_storage.xml";
    private RecordsStorage storage;
    private JFrame mainFrame;

    public JournalRecordsController() {
    }

    public void loadRecords() {
        File file = new File(PATH_TO_FILE);
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
    
    public void addRecord(String product, int count, Date date){
        JournalRecord record = new JournalRecord(product, date, count);
        this.storage.addRecord(record);
        this.saveToDisk();
        this.mainFrame.revalidate();
        this.mainFrame.repaint();
    }
    
    public List<JournalRecord> getRecords(){
        return this.storage.getAllRecords();
    }
    
    public void setTableFrame(JFrame frame){
        this.mainFrame = frame;
    }
    
    private void saveToDisk(){
        File file = new File(PATH_TO_FILE);
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
