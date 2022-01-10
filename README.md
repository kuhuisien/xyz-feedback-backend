# XYZ Feedback Portal Service

This project builds REST APIs to be consumed by XYZ feedback portal.

Built with SpringBoot + MySQL.

Accessing the deployed API endpoints [here](https://xyz-feedback-app-1.herokuapp.com/xyz/).

### Running locally

* Open the project with any java IDE (e.g. Eclipse).
* Right Click FeedbackportalServiceRestApplication.java and choose 'Run as java application'.

### Deploy

* This project is deployed with Heroku with ClearDB MySQL Add-on.
* Log in to Heroku 

```bash
heroku login
```
* Key in the credential when prompted.
* Push the commited change to Heroku.

```bash
git push heroku master
```

* You may verify deployment by accessing logs.

```bash
heroku logs
```

* To connect to database, you may use Sequal Pro (mac) or MySql Workbench (Windows). 
Key in host, username and password respectively, which can be found in environment variable JDBC_DATABASE_URL.

```bash
heroku run -a [appName] printenv
```

## Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.6.2/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.6.2/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.6.2/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.6.2/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.6.2/reference/htmlsingle/#using-boot-devtools)

## Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)

