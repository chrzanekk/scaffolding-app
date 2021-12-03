var url = "/admin/api/scaffolding"
var serviceActionTypesApiUrl = url + "/service-action-types?"
var serviceActionTypeApiUrl = url + "/service-action-type"

$(document).ready(function () {
    $('#create-modal').on('hide.bs.modal', function (e) {
        clearCreateModal();
    });


    findServiceActionTypes();
    $("#filter input, #filter select, [form='filter']").on("change", function () {
        findServiceActionTypes();
    });

});


function findServiceActionTypes() {
    $.ajax({
        url: serviceActionTypesApiUrl + preparePaginationUrl(),
        type: "get",
        dataType: "json",
        contentType: "application/json"
    })
    .done(function (serviceActionTypes) {
        $("#records").empty();
        fillResults(serviceActionTypes.serviceActionTypes);
    })
    .fail(function(jqxhr, textStatus, errorThrown){
        displayErrorInformation(jqxhr.responseText);
    });
}

function fillResults(serviceActionTypes) {
    let value = 1;
    serviceActionTypes.forEach(function (serviceActionType){
        fillRow(serviceActionType, value);
        value = value + 1;
    });
}

function fillRow(serviceActionType, value) {
    $('#records').append(
        "<tr>" +
            "<td class='align-middle'>" + value + "</td>" +
            "<td class='align-middle'>" + serviceActionType.name + "</td>" +
            "<td class='align-middle'>" + prepareDetailsButton(serviceActionType.id) + "</td>" +
        "</tr>"
    );
}


function prepareDetailsButton(id) {
    return '<button type="button" class="btn btn-primary" onclick="goToDetailsPage(' + id + ')">Edytuj</button>';
}

function prepareDeleteButton(id) {
    return '<button type="button" class="btn btn-danger" onclick="setObjectToDeleteIdAndShowModal(' + id + ')">Usuń/Zezłomuj</button>';
}

function goToDetailsPage(id) {
    window.location.href = "/admin/service-action-type/" + id;
}

function setObjectToDeleteIdAndShowModal(id) {
    objToDeleteId = id;
    $('#delete-object-modal').modal('show');
}

function clearCreateModal() {
    $("#create-name").val('');
}
//todo -> cały flow dodawania i usuawnia pojazdu(konstruktory, repozytoria do innych tabel itp)
function sendCreateRequest() {
    $.ajax({
        url: serviceActionTypeApiUrl,
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify({
                name: $("#create-name").val()
        })
    })
        .done(function () {
            $("#create-modal").modal('hide');
            $("#operation-successful-modal").modal('show');
            findServiceActionTypes();
        })
        .fail(function (jqxhr, textStatus, errorThrown) {
            showError(prepareErrorMessage(jqxhr.responseText));
        })
}

function showError(text) {
    $("#error-alert-text").text(text);
    $("#error-alert").removeClass('d-none');
}




