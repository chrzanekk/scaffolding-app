var url = "/admin/api/scaffolding"
var brandsApiUrl = url + "/brands?"
var brandApiUrl = url + "/brands"

$(document).ready(function () {
    $('#create-modal').on('hide.bs.modal', function (e) {
        clearCreateModal();
    });


    findBrands();

    $("#filter input, #filter select, [form='filter']").on("change", function () {
        findBrands();
    });

});


function findBrands() {
    $.ajax({
        url: brandsApiUrl + preparePaginationUrl(),
        type: "get",
        dataType: "json",
        contentType: "application/json"
    })
    .done(function (brands) {
        $("#records").empty();
        fillResults(brands.brands);
    })
    .fail(function(jqxhr, textStatus, errorThrown){
        displayErrorInformation(jqxhr.responseText);
    });
}

function fillResults(brands) {
    let value = 1;
    brands.forEach(function(brand){
        fillRow(brand, value);
        value = value + 1;
    });
}

function fillRow(brand, value) {
    $('#records').append(
        "<tr>" +
            "<td class='align-middle'>" + value + "</td>" +
            "<td class='align-middle'>" + brand.name + "</td>" +
            "<td class='align-middle'>" + prepareDetailsButton(brand.id) + "</td>" +
            "<td class='align-middle'>" + prepareModelsListButton(brand.id) + "</td>" +
        "</tr>"
    );
}

function prepareDetailsButton(id) {
    return '<button type="button" class="btn btn-primary" onclick="goToDetailsPage(' + id + ')">Detale</button>';
}

function prepareModelsListButton(id) {
    return '<button type="button" class="btn btn-primary" onclick="goToModelListPage(' + id + ')">Lista modeli</button>';
}

function prepareDeleteButton(id) {
    return '<button type="button" class="btn btn-danger" onclick="setObjectToDeleteIdAndShowModal(' + id + ')">Usuń/Zezłomuj</button>';
}

function goToDetailsPage(id) {
    window.location.href = "/admin/brands/" + id;
}
function goToModelListPage(id) {
    window.location.href = "/admin/brands/" + id + "/models";
}

function getModelsByBrandId(value) {
    var id = value.value;
    var models = "";
    $.ajax({
        url: modelsByBrandIdApiUri + "/" + id,
        type: "get",
        dataType: "json",
        contentType: "application/json"
    })
    .done(function (models) {
       alert(JSON.stringify(models));
       models = JSON.stringify(models);
    })
    .fail(function(jqxhr, textStatus, errorThrown){
        displayErrorInformation(jqxhr.responseText);
    });
    return models;
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
        url: brandApiUrl,
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify({
                brandName: $("#create-brand").val(),
        })
    })
        .done(function () {
            $("#create-modal").modal('hide');
            $("#operation-successful-modal").modal('show');
            findBrandsAndModels();
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


