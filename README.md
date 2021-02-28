# Тестовое задание
## Запуск Cypress
Для запуска потребуется установленный node.js
1. cd Cypress
2. npm i
3. npx cypress open
4. Выбираем любой доступный браузер и запускаем единственный файл с тестами taskSpec.js

Файл с тестами хранится в папке cypress/integration

В файле cypress/support/index.js замьючен запрос к "yandex.ru/webvisor/", чтобы не засорять тест раннер
## Запуск тестов на селениуме
Тесты хранятся в файле Selenium/selenium_ofd_java/src/test/java/TestPlan.java

С браузерами не заморачивался и просто выбрал хром. В проекте установлен chromedriver_mac64 для 88 версии хрома, возможно потребуется заменить его другим драйвером для запуска тестов https://chromedriver.chromium.org/downloads
