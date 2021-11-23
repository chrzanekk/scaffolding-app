package pl.com.chrzanowski.scaffolding.logic;

import org.springframework.stereotype.Service;
import pl.com.chrzanowski.scaffolding.domain.VehicleData;
import pl.com.chrzanowski.scaffolding.domain.VehicleFilter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pl.com.chrzanowski.scaffolding.logic.JdbcUtil.*;
import static pl.com.chrzanowski.scaffolding.logic.JdbcUtil.getFloat;

@Service
public class VehiclesService implements IVehicles {

    private VehiclesJdbcRepository vehiclesJdbcRepository;

    public VehiclesService(VehiclesJdbcRepository vehiclesJdbcRepository) {
        this.vehiclesJdbcRepository = vehiclesJdbcRepository;
    }

    public List<VehicleData> find(VehicleFilter filter)  {
        return getVehicles(vehiclesJdbcRepository.find(filter));
    }

    public VehicleData findById(VehicleFilter filter)  {
        return getVehicles(vehiclesJdbcRepository.find(filter)).get(0);
    }

    public Long add(VehicleData data) {
        validateVehicleData(data);
        return vehiclesJdbcRepository.create(new VehicleData(
                data.getBrandId(),
                data.getModelId(),
                data.getRegistrationNumber(),
                data.getVin(),
                data.getProductionYear(),
                data.getFirstRegistrationDate(),
                data.getFreePlacesForTechnicalInspections(),
                data.getFuelTypeId(),
                data.getVehicleTypeId(),
                data.getLength(),
                data.getWidth(),
                data.getHeight()
                ));
    }

    public void update(VehicleData data) {
        validateVehicleData(data);
        vehiclesJdbcRepository.update(
                data);
    }


    public VehicleData findByIdAndCheckTires(VehicleFilter filter) {
        validateVehicleTires(filter);
        return findById(filter);
    }

    public List<VehicleData> findWithoutTires(VehicleFilter filter) {
        return getVehiclesWithoutTires(vehiclesJdbcRepository.findWithoutTires(filter));
    }

    private List<VehicleData> getVehicles(List<Map<String, Object>> data) {
        List<VehicleData> list = new ArrayList<>();
        for (Map<String, Object> row : data) {
            list.add(new VehicleData(
                    getLong(row, "id"),
                    getLong(row, "brandId"),
                    getString(row, "brand"),
                    getLong(row, "modelId"),
                    getString(row, "model"),
                    getString(row, "registration_number"),
                    getString(row, "vin"),
                    getInteger(row, "production_year"),
                    getDate(row, "first_registration_date"),
                    getInteger(row, "free_places_for_technical_inspections"),
                    getString(row, "fuel_type"),
                    getString(row, "vehicle_type"),
                    getFloat(row,"length"),
                    getFloat(row,"width"),
                    getFloat(row,"height")
            ));
        }
        return list;
    }
    private List<VehicleData> getVehiclesWithoutTires(List<Map<String, Object>> data) {
        List<VehicleData> list = new ArrayList<>();
        for (Map<String, Object> row : data) {
            list.add(new VehicleData(
                    getLong(row, "id"),
                    getString(row, "brand"),
                    getString(row, "model"),
                    getString(row, "registration_number")
            ));
        }
        return list;
    }

    private void validateVehicleTires(VehicleFilter filter) {
        if(!findWithoutTires(filter).isEmpty()){
            throw new IllegalArgumentException("Samochód nie może jeździć bez kół! Dodaj zestaw opon");
        }
    }

    private void validateVehicleData(VehicleData data) {
        validateValue(data.getBrandId(), "Marka");
        validateValue(data.getModelId(), "Model");
        validateTextField(data.getRegistrationNumber(), "Numer rejestracyjny");
        validateTextField(data.getVin(), "VIN");
        validateDate(data.getFirstRegistrationDate(), "Data pierwszej rejestracji.");
        validateFreePlacesForTechnicalInspections(data.getFreePlacesForTechnicalInspections(), "Ilość wolnych miejsc na przegląd techniczny");
        validateValue(data.getFuelTypeId(), "Typ paliwa");
        validateValue(data.getVehicleTypeId(), "Typ pojazdu");
        validateValue(data.getLength(), "Długość (m)");
        validateValue(data.getWidth(), "Szerokość (m)");
        validateValue(data.getHeight(), "Wysokość (m)");
    }

    private void validateFreePlacesForTechnicalInspections(Integer value, String fieldName) {
        validateValue(value,fieldName);
        if(value > 6 || value < 1) {
            throw new IllegalArgumentException("Niepoprawna ilość wolnych miejsc na przeglądy techniczne.");
        }
    }

    private void validateValue(Long value, String fieldName) {
        if(value == null) {
            throw new IllegalArgumentException("Pole \"" + fieldName + " \" nie może być puste.");
        }
        if (value < 0) {
            throw new IllegalArgumentException("Pole \" " + fieldName + " \" nie może mieć wartości ujemnej.");
        }
    }

    private void validateValue(Float value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException("Pole \" " + fieldName + " \" nie może być puste.");
        }
        if (value < 0) {
            throw new IllegalArgumentException("Pole \" " + fieldName + " \" nie może mieć wartości ujemnej.");
        }

    }

    private void validateValue(Integer value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException("Pole \" " + fieldName + " \" nie może być puste.");
        }
        if (value < 0) {
            throw new IllegalArgumentException("Pole \" " + fieldName + " \" nie może mieć wartości ujemnej.");
        }
    }

    private void validateTextField(String textField, String fieldName) {
        if (textField == null || textField.equals("")) {
            throw new IllegalArgumentException("Pole \" " + fieldName + "\"  nie może być puste.");
        }
    }

    private void validateDate(LocalDate date, String fieldName) {
        if (date == null) {
            throw new IllegalArgumentException("Pole \" " + fieldName + " \" nie może być puste.");
        }
        if(date.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Data nie może być późniejsza niż aktualna.");
        }
    }
}
