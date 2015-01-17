/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lab8.logic;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import lab8.Helper;

/**
 * Класс аннотирован для того, чтобы JAXB мог аккуратно его сериализовать в xml
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class JournalRecord {
    @XmlElement(name = "product")
    private String product;
    
    @XmlElement(name = "supplyDate")
    private Date date;
    
    @XmlElement(name = "count")
    private int count;

    public JournalRecord(String product, Date date, int count) {
        this.product = product;
        this.date = date;
        this.count = count;
    }

    public String getProduct() {
        return product;
    }

    public Date getDate() {
        return date;
    }

    public int getCount() {
        return count;
    }
    
    public String getFormattedDate(){
        return Helper.DATE_FORMAT.format(this.date);
    }
    
    /**
     * Для работы JAXB обязательно нужен конструктор без параметров.
     * Но так как в системе не предусмотрено создание пустых сущностей -- 
     * делаем его приватным.
     */
    private JournalRecord(){}
    
}
