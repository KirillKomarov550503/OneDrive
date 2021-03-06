﻿# 1. Введение
## 1.1 Назначение
Данный документ содержит функциональные и нефункциональные требования к приложению "Cloud Drive", которые будут полезны всем, кто захочет поддерживать этот проект.
## 1.2 Бизнес-требования
### 1.2.1 Исходные данные
Наше современное общество построено на информации. Данные окружают нас каждый день в огромных количествах. Чтобы их систематизировать мы 
используем различные файлы для хранения информации. Это файлы мы храним на различных устройствах: компьютерах, телефонах, usb носителях. 
Но память этих устройств сильно ограничена, поэтому лучше хранить такие файлы в облачных хранилищах.
### 1.2.2 Возможности бизнеса
Данное приложение поможет хранить информацию удаленно на сервере, освободив память ваших устройств для действительно важных файлов и 
приложений, которыми вы пользуетесь ежедневно. У вас будет возможность получить доступ к вашим файлам в любое время и в любом месте, где
есть Интернет-соединение. Также это приложение поможет вам избежать потери важных данных в случае поломки ваших устройств, так как вы 
сможете в любой момент загрузить эти данные с сервера обратно себе на устройства.
### 1.3 Аналоги
Google.Disk - это файловый хостинг, созданный и поддерживаемый компанией Google. Его функции включают хранение файлов в Интернете, общий доступ к ним и совместное редактирование.
Yandex.Disk - облачный сервис, принадлежащий компании Яндекс, позволяющий пользователям хранить свои данные на серверах в «облаке» и передавать их другим пользователям в Интернете. Работа построена на синхронизации данных между различными устройствами.
OneDrive - облачное хранилище, созданное в августе 2007 года и управляемое компанией Microsoft. Является частью спектра онлайновых услуг Windows Live.
DropBox - файловый хостинг компании Dropbox Inc., включающий персональное облачное хранилище, синхронизацию файлов и программу-клиент. Dropbox позволяет пользователям создать специальную папку на своих компьютерах, которую Dropbox синхронизирует таким образом, что она имеет одинаковое содержимое независимо от того, какое устройство используется для просмотра.

# 2. Требования пользователя
## 2.1 Программные интерфейсы
Данное приложение будет реализовано с использованием JSP, CSS, JavaScript для front-end. Для back-end будет использоваться Spring framework. Приложение будет построено на микросервисной архитектуре. Сервером базы данных будет выступать PostgreSQL.

## 2.2 Интерфейс пользователя
Модель интерфейса хранится в папке Mockups
При открытии приложения пользователь попадает на главную страницу своего диска с возможностью скачать необходимые файлы:
![Files list](https://github.com/KirillKomarov550503/OneDrive/blob/master/Mockups/MyDisk.png)

Гость же при открытии сайта попадет на главную страницу с приветственной речью:
![Log out](https://github.com/KirillKomarov550503/OneDrive/blob/master/Mockups/Welcome.png)

На главной странице ему будет предложено пройти регистрацию:
![Registration](https://github.com/KirillKomarov550503/OneDrive/blob/master/Mockups/Registration.png)

Или авторизацию:
![Authorization](https://github.com/KirillKomarov550503/OneDrive/blob/master/Mockups/Authorization.png)

Пользователь может добавить файл на свой "диск":
![Upload](https://github.com/KirillKomarov550503/OneDrive/blob/master/Mockups/Add.png)

Пользователь может удалить файл с "диска":
![Remove](https://github.com/KirillKomarov550503/OneDrive/blob/master/Mockups/Remove.png)

Администратор может получить статистику:
![Statistic](https://github.com/KirillKomarov550503/OneDrive/blob/master/Mockups/Statictics.png)

## 2.3 Характеристики пользователей
Студенты, которые могут хранить свои лабораторные, курсовые и дипломные работы, различную методическую литературу.
Представители бизнеса, которые хотят хранить важные документы в безопасности и всегда иметь к ним доступ.
Различные организации, которым не хватает мощностей для хранения данных у себя на серверах.

# 3. Системные требования
## 3.1 Функциональные требования
### 3.1.1 Для Гостя:
	1. Регистрация.
	2. Авторизация.
### 3.1.2 Для Пользователя:
	1. Скачать файлы с "диска".
	2. Загрузить файлы с "диска".
	3. Просмотреть список файлов на "диске".
	4. Удалить выбранные файлы с "диска".
### 3.1.3 Для Администратора:
	1. Получение различной статистики.
## 3.2 Нефункциональные требования
### 3.2.1 Атрибуты качества
#### 3.2.1.1 Требования по удобству использования
Все пользователи должны будут сразу же освоить данное приложение, так как интерфейс является интуитивно понятным.
#### 3.2.1.2 Требования по безопасности и надежности
а) На различных этапах работы программы будет происходить валидация данных.
б) Пользователь сможет получить доступ только к своему "диску" и соответственно только к своим файлам.
#### 3.2.1.3 Ограничения
Для работы с приложением требуется наличие интернет-соединения.
