<html lang="eng" xmlns="http://www.w3.org/1999/html">
<head>
    <title>Profile</title>
    <meta charset="UTF-8">
    <link href="resources/css/menu.css" rel="stylesheet">
    <link href="resources/css/main.css" rel="stylesheet">
    <link href="resources/css/profile.css" rel="stylesheet">
</head>
<body>
<div class="menu">
    <#include "authorizedmenu.ftl">
</div>
<div>
    <p>User name: ${userName}</p>
    <p>Your ID: ${userId}</p>

    <#include "awards/awardlist.ftl">

</div>
</body>
</html>