## Used technologies Spring + JDBC templates + JSP + Maven + JUnit

App represents a test task for junior manual QA engineers
Admins can create users and view user answers

To build app locally please run mvn -Dspring.profiles.active=dev tomcat7:run
and navigate to http://localhost:8082
in dev mode 2 users are present.
Admin user: admin@test.com pass:qwerty
Applicant user: user@test.com pass:qwerty

For dev purpose all new users will be created with default password 'qwerty'
