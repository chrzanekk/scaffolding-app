var url = "/admin/api/scaffolding"
var brandsApiUrl = url + "/brands/"
var modelsApiUrl = "/models?"
var brandApiUrl = url + "/brands/"
var modelApiUrl = "/models"

$(document).ready(function () {
    $('#create-modal').on('hide.bs.modal', function (e) {
        clearCreateModal();
    });


    findModels();

    $("#filter input, #filter select, [form='filter']").on("change", function () {
        findModels();
    });

});


function findModels() {
    $.ajax({
        url: brandsApiUrl + brand.id + modelsApiUrl + preparePaginationUrl(),
        type: "get",
        dataType: "json",
        contentType: "application/json"
    })
    .done(function (models) {
        $("#records").empty();
        fillResults(models.models);
    })
    .fail(function(jqxhr, textStatus, errorThrown){
        displayErrorInformation(jqxhr.responseText);
    });
}

function fillResults(models) {
    let value = 1;
    models.forEach(function(model){
        fillRow(model, value);
        value = value + 1;
    });
}

function fillRow(model, value) {
    $('#records').append(
        "<tr>" +
            "<td class='align-middle'>" + value + "</td>" +
            "<td class='align-middle'>" + model.name + "</td>" +
            "<td class='align-middle'>" + prepareDetailsButton(brand.id, model.id) + "</td>" +
        "</tr>"
    );
}

function prepareDetailsButton(brandId, modelId) {
    return '<button type="button" class="btn btn-primary" onclick="goToDetailsPage(' + brandId + ',' + modelId + ')">Detale</button>';
}


function prepareDeleteButton(id) {
    return '<button type="button" class="btn btn-danger" onclick="setObjectToDeleteIdAndShowModal(' + id + ')">Usuń/Zezłomuj</button>';
}

function goToDetailsPage(brandId, modelId) {
    window.location.href = "/admin/brands/" + brandId + "/models/" + modelId;
}



function setObjectToDeleteIdAndShowModal(id) {
    objToDeleteId = id;
    $('#delete-object-modal').modal('show');
}

function clearCreateModal() {
    $("#create-brand").val('');
}

function sendCreateRequest() {
    $.ajax({
        url: brandApiUrl + brand.id + modelApiUrl,
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify({
                modelName: $("#create-model").val(),
                brandId: brand.id
        })
    })
        .done(function () {
            $("#create-modal").modal('hide');
            $("#operation-successful-modal").modal('show');
            findModels();
        })
        .fail(function (jqxhr, textStatus, errorThrown) {
            displayErrorInformation(jqxhr.responseText);
            $("#brands.already.exist").modal('show');
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


