package ru.otus.hw4.data;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Currency {
    @XmlElement(name = "CharCode")
    private String code;

    @XmlElement(name = "Value")
    private String course;
}
