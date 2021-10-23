var url = "/admin/api/scaffolding"
var brandsApiUrl = url + "/brands?"
var brandApiUrl =  "/brands/"

$(document).ready(function () {

});



function showDeleteModal() {
    $('#delete-object-modal').modal('show');
}


function sendUpdateRequest() {
    $.ajax({
        url: url + brandApiUrl + brand.id,
        method: "PUT",
        contentType: "application/json",
        data: JSON.stringify({
            brandId: brand.id,
            brandName: $("#brand").val()
        })
    })
        .done(function () {
            $("#operation-successful-modal").modal('show');
        })
        .fail(function (jqxhr, textStatus, errorThrown) {
            displayErrorInformation(jqxhr.responseText);
        })
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
