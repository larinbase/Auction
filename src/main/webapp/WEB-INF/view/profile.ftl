<html lang="eng" xmlns="http://www.w3.org/1999/html">
<head>
    <title>Profile</title>
    <meta charset="UTF-8">
    <style>
        <#include "resources/css/menu.css" >
        <#include "resources/css/main.css" >
        <#include "resources/css/profile.css">
    </style>
</head>
<body>
<div class="menu">
    <#include "authorizedmenu.ftl">
</div>
<div>
    <p>HELLO: ${userName}</p>
    <p>Your ID: ${userId}</p>

    <div class="award-list">
        <h1>Your awards</h1>
        <table>
            <thead>
            <tr>
                <th>Artikul</th>
                <th>Name</th>
                <th>Description</th>
            </tr>
            </thead>
            <tbody>
            <#list lots as lot>
                <tr>
                    <td>${lot.getArtikul()}</td>
                    <td>${lot.getName()}</td>
                    <td>${lot.getDescription()}</td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>

</div>
</body>
</html>