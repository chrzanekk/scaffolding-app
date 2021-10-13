var url = "/admin/api/scaffolding"
var workshopsApiUrl = url + "/workshops?"
var workshopApiUrl = url + "/workshop"

$(document).ready(function () {
    $('#create-modal').on('hide.bs.modal', function (e) {
        clearCreateModal();
    });


    findWorkshops();
    $("#filter input, #filter select, [form='filter']").on("change", function () {
        findWorkshops();
    });

});


function findWorkshops() {
    $.ajax({
        url: workshopsApiUrl + preparePaginationUrl(),
        type: "get",
        dataType: "json",
        contentType: "application/json"
    })
    .done(function (workshops) {
        $("#records").empty();
        fillResults(workshops.workshops);
    })
    .fail(function(jqxhr, textStatus, errorThrown){
        displayErrorInformation(jqxhr.responseText);
    });
}

function fillResults(workshops) {
    let value = 1;
    workshops.forEach(function (workshop){
        fillRow(workshop, value);
        value = value + 1;
    });
}

function fillRow(workshop, value) {
    $('#records').append(
        "<tr>" +
            "<td class='align-middle'>" + value + "</td>" +
            "<td class='align-middle'>" + workshop.name + "</td>" +
            "<td class='align-middle'>" + workshop.street + " "  + workshop.buildingNo + showApartmentNo(workshop.apartmentNo) + "</td>" +
            "<td class='align-middle'>" + workshop.postalCode +"</td>" +
            "<td class='align-middle'>" + workshop.city +"</td>" +
            "<td class='align-middle'>" + workshop.taxNumber +"</td>" +
            "<td class='align-middle'>" + prepareDetailsButton(workshop.id) + "</td>" +
        "</tr>"
    );
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

function prepareDetailsButton(id) {
    return '<button type="button" class="btn btn-primary" onclick="goToDetailsPage(' + id + ')">Detale</button>';
}

function prepareDeleteButton(id) {
    return '<button type="button" class="btn btn-danger" onclick="setObjectToDeleteIdAndShowModal(' + id + ')">Usuń/Zezłomuj</button>';
}

function goToDetailsPage(id) {
    window.location.href = "/admin/workshop/" + id;
}

function setObjectToDeleteIdAndShowModal(id) {
    objToDeleteId = id;
    $('#delete-object-modal').modal('show');
}

function clearCreateModal() {
    $("#create-name").val('');
    $("#create-street").val('');
    $("#create-buildingNo").val('');
    $("#create-apartmentNo").val('');
    $("#create-postalCode").val('');
    $("#create-city").val('');
    $("#create-taxNumber").val('');

}

function sendCreateRequest() {
    $.ajax({
        url: workshopApiUrl,
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify({
                name: $("#create-name").val(),
                street: $("#create-street").val(),
                buildingNo: $("#create-buildingNo").val(),
                apartmentNo: $("#create-apartmentNo").val(),
                postalCode: $("#create-postalCode").val(),
                city: $("#create-city").val(),
                taxNumber: $("#create-taxNumber").val()
        })
    })
        .done(function () {
            $("#create-modal").modal('hide');
            $("#operation-successful-modal").modal('show');
            findWorkshops();
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


