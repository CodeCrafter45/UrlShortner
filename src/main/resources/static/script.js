const errorMessage = document.getElementById("errorMessage");
const urlInput = document.getElementById("urlInput");
const shortenBtn = document.getElementById("shortenBtn");
const resultSection = document.getElementById("resultSection");
const shortUrl = document.getElementById("shortUrl");
const copyBtn = document.getElementById("copyBtn");
const loadStatsBtn = document.getElementById("loadStatsBtn");
const totalUrls = document.getElementById("totalUrls");
const totalClicks = document.getElementById("totalClicks");
const topCode = document.getElementById("topCode");
const statsBody = document.getElementById("statsBody");

shortenBtn.addEventListener("click", async () => {
    errorMessage.hidden = true;
    resultSection.hidden = true;

    shortenBtn.disabled = true;
    shortenBtn.textContent="Generating....";
     try {
            const response = await fetch("/api/url/shorten", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    url: urlInput.value
                })
            });

            const data = await response.json();

            if (!response.ok) {
                errorMessage.textContent = data.message;
                errorMessage.hidden = false;
                return;
            }

            shortUrl.textContent = data.shortUrl;
            resultSection.hidden = false;

        } finally {
            // Restore button
            shortenBtn.disabled = false;
            shortenBtn.textContent = "Shorten";
        }
    });

copyBtn.addEventListener("click", async () => {
    try {
        await navigator.clipboard.writeText(shortUrl.textContent);

        copyBtn.textContent = "Copied!";

        setTimeout(() => {
            copyBtn.textContent = "Copy";
        }, 2000);
    } catch {
        alert("Could not copy the URL.");
    }
});

async function loadStats() {

    const response = await fetch("/api/url/stats");
    const data = await response.json();


    totalUrls.textContent = data.length;


    let clicks = 0;

    for (let i = 0; i < data.length; i++) {
        clicks += data[i].clickCount;
    }

    totalClicks.textContent = clicks;


    let top = "--";
    let maxClicks = -1;

    for (let i = 0; i < data.length; i++) {
        if (data[i].clickCount > maxClicks) {
            maxClicks = data[i].clickCount;
            top = data[i].shortCode;
        }
    }

    topCode.textContent = top;

    // Clear previous table rows
    statsBody.innerHTML = "";

    // Create table rows
    for (let i = 0; i < data.length; i++) {

        const row = document.createElement("tr");
        const domain = data[i].originalUrl
            .replace("https://","")
            .replace("http://","")
            .replace("www.","");
        row.innerHTML = `
            <td>
                <a href="/r/${data[i].shortCode}" target="_blank">
                    ${data[i].shortCode}
                </a>
            </td>
            <td>${domain}</td>
            <td>
               <span class="click-badge">${data[i].clickCount}</span>
            </td>
        `;

        statsBody.appendChild(row);
    }
}

loadStatsBtn.addEventListener("click", loadStats);