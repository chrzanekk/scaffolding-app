$(document).ready(function () {
    fillActionTypes();


});



function showDeleteModal() {
    $('#delete-object-modal').modal('show');
}

function sendDeleteRequest(){
    $.ajax({
        url: "/admin/api/scaffolding/vehicle/" + vehicle.id,
        type: "DELETE"
    })
        .done(function(response) {
            $('#delete-object-modal').modal('hide');
            window.location.href = '/admin/vehicles';
        })
        .fail(function(jqxhr, textStatus, errorThrown){
            displayErrorInformation(jqxhr.responseText);
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
             buildingNo: $("#buildingNo").val(),
             apartmentNo: $("#apartmentNo").val(),
             postalCode: $("#postalCode").val(),
             city: $("#city").val(),
             taxNumber: $("#taxNumber").val(),
             actionTypes: getActionTypes()
        })
    })
        .done(function () {
            $("#operation-successful-modal").modal('show');
        })
        .fail(function (jqxhr, textStatus, errorThrown) {
            displayErrorInformation(jqxhr.responseText);
        })
}

function fillActionTypes() {
    workshop.actionTypes.forEach(function(actionType) {
    $('#'+actionType).attr('checked', true);
    })
}


