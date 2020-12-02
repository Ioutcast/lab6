package org.example.Worker;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Класс рабочего, который записывается в коллекцию.
 */
@ToString
@EqualsAndHashCode
public class Worker implements Comparable<Worker>, Serializable {
    private static int globalID = 0;
    @Getter
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    @Getter
    private String name; //Поле не может быть null, Строка не может быть пустой
    @Getter
    private Coordinates coordinates; //Поле не может быть null
    @Getter
    private LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    @Getter
    private Double salary; //Поле не может быть null, Значение поля должно быть больше 0
    @Getter
    private LocalDate startDate; //Поле не может быть null
    @Getter
    private LocalDateTime endDate; //Поле может быть null
    @Getter
    private Status status; //Поле не может быть null
    @Getter

    private Organization organization; //Поле может быть null

    /**
     * Сесстер без параметров для дальнейшего заполнения
     */
    public Worker() {
        globalID++;
        this.id = globalID;
        this.creationDate = LocalDateTime.now();
    }

    /**
     * Сеттер с параметрами
     *
     * @param id
     * @param name
     * @param coordinates
     * @param salary
     * @param startDate
     * @param endDate
     * @param status
     * @param organization
     */
    public Worker(int id, String name, Coordinates coordinates, Double salary, LocalDate startDate, LocalDateTime endDate, Status status, Organization organization) {
        globalID++;
        this.id = globalID;
        this.name = name;
        this.coordinates = coordinates;
        assert salary > 0;
        this.salary = salary;
        this.startDate = startDate;
        this.creationDate = LocalDateTime.now();
        this.endDate = endDate;
        this.status = status;
        this.organization = organization;
    }


    /**
     * сеттер имени
     *
     * @param name
     */
    public void setName(@NonNull String name) {
        if (name.trim().equals("")) {
            throw new NullPointerException("name is marked NotNull but is null");
        }
        this.name = name;
    }

    /**
     * сеттер координат
     *
     * @param coordinates
     */
    public void setCoordinates(@NonNull Coordinates coordinates) {
        this.coordinates = coordinates;

    }

    /**
     * сеттер зарплаты
     *
     * @param salary
     */
    public void setSalary(@NonNull Double salary) {
        if (salary < 0) {
            throw new IllegalArgumentException("salary<0!!?");
        }
        this.salary = salary;
    }

    /**
     * сеттер даты начала
     *
     * @param startDate
     */
    public void setStartDate(@NonNull LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * сеттер статуса
     *
     * @param status
     */
    public void setStatus(@NonNull Status status) {
        this.status = status;
    }

    /**
     * сеттер организации
     *
     * @param organization
     */
    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    /**
     * сравнение
     *
     * @param o
     * @return result
     */
    @Override
    public int compareTo(Worker o) {
        int result = this.name.compareTo(o.name);
        if (result==0)  result = getResult(result, this.coordinates.compareTo(o.coordinates));
        if (result==0)  result = getResult(result, this.salary.compareTo(o.salary));
        if (result==0) result = getResult(result, this.organization.compareTo(o.organization));
        if (result==0) result = getResult(result, this.status.compareTo(o.status));
        if (result==0) result = getResult(result, this.creationDate.compareTo(o.creationDate));
        if (result==0)  result = getResult(result, this.startDate.compareTo(o.startDate));
        if (result==0) result = getResult(result, this.endDate.compareTo(o.endDate));
        return result;
    }

    /**
     * результат сравнения
     *
     * @param result
     * @param i
     * @return
     */
    private int getResult(int result, int i) {
        if (result == 0) {
            result = i;
        }
        return result;
    }

    /**
     * сеттер даты конца, localDatetime
     *
     * @param endDate
     */
    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    /**
     * сеттер даты конца , стринг
     *
     * @param endDate
     */
    public void setEndDate(String endDate) {
        this.endDate = null;
    }

}


