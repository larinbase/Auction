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
                <td colspan="3"> <!-- Span three columns for bets -->
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
                            <button class="open-modal-button" id="bet-button" onclick="openModal(this)" data-lot-artikul="${lot.getArtikul()}">Place Bid</button>
                        </th>
                        </tbody>
                    </table>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
    <#--            pop-up-windows -->
    <#include "../../bet-pop-up-window.ftl"/>
    <#--            <#include "delete-pop-up-window.ftl"/>-->
</div>