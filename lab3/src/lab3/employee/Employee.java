/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lab3.employee;

/**
 * Класс, представляющий собой работника.
 * Содержит данные о фамилии, зарплате и наличии детей, 
 * а так же предоставляет доступ к этим данным.
 * Не имеет методов для изменения данных.
 */
public class Employee {
    /**
     * Значение по умолчанию для зарплаты
     */    
    private final static int DEFAULT_SALARY = 450;
    
    /**
    * Значение по умолчанию для детей.
    */
    private final static boolean DEFAULT_HAS_CHILDREN = false;
    
    private final String lastName;
    private final int salary;
    private final boolean hasChildren;

    /**
     * Основной конструктор, требующий все параметры.
     * @param lastName фамилия работника
     * @param salary зарплата работника
     * @param hasChildren есть ли дети
     */
    public Employee(String lastName, int salary, boolean hasChildren) {
        this.lastName = lastName;
        this.salary = salary;
        this.hasChildren = hasChildren;
    }

    /**
     * Упрощенный констркутор, инициализирующий явно только фамилию
     * и использующий для зарплаты и детей стандартные значения.
     * @param lastName 
     */
    public Employee(String lastName) {
        this.lastName = lastName;
        this.salary = Employee.DEFAULT_SALARY;
        this.hasChildren = Employee.DEFAULT_HAS_CHILDREN;
    }
    
    /**
     * Конструктор копирования.
     * @param source исходный объект, который копируем
     * 
     */
    public Employee(Employee source){
       this.hasChildren = source.hasChildren;
       this.lastName = source.lastName;
       this.salary = source.salary;
    }

    public String getLastName() {
        return lastName;
    }

    public int getSalary() {
        return salary;
    }

    public boolean isHasChildren() {
        return hasChildren;
    }  

    /**
     * Переопределяем стандартный метод {@link #toString()}, 
     * чтобы удобнее было выводить данные объекта в виде строки.
     * Для сложения строк, из которых состоит наше описание используем
     * {@link StringBuilder}, так как он для этого предназначен и работает быстрее всего.
     * (в нагрузочных тестах быстрее почти в 1000 раз, чем просто +)
     * @return 
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Employee[lastname: ");
        sb.append(this.lastName).append(", salary: ").append(this.salary)
                .append(", hasChildren: ").append(this.hasChildren).append("]");
        return sb.toString();
    }
    
    
    
}
