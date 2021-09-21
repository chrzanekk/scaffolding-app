var url = "/admin/api/scaffolding"
var serviceActionsApiUrl = url + "/vehicle-service-actions?"
var vehicleServiceActionsApiUrl = url + "/vehicle-service-actions/"
var vehicleActionApiUrl = url + "/vehicle-service-action"

$(document).ready(function () {
    $('#create-modal').on('hide.bs.modal', function (e) {
        clearCreateModal();
    });


    findServiceActions();
    $("#filter input, #filter select, [form='filter']").on("change", function () {
        findServiceActions();
    });

});


function findServiceActions() {
    $.ajax({
        url: vehicleServiceActionsApiUrl + vehicle.id + "/?" + preparePaginationUrl(),
        type: "get",
        dataType: "json",
        contentType: "application/json"
    })
    .done(function (serviceActions) {
        $("#records").empty();
        fillResults(serviceActions.actions);
    })
    .fail(function(jqxhr, textStatus, errorThrown){
        displayErrorInformation(jqxhr.responseText);
    });
}

function fillResults(actions) {
    let value = 1;
    serviceActions.forEach(function(action){
        fillRow(action, value);
        value = value + 1;
    });
}

function fillRow(action, value) {
    $('#records').append(
        "<tr>" +
            "<td class='align-middle'>" + value + "</td>" +
            "<td class='align-middle'>" + action.serviceDate + "</td>" +
            "<td class='align-middle'>" + action.serviceActionName + " </td>" +
            "<td class='align-middle'>" + action.serviceWorkshop + "</td>" +
            "<td class='align-middle'>" + prepareDetailsButton(action.id) + "</td>" +
        "</tr>"
    );
}

function prepareDetailsButton(id) {
    return '<button type="button" class="btn btn-primary" onclick="goToDetailsPage(' + id + ')">Detale</button>';
}


function prepareDeleteButton(id) {
    return '<button type="button" class="btn btn-danger" onclick="setObjectToDeleteIdAndShowModal(' + id + ')">Usuń/Zezłomuj</button>';
}

function goToDetailsPage(id) {
    window.location.href = "/admin/vehicle-service-action/" + id;
}


function setObjectToDeleteIdAndShowModal(id) {
    objToDeleteId = id;
    $('#delete-object-modal').modal('show');
}

function clearCreateModal() {
    $("#create-invoiceNumber").val('');
    $("#create-serviceDate").val('');
    $("#create-serviceWorkshop").val('');
    $("#create-carMileage").val('');
    $("#create-serviceActionName").val('');
    $("#create-serviceActionDescription").val('');

}

function sendCreateRequest() {
    $.ajax({
        url: vehicleActionApiUrl,
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify({
                vehicleId: vehicle.id,
                carMileage: $("#create-carMileage").val(),
                serviceDate: $("#create-serviceDate").val(),
                invoiceNumber: $("#create-invoiceNumber").val(),
                serviceWorkshop: $("#create-serviceWorkshop").val(),
                serviceActionName: $("#create-serviceActionName").val(),
                serviceActionDescription: $("#create-serviceActionDescription").val()
        })
    })
        .done(function () {
            $("#create-modal").modal('hide');
            $("#operation-successful-modal").modal('show');
            findServiceActions();
        })
        .fail(function (jqxhr, textStatus, errorThrown) {
            displayErrorInformation(jqxhr.responseText);
        })
}

//todo to na koniec, jak już będzie działało dodawanie i edycja
//function sendDeleteRequest(){
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
//}


