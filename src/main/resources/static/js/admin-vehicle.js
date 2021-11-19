var url = "/admin/api/scaffolding"
var vehicleApiUrl = url + "/vehicle/"
var vehicleTiresApiUrl = url +"/vehicle-tires-check/"


$(document).ready(function () {

findVehicle();
checkTires();
});



function showDeleteModal() {
    $('#delete-object-modal').modal('show');
}

function findVehicle() {
    var id = getUrlId();
    $.ajax({
        url: vehicleApiUrl + id,
        type: "GET",
        dataType: "json",
        contentType: "application/json"
    })
    .done(function (vehicle) {
        $('#first').empty();
        $('#second').empty();
        $('#third').empty();
        $('#fourth').empty();
        fillRow(vehicle.vehicle);
    })
    .fail(function(jqxhr, textStatus, errorThrown){
        displayErrorInformation(jqxhr.responseText);
    });
}


function fillRow(vehicle) {
    $('#first').append(
        "<tr>" +
            "<td class='align-middle'>" + vehicle.brandName + "</td>" +
            "<td class='align-middle'>" + vehicle.modelName + " </td>" +
            "<td class='align-middle'>" + vehicle.registrationNumber + "</td>" +
        "</tr>"
    );
    $('#second').append(
        "<tr>" +
            "<td class='align-middle'>" + vehicle.vin + "</td>" +
            "<td class='align-middle'>" + vehicle.productionYear + " </td>" +
            "<td class='align-middle'>" + vehicle.firstRegistrationDate + "</td>" +
        "</tr>"
    );
    $('#third').append(
        "<tr>" +
            "<td class='align-middle'>" + vehicle.freePlacesForTechnicalInspections + "</td>" +
            "<td class='align-middle'>" + vehicle.fuelType + " </td>" +
            "<td class='align-middle'>" + vehicle.vehicleType + "</td>" +
        "</tr>"
    );
    $('#fourth').append(
        "<tr>" +
            "<td class='align-middle'>" + vehicle.length + "</td>" +
            "<td class='align-middle'>" + vehicle.width + " </td>" +
            "<td class='align-middle'>" + vehicle.height + "</td>" +
        "</tr>"
    );
}

function goToEditPage() {
    var id = getUrlId();
    window.location.href = "/admin/vehicle-edit/" + id;
}

function checkTires() {
    var id = getUrlId();
    $.ajax({
        url: vehicleTiresApiUrl + id,
        type: "get",
        dataType: "json",
        contentType: "application/json"
    })
    .done(function (vehicle) {
        $("#error-alert").hide();
    })
    .fail(function(jqxhr, textStatus, errorThrown){
        showError(prepareErrorMessage(jqxhr.responseText));
    });
}


function getUrlId(){
    return window.location.pathname.substring(window.location.pathname.lastIndexOf('/') + 1);
}

function showError(text) {
    $("#error-alert-text").text(text);
    $("#error-alert").removeClass('d-none');
}