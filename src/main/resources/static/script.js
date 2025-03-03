// script.js
document.addEventListener('DOMContentLoaded', function () {
    fetchData();
    setInterval(fetchData, 5000); // 每 5 秒刷新一次数据
});

function fetchData() {
    fetch('/index')
        .then(response => response.text())
        .then(data => {
            displayData(data);
            updateRefreshTime();
        })
        .catch(error => {
            console.error('获取数据失败:', error);
        });
}

function displayData(data) {
    const dataDisplay = document.getElementById('data-display');
    const dataPairs = data.split(',');

    let html = '';
    dataPairs.forEach(pair => {
        const [key, value] = pair.split('=');
        html += `
            <div class="data-card">
                <strong>${key.toUpperCase()}</strong>
                <p>${value}</p>
            </div>
        `;
    });

    dataDisplay.innerHTML = html;
}

function updateRefreshTime() {
    const refreshTimeElement = document.getElementById('refresh-time');
    const now = new Date();
    const timeString = now.toLocaleTimeString(); // 格式化时间
    refreshTimeElement.textContent = `最后更新时间: ${timeString}`;
}