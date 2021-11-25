package pl.com.chrzanowski.scaffolding.logic;


import java.time.LocalDate;


public class DataValidationUtil {


    public static void validateValue(Long value, String fieldName) {
        if(value == null) {
            throw new IllegalArgumentException(prepareMessageForEmpty(fieldName));
        }
        if (value < 0) {
            throw new IllegalArgumentException(prepareMessageForNegativeValue(fieldName));
        }
    }

    public static void validateValue(Float value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(prepareMessageForEmpty(fieldName));
        }
        if (value < 0) {
            throw new IllegalArgumentException(prepareMessageForNegativeValue(fieldName));
        }

    }

    public static void validateValue(Integer value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(prepareMessageForEmpty(fieldName));
        }
        if (value < 0) {
            throw new IllegalArgumentException(prepareMessageForNegativeValue(fieldName));
        }
    }

    public static void validateTextField(String textField, String fieldName) {
        if (textField == null || textField.equals("")) {
            throw new IllegalArgumentException(prepareMessageForEmpty(fieldName));
        }
    }

    public static void validateDate(LocalDate date, String fieldName) {
        if (date == null) {
            throw new IllegalArgumentException(prepareMessageForEmpty(fieldName));
        }
        if(date.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException(prepareMessageForFutureDate());
        }
    }

    public static void validateCarMileage(Integer carMileage, String fieldName) {
        if (carMileage == null) {
            throw new IllegalArgumentException(prepareMessageForEmpty(fieldName));
        }
        if (carMileage <= 0) {
            throw new IllegalArgumentException(prepareMessageForNegativeValue(fieldName));
        }
    }

    public static void validateServiceTypes(Long[] actionTypes) {
        if(actionTypes.length==0) {
            throw new IllegalArgumentException("Wybierz przynajmniej jeden rodzaj usługi.");
        }
    }

    private static String prepareMessageForEmpty(String fieldName) {
        return "Pole \" " + fieldName + " \" nie może być puste.";
    }

    private static String prepareMessageForNegativeValue(String fieldName) {
        return "Pole \" " + fieldName + " \" nie może mieć wartości ujemnej.";
    }

    private static String prepareMessageForFutureDate() {
        return "Data nie może być późniejsza niż aktualna.";
    }

}
