
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
        url: "/admin/api/scaffolding/tires/" + tire.id,
        method: "PUT",
        contentType: "application/json",
        data: JSON.stringify({
            vehicleId : tire.vehicleId,
            tireId : tire.tireId,
            brand: $("#tireBrand").val(),
            model: $("#tireModel").val(),
            productionYear: $("#productionYear").val(),
            purchaseDate: $("#purchaseDate").val(),
            width: $("#width").val(),
            profile: $("#profile").val(),
            diameter: $("#diameter").val(),
            speedIndex: $("#speedIndex").val(),
            capacityIndex: $("#loadIndex").val(),
            reinforced: $("#reinforced").val(),
            runOnFlat: $("#runOnFlat").val(),
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
