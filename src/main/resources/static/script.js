
const urlInput = document.getElementById("urlInput");
const shortenBtn = document.getElementById("shortenBtn");
const resultSection = document.getElementById("resultSection");
const shortUrl = document.getElementById("shortUrl");

shortenBtn.addEventListener("click", () => {

    fetch("/api/url/shorten", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            url: urlInput.value
        })
    })
    .then(response => response.json())
    .then(data => {

        shortUrl.textContent = data.shortUrl;

        resultSection.hidden = false;
    });
});




const copyBtn = document.getElementById("copyBtn");

copyBtn.addEventListener("click", async () => {
    const textToCopy = shortUrl.textContent;

    try {
        await navigator.clipboard.writeText(textToCopy);

        copyBtn.textContent = "Copied!";

        setTimeout(() => {
            copyBtn.textContent = "Copy";
        }, 2000);

    } catch (error) {
        console.error("Clipboard failed:", error);
        alert("Could not copy the URL.");
    }
});