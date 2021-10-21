var url = "/admin/api/scaffolding"
var tiresApiUrl = url + "/tires?"
var vehicleTiresApiUrl = url + "/tires/"
var tiresApiUrl = url + "/tires"

$(document).ready(function () {
    $('#create-modal').on('hide.bs.modal', function (e) {
        clearCreateModal();
    });


    findTires();
    $("#filter input, #filter select, [form='filter']").on("change", function () {
        findTires();
    });

});


function findTires() {
    $.ajax({
        url: vehicleTiresApiUrl + vehicle.id + "?" + preparePaginationUrl(),
        type: "get",
        dataType: "json",
        contentType: "application/json"
    })
    .done(function (tires) {
        $("#records").empty();
        fillResults(tires.tires);
    })
    .fail(function(jqxhr, textStatus, errorThrown){
        displayErrorInformation(jqxhr.responseText);
    });
}

function fillResults(tires) {
    let value = 1;
    tires.forEach(function(tire){
        fillRow(tire, value);
        value = value + 1;
    });
}

function fillRow(tire, value) {
    $('#records').append(
        "<tr>" +
            "<td class='align-middle'>" + value + "</td>" +
            "<td class='align-middle'>" + tire.brand + "</td>" +
            "<td class='align-middle'>" + tire.model + " </td>" +
            "<td class='align-middle'>" + tire.seasonName + " </td>" +
            "<td class='align-middle'>" + prepareDetailsButton(tire.id) + "</td>" +
        "</tr>"
    );
}



function prepareDetailsButton(id) {
    return '<button type="button" class="btn btn-primary" onclick="goToDetailsPage(' + id + ')">Detale</button>';
}



function goToDetailsPage(id) {
    window.location.href = "/admin/tire/" + id;
}


function setObjectToDeleteIdAndShowModal(id) {
    objToDeleteId = id;
    $('#delete-object-modal').modal('show');
}

function clearCreateModal() {
    $("#create-brand").val('');
    $("#create-model").val('');
    $("#create-production-year").val('');
    $("#create-purchased-date").val('');
    $("#create-width").val('');
    $("#create-profile").val('');
    $("#create-diameter").val('');
    $("#create-speedIndex").val('');
    $("#create-loadIndex").val('');
    $("#create-reinforced").val('');
    $("#create-runOnFlat").val('');
    $("#create-season").val('');
    $("#create-status").val('');
}

function sendCreateRequest() {
    $.ajax({
        url: tiresApiUrl,
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify({
                vehicleId : vehicle.id,
                brand: $("#create-brand").val(),
                model: $("#create-model").val(),
                productionYear: $("#create-production-year").val(),
                purchaseDate: $("#create-purchase-date").val(),
                width: $("#create-width").val(),
                profile: $("#create-profile").val(),
                diameter: $("#create-diameter").val(),
                speedIndex: $("#create-speedIndex").val(),
                loadIndex: $("#create-loadIndex").val(),
                reinforced: $("#create-reinforced").val(),
                seasonName: $("#create-season").val(),
                status: $("#create-status").val(),
        })
    })
        .done(function () {
            $("#create-modal").modal('hide');
            $("#operation-successful-modal").modal('show');
            findTires();
        })
        .fail(function (jqxhr, textStatus, errorThrown) {
            displayErrorInformation(jqxhr.responseText);
        })
}

//todo to na koniec, jak już będzie działało dodawanie i edycja
function sendDeleteRequest(){
//    $.ajax({
//        url: "/admin/api/crs/author/" + objToDeleteId,
//        type: "DELETE"
//    })
//        .done(function(response) {
//            $('#delete-object-modal').modal('hide');
//            $('#operation-successful-modal').modal('show');
//            findAuthors();
//        })
//        .fail(function(jqxhr, textStatus, errorThrown){
//            displayErrorInformation(jqxhr.responseText);
//        });
}


