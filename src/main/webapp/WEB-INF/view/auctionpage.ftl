<html lang="eng">
<head>
    <title>Auction</title>
    <link href="resources/css/menu.css" rel="stylesheet">
    <link href= "resources/css/main.css" rel="stylesheet">
    <link href="resources/css/auction.css" rel="stylesheet">
    <link href="resources/css/lotlist.css" rel="stylesheet">
    <link href="resources/css/popupwindow.css" rel="stylesheet">
<#--    <link rel="stylesheet" href="resources/css/menu.css" />-->
    <script src="resources/js/auction.js"></script>
</head>
<body>
<div class="menu">
    <#include "authorizedmenu.ftl">
</div>
<div class="content">
    <#if role = "admin">
        <#include "lots/view/creatorview.ftl" />

    <#else>
    <#--        <h2>Create bet</h2>-->
    <#--        <#include "bets/betform.ftl"/>-->
    <#--        <br/>-->
        <#include "lots/view/userview.ftl"/>

    </#if>


</div>
</body>
</html>