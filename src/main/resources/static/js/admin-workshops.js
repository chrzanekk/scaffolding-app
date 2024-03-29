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
        url: workshopsApiUrl + prepareUrl(),
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
    $("#create-building-no").val('');
    $("#create-apartment-no").val('');
    $("#create-postal-code").val('');
    $("#create-city").val('');
    $("#create-tax-number").val('');
    $("#service-action-types").prop('checked', false);
}

function getActionTypes(){
        var actionTypesList = [];
          $.each($("input[name='actionTypes']:checked"), function(){
            actionTypesList.push($(this).val());
            });
        return actionTypesList;
    }

function sendCreateRequest() {
    $.ajax({
        url: workshopApiUrl,
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify({
                name: $("#create-name").val(),
                street: $("#create-street").val(),
                buildingNo: $("#create-building-no").val(),
                apartmentNo: $("#create-apartment-no").val(),
                postalCode: $("#create-postal-code").val(),
                city: $("#create-city").val(),
                taxNumber: $("#create-tax-number").val(),
                actionTypes : getActionTypes()
        })
    })
        .done(function () {
            $("#create-modal").modal('hide');
            $("#operation-successful-modal").modal('show');
            findWorkshops();
        })
        .fail(function (jqxhr, textStatus, errorThrown) {
            showError(prepareErrorMessage(jqxhr.responseText));
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


function prepareUrl(){
     var url = "";
     url += preparePaginationUrl();

     var name = $("#name-filter").find(":selected").val();
     var city = $("#city-filter").children(":selected").val();


     if (name != "") {
        url += "&name=" + name;
     }
     if (city != "") {
        url += "&city=" + city;
     }

     return url;
}

function showError(text) {
    $("#error-alert-text").text(text);
    $("#error-alert").removeClass('d-none');
}
