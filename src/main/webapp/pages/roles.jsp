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
    <script src="/pages/rol.js"></script>
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
    <link rel="stylesheet" href="/pages/rol.css" type="text/css">
    <title>Роли</title>

</head>

<body onload="loadRole(r)">
<script>
    var r = ${listRolesR};
</script>
<form name="roles" action="Roles/form" method="post">
    <input type="hidden" id="command" value="" name="command">
    <div class="main">
        <h1>
            Роли
        </h1>
        <div class="list" id="selectRole">

        </div>
        <div class="Input">


            <input type="button" name="Input" value="Добавить" onclick="add()">


        </div>
        <div class="Edit">

            <input type="button" name="Edit" value="Редактировать" onclick="edit()">

        </div>
        <div class="Delete">

            <input type="button" name="Delete" value="Удалить" onclick="del()">

        </div>
        <div>
            <table>
                <tr>
                    <td>имя роли</td>
                    <td><input type="text" id="Tnamer" name="idrole"></td>
                </tr>
            </table>
        </div>
        <a href="http://localhost:8001/user">Пользователи</a>

    </div>

</form>

<script type="text/temlate" id="templateRole">

    <select name="Role" id="Tidrole"  onchange="selectList()">
    <option value="role" selected>Выберите роль</option>
    {{ for (var j = 0; j < listR.length; j++) { }}
        <option value="{{=listR[j].idR}}" >{{=listR[j].nameR}}</option>
  {{ } }}



</script>

</body>
</html>
