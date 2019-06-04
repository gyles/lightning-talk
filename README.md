# lightning-talk

## Quickstart Guide

To start the application, import the project in Eclipse. Run as Java Application, pointing to LightningTalkApplication as the main class.

Application will start in [http://localhost:8080/](http://localhost:8080/). You may login with any of the following pre-configured users:

| User             | Password         | Role        |
| ---------------- | ---------------- | ----------- |
| admin@think.com  | password         | User, Admin |
| user@think.com   | password         | User        |

To run the application tests,

```
./mvnw clean install
```

### Continuous Integration

Since the application can be packaged as a jar, it is very easy to setup CI/CD pipeline on any platform. As proof of concept, this app has been deployed to Heroku: [https://lightningtk.herokuapp.com](https://lightningtk.herokuapp.com).
