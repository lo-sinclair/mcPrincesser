pwdx 5340

ps -ax  | grep -E 'java.*server.jar' | awk '{print $1}'
jps -v  | grep 'server.jar' | awk '{print $1}'

ps -ax  | grep -E 'mcprincesser.Application' | awk '{print $1}'


p=($(ps -ax  | grep -E 'java.*server.jar' | awk '{print $1}'))
echo ${p[0]}


if pgrep -f server.jar > /dev/null;
then
  echo "Сервер останавливается!"
  screen -S mc -X eval 'stuff "^U"'
  screen -S mc -X eval 'stuff "stop\015"'
fi

while  pgrep -f server.jar > /dev/null ; do
  sleep 1
  echo -n ". "
done
echo ""
echo "Запуск mc$1/server.jar! "
screen -S mc -X eval 'stuff "^U"'
#screen -S mc -X eval 'stuff "\025"'
screen -S mc -X  eval 'stuff "cd /var/www/mc'$1'\015"'
screen -S mc -X eval 'stuff "./start.sh\015"'
