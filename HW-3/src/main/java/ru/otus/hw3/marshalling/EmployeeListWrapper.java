package ru.otus.hw3.marshalling;

import lombok.Getter;
import lombok.Setter;
import ru.otus.hw3.db.model.Employee;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "employees")
@XmlAccessorType(XmlAccessType.FIELD)

public class EmployeeListWrapper {
    @XmlElement(name = "employee")
    @Getter @Setter
    private List<Employee> employeeList;
}
