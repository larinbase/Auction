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

    <#include "awards/awardlist.ftl">

</div>
</body>
</html>