var url = "/admin/api/scaffolding"
var workshopsApiUrl = url + "/removed-workshops?"

$(document).ready(function () {

    findRemovedWorkshops()
    $("#filter input, #filter select, [form='filter']").on("change", function () {
        findRemovedWorkshops()
    });

});


function findRemovedWorkshops() {
    $.ajax({
        url: workshopsApiUrl + prepareUrl(),
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
            fillResults(workshops.workshops);
        }
    })
    .fail(function(jqxhr, textStatus, errorThrown){
        displayErrorInformation(jqxhr.responseText);
    });
}

function fillResults(workshops) {
    let value = 1;
    workshops.forEach(function (workshop){
        fillRow(workshop, value);
        value = value + 1;
    });
}

function fillRow(workshop, value) {
    $('#removed-workshops').append(
        "<tr>" +
            "<td class='align-middle'>" + value + "</td>" +
            "<td class='align-middle'>" + workshop.name + "</td>" +
            "<td class='align-middle'>" + workshop.street + " "  + workshop.buildingNo + showApartmentNo(workshop.apartmentNo) + "</td>" +
            "<td class='align-middle'>" + workshop.postalCode +"</td>" +
            "<td class='align-middle'>" + workshop.city +"</td>" +
            "<td class='align-middle'>" + workshop.removeDate +"</td>" +
            "<td class='align-middle'>" + prepareDetailsButton(workshop.id) + "</td>" +
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

function prepareDetailsButton(id) {
    return '<button type="button" class="btn btn-primary" onclick="goToDetailsPage(' + id + ')">Sprawdź i przywróć</button>';
}

function goToDetailsPage(id) {
    window.location.href = "/admin/removed-workshop/" + id;
}


function getActionTypes(){
        var actionTypesList = [];
          $.each($("input[name='actionTypes']:checked"), function(){
            actionTypesList.push($(this).val());
            });
        return actionTypesList;
}

function prepareUrl(){
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

function showError(text) {
    $("#error-alert-text").text(text);
    $("#error-alert").removeClass('d-none');
}
