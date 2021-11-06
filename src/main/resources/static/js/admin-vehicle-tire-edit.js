var url = "/admin/api/scaffolding"
var vehicleTiresApiUrl = url + "/vehicles/"
var tiresApiUrl = "/tires/"
$(document).ready(function () {

});



function showDeleteModal() {
    $('#delete-object-modal').modal('show');
}

function sendDeleteRequest(){
    $.ajax({
        url: "/admin/api/scaffolding/tires/" + vehicle.id,
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
        url: vehicleTiresApiUrl + tire.vehicleId + tiresApiUrl + tire.id,
        method: "PUT",
        contentType: "application/json",
        data: JSON.stringify({
            vehicleId : tire.vehicleId,
            tireId : tire.tireId,
            brand: $("#tire-brand").val(),
            model: $("#tire-model").val(),
            productionYear: $("#production-year").val(),
            purchaseDate: $("#purchase-date").val(),
            width: $("#width").val(),
            profile: $("#profile").val(),
            diameter: $("#diameter").val(),
            speedIndex: $("#speed-index").val(),
            capacityIndex: $("#load-index").val(),
            reinforced: $("#reinforced").val(),
            runOnFlat: $("#run-on-flat").val(),
            seasonId: $("#season").val(),
            status: $("#status").val()
        })
    })
        .done(function () {
            $("#operation-successful-modal").modal('show');
        })
        .fail(function (jqxhr, textStatus, errorThrown) {
            displayErrorInformation(jqxhr.responseText);
        })
}
