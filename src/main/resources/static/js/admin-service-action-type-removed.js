$(document).ready(function () {



});

function showRestoreModal() {
    $('#restore-object-modal').modal('show');
}
function sendRestoreRequest(){
    $.ajax({
        url: "/admin/api/scaffolding/service-action-type/" + serviceActionType.id,
        type: "PUT",
         contentType: "application/json",
         data: JSON.stringify({
         name: $("#name").val()
         })
    })
        .done(function(response) {
            $("#operation-successful-modal").modal('show');
            $('#restore-object-modal').modal('hide');
            window.location.href = "/admin/removed/";
        })
        .fail(function(jqxhr, textStatus, errorThrown){
            $('#restore-object-modal').modal('hide');
            showError(prepareErrorMessage(jqxhr.responseText));
        });
}

function showError(text) {
    $("#error-alert-text").text(text);
    $("#error-alert").removeClass('d-none');
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
