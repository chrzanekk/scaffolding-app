$(document).ready(function () {
    fillActionTypes();


});



function showDeleteModal() {
    $('#delete-object-modal').modal('show');
}

function sendDeleteRequest(){
    $.ajax({
        url: "/admin/api/scaffolding/workshop-to-remove/" + workshop.id,
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
                     removeDate: prepareActualDate()
                })
    })
        .done(function(response) {
            $('#delete-object-modal').modal('hide');
            $("#operation-successful-modal").modal('show');
            window.location.href = "/admin/workshops/";
        })
        .fail(function(jqxhr, textStatus, errorThrown){
            $('#delete-object-modal').modal('hide');
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

function sendUpdateRequest() {
    $.ajax({
        url: "/admin/api/scaffolding/workshop/" + workshop.id,
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
             actionTypes: getActionTypes()
        })
    })
        .done(function () {
            $("#operation-successful-modal").modal('show');
        })
        .fail(function (jqxhr, textStatus, errorThrown) {
            showError(prepareErrorMessage(jqxhr.responseText));
        })
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

