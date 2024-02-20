"use strict";


window.onload = function () {

    window.eventSource = registerSSE('http://localhost:8080/api/register-client');

    window.addEventListener('beforeunload', () => {
        eventSource.close();
    });
}


function handleStatusEvent(data) {
    console.log(data);
    document.getElementById('info').innerText = data.servers[0].name;
}

function registerSSE(url) {
    const source = new EventSource(url);
    source.addEventListener('status', event => {
        handleStatusEvent(JSON.parse(event.data));
    })
    source.onopen = event => console.log("Connection opened");
    source.onerror = event => console.log("Connection error");
    return source
}


