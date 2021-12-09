$(document).ready(function () {
    fillActionTypes();


});



function showRestoreModal() {
    $('#restore-object-modal').modal('show');
}
function sendRestoreRequest(id){

    $.ajax({
        url: "/admin/api/scaffolding/restore-workshop/" + workshop.id,
        method: "PUT",
        contentType: "application/json",
        data: JSON.stringify({
                     name: $("#name").val(),
                     street: $("#street").val(),
                     buildingNo: $("#building-no").val(),
                     apartmentNo: $("#apartment-no").val(),
                     postalCode: $("#postal-code").val(),
                     city: $("#city").val(),
                     taxNumber: $("#tax-number").val(),
                     actionTypes: getActionTypes(),
                     modifyDate: prepareActualDate()
                })
    })
        .done(function(response) {
            $('#restore-object-modal').modal('hide');
            $("#operation-successful-modal").modal('show');
            window.location.href = "/admin/removed/";
        })
        .fail(function(jqxhr, textStatus, errorThrown){
            $('#restore-object-modal').modal('hide');
            showError(prepareErrorMessage(jqxhr.responseText));
        });
}

function getActionTypes() {
        var actionTypesList = [];
        for (i = 0; i < actionTypes.length; i++) {
            if($('#'+actionTypes[i].id).is(":checked")){
                actionTypesList.push(actionTypes[i].id);
            }
        }
        return actionTypesList;
}

function fillActionTypes() {
    workshop.actionTypes.forEach(function(actionType) {
    $('#'+actionType).attr('checked', true);
    })
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

