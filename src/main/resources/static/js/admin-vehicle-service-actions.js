var url = "/admin/api/scaffolding"
var serviceActionsApiUrl = url + "/vehicle-service-actions?"
var vehicleServiceActionsApiUrl = url + "/vehicle-service-actions/"
var vehicleActionApiUrl = url + "/vehicle-service-action"
var workshopsApiUrl = url + "/workshop/"
var summaryApiUrl = url + "/vehicle-service-action-value-summary/"
var workshopServiceTypes = url + "/workshop-service-types?"


$(document).ready(function () {
    $('#create-modal').on('hide.bs.modal', function (e) {
        clearCreateModal();

    });


    findServiceActions();
    findServiceActionsSummary();
    $("#filter input, #filter select, [form='filter']").on("change", function () {
        findServiceActions();
        findServiceActionsSummary();
    });

});



function findServiceActions() {
    $.ajax({
        url: vehicleServiceActionsApiUrl + vehicle.id + "?" + prepareUrl(),
        type: "GET",
        dataType: "json",
        contentType: "application/json"
    })
    .done(function (actions) {
        $("#records").empty();
        fillResults(actions.actions);
    })
    .fail(function(jqxhr, textStatus, errorThrown){
        displayErrorInformation(jqxhr.responseText);
    });
}

function fillResults(actions) {
    let value = 1;
    actions.forEach(function(action){
        fillRow(action, value);
        value = value + 1;
    });
}


function fillRow(action, value) {
    $('#records').append(
        "<tr>" +
            "<td class='align-middle'>" + value + "</td>" +
            "<td class='align-middle' type='date'>" + action.serviceDate + "</td>" +
            "<td class='align-middle'>" + action.serviceActionTypeName + " </td>" +
            "<td class='align-middle'>" + action.workshopName + "</td>" +
            "<td class='align-middle'>" + action.invoiceNetValue + " PLN</td>" +
            "<td class='align-middle'>" + action.taxValue + " PLN</td>" +
            "<td class='align-middle'>" + action.invoiceGrossValue + " PLN</td>" +
            "<td class='align-middle'>" + prepareDetailsButton(action.id) + "</td>" +
        "</tr>"
    );
}

function findServiceActionsSummary() {
    $.ajax({
        url: summaryApiUrl + vehicle.id + "?" + prepareUrl(),
        type: "GET",
        dataType: "json",
        contentType: "application/json"
    })
    .done(function (summary) {
        $("#summary").empty();
        fillResultsSummary(summary);
    })
    .fail(function(jqxhr, textStatus, errorThrown){
        displayErrorInformation(jqxhr.responseText);
    });
}


function fillResultsSummary(summary) {
        fillRowSummary(summary);
}

function fillRowSummary(summary) {
    $('#summary').append(
        "<tr>" +
            "<td class='align-middle'> SUMA </td>" +
            "<td class='align-middle'>" + summary.summaryNetValue + " PLN</td>" +
            "<td class='align-middle'>" + summary.summaryTaxValue + " PLN</td>" +
            "<td class='align-middle'>" + summary.summaryGrossValue + " PLN</td>" +
            "<td class='align-right'></td>" +
        "</tr>"
    );
}

function prepareDetailsButton(id) {
    return '<button type="button" class="btn btn-primary" onclick="goToDetailsPage(' + id + ')">Detale</button>';
}

function goToDetailsPage(id) {
    window.location.href = "/admin/vehicle-service-action/" + id;
}

function clearCreateModal() {
    $("#create-invoice-number").val('');
    $("#create-invoice-net-value").val('');
    $("#create-invoice-tax-rate").val('');
    $("#create-service-date").val('');
    $("#create-workshop").val('');
    $("#create-car-mileage").val('');
    $("#create-service-action").val('');
    $("#create-service-action-description").val('');

}

function sendCreateRequest() {
    $.ajax({
        url: vehicleActionApiUrl,
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify({
                vehicleId: vehicle.id,
                carMileage: $("#create-car-mileage").val(),
                serviceDate: $("#create-service-date").val(),
                invoiceNumber: $("#create-invoice-number").val(),
                invoiceNetValue: $("#create-invoice-net-value").val(),
                taxRate: $("#create-invoice-tax-rate").val(),
                workshopId: $("#create-workshop").val(),
                serviceActionTypeId: $("#create-service-action").val(),
                serviceActionDescription: $("#create-service-action-description").val()
        })
    })
        .done(function () {
            $("#create-modal").modal('hide');
            $("#operation-successful-modal").modal('show');
            findServiceActions();
            findServiceActionsSummary();
        })
        .fail(function (jqxhr, textStatus, errorThrown) {
            showError(prepareErrorMessage(jqxhr.responseText));
        })
}

function prepareUrl(){
     var url = "";
     url += preparePaginationUrl();

     var actionType = $("#action-type-filter").find(":selected").val();
     var workshop = $("#workshop-filter").children(":selected").val();
     var dateFrom = $("#date-from-filter").val();
     var dateTo = $("#date-to-filter").val();


     if (actionType != "") {
        url += "&serviceActionTypeName=" + actionType;
     }
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



function reloadWorkshopServices() {
    $.ajax({
        url: workshopServiceTypes + "workshop_id=" + $("#create-workshop").val(),
        type: "GET",
        dataType: "json",
        contentType: "application/json"
    })
    .done(function (workshopActions) {
        $('#create-service-action').empty();
        fillResultsReloadedWorkshopServices(workshopActions.workshopActions);

    })
        .fail(function(jqxhr, textStatus, errorThrown){
        displayErrorInformation(jqxhr.responseText);
    });
}

function fillResultsReloadedWorkshopServices(workshopActions) {
    workshopActions.forEach(function(action){
            fillReloadedWorkshopService(action);
        });
}

function fillReloadedWorkshopService(action) {
    $('#create-service-action').append('<option value=' + action.serviceActionId + '>' + action.serviceActionName +
    '</option>');
}

function showError(text) {
    $("#error-alert-text").text(text);
    $("#error-alert").removeClass('d-none');
}







