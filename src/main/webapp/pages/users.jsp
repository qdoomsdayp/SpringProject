<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: wital
  Date: 25.01.2017
  Time: 9:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
    <script src="/pages/us.js"></script>
    <script src="http://underscorejs.org/underscore-min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/lodash.js/3.2.0/lodash.js"></script>
    <script>
        _.templateSettings = {
            evaluate: /\{\{(.+?)\}\}/g,
            interpolate: /\{\{=(.+?)\}\}/g,
            escape: /\{\{-(.+?)\}\}/g
        };
    </script>
    <meta cgarset="utf-8">
    <link rel="stylesheet" href="/pages/u.css" type="text/css">
    <title>Пользователи</title>

</head>


<body onload="loadTable(us,r);loadRole(r,0)">
<script>
    var us = ${listUsers};
    var r = ${listRoles};
</script>
<form name="users" action="Users/form" method="post">
    <input type="hidden" name="nev" id="nev">

    <div class="main">
        <div class="UP">
            <h1>
                Пользователи
            </h1>
            <a href="http://localhost:8001/roles">Роли</a>
        </div>
        <div class="block1" id="refresh">


        </div>
        <div class="block2">
            <table id="Table" class="table2">
                <tr>
                    <td>имя пользователя</td>
                    <td><input type="text" id="Tname" name="nameUser"></td>
                    <td><input type="button" id="but_user" name="Input" value="Добавить" onclick="add(r)">
                    </td>
                </tr>
                <tr>
                    <td>E-mail пользователя</td>
                    <td><input type="text" name="email" id="Temail"></td>
                </tr>
                <tr>
                    <td>Роль</td>
                    <td id="refreshR">

                    </td>
                </tr>
                <tr>
                    <td><input type="button" value="Сохранить" onclick="edit(r)"></td>
                </tr>
            </table>
        </div>

    </div>


</form>

<script type="text/template" id="templateTable">
    <table id="myTable" class="table1">
        <tr class="table1 rows">
            <th class="table1">Имя пользователя</th>
            <th class="table1">Электронная почта</th>
            <th class="table1">Роль</th>
        </tr>
        {{ for (var i = 0; i < listU.length; i++) { }}
        <tr class="table1 rows">
            <td class="table1"> {{=listU[i].name}}</td>
            <td class="table1"> {{=listU[i].email}}</td>
            {{for (var j = 0; j < listR.length; j++) { }}
            {{if (listU[i].idrole == listR[j].idR) { }}
            <td class="table1" abbr=" {{=listR[j].idR}}   "> {{=listR[j].nameR}}</td>

            {{ } }}
            {{ } }}
            <td class="table1">
                <input type="button" name="{{=listU[i].id}} " value="R" onclick="editRow(this,this.name,r)">
                <input type="button" name="{{=listU[i].id}}" value="[X]" onclick="del(this.name,r)">
            </td>
            {{ } }}
        </tr>
    </table>


</script>

<script type="text/temlate" id="templateRole">

    <select name="Role" id="Tidrole">
    <option value="role" selected>Выберите роль</option>
    {{ for (var j = 0; j < listR.length; j++) { }}
        {{ if (str!=0 & listR[j].nameR==str){ }}
        <option value="{{=listR[j].idR}}" selected>{{=listR[j].nameR}}</option>
        {{ }else {}}
        <option value="{{=listR[j].idR}}" >{{=listR[j].nameR}}</option>
    {{ } }}
{{ } }}



</script>

</body>

</html>
