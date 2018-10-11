﻿# Гость
## 1. Регистрация
   1.1 Вариант использования начинается, когда пользователь кликнет по ссылке "Регистрация"
   1.2 Открывается соответствуюущая страница в окне браузера, в которой пользователь должен ввести
   - Логин
   + Адрес электронной почты
   + Пароль
   + Пароль для подтверждения
   1.3 Если введены некорректные данные, то повторять пункт 1.2 пока не будут введены корректные данные
   1.4 Нажать кнопку отправить.
   1.5 Сервер начнет обработку данных и сделает запрос в БД
   1.6 Если в БД существует посетитель с таким логином или адресом электронной почты, то сервер пошлет ответ с просьбой выполнить пункт 1.2
   1.7 Записать данные в БД и сделать посетителя пользователем
   1.8 Перевести пользователя на его "Онлайн-Диск" в раздел "Просмотр".
## 2. Авторизация
   2.1 Вариант использования начинается, когда пользователь кликнет по ссылке "Авторизация"
   2.2 Открывается соответствуюущая страница в окне браузера, в которой пользователь должен ввести
   - Логин
   + Пароль
   2.3 Если введены некорректные данные, то повторить пункт 2.2 пока не будут введены корректные данные
   2.4 Нажать кнопку отправить
   2.5 Сервер начнет обработку данных и сделает запрос в БД
   2.6 Если в БД не существует такого логина, то сервер пошлет ответ с просьбой пройти Регистрацию или, если не совпал пароль, то  сервер 
   пошлет ответ с просьбой выполнить пункт 2.2 
   2.7 Если совпали логин то пароль, сделать посетителя пользователем
   2.8 Перевести пользователя на его "Онлайн-диск" в раздел "Просмотр"

---
# Пользователь
## 1. Просмотр списка файлов
   1.1 Варинат начинается, в случае нажатия ссылки "Просмотр" или же в случае успешной регистрации или авторизации
   1.2 Сервер пришлет список всех файлов хранящихся на "Онлайн-Диске" пользователя.
## 2. Скачать с "Онлайн-Диск"
   2.1 Вариант начинается, после нажатия ссылки "Скачать"
   2.2 Сервер пришлет список всех файлов хранящихся на "Онлайн-диске" пользователя и справа от каждого файла будет кнопка с надписью 
   "Скачать"
   2.3 После нажатия кнопки "Скачать" будет скачан нужный файл.
## 3. Удалить c "Онлайн-Диск"
   3.1 Варинат начинается, в случае нажатия ссылки "Удалить"
   3.2 Сервер пришлет список всех файлов хранящихся на "Онлайн-диске" пользователя и справа от каждого файла будет кнопка с надписью 
   "Удалить"
   3.3 После нажатия кнопки "Скачать" буде тудален нужный файл, сервер перезагрузит страницу и пришлет новый список файлов.
## 4. Загрузить файлы на "Онлайн-диск"
   4.1 Вариант начинается, после нажатия ссылки "Добавить"
   4.2 Сервер пришлет страницу с кнопками "Добавить" и "Отправить".
   4.3 После нажатия кноки Добавить откроется проводник
   4.4 Пользователь выберет список файлов и нажмет ОК.
   4.5 После нажатия кнопки сервер добавит файлы в Онлайн-Диск пользователя.
## 5. Выход из учетной записи
   5.1 Вариант начинается, после нажатия ссылки "Выход"
   5.2 Пользователь будет переведен в статус посетителя и попадет на главную страницу сайта.
---
# Администратор
## 1. Получить список параметров для статистики
   1.1 Варинат начинается, в случае успешной авторизации
   1.2 Сервер пришлет список параметров для выбора.
## 2. Получить данные статистики
   1.1 После выбора параметров статистики, нажать кнопку "Получить статистику" и сервер отправит данные
   
