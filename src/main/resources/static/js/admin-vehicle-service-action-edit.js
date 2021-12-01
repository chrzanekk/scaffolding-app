var url = "/admin/api/scaffolding"
var vehicleServiceActionsApiUrl = url + "/vehicle-service-action/"
var vehicleServiceActionsToRemoveApiUrl = url + "/vehicle-service-action-to-remove/"
var workshopServiceTypes = url + "/workshop-service-types?"
$(document).ready(function () {



});



function showDeleteModal() {
    $('#delete-object-modal').modal('show');
}

function sendUpdateRequest() {
    $.ajax({
        url: vehicleServiceActionsApiUrl + serviceAction.id,
        method: "PUT",
        contentType: "application/json",
        data: JSON.stringify({
             id: serviceAction.id,
             vehicleId: serviceAction.vehicleId,
             carMileage: $("#car-mileage").val(),
             serviceDate: $("#service-date").val(),
             invoiceNumber: $("#invoice-number").val(),
             invoiceNetValue: $("#invoice-net-value").val(),
             taxRate: $("#invoice-tax-rate").val(),
             workshopId: $("#service-workshop").val(),
             serviceActionTypeId: $("#service-action-type").val(),
             serviceActionDescription: $("#service-action-description").val()
        })
    })
        .done(function () {
            $("#operation-successful-modal").modal('show');
        })
        .fail(function (jqxhr, textStatus, errorThrown) {
            showError(prepareErrorMessage(jqxhr.responseText));
        })
}

function reloadWorkshopServices() {
    $.ajax({
        url: workshopServiceTypes + "workshop_id=" + $("#service-workshop").val(),
        type: "GET",
        dataType: "json",
        contentType: "application/json"
    })
    .done(function (workshopActions) {
        $('#service-action-type').empty();
        fillResultsReloadedWorkshopServices(workshopActions.workshopActions);

    })
        .fail(function(jqxhr, textStatus, errorThrown){
        displayErrorInformation(jqxhr.responseText);
    });
}

function fillResultsReloadedWorkshopServices(workshopActions) {
    workshopActions.forEach(function(action){
            fillReloadedWorkshopService(action);
        });
}

function fillReloadedWorkshopService(action) {
    $('#service-action-type').append('<option value=' + action.serviceActionId + '>' + action.serviceActionName +
    '</option>');

}

function showError(text) {
    $("#error-alert-text").text(text);
    $("#error-alert").removeClass('d-none');
}

function prepareDeleteButton(id) {
    return '<button type="button" class="btn btn-danger" onclick="setObjectToDeleteIdAndShowModal(' + id + ')">Usuń/Zezłomuj</button>';
}

function setObjectToDeleteIdAndShowModal(id) {
    objToDeleteId = id;
    $('#delete-object-modal').modal('show');
}

function sendDeleteRequest(){
        $.ajax({
            url: vehicleServiceActionsToRemoveApiUrl + serviceAction.id,
            method: "PUT",
            contentType: "application/json",
            data: JSON.stringify({
                 id: serviceAction.id,
                 vehicleId: serviceAction.vehicleId,
                 carMileage: $("#car-mileage").val(),
                 serviceDate: $("#service-date").val(),
                 invoiceNumber: $("#invoice-number").val(),
                 invoiceNetValue: $("#invoice-net-value").val(),
                 taxRate: $("#invoice-tax-rate").val(),
                 workshopId: $("#service-workshop").val(),
                 serviceActionTypeId: $("#service-action-type").val(),
                 serviceActionDescription: $("#service-action-description").val(),
                 removeDate: prepareActualDate()
            })
        })
            .done(function () {
                $("#operation-successful-modal").modal('show');
            })
            .fail(function (jqxhr, textStatus, errorThrown) {
                showError(prepareErrorMessage(jqxhr.responseText));
            })
    }


function prepareActualDate() {

var m = new Date();
var dateString =
    m.getUTCFullYear() + "-" +
    ("0" + (m.getUTCMonth()+1)).slice(-2) + "-" +
    ("0" + m.getUTCDate()).slice(-2) + "T" +
    ("0" + m.getUTCHours()).slice(-2) + ":" +
    ("0" + m.getUTCMinutes()).slice(-2) + ":" +
    ("0" + m.getUTCSeconds()).slice(-2);


    return dateString;
}

