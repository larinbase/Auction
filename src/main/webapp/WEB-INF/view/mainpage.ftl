<html lang="eng">
<head>
    <title>Main</title>
    <link href="resources/css/menu.css" rel="stylesheet">
    <link href="resources/css/main.css" rel="stylesheet">
    <link href= "resources/css/mainpage.css" rel="stylesheet">
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