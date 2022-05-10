ДЗ 7

Цель:
В результате выполнения дз вы реализуете автоматический тест, используя Java + Selenium

Описание/Пошаговая инструкция выполнения домашнего задания:  
Создайте класс WebDriverFactory со статическим методом create();  
Метод create() принимает обязательный параметр webDriverName и необязательный параметр options, а возвращает соответствующий имени вебдрайвер с заданными (если были) options.

Примеры использования:
```
WebDriver wd = WebDriverFactory.createNewDriver("chrome"); или
FirefoxOptions options = new FirefoxOptions(); 
WebDriver wd = WebDriverFactory.createNewDriver("firefox", options);
```

Шаги теста:

1) Открыть https://otus.ru
2) Авторизоваться на сайте
3) Войти в личный кабинет
4) В разделе "О себе" заполнить все поля "Личные данные" и добавить не менее двух контактов
5) Нажать сохранить
6) Открыть https://otus.ru в "чистом браузере"
7) Авторизоваться на сайе
8) Войти в личный кабинет
9) Проверить, что в разделе "О себе" отображаются указанные ранее данные

Критерии оценки:
- +1 балл - код компилируется и запускается
- +1 балл - код запускается без дополнительных действий со стороны проверяющего (не нужно скачивать WebDriver, решать конфликты зависимостей и т.п.)
- +1 балл - логин/пароль для авторизации не зашиты в код (передаются как параметры при старте)
- +1 балл - логи пишутся в консоль и файл
- +1 балл - тест проходит без падений (допускается падение теса только при некорректной работе SUT)
- +1 балла - реализован паттерн PageObject
- +1 балл - проект компилируется и собирается
- +1 балл - в репозитории нет лишних файлов (.iml, директория idea и т.д.)
- +1 балл - регистр значения параметра -Dbrowser не влияет на результат
- +1 балл - для хранения имен драйверов используются Enum

##Пример сборки и запуска
```
mvn clean test -Dbrowser=CHROME -Dlogin=ваш_логин -Dpass=ваш_пароль -Durl=https://otus.ru
```