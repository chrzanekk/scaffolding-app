
$(document).ready(function () {

fillRow(serviceAction);
});



function showDeleteModal() {
    $('#delete-object-modal').modal('show');
}

function fillRow(serviceAction) {
var workshop = serviceAction.workshopsData;
    $('#first').append(
        "<tr>" +
            "<td class='align-middle'>" + serviceAction.invoiceNumber + "</td>" +
            "<td class='align-middle' colspan='2'>" + serviceAction.invoiceGrossValue + " PLN</td>" +
            "<td class='align-middle'>" + serviceAction.serviceDate + " </td>" +
        "</tr>"
    );
    $('#second').append(
        "<tr>" +
            "<td class='align-middle' colspan='6'>" + workshop.name + "<br>" +
             workshop.street + " "  + workshop.buildingNo + showApartmentNo(workshop.apartmentNo) + "<br>" +
             workshop.postalCode + " " + workshop.city + "<br>"
            + workshop.taxNumber + "</td>" +
        "</tr>"
    );
    $('#third').append(
        "<tr>" +
            "<td class='align-middle' colspan='2'>" + serviceAction.carMileage + "</td>" +
            "<td class='align-middle' colspan='2'>" + serviceAction.serviceActionTypeName + " </td>" +
        "</tr>"
    );
    $('#fourth').append(
        "<tr>" +
            "<td class='align-middle' colspan='6'>" + serviceAction.serviceActionDescription + "</td>" +
        "</tr>"
    );
}

function goToEditPage() {
    window.location.href = "/admin/vehicle-service-action-edit/" + serviceAction.id;
}

function showApartmentNo(apartmentNo) {
    var value = "";
    if (apartmentNo == "") {
        value = "";
    } else {
        value = "/" + apartmentNo;
    }
    return value;
}
