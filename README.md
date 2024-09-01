# SocialApp

**SocialApp**: A social media application where users can share posts, like posts, and leave comments. The project is a backend application developed with Spring Boot and provides basic social media functionalities such as user, post, and like management.

## Features

- **User Management**: Users can be created, updated, deleted, and viewed.
- **Post Management**: Users can create, update, and delete posts.
- **Like Management**: Users can like posts, and a user cannot like the same post more than once.
- **Comment Management**: Users can add comments to posts, view comments, and manage them.

## API Documentation

### User API

- **GET /users**: List all users
- **GET /users/{id}**: View a specific user
- **POST /users**: Create a new user
- **PUT /users/{id}**: Update an existing user
- **DELETE /users/{id}**: Delete a user

### Post API

- **GET /posts**: List all posts
- **GET /posts/{id}**: View a specific post
- **POST /posts**: Create a new post
- **PUT /posts/{id}**: Update an existing post
- **DELETE /posts/{id}**: Delete a post

### Like API

- **GET /likes**: List all likes
- **GET /likes/{id}**: View a specific like
- **POST /likes**: Create a new like
- **DELETE /likes/{id}**: Delete a like

### Technologies Used

- **Java 21**
- **Spring Boot**
- **Spring Data JPA**
- **PostgreSQL**
- **MapStruct**
- **Lombok**

These technologies support key factors such as performance, efficiency, and sustainability in the project.



## ----------------------------------------

# SocialApp

**SocialApp**: Kullanıcıların gönderi (post) paylaşabildiği, gönderilere beğeni (like) bırakabildiği ve yorum yapabildiği bir sosyal medya uygulamasıdır. Proje, Spring Boot ile geliştirilmiş bir backend uygulamasıdır ve kullanıcı, gönderi ve beğeni yönetimi gibi temel sosyal medya işlevlerini sağlar.

## Özellikler

- **Kullanıcı Yönetimi**: Kullanıcılar oluşturulabilir, güncellenebilir, silinebilir ve detayları görüntülenebilir.
- **Gönderi Yönetimi**: Kullanıcılar gönderi oluşturabilir, güncelleyebilir ve silebilir.
- **Beğeni Yönetimi**: Gönderilere beğeni bırakabilir, bir kullanıcının aynı gönderiye tekrar beğeni bırakması engellenir.
- **Yorum Yönetimi**: Gönderilere yorum ekleyebilir, yorumları görüntüleyebilir ve yönetebilir.

## API Dokümantasyonu

### Kullanıcı API

- **GET /users**: Tüm kullanıcıları listele
- **GET /users/{id}**: Belirli bir kullanıcıyı görüntüle
- **POST /users**: Yeni bir kullanıcı oluştur
- **PUT /users/{id}**: Var olan bir kullanıcıyı güncelle
- **DELETE /users/{id}**: Bir kullanıcıyı sil

### Gönderi API

- **GET /posts**: Tüm gönderileri listele
- **GET /posts/{id}**: Belirli bir gönderiyi görüntüle
- **POST /posts**: Yeni bir gönderi oluştur
- **PUT /posts/{id}**: Var olan bir gönderiyi güncelle
- **DELETE /posts/{id}**: Bir gönderiyi sil

### Beğeni API

- **GET /likes**: Tüm beğenileri listele
- **GET /likes/{id}**: Belirli bir beğeniyi görüntüle
- **POST /likes**: Yeni bir beğeni oluştur
- **DELETE /likes/{id}**: Bir beğeniyi sil

### Kullanılan Teknolojiler

- **Java 21**
- **Spring Boot**
- **Spring Data JPA**
- **PostgreSQL**
- **MapStruct**
- **Lombok**

Bu teknolojiler, projede performans, verimlilik ve sürdürülebilirlik gibi önemli faktörleri desteklemektedir.


