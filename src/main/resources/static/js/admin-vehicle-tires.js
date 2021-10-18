var url = "/admin/api/scaffolding"
var TiresApiUrl = url + "/tires?"
var vehicleTiresApiUrl = url + "/tires/"
var tiresApiUrl = url + "/tires"

$(document).ready(function () {
    $('#create-modal').on('hide.bs.modal', function (e) {
        clearCreateModal();
    });


    findTires();
    $("#filter input, #filter select, [form='filter']").on("change", function () {
        findTires();
    });

});


function findTires() {
    $.ajax({
        url: vehicleTiresApiUrl + vehicle.id + "?" + preparePaginationUrl(),
        type: "get",
        dataType: "json",
        contentType: "application/json"
    })
    .done(function (tires) {
        $("#records").empty();
        fillResults(tires.tires);
    })
    .fail(function(jqxhr, textStatus, errorThrown){
        displayErrorInformation(jqxhr.responseText);
    });
}

function fillResults(tires) {
    let value = 1;
    tires.forEach(function(tire){
        fillRow(tire, value);
        value = value + 1;
    });
}

function fillRow(tire, value) {
    $('#records').append(
        "<tr>" +
            "<td class='align-middle'>" + value + "</td>" +
            "<td class='align-middle'>" + tire.brand + "</td>" +
            "<td class='align-middle'>" + tire.model + " </td>" +
            "<td class='align-middle'>" + tire.seasonName + " </td>" +
            "<td class='align-middle'>" + prepareDetailsButton(tire.id) + "</td>" +
        "</tr>"
    );
}



function prepareDetailsButton(id) {
    return '<button type="button" class="btn btn-primary" onclick="goToDetailsPage(' + id + ')">Detale</button>';
}



function goToDetailsPage(id) {
    window.location.href = "/admin/tire/" + id;
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
            findTires();
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


