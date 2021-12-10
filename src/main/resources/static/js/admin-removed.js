var url = "/admin/api/scaffolding"
var workshopsApiUrl = url + "/removed-workshops?"
var vehicleServiceActionsApiUrl = url + "/removed-vehicle-service-actions/"
var serviceActionTypesApiUrl = url + "/removed-service-action-types?"

$(document).ready(function () {

    findRemovedWorkshops()
    findRemovedServiceActions()
    findServiceActionTypes();
    $("#filter input, #filter select, [form='workshops-filter']").on("change", function () {
        findRemovedWorkshops()
    });
    $("#filter input, #filter select, [form='service-actions-filter']").on("change", function () {
        findRemovedServiceActions()
    });
    $("#filter input, #filter select, [form='service-action-types-filter']").on("change", function () {
        findServiceActionTypes();
    });

});


function findRemovedWorkshops() {
    $.ajax({
        url: workshopsApiUrl + prepareWorkshopUrl(),
        type: "get",
        dataType: "json",
        contentType: "application/json"
    })
    .done(function (workshops) {
        if(workshops.workshops.length==0) {
            $('#removed-workshop-container').hide();
        }
        else{
            $('#removed-workshops').empty();
            fillWorkshopsResults(workshops.workshops);
        }
    })
    .fail(function(jqxhr, textStatus, errorThrown){
        displayErrorInformation(jqxhr.responseText);
    });
}

function fillWorkshopsResults(workshops) {
    let value = 1;
    workshops.forEach(function (workshop){
        fillWorkshopsRow(workshop, value);
        value = value + 1;
    });
}

function fillWorkshopsRow(workshop, value) {
    $('#removed-workshops').append(
        "<tr>" +
            "<td class='align-middle'>" + value + "</td>" +
            "<td class='align-middle'>" + workshop.name + "</td>" +
            "<td class='align-middle'>" + workshop.street + " "  + workshop.buildingNo + showApartmentNo(workshop.apartmentNo) + "</td>" +
            "<td class='align-middle'>" + workshop.postalCode +"</td>" +
            "<td class='align-middle'>" + workshop.city +"</td>" +
            "<td class='align-middle'>" + workshop.removeDate +"</td>" +
            "<td class='align-middle'>" + prepareWorkshopDetailsButton(workshop.id) + "</td>" +
        "</tr>"
    );
}

function showApartmentNo(apartmentNo) {
    var value = "";
    if (apartmentNo == "") {
        value = "";
    } else {
        value = "/" + apartmentNo;
    }
    return value;
}

function prepareWorkshopDetailsButton(id) {
    return '<button type="button" class="btn btn-primary" onclick="goToWorkshopDetailsPage(' + id + ')">Sprawdź i przywróć</button>';
}

function goToWorkshopDetailsPage(id) {
    window.location.href = "/admin/removed-workshop/" + id;
}


function getActionTypes(){
        var actionTypesList = [];
          $.each($("input[name='actionTypes']:checked"), function(){
            actionTypesList.push($(this).val());
            });
        return actionTypesList;
}

function prepareWorkshopUrl(){
     var url = "";
     url += preparePaginationUrl();

     var name = $("#name-filter").find(":selected").val();
     var city = $("#city-filter").children(":selected").val();


     if (name != "") {
        url += "&name=" + name;
     }
     if (city != "") {
        url += "&city=" + city;
     }

     return url;
}


function findRemovedServiceActions() {
    $.ajax({
        url: vehicleServiceActionsApiUrl + "?" + prepareServiceActionUrl(),
        type: "GET",
        dataType: "json",
        contentType: "application/json"
    })
    .done(function (actions) {
        if(actions.actions.length == 0) {
            $('#removed-service-actions-container').hide();
        }
        else {
            $('#removed-service-actions').empty();
            fillServiceActionsResults(actions.actions);
        }
    })
    .fail(function(jqxhr, textStatus, errorThrown){
        displayErrorInformation(jqxhr.responseText);
    });
}

function fillServiceActionsResults(actions) {
    let value = 1;
    actions.forEach(function(action){
        fillServiceActionsRow(action, value);
        value = value + 1;
    });
}


function fillServiceActionsRow(action, value) {
    $('#removed-service-actions').append(
        "<tr>" +
            "<td class='align-middle'>" + value + "</td>" +
            "<td class='align-middle' type='date'>" + action.serviceDate + "</td>" +
            "<td class='align-middle'>" + action.serviceActionTypeName + " </td>" +
            "<td class='align-middle'>" + action.workshopName + "</td>" +
            "<td class='align-middle'>" + action.invoiceNetValue + " PLN</td>" +
            "<td class='align-middle'>" + action.taxValue + " PLN</td>" +
            "<td class='align-middle'>" + action.invoiceGrossValue + " PLN</td>" +
            "<td class='align-middle'>" + prepareServiceActionDetailsButton(action.id) + "</td>" +
        "</tr>"
    );
}
function prepareServiceActionDetailsButton(id) {
    return '<button type="button" class="btn btn-primary" onclick="goToServiceActionDetailsPage(' + id + ')">Sprawdź i przywróć</button>';
}

function goToServiceActionDetailsPage(id) {
    window.location.href = "/admin/removed-vehicle-service-action/" + id;
}

function prepareServiceActionUrl(){
     var url = "";
     url += preparePaginationUrl();

     var workshop = $("#workshop-filter").children(":selected").val();
     var dateFrom = $("#date-from-filter").val();
     var dateTo = $("#date-to-filter").val();

     if (workshop != "") {
        url += "&workshopName=" + workshop;
     }
     if (dateFrom != "") {
        url += "&dateFrom=" + dateFrom;
     }
     if (dateTo != "") {
        url += "&dateTo=" + dateTo;
     }

     return url;
}



function findServiceActionTypes() {
    $.ajax({
        url: serviceActionTypesApiUrl + prepareServiceActionTypesUrl(),
        type: "get",
        dataType: "json",
        contentType: "application/json"
    })
    .done(function (serviceActionTypes) {
        if(serviceActionTypes.serviceActionTypes.length == 0) {
            $('#removed-service-action-types-container').hide();
        }
        else {
            $('#removed-service-action-types').empty();
            fillServiceActionTypesResults(serviceActionTypes.serviceActionTypes);
        }

    })
    .fail(function(jqxhr, textStatus, errorThrown){
        displayErrorInformation(jqxhr.responseText);
    });
}

function fillServiceActionTypesResults(serviceActionTypes) {
    let value = 1;
    serviceActionTypes.forEach(function (serviceActionType){
        fillServiceActionTypesRow(serviceActionType, value);
        value = value + 1;
    });
}

function fillServiceActionTypesRow(serviceActionType, value) {
    $('#removed-service-action-types').append(
        "<tr>" +
            "<td class='align-middle'>" + value + "</td>" +
            "<td class='align-middle'>" + serviceActionType.name + "</td>" +
            "<td class='align-middle'>" + prepareServiceActionTypeDetailsButton(serviceActionType.id) + "</td>" +
        "</tr>"
    );
}

function prepareServiceActionTypeDetailsButton(id) {
    return '<button type="button" class="btn btn-primary" onclick="goToServiceActionTypeDetailsPage(' + id + ')">Sprawdź i przywróć</button>';
}
function goToServiceActionTypeDetailsPage(id) {
    window.location.href = "/admin/removed-service-action-type/" + id;
}

function prepareServiceActionTypesUrl(){
     var url = "";
     url += preparePaginationUrl();

     var name = $("#service-action-type-filter").find(":selected").val();

     if (name != "") {
        url += "&name=" + name;
     }

     return url;
}

function showError(text) {
    $("#error-alert-text").text(text);
    $("#error-alert").removeClass('d-none');
}
