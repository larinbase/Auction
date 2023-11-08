<div  class="award-list" >
    <h1>Your awards</h1>
    <ul>
        <#list lots as lot >
            <li> ${lot.getArtikul()} - ${lot.getName()}: ${lot.getDescription()} </li>
            <br/>
        </#list>
    </ul>
</div>