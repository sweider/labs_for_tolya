/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lab3;

import lab3.employee.Employee;

/**
 *
 *
 */
public class launcher {
    //размер нашего массива в виде константы    
    private final static int ARRAY_SIZE = 100;
    //количество инициализируемых сокращенным конструктором
    private final static int DEFAULT_EMPLOYEE_SIZE = 50;
    //множитель для формирования зп
    private final static int SALARY_MULTIPLIER = 7;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Employee[] employeeArray = new Employee[ARRAY_SIZE];
        
        //первую часть заполняем с использованием страндартного конструктора
        for(int i = 0; i < DEFAULT_EMPLOYEE_SIZE; i++){
            employeeArray[i] = new Employee("Employee#" + i);
        }
        
        //второй части устанавливаем зп в зависимости от порядкового номера
        //а детей устанавливаем в зависимости от четности порядкового номера
        for(int i = DEFAULT_EMPLOYEE_SIZE; i< ARRAY_SIZE; i++){
            employeeArray[i] = new Employee("Employee#" + i, i * SALARY_MULTIPLIER, i % 2 == 0);
        }
        
        //выводим всех в консоль.
        for(Employee employee: employeeArray){
            System.out.println(employee.toString());
        }
        
        //считаем и выводим среднюю зп
        System.out.println("Average salary: " + getAverageSalary(employeeArray));
        
        //считаем и выводим количество с детьми
        System.out.println("Employees with children: " + getWithChildrenCount(employeeArray));
    }
    
    /**
     * Статический метод для подсчета средней зарплаты.
     * По массиву ходит циклом foreach.
     * sum объявлен как float чтобы не было проблем с делением.
     * (int / int -- целая часть только останется)
     * @param employees массив работников
     * @return 
     */
    public static float getAverageSalary(Employee[] employees){
        float sum = 0;
        for(Employee employee : employees){
            sum += employee.getSalary();
        }
        return sum / employees.length;
    }
    
    /**
     * Для подсчета количества детей
     * @param employees
     * @return 
     */
    public static float getWithChildrenCount(Employee[] employees){
        int sum = 0;
        for(Employee employee : employees){
            if(employee.isHasChildren()) { sum += 1; }
        }
        return sum;
    }
    
}
