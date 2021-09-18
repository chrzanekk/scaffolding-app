
$(document).ready(function () {



});



function showDeleteModal() {
    $('#delete-object-modal').modal('show');
}

function sendDeleteRequest(){
    $.ajax({
        url: "/admin/api/scaffolding/vehicle/" + vehicle.id,
        type: "DELETE"
    })
        .done(function(response) {
            $('#delete-object-modal').modal('hide');
            window.location.href = '/admin/vehicles';
        })
        .fail(function(jqxhr, textStatus, errorThrown){
            displayErrorInformation(jqxhr.responseText);
        });
}

function sendUpdateRequest() {
    $.ajax({
        url: "/admin/api/scaffolding/vehicle/" + vehicle.id,
        method: "PUT",
        contentType: "application/json",
        data: JSON.stringify({
             brandName: $("#brand").val(),
             modelName: $("#model").val(),
             registrationNumber: $("#registrationNumber").val(),
             vin: $("#vin").val(),
             productionYear: $("#productionYear").val(),
             firstRegistrationDate: $("#firstRegistrationDate").val(),
             freePlacesForTechnicalInspections: $("#freePlacesForTechnicalInspections").val(),
             fuelTypeId: $("#fuelType").val(),
             vehicleTypeId: $("#vehicleType").val()
        })
    })
        .done(function () {
            $("#operation-successful-modal").modal('show');
        })
        .fail(function (jqxhr, textStatus, errorThrown) {
            displayErrorInformation(jqxhr.responseText);
        })
}
