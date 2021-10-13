var url = "/admin/api/scaffolding"
var brandsAndModelsApiUrl = url + "/brands-and-models?"
var brandAndModelApiUrl = url + "/brand-and-model"

$(document).ready(function () {
    $('#create-modal').on('hide.bs.modal', function (e) {
        clearCreateModal();
    });


    findBrandsAndModels();
    $("#filter input, #filter select, [form='filter']").on("change", function () {
        findBrandsAndModels();
    });

});


function findBrandsAndModels() {
    $.ajax({
        url: brandsAndModelsApiUrl + preparePaginationUrl(),
        type: "get",
        dataType: "json",
        contentType: "application/json"
    })
    .done(function (brandsAndModels) {
        $("#records").empty();
        fillResults(brandsAndModels.brandsAndModels);
    })
    .fail(function(jqxhr, textStatus, errorThrown){
        displayErrorInformation(jqxhr.responseText);
    });
}

function fillResults(brandsAndModels) {
    let value = 1;
    brandsAndModels.forEach(function(brandAndModel){
        fillRow(brandAndModel, value);
        value = value + 1;
    });
}

function fillRow(brandAndModel, value) {
    $('#records').append(
        "<tr>" +
            "<td class='align-middle'>" + value + "</td>" +
            "<td class='align-middle'>" + brandAndModel.brandName + "</td>" +
            "<td class='align-middle'>" + brandAndModel.modelName + " </td>" +
            "<td class='align-middle'>" + prepareDetailsButton(brandAndModel.brandId) + "</td>" +
        "</tr>"
    );
}

function prepareDetailsButton(id) {
    return '<button type="button" class="btn btn-primary" onclick="goToDetailsPage(' + id + ')">Detale</button>';
}

function prepareDeleteButton(id) {
    return '<button type="button" class="btn btn-danger" onclick="setObjectToDeleteIdAndShowModal(' + id + ')">Usuń/Zezłomuj</button>';
}

function goToDetailsPage(id) {
    window.location.href = "/admin/brand-and-model/" + id;
}


function setObjectToDeleteIdAndShowModal(id) {
    objToDeleteId = id;
    $('#delete-object-modal').modal('show');
}

function clearCreateModal() {
    $("#create-brand").val('');
    $("#create-model").val('');
}

function sendCreateRequest() {
    $.ajax({
        url: brandAndModelApiUrl,
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify({
                brandName: $("#create-brand").val(),
                modelName: $("#create-model").val(),
        })
    })
        .done(function () {
            $("#create-modal").modal('hide');
            $("#operation-successful-modal").modal('show');
            findBrandsAndModels();
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


