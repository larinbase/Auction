<div class="auction-list">
    <div>
        <h1>My auctions</h1>
        <table>
            <thead>
            <tr>
                <th>Auction ID</th>
                <th>Name</th>
                <th>User ID</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <#list auctionList as auction>
                <#if auction.getUserId().equals(userId)>
                    <tr>
                        <td>${auction.getId()}</td>
                        <td>${auction.getName()}</td>
                        <td>${auction.getUserId()}</td>
                        <td>
                            <table>
                                <tr>
                                    <td>
                                        <a href="auctions/${auction.id}">Перейти</a>
                                    </td>
                                    <td >
                                        <a  href="auctions/delete/${auction.id}">Удалить</a>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </#if>
            </#list>
            </tbody>
        </table>
    </div>

    <div>
        <h1>Available auctions</h1>
        <table>
            <thead>
            <tr>
                <th>Auction ID</th>
                <th>Name</th>
                <th>User ID</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <#list auctionList as auction>
                <#if !auction.getUserId().equals(userId)>
                    <tr>
                        <td>${auction.getId()}</td>
                        <td>${auction.getName()}</td>
                        <td>${auction.getUserId()}</td>
                        <td>
                            <a href="auctions/${auction.id}">Участвовать</a>
                        </td>
                    </tr>
                </#if>
            </#list>
            </tbody>
        </table>
    </div>
</div>