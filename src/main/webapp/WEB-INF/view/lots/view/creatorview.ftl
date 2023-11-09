<div class="create-lot-section">
    <h2>Create lot</h2>
    <#include "../createform.ftl">
</div>
<div class="lots-list">
    <h1>List of Lots</h1>
    <table>
        <thead>
        <tr>
            <th>Artikul</th>
            <th>Name</th>
            <th>Description</th>
            <th>Bets</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <#list lots as lot>
            <tr>
                <td>${lot.getArtikul()}</td>
                <td>${lot.getName()}</td>
                <td>${lot.getDescription()}</td>
                <td> <!-- Span three columns for bets -->
                    <table>
                        <thead>
                        <tr>
                            <th>Bet Amount</th>
                            <th>Bet Date</th>
                            <th>User</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list lot.getBets() as bet>
                            <tr>
                                <td>${bet.getAmount()}</td>
                                <td>${bet.getDateTime()}</td>
                                <td>${bet.getUserId()}</td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </td>
                <td>
                    <a href="lots/${lot.artikul}">Закрыть лот</a>
                </td>
            </tr>
        </#list>
        <#--                <th>-->
        <#--                    <button class="open-modal-button" id="delete-button" onclick="openModal(this)" data-lot-artikul="${lot.getArtikul()}">Close Lot</button>-->
        <#--                </th>-->
        </tbody>
    </table>
</div>