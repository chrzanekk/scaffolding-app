
$(document).ready(function () {

fillRow(vehicle);
});



function showDeleteModal() {
    $('#delete-object-modal').modal('show');
}

function fillRow(vehicle) {
    $('#first').append(
        "<tr>" +
            "<td class='align-middle'>" + vehicle.brandName + "</td>" +
            "<td class='align-middle'>" + vehicle.modelName + " </td>" +
            "<td class='align-middle'>" + vehicle.registrationNumber + "</td>" +
        "</tr>"
    );
    $('#second').append(
        "<tr>" +
            "<td class='align-middle'>" + vehicle.vin + "</td>" +
            "<td class='align-middle'>" + vehicle.productionYear + " </td>" +
            "<td class='align-middle'>" + vehicle.firstRegistrationDate + "</td>" +
        "</tr>"
    );
    $('#third').append(
        "<tr>" +
            "<td class='align-middle'>" + vehicle.freePlacesForTechnicalInspections + "</td>" +
            "<td class='align-middle'>" + vehicle.fuelType + " </td>" +
            "<td class='align-middle'>" + vehicle.vehicleType + "</td>" +
        "</tr>"
    );
    $('#fourth').append(
        "<tr>" +
            "<td class='align-middle'>" + vehicle.length + "</td>" +
            "<td class='align-middle'>" + vehicle.width + " </td>" +
            "<td class='align-middle'>" + vehicle.height + "</td>" +
        "</tr>"
    );
}

function goToEditPage() {
    window.location.href = "/admin/vehicle-edit/" + vehicle.id;
}
