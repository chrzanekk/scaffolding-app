
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
             carMileage: $("#carMileage").val(),
             serviceDate: $("#serviceDate").val(),
             invoiceNumber: $("#invoiceNumber").val(),
             workshopId: $("#serviceWorkshop").val(),
             serviceActionTypeId: $("#serviceActionName").val(),
             serviceActionDescription: $("#serviceActionDescription").val()
        })
    })
        .done(function () {
            $("#operation-successful-modal").modal('show');
        })
        .fail(function (jqxhr, textStatus, errorThrown) {
            displayErrorInformation(jqxhr.responseText);
        })
}
