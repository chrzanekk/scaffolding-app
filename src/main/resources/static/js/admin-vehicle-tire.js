var url = "/admin/api/scaffolding"
var tireApiUrl = url + "/tires"
$(document).ready(function () {

fillRow(tire);
});



function showDeleteModal() {
    $('#delete-object-modal').modal('show');
}

function findTire() {
    $.ajax({
        url:  + tire.id,
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
            "<td class='align-middle col-sm-6' colspan='6'>" + tire.width + " / " + tire.profile + " / " + tire.diameter
             + " " + tire.speedIndex + " " + tire.capacityIndex +  "</td>" +
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
    window.location.href = "/admin/tire-edit/" + tire.id;
}

