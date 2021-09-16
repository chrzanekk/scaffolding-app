
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
             brandName: $("#create-brand").val(),
             modelName: $("#create-model").val(),
             registrationNumber: $("#create-registration-number").val(),
             vin: $("#create-vin").val(),
             productionYear: $("#create-production-year").val(),
             firstRegistrationDate: $("#create-first-registration-date").val(),
             freePlacesForTechnicalInspections: $("#create-free-places-for-technical-inspections").val(),
             fuelTypeId: $("#create-fuel-type").val(),
             vehicleTypeId: $("#create-vehicle-type").val()
        })
    })
        .done(function () {
            $("#operation-successful-modal").modal('show');
        })
        .fail(function (jqxhr, textStatus, errorThrown) {
            displayErrorInformation(jqxhr.responseText);
        })
}
