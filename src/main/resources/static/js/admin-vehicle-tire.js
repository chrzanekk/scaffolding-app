var url = "/admin/api/scaffolding"
var vehicleApiUrl = url + "/vehicles/"
var tireApiUrl = "/tires/"
$(document).ready(function () {

findTire();
});



function showDeleteModal() {
    $('#delete-object-modal').modal('show');
}

function findTire() {

    $.ajax({
        url:  vehicleApiUrl + vehicle.id + tireApiUrl + tire.id,
        type: "GET",
        dataType: "json",
        contentType: "application/json"
    })
    .done(function (tire) {
        $('#first').empty();
        $('#second').empty();
        $('#third').empty();
        $('#fourth').empty();
        $('#fifth').empty();
        fillRow(tire.tire);
    })
    .fail(function(jqxhr, textStatus, errorThrown){
        displayErrorInformation(jqxhr.responseText);
    });
}



function fillRow(tire) {
    $('#first').append(
        "<tr>" +
            "<td class='align-middle col-sm-3' colspan='3'>" + tire.brand + "</td>" +
            "<td class='align-middle col-sm-3' colspan='3'>" + tire.model + " </td>" +
        "</tr>"
    );
    $('#second').append(
        "<tr>" +
            "<td class='align-middle' colspan='6'>" + tire.status +  "</td>" +
        "</tr>"
    );
    $('#third').append(
        "<tr>" +
            "<td class='align-middle col-sm-3' colspan='3'>" + tire.purchaseDate + "</td>" +
            "<td class='align-middle col-sm-3' colspan='3'>" + tire.productionYear + " </td>" +
        "</tr>"
    );
    $('#fourth').append(
        "<tr>" +
            "<td class='align-middle col-sm-6' colspan='6'>" + tire.width + " / " + tire.profile + " / " + tire.type +
            tire.diameter + " " + tire.speedIndex + " " + tire.capacityIndex +  "</td>" +
        "</tr>"
    );
    $('#fifth').append(
        "<tr>" +
            "<td class='align-middle' colspan='2'>" + tire.reinforced + "</td>" +
            "<td class='align-middle' colspan='2'>" + tire.runOnFlat + "</td>" +
            "<td class='align-middle' colspan='2'>" + tire.seasonName + "</td>" +
        "</tr>"
    );
}

function goToEditPage() {
    var id = getUrlId();
    window.location.href = "/admin/vehicles/"+ vehicle.id + "/tire-edit/" + tire.id;
}

function getUrlId(){
    return window.location.pathname.substring(window.location.pathname.lastIndexOf('/') + 1);
}