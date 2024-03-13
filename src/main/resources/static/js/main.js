"use strict";


window.onload = function () {
    let href = window.location.href;
    console.log(window.location.host);
    if (href.includes('localhost')) {
        window.mainUrl = 'http://localhost:8080';
    }
    else {
        window.mainUrl ='http://94.176.234.20:8081';
    }
    console.log(mainUrl);


    window.eventSource = registerSSE(mainUrl+'/api/register-client');

    window.addEventListener('beforeunload', () => {
        eventSource.close();
    });
    document.addEventListener("click", documentActions);
}

function documentActions(e) {

    const targetElement = e.target;
    if(targetElement.classList.contains('start-server')) {
        let buttons = document.getElementsByClassName("start-server");
        Array.prototype.forEach.call(document.getElementsByClassName("start-server"), el => {
            el.setAttribute("disabled", "disabled");
        });

        let attribute = targetElement.getAttribute('serverName');

        fetch(mainUrl+'/api/start?serverName=' + attribute)
            .then((response) => {
                console.log(response);
            });
            /*.then((data) => {
                console.log(data);
            });*/
    }
}

function handleStatusEvent(data) {
    console.log(data);

    let content = "";
    content += '<table class="table table-striped">';
    content += '<tbody>';

    let servers = data.servers;
    for(let i=0; i < servers.length; i++) {
        let button_state = servers[i].active ? 'disabled="disabled"' : '';
        let server_status = servers[i].active ? '<span class="text-success">Активен</span>' : '<span class="text-muted">Не активен</span>';
        content += '<tr class="server">';
        content += '<th scope="row">' + (i+1) + '</th>';
        content += '<td class="title">' + servers[i].title + '</td>';
        content += '<td class="status">' + server_status + '</td>';
        content += '<td><button class="btn btn-primary start-server" serverName="' + servers[i].name +  '" type="submit"' + button_state + '>' +
            '<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>Start</button></td>';
        content += '</tr>';
    }

    content += '<tbody>';
    content += '</table>';
    document.getElementById('info').innerHTML = content;
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


