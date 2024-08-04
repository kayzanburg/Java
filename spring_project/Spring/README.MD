# Spring ile SIGNUP Projesi

Bu proje, kullanıcı kaydetmek için bir Spring Boot uygulamasıdır. Verilerini saklamak için Oracle Veritabanı kullanır ve kullanıcı girişi için basit bir web arayüzü sağlar.

1. **Web tarayıcısı:**

- Web tarayıcısı `http://localhost:8080/register` adresi:

    ![homepage](./images/homepage.png)

## Endpointler

- **GET /register**

    Abone kayıt formunu görüntüler.

- **POST /api/signUp**

    Kayıt formunu gönderir ve abone verilerini veritabanına kaydeder.

## Çıktılar

- Register butonuna bastığında:

    ![başarılı](./images/success.png)

- SQL Veri Tabanı:

    ![sql](./images/sql.png)
