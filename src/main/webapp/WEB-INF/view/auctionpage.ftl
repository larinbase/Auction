<html lang="eng">
<head>
    <title>Hello</title>
    <style>
        <#include "resources/css/menu.css" >
        <#include "resources/css/main.css" >
        <#include "resources/css/auction.css">
        <#include "resources/css/lotlist.css">
        <#include "resources/css/popupwindow.css">
    </style>
<#--    <link rel="stylesheet" href="resources/css/menu.css" />-->
    <script>
        <#include "resources/js/auction.js">
    </script>
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