Library App
Опис проекту
Library App - це Spring Boot веб-додаток для управління бібліотекою, який дозволяє здійснювати операції з книгами, авторами та жанрами. Додаток надає повний набір REST API endpoints для маніпуляції даними про книжковий фонд.
Функціональні вимоги
Книги (Books)

Створення нової книги
Перегляд списку всіх книг
Отримання книги за унікальним ідентифікатором
Оновлення інформації про книгу
Видалення книги

Автори (Authors)

Створення нового автора
Перегляд списку всіх авторів
Отримання автора за унікальним ідентифікатором
Пошук авторів за іменем
Оновлення інформації про автора
Видалення автора
Отримання книг конкретного автора

Жанри (Genres)

Створення нового жанру
Перегляд списку всіх жанрів
Отримання жанру за унікальним ідентифікатором
Пошук жанрів за назвою
Оновлення інформації про жанр
Видалення жанру
Отримання книг за назвою жанру

REST API Endpoints
Книги

POST /api/books - Створити нову книгу
GET /api/books - Отримати список усіх книг
GET /api/books/{id} - Отримати книгу за ID
PUT /api/books/{id} - Оновити книгу
DELETE /api/books/{id} - Видалити книгу

Автори

POST /api/authors - Створити нового автора
GET /api/authors - Отримати список усіх авторів
GET /api/authors/{id} - Отримати автора за ID
GET /api/authors/search?name={name} - Пошук авторів за іменем
PUT /api/authors/{id} - Оновити автора
DELETE /api/authors/{id} - Видалити автора
GET /api/authors/{id}/books - Отримати книги автора за його ID
GET /api/authors/books/{authorName} - Отримати книги за іменем автора

Жанри

POST /api/genres - Створити новий жанр
GET /api/genres - Отримати список усіх жанрів
GET /api/genres/{id} - Отримати жанр за ID
GET /api/genres/search?name={name} - Пошук жанрів за назвою
PUT /api/genres/{id} - Оновити жанр
DELETE /api/genres/{id} - Видалити жанр
GET /api/genres/books/{genreName} - Отримати книги за назвою жанру
