var url = "/admin/api/scaffolding"
var brandsApiUrl = url + "/brands/"
var modelsApiUrl = "/models?"
var brandApiUrl = url + "/brands/"
var modelApiUrl = "/models/"

$(document).ready(function () {

});



function showDeleteModal() {
    $('#delete-object-modal').modal('show');
}

function showError(text) {
    $("#error-alert-text").text(text);
    $("#error-alert").removeClass('d-none');
}
//function sendDeleteRequest(){
//    $.ajax({
//        url: "/admin/api/scaffolding/vehicle/" + vehicle.id,
//        type: "DELETE"
//    })
//        .done(function(response) {
//            $('#delete-object-modal').modal('hide');
//            window.location.href = '/admin/vehicles';
//        })
//        .fail(function(jqxhr, textStatus, errorThrown){
//            displayErrorInformation(jqxhr.responseText);
//        });
//}

function sendUpdateRequest() {
    $.ajax({
        url: brandApiUrl + brand.id + modelApiUrl + model.id,
        method: "PUT",
        contentType: "application/json",
        data: JSON.stringify({
            modelName: $("#model").val(),
            brandId: $("#brand").val(),
        })
    })
        .done(function () {
            $("#operation-successful-modal").modal('show');
        })
        .fail(function (jqxhr, textStatus, errorThrown) {
            showError(prepareErrorMessage(jqxhr.responseText));
        })
}
