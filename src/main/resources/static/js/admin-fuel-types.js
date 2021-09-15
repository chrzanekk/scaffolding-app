var url = "/admin/api/scaffolding"
var fuelTypesApiUrl = url + "/fuel-types?"

$(document).ready(function () {

    findFuelTypes();
});


function findFuelTypes() {
    $.ajax({
        url: fuelTypesApiUrl + preparePaginationUrl(),
        type: "get",
        dataType: "json",
        contentType: "application/json"
    })
    .done(function (fuelTypes) {
        $("#records").empty();
        return fuelTypes.fuelTypes;
    })
    .fail(function(jqxhr, textStatus, errorThrown){
        displayErrorInformation(jqxhr.responseText);
    });
}

function fillResults(fuelTypes) {
    fuelTypes.forEach(function(fuelTypes){

    });
}





