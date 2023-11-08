<div id="deleteLotModal" class="modal">
    <div class="modal-content">
        <span class="close" onclick="closeModal()">&times;</span>
        <h2>Place a Bid</h2>
        <form id="bidForm" onsubmit="submitDeletionData(); return false;">
            <label for="bidAmount">Bid Amount:</label>
            <input type="number" id="bidAmount" name="bidAmount" required>
            <input type="submit" value="Place Bid"></input>
        </form>
    </div>
</div>