var url = "/admin/api/scaffolding"
var workshopServiceTypes = url + "/workshop-service-types?"
$(document).ready(function () {



});



function showDeleteModal() {
    $('#delete-object-modal').modal('show');
}

function sendDeleteRequest(){
    $.ajax({
        url: "/admin/api/scaffolding/vehicle-service-action/" + serviceAction.id,
        type: "DELETE"
    })
        .done(function(response) {
            $('#delete-object-modal').modal('hide');
            window.location.href = '/admin/vehicle-service-actions';
        })
        .fail(function(jqxhr, textStatus, errorThrown){
            displayErrorInformation(jqxhr.responseText);
        });
}

function sendUpdateRequest() {
    $.ajax({
        url: "/admin/api/scaffolding/vehicle-service-action/" + serviceAction.id,
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
            displayErrorInformation(jqxhr.responseText);
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
