URL='http://localhost:8080/api/event/update'


if pgrep -f mcserver.jar > /dev/null;
then
  echo "Сервер останавливается!"
  screen -S mc -X eval 'stuff "^U"'
  screen -S mc -X eval 'stuff "stop\015"'
fi

while  pgrep -f mcserver.jar > /dev/null ; do
  sleep 1
  echo -n ". "
done
echo ""
echo "Запуск $1/server.jar! "
screen -S mc -X eval 'stuff "^U"'
#screen -S mc -X eval 'stuff "\025"'
screen -S mc -X  eval 'stuff "cd '$1'\015"'
screen -S mc -X eval 'stuff "./start.sh\015"'
sleep 1
curl "$URL"