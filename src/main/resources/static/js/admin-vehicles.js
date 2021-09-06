var url = "/admin/api/scaffolding"
var vehiclesApiUrl = url + "/vehicles?"

$(document).ready(function () {

    findVehicles();
    $("#filter input, #filter select, [form='filter']").on("change", function () {
        findVehicles();
    });

});


function findVehicles() {
    $.ajax({
        url: vehiclesApiUrl + preparePaginationUrl(),
        type: "get",
        dataType: "json",
        contentType: "application/json"
    })
    .done(function (vehicles) {
        $("#records").empty();
        fillResults(vehicles.vehicles);
    })
    .fail(function(jqxhr, textStatus, errorThrown){
        displayErrorInformation(jqxhr.responseText);
    });
}

function fillResults(vehicles) {
    vehicles.forEach(function(vehicle){
        fillRow(vehicle);
    });
}

function fillRow(vehicle, value) {
    $('#records').append(
        "<tr>" +
            "<td class='align-middle'>" + value + "</td>" +
            "<td class='align-middle'>" + vehicle.brand.name + "</td>" +
            "<td class='align-middle'>" + vehicle.model.name + " </td>" +
            "<td class='align-middle'>" + vehicle.registrationNumber + "</td>" +
        "</tr>"
    );
}



