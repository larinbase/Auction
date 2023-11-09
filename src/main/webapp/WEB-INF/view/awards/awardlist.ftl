<div class="award-list">
    <h1>Your awards</h1>
    <table>
        <thead>
        <tr>
            <th>Artikul</th>
            <th>Name</th>
            <th>Description</th>
        </tr>
        </thead>
        <tbody>
        <#list lots as lot>
            <tr>
                <td>${lot.getArtikul()}</td>
                <td>${lot.getName()}</td>
                <td>${lot.getDescription()}</td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>