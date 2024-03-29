package pl.com.chrzanowski.scaffolding.logic.utils;


import java.math.BigDecimal;
import java.time.LocalDate;


public class DataValidationUtil {


    public static void validateValue(Long value, String fieldName) {
        if (value == null) {
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

    public static void validateBoolean(Boolean condition, String fieldName) {
        if (condition == null) {
            throw new IllegalArgumentException(prepareMessageForEmpty(fieldName));
        }
    }


    public static void validateDate(LocalDate date, String fieldName) {
        if (date == null) {
            throw new IllegalArgumentException(prepareMessageForEmpty(fieldName));
        }
        if (date.isAfter(LocalDate.now())) {
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
        if (actionTypes.length == 0) {
            throw new IllegalArgumentException("Wybierz przynajmniej jeden rodzaj usługi.");
        }
    }

    public static void validateFreePlacesForTechnicalInspections(Integer value, String fieldName) {
        validateValue(value, fieldName);
        if (value > 5 || value < 1) {
            throw new IllegalArgumentException("Niepoprawna ilość wolnych miejsc na przeglądy techniczne.");
        }
    }

    public static BigDecimal validateAndCreateValue(String value) {
        if (!value.isEmpty()) {
            if (isValuePositive(value)) {
                return new BigDecimal(value);
            } else {
                throw new IllegalArgumentException("Wartość nie może być ujemna.");
            }
        } else {
            throw new IllegalArgumentException("Wartość netto faktury lub stawka podatku VAT nie może być pusta.");
        }
    }

    public static void validateTireProductionYear(Integer productionYear, String fieldName) {
        validateValue(productionYear, fieldName);
        if (LocalDate.now().getYear() - productionYear > 4) {
            throw new IllegalArgumentException("Zakupione opony mają powyżej 4 lat. Zaleca się zakup nowszych.");
        }
    }

    public static void validateTireWidth(Integer width, String fieldName) {
        validateValue(width, fieldName);
        validateIntegerValueInCompartment(width, fieldName, 125, 355);
    }

    public static void validateTireProfile(Integer profile, String fieldName) {
        validateValue(profile, fieldName);
        validateIntegerValueInCompartment(profile, fieldName, 30, 85);
    }

    public static void validateTireDiameter(Integer diameter, String fieldName) {
        validateValue(diameter, fieldName);
        validateIntegerValueInCompartment(diameter, fieldName, 10, 24);
    }

    public static void validateCapacityIndex(Integer capacityIndex, String fieldName) {
        validateValue(capacityIndex, fieldName);
        validateIntegerValueInCompartment(capacityIndex, fieldName, 75, 110);
    }




    private static void validateIntegerValueInCompartment(Integer value, String fieldName, Integer minValue,
                                                          Integer maxValue) {
        validateValue(value, fieldName);
        if (value < minValue || value > maxValue) {
            throw new IllegalArgumentException(prepareErrorMessageOutOfCompartment(fieldName, minValue, maxValue));
        }

    }

    private static boolean isValuePositive(String value) {
        return !value.startsWith("-");
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

    private static String prepareErrorMessageOutOfCompartment(String fieldName, Integer minValue, Integer maxValue) {
        return "Niepoprawna wartość pola " + fieldName + ". Podaj wartość z zakresu: " + minValue + "-" + maxValue +
                ".";
    }

    public static String formatTaxNumber(String taxNumber) {
        taxNumber = cleanInput(taxNumber);
        return taxNumber.substring(0, 3) + "-" + taxNumber.substring(3, 6) + "-" + taxNumber.substring(6, 8) + "-" + taxNumber.substring(8, 10);
    }

    public static String formatPostalCode(String postalCode) {
        postalCode = cleanInput(postalCode);
        return postalCode.substring(0, 2) + "-" + postalCode.substring(2, 5);
    }

    private static String cleanInput(String input) {
        if (input != null) {
            return input.replaceAll("[^a-zA-Z0-9]", "");
        }
        return "";
    }

}
