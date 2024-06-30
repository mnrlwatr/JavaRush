taskKey="JavaRush.MiniProjects.LogParser.big08"\n\nПарсер логов (8)

Ты реализовал парсер логов из разных файлов.

Помимо парсера, ты реализовал свой собственный язык запросов. Он нужен для того, что бы минимизировать
количество методов. Строчка в нашем лог-файле содержала всего 5 параметров плюс один вариативный параметр.
При количестве параметров запроса два - это 25 возможный комбинаций, соответственно, что бы сделать любую
выборку нужно реализовать 25 методов. Теперь представь, что параметров в строчке лог-файла не 5, а 10. И
количество параметров запроса не 2, а 3. Уже нужно было бы написать 10 * 10 * 10 = 1000 методов.
Чем сложнее лог - тем больше времени разработчик может себе сэкономить.

Из рекомендаций и возможных улучшений можно реализовать запрос с количеством параметров 3, например такой:
get field1 for field2 = &quot;value1&quot; and field3 = &quot;value2&quot; and date between &quot;after&quot;
and &quot;before&quot;

Из архитектурных улучшений в этой программе уместно использовать паттерн команда (для получения значения
полей field, действуя единообразно). Реализуй его, если еще не сделал этого.


Требования:
1.	Поздравляю! Ты написал собственный парсер и свой язык запросов.


Парсер логов (8)

Теперь добавим поддержку дополнительного параметра запроса в наш QL.
Дополнительный параметр будет отвечать за диапазон дат, которые нас интересуют.
Пример запроса:
get ip for user = &quot;Eduard Petrovich Morozko&quot; and date between &quot;11.12.2013 0:00:00&quot; and &quot;03.01.2014 23:59:59&quot;
Ожидаемый результат: Set&lt;String&gt; с записями: 127.0.0.1 и 146.34.15.5.

Общий формат запроса:
get field1 for field2 = &quot;value1&quot; and date between &quot;after&quot; and &quot;before&quot;
Дополнительным параметром может быть только интервал дат, который нас интересует.

Поддержка старых форматов запросов должна сохраниться.



Парсер логов (8)

Давай добавим поддержку параметра запроса в наш QL.
Примеры запросов с параметром:
1)	get ip for user = &quot;Vasya&quot;
2)	get user for event = &quot;DONE_TASK&quot;
3)	get event for date = &quot;03.01.2014 03:45:23&quot;
Общий формат запроса с параметром:
get field1 for field2 = &quot;value1&quot;
Где: field1 - одно из полей: ip, user, date, event или status;
field2 - одно из полей: ip, user, date, event или status;
value1 - значение поля field2.

Алгоритм обработки запроса следующий: просматриваем записи в логе, если поле
field2 имеет значение value1, то добавляем поле field1 в множество, которое затем
будет возвращено методом execute.

Пример: Вызов метода execute(&quot;get event for date = \&quot;30.01.2014 12:56:22\&quot;&quot;) должен
вернуть Set&lt;Event&gt;, содержащий только одно событие SOLVE_TASK. Какая именно
задача решалась возвращать не нужно.

Поддержка старого формата запросов должна сохраниться.



Парсер логов (8)

Как ты заметил существует огромное количество комбинаций параметров для выбора
определенных записей из лог файла. Покрыть их все соответствующими методами
дело не благодарное.  Поэтому мы реализуем свой язык запросов (QL).
Пример запроса:
get ip for user = &quot;Vasya&quot;
Такой запрос должен будет вернуть все IP адреса, с которых пользователь Vasya что-то
делал и это отображено в нашем логе.
Представь, как будет удобно ввести запрос в консоль и получить необходимую
информацию из лога.

5.1.	Реализуй интерфейс QLQuery у класса LogParser. Метод execute() пока должен
поддерживать только следующие запросы:
5.1.1.	get ip
5.1.2.	get user
5.1.3.	get date
5.1.4.	get event
5.1.5.	get status

Пример: Вызов метода execute(&quot;get ip&quot;) должен вернуть Set&lt;String&gt;, содержащий все
уникальные IP из лога (это будет: 127.0.0.1, 12.12.12.12, 146.34.15.5, 192.168.100.2
для тестового файла). Аналогично должны работать и другие запросы.

Реальные объекты в возвращаемом множестве должны быть типа String для запросов ip и user,
для запроса date - тип объектов Date, для event и status - Event и Status соответственно.



Парсер логов (8)

Реализуй интерфейс EventQuery у класса LogParser:
4.1.	Метод getNumberOfAllEvents() должен возвращать количество событий за указанный период.
4.2.	Метод getAllEvents() должен возвращать все события за указанный период.
4.3.	Метод getEventsForIP() должен возвращать события, которые происходили с указанного IP.
4.4.	Метод getEventsForUser() должен возвращать события, которые инициировал
определенный пользователь.
4.5.	Метод getFailedEvents() должен возвращать события, которые не выполнились.
4.6.	Метод getErrorEvents() должен возвращать события, которые завершились ошибкой.
4.7.	Метод getNumberOfAttemptToSolveTask() должен возвращать количество попыток
решить определенную задачу.
4.8.	Метод getNumberOfSuccessfulAttemptToSolveTask() должен возвращать количество
успешных решений определенной задачи.
4.9.	Метод getAllSolvedTasksAndTheirNumber() должен возвращать мапу (номер_задачи :
количество_попыток_решить_ее).
4.10.	Метод getAllDoneTasksAndTheirNumber() должен возвращать мапу (номер_задачи :
сколько_раз_ее_решили).



Парсер логов (8)

Реализуй интерфейс DateQuery у класса LogParser:
3.1.	Метод getDatesForUserAndEvent() должен возвращать даты, когда определенный
пользователь произвел определенное событие.
3.2.	Метод getDatesWhenSomethingFailed() должен возвращать даты, когда
любое событие не выполнилось (статус FAILED).
3.3.	Метод getDatesWhenErrorHappened() должен возвращать даты, когда
любое событие закончилось ошибкой (статус ERROR).
3.4.	Метод getDateWhenUserLoggedFirstTime() должен возвращать дату, когда
пользователь залогинился впервые за указанный период. Если такой даты в логах нет - null.
3.5.	Метод getDateWhenUserSolvedTask() должен возвращать дату, когда пользователь
впервые попытался решить определенную задачу. Если такой даты в логах нет - null.
3.6.	Метод getDateWhenUserDoneTask() должен возвращать дату, когда пользователь
впервые решил определенную задачу. Если такой даты в логах нет - null.
3.7.	Метод getDatesWhenUserWroteMessage() должен возвращать даты, когда
пользователь написал сообщение.
3.8.	Метод getDatesWhenUserDownloadedPlugin() должен возвращать даты, когда
пользователь скачал плагин.


