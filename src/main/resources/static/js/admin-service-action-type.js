
$(document).ready(function () {



});



function showDeleteModal() {
    $('#delete-object-modal').modal('show');
}

function sendDeleteRequest(){
    $.ajax({
        url: "/admin/api/scaffolding/service-action-type-to-remove/" + serviceActionType.id,
        type: "PUT",
         contentType: "application/json",
         data: JSON.stringify({
         name: $("#name").val(),
         removeDate: prepareActualDate()
         })
    })
        .done(function(response) {
            $("#operation-successful-modal").modal('show');
            $('#delete-object-modal').modal('hide');
            backToServiceActionsList();
        })
        .fail(function(jqxhr, textStatus, errorThrown){
            $('#delete-object-modal').modal('hide');
            showError(prepareErrorMessage(jqxhr.responseText));
        });
}

function sendUpdateRequest() {
    $.ajax({
        url: "/admin/api/scaffolding/service-action-type/" + serviceActionType.id,
        method: "PUT",
        contentType: "application/json",
        data: JSON.stringify({
             name: $("#name").val(),
        })
    })
        .done(function () {
            $("#operation-successful-modal").modal('show');
        })
        .fail(function (jqxhr, textStatus, errorThrown) {
            showError(prepareErrorMessage(jqxhr.responseText));
        })
}

function showError(text) {
    $("#error-alert-text").text(text);
    $("#error-alert").removeClass('d-none');
}

function backToServiceActionsList() {
    window.location.href = "/admin/service-action-types/";
}

function prepareActualDate() {

var m = new Date();
var dateString =
    m.getUTCFullYear() + "-" +
    ("0" + (m.getUTCMonth()+1)).slice(-2) + "-" +
    ("0" + m.getUTCDate()).slice(-2) + "T" +
    ("0" + m.getUTCHours()).slice(-2) + ":" +
    ("0" + m.getUTCMinutes()).slice(-2) + ":" +
    ("0" + m.getUTCSeconds()).slice(-2);


    return dateString;
}
