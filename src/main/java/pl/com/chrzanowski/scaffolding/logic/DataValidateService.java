package pl.com.chrzanowski.scaffolding.logic;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;

@Service
@Transactional
public class DataValidateService {

    public DataValidateService() {
    }


    public void validateValue(Long value, String fieldName) {
        if(value == null) {
            throw new IllegalArgumentException(prepareMessageForEmpty(fieldName));
        }
        if (value < 0) {
            throw new IllegalArgumentException(prepareMessageForNegativeValue(fieldName));
        }
    }

    public void validateValue(Float value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(prepareMessageForEmpty(fieldName));
        }
        if (value < 0) {
            throw new IllegalArgumentException(prepareMessageForNegativeValue(fieldName));
        }

    }

    public void validateValue(Integer value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(prepareMessageForEmpty(fieldName));
        }
        if (value < 0) {
            throw new IllegalArgumentException(prepareMessageForNegativeValue(fieldName));
        }
    }

    public void validateTextField(String textField, String fieldName) {
        if (textField == null || textField.equals("")) {
            throw new IllegalArgumentException(prepareMessageForEmpty(fieldName));
        }
    }

    public void validateDate(LocalDate date, String fieldName) {
        if (date == null) {
            throw new IllegalArgumentException(prepareMessageForEmpty(fieldName));
        }
        if(date.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException(prepareMessageForFutureDate());
        }
    }

    private String prepareMessageForEmpty(String fieldName) {
        return "Pole \" " + fieldName + " \" nie może być puste.";
    }

    private String prepareMessageForNegativeValue(String fieldName) {
        return "Pole \" " + fieldName + " \" nie może mieć wartości ujemnej.";
    }

    private String prepareMessageForFutureDate() {
        return "Data nie może być późniejsza niż aktualna.";
    }

}
