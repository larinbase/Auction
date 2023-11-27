<div class="lots-list">
    <h1>List of Lots</h1>
    <table id="lots-table">
        <thead>
        <tr>
            <th>Artikul</th>
            <th>Name</th>
            <th>Description</th>
            <th>Bets</th>
        </tr>
        </thead>
        <tbody>
        <#list lots as lot>
            <tr>
                <td>${lot.getArtikul()}</td>
                <td>${lot.getName()}</td>
                <td>${lot.getDescription()}</td>
                <td id="bets-${lot.getArtikul()}" colspan="3">
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
                        <th>
                            <button class="open-modal-button" id="bet-button" onclick="openModal(this)" data-lot-artikul="${lot.getArtikul()}" curr-user-id="${currentUserId}">Place Bid</button>
                        </th>
                        </tbody>
                    </table>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
    <#include "../../bet-pop-up-window.ftl"/>
</div>