/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lab8;

import java.io.File;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;

/**
 * Класс-помощник, содержащий в себе общие сведения, нужные в приложении.
 * Можно рассматривать как своеобразный конфиг.
 */
public class Helper {    
    
    /**
     * Форматтер для работы с датой.
     */
    public final static SimpleDateFormat DATE_FORMAT  = new SimpleDateFormat("dd/MM/yyyy");
    
    /**
     * Путь к файлу с журналом записей на диске. Сюда будут сохраняться данные.
     */
    public final static String PATH_TO_FILE = Paths.get("").toAbsolutePath().toString() + File.separator + "records_storage.xml";
}
