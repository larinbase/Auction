<html lang="eng">
<head>
    <title>Main</title>
    <style>
        <#include "resources/css/menu.css" >
        <#include "resources/css/main.css" >
        <#include  "resources/css/mainpage.css">
    </style>
</head>
<body>
<div class="menu">
    <#include "authorizedmenu.ftl">
</div>
<div class="content">
    <div class="create-auction-section">
        <form class="create-auction-section" method="post">
            <label for="auctionName">Auction name</label>
            <input type="text" id="auctionName" name="auctionName" required/>
            <input type="submit" value="Create Auction"></input>
        </form>
    </div>

    <#include "auctions/auctionlist.ftl">

</div>
</body>
</html>