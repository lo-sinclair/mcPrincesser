
function go() {
    alert("Go");
}

async function fetchMoviesJSON() {
    const response = await fetch('http://localhost:8080/api/status');
    const movies = await response.json();
    return movies;
}
fetchMoviesJSON().then(movies => {
    movies; // полученный список фильмов
    console.log(movies);
});



async function subscribe() {
    let response = await fetch("/subscribe");

    if (response.status == 502) {
        // Статус 502 - это таймаут соединения;
        // возможен, когда соединение ожидало слишком долго
        // и сервер (или промежуточный прокси) закрыл его
        // давайте восстановим связь
        await subscribe();
    } else if (response.status != 200) {
        // Какая-то ошибка, покажем её
        showMessage(response.statusText);
        // Подключимся снова через секунду.
        await new Promise(resolve => setTimeout(resolve, 1000));
        await subscribe();
    } else {
        // Получим и покажем сообщение
        let message = await response.text();
        showMessage(message);
        // И снова вызовем subscribe() для получения следующего сообщения
        await subscribe();
    }
}

