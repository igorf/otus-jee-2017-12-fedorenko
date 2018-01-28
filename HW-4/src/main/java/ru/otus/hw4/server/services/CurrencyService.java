package ru.otus.hw4.server.services;

import ru.otus.hw4.data.Currency;
import ru.otus.hw4.data.ValCurs;
import ru.otus.hw4.util.ConstantHolder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.net.URL;
import java.util.List;

public class CurrencyService {

    public List<Currency> load() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ValCurs.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            ValCurs data = (ValCurs) jaxbUnmarshaller.unmarshal(new URL(ConstantHolder.CURRENCY_API_URL));
            return data.getCurrencyList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
