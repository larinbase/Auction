let lotArtikul;


function openModal(element) {
    console.log(element)

    var modal = document.getElementById("bidModal");
    modal.style.display = "block";
    lotArtikul = element.getAttribute("data-lot-artikul")
    console.log("Lot artikul: " + lotArtikul)
}

function closeModal() {
    var modal = document.getElementById("bidModal");
    modal.style.display = "none";
}

function submitBid() {
    var bidAmount = document.getElementById("bidAmount").value;

    var xhr = new XMLHttpRequest();

    var servletUrl = "bet/create";

    xhr.open("POST", servletUrl, true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    var data = "bidAmount=" + bidAmount + "&lotArtikul=" + lotArtikul;

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // Handle the response from the servlet
        }
    };

    xhr.send(data);

    closeModal();
}

