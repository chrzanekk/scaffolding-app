var url = "/admin/api/scaffolding"
var vehiclesApiUrl = url + "/vehicles?"
var vehiclesWithoutTiresApiUrl = url + "/vehicles-without-tires"
var vehicleApiUrl = url + "/vehicle"
var brandsApiUrl = url + "/brands"
var modelsApiUrl = "/models"
var allModelsApiUrl = url + "/models"

$(document).ready(function () {
    $('#create-modal').on('hide.bs.modal', function (e) {
        clearCreateModal();
    });


    findVehicles();
    findVehiclesWithoutTires();
    $("#filter input, #filter select, [form='filter']").on("change", function () {
        findVehicles();
        findVehiclesWithoutTires();
    });

});


function findVehicles() {
    $.ajax({
        url: vehiclesApiUrl + prepareUrl(),
        type: "get",
        dataType: "json",
        contentType: "application/json"
    })
    .done(function (vehicles) {
        $("#records").empty();
        fillResults(vehicles.vehicles);
    })
    .fail(function(jqxhr, textStatus, errorThrown){
        displayErrorInformation(jqxhr.responseText);
    });
}

function fillResults(vehicles) {
    let value = 1;
    vehicles.forEach(function(vehicle){
        fillRow(vehicle, value);
        value = value + 1;
    });
}

function fillRow(vehicle, value) {
    $('#records').append(
        "<tr>" +
            "<td class='align-middle'>" + value + "</td>" +
            "<td class='align-middle'>" + vehicle.brandName + "</td>" +
            "<td class='align-middle'>" + vehicle.modelName + " </td>" +
            "<td class='align-middle'>" + vehicle.registrationNumber + "</td>" +
            "<td class='align-middle'>" + prepareDetailsButton(vehicle.id) + "</td>" +
            "<td class='align-middle'>" + prepareTiresButton(vehicle.id) + "</td>" +
            "<td class='align-middle'>" + prepareServicesButton(vehicle.id) + "</td>" +
        "</tr>"
    );
}

function prepareTiresButton(id) {
    return '<button type="button" class="btn btn-primary" onclick="goToTiresPage(' + id + ')">Opony</button>';
}

function prepareDetailsButton(id) {
    return '<button type="button" class="btn btn-primary" onclick="goToDetailsPage(' + id + ')">Detale</button>';
}

function prepareServicesButton(id) {
    return '<button type="button" class="btn btn-primary" onclick="goToServicesPage('+ id + ')">Serwis/Naprawa</button>';
}

function prepareDeleteButton(id) {
    return '<button type="button" class="btn btn-danger" onclick="setObjectToDeleteIdAndShowModal(' + id + ')">Usuń/Zezłomuj</button>';
}

function goToTiresPage(id) {
    window.location.href = "/admin/vehicles/" + id + "/tires";
}

function goToDetailsPage(id) {
    window.location.href = "/admin/vehicle/" + id;
}

function goToServicesPage(id) {
    window.location.href = "/admin/vehicle-service-actions/" + id;
}

function setObjectToDeleteIdAndShowModal(id) {
    objToDeleteId = id;
    $('#delete-object-modal').modal('show');
}

function clearCreateModal() {
    $("#create-brand").val('');
    $("#create-model").val('');
    $("#create-registration-number").val('');
    $("#create-vin").val('');
    $("#create-production-year").val('');
    $("#create-first-registration-date").val('');
    $("#create-free-places-for-technical-inspections").val('');
    $("#create-fuel-type").val('');
    $("#create-vehicle-type").val('');
    $("#create-length").val('');
    $("#create-width").val('');
    $("#create-height").val('');
}

function sendCreateRequest() {
    $.ajax({
        url: vehicleApiUrl,
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify({
                brandId: $("#create-brand").val(),
                modelId: $("#create-model").val(),
                registrationNumber: $("#create-registration-number").val(),
                vin: $("#create-vin").val(),
                productionYear: $("#create-production-year").val(),
                firstRegistrationDate: $("#create-first-registration-date").val(),
                freePlacesForTechnicalInspections: $("#create-free-places-for-technical-inspections").val(),
                fuelTypeId: $("#create-fuel-type").val(),
                vehicleTypeId: $("#create-vehicle-type").val(),
                length: $("#create-length").val(),
                width: $("#create-width").val(),
                height: $("#create-height").val()
        })
    })
        .done(function () {
            $("#create-modal").modal('hide');
            $("#operation-successful-modal").modal('show');
            findVehicles();
        })
        .fail(function (jqxhr, textStatus, errorThrown) {
            showError(prepareErrorMessage(jqxhr.responseText));
        })
}

//todo to na koniec, jak już będzie działało dodawanie i edycja
function sendDeleteRequest(){
//    $.ajax({
//        url: "/admin/api/crs/author/" + objToDeleteId,
//        type: "DELETE"
//    })
//        .done(function(response) {
//            $('#delete-object-modal').modal('hide');
//            $('#operation-successful-modal').modal('show');
//            findAuthors();
//        })
//        .fail(function(jqxhr, textStatus, errorThrown){
//            displayErrorInformation(jqxhr.responseText);
//        });
}

function prepareUrl(){
     var url = "";
     url += preparePaginationUrl();

     var brand = $("#brand-filter").find(":selected").val();
     var model = $("#model-filter").children(":selected").val();
     var registrationNumber = $("#registration-number-filter").children(":selected").val();

     if (brand != "") {
        url += "&brand_id=" + brand;
     }
     if (model != "") {
        url += "&model_id=" + model;
     }
     if (registrationNumber != "") {
        url += "&registrationNumber=" + registrationNumber;
     }

     return url;
}

function reloadModels() {
        var url = "";
        if ($("#create-brand").val() == "") {
            $("#create-model").val('');
            url = allModelsApiUrl;
        }
        else {
            url = brandsApiUrl + "/" + $("#create-brand").val() + modelsApiUrl;
        }
    $.ajax({
        url:  url,
        type: "GET",
        dataType: "json",
        contentType: "application/json"
    })
    .done(function (models) {
        $('#create-model').empty();
        fillResultsReloadedModels(models.models);

    })
        .fail(function(jqxhr, textStatus, errorThrown){
        displayErrorInformation(jqxhr.responseText);
    });
}

function fillResultsReloadedModels(models) {
    $('#create-model').append('<option value="">---</option>');
    models.forEach(function(model){
            fillReloadedModel(model);
        });
}

function fillReloadedModel(model) {
    $('#create-model').append('<option value=' + model.id + '>' + model.name +
    '</option>');
}

function reloadFilteredModels() {

    var url = "";
        if ($("#brand-filter").val() == "") {
            $("#model-filter").val('');
            url = allModelsApiUrl;
            findVehicles();
        }
        else {
            url = brandsApiUrl + "/" + $("#brand-filter").val() + modelsApiUrl;
        }
            $.ajax({
                url: url,
                type: "GET",
                dataType: "json",
                contentType: "application/json"
            })
            .done(function (models) {
                $('#model-filter').empty();
                fillResultsReloadedFilterModels(models.models);

            })
            .fail(function(jqxhr, textStatus, errorThrown){
                displayErrorInformation(jqxhr.responseText);
            });
}

function fillResultsReloadedFilterModels(models) {
    $('#model-filter').append('<option value="">---</option>');
    models.forEach(function(model){
            fillReloadedFilterModel(model);
    });
}

function fillReloadedFilterModel(model) {
    $('#model-filter').append('<option value=' + model.id + '>' + model.name + '</option>');
}

function findVehiclesWithoutTires() {
    $.ajax({
        url: vehiclesWithoutTiresApiUrl,
        type: "get",
        dataType: "json",
        contentType: "application/json"
    })
    .done(function (vehicles) {
          if(vehicles.vehicles.length == 0){
            $('#tire-error-container').hide();
          }
          else {
            $('#vehicles-without-tires').empty();
            fillResultsWithoutTires(vehicles.vehicles);
          }
    })
    .fail(function(jqxhr, textStatus, errorThrown){
        displayErrorInformation(jqxhr.responseText);
    });
}

function fillResultsWithoutTires(vehicles) {
    let value = 1;
    vehicles.forEach(function(vehicle){
        fillRowWithoutTires(vehicle, value);
        value = value + 1;
    });
}

function fillRowWithoutTires(vehicle, value) {
    $("#vehicles-without-tires").append(
        "<tr>" +
            "<td class='align-middle'>" + value + "</td>" +
            "<td class='align-middle'>" + vehicle.brandName + "</td>" +
            "<td class='align-middle'>" + vehicle.modelName + " </td>" +
            "<td class='align-middle'>" + vehicle.registrationNumber + "</td>" +
        "</tr>"
    );
}

function showError(text) {
    $("#error-alert-text").text(text);
    $("#error-alert").removeClass('d-none');
}