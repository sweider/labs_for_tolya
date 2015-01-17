/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lab8.logic;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * 
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RecordsStorage {
    
    @XmlElementWrapper(name = "records")
    private final List<JournalRecord> records;

    RecordsStorage() {
        this.records = new ArrayList<>();
    }
    
    public void addRecord(JournalRecord record){
        this.records.add(record);
    }
    
    public void removeRecord(JournalRecord record){
        this.records.remove(record);
    }
    
    public void removeRecord(int index){
        this.records.remove(index);
    }
    
    public List<JournalRecord> getAllRecords(){
        return this.records;
    }
    
    public void saveToFile(File fileToSave) throws JAXBException{
        JAXBContext context = JAXBContext.newInstance(RecordsStorage.class);
        context.createMarshaller().marshal(this, fileToSave);
    }
    
    public static RecordsStorage loadFromFile(File file) throws JAXBException{
        JAXBContext context = JAXBContext.newInstance(RecordsStorage.class);
        return (RecordsStorage) context.createUnmarshaller().unmarshal(file);
    }
    
}
