var url = "/admin/api/scaffolding"
var vehiclesApiUrl = url + "/vehicles?"
var vehicleApiUrl = url + "/vehicle"

$(document).ready(function () {
    $('#create-modal').on('hide.bs.modal', function (e) {
        clearCreateModal();
    });


    findVehicles();
    $("#filter input, #filter select, [form='filter']").on("change", function () {
        findVehicles();
    });

});


function findVehicles() {
    $.ajax({
        url: vehiclesApiUrl + preparePaginationUrl(),
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
            "<td class='align-middle'>" + prepareServicesButton(vehicle.id) + "</td>" +
        "</tr>"
    );
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
//todo -> cały flow dodawania i usuawnia pojazdu(konstruktory, repozytoria do innych tabel itp)
function sendCreateRequest() {
    $.ajax({
        url: vehicleApiUrl,
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify({
                brandName: $("#create-brand").val(),
                modelName: $("#create-model").val(),
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
            findWorkshops();
        })
        .fail(function (jqxhr, textStatus, errorThrown) {
            displayErrorInformation(jqxhr.responseText);
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


