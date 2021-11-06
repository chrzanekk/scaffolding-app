
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
             brandId: $("#brand").find(":selected").val(),
             modelId: $("#model").find(":selected").val(),
             brandName: $("#brand").val(),
             modelName: $("#model").val(),
             registrationNumber: $("#registration-number").val(),
             vin: $("#vin").val(),
             productionYear: $("#production-year").val(),
             firstRegistrationDate: $("#first-registration-date").val(),
             freePlacesForTechnicalInspections: $("#free-places-for-technical-inspections").val(),
             fuelTypeId: $("#fuel-type").val(),
             vehicleTypeId: $("#vehicle-type").val(),
             length: $("#length").val(),
             width: $("#width").val(),
             height: $("#height").val()
        })
    })
        .done(function () {
            $("#operation-successful-modal").modal('show');
        })
        .fail(function (jqxhr, textStatus, errorThrown) {
            displayErrorInformation(jqxhr.responseText);
        })
}
