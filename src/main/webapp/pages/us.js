function del(obj, robj) {
    var dataJson = {id: obj};
    $.ajax({
        type: "POST",
        url: "/deleteU",
        data: JSON.stringify(dataJson),

        async: false,
        dataType: "json",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        success: function (data, textStatus, jqXHR) {
            debugger;
            deleteTable();
            loadTable(data, robj);
        },
        error: function (data) {
            alert(data);
        }
    })
}
function add(robj) {

    var dataJson = {
        name: document.getElementById("Tname").value,
        email: document.getElementById("Temail").value,
        idrole: document.getElementById("Tidrole").value
    };

    $.ajax({
        type: "POST",
        url: "/addU",
        data: JSON.stringify(dataJson)

        ,
        async: false,
        dataType: "json",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        success: function (data, textStatus, jqXHR) {
            $("#Tname").val("");
            $("#Temail").val("");
            deleteTable();
            loadTable(data, robj);
        },
        error: function (data) {
            alert(data);
        }
    })
}
function editRow(obj, objid, robj) {
    var index = obj.parentNode.parentNode.rowIndex;
    document.getElementById("nev").value = objid.trim();
    var table = document.getElementById("myTable");
    document.getElementById("Tname").value = table.rows[index].cells[0].innerHTML;
    document.getElementById("Temail").value = table.rows[index].cells[1].innerHTML;
    loadRole(robj, table.rows[index].cells[2].innerHTML.trim());
}
function edit(robj) {
    var dataJson = {
        id: document.getElementById("nev").value,
        name: document.getElementById("Tname").value,
        email: document.getElementById("Temail").value,
        idrole: document.getElementById("Tidrole").value
    };
    $.ajax({
        type: "POST",
        url: "/editU",
        data: JSON.stringify(dataJson),


        async: false,
        dataType: "json",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        success: function (data, textStatus, jqXHR) {
            $("#Tname").val("");
            $("#Temail").val("");
            deleteTable();
            loadTable(data, robj);

        },
        error: function (data) {
            alert(data);
        }
    })
}
function deleteTable() {
    var table = document.getElementById("myTable")

    for (var i = table.rows.length - 1; i > 0; i--) {
        table.deleteRow(i);
    }

}
function loadTable(listU, listR) {

    var template = document.getElementById('templateTable').innerHTML.trim();
    template = _.template(template);
    document.getElementById('refresh').innerHTML = template({
        listU: listU,
        listR: listR

    });
}
function loadRole(listR, obj) {

    var tmpl = document.getElementById('templateRole').innerHTML.trim();
    tmpl = _.template(tmpl);
    document.getElementById('refreshR').innerHTML = tmpl({
        listR: listR,
        str: obj

    });
}

