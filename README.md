## Sample Java app using Spring and Hibernate. Deployed to Heroku.

## To run this app localy

First you need to create data base schema. SQL queries can be found src/main/resources/sql

Also you need  [heroku-cli](https://devcenter.heroku.com/articles/heroku-cli "Direct link") to run app locally

create `.env` file and place data base environment variables
`JDBC_DATABASE_URL`
`JDBC_DATABASE_USERNAME`
`JDBC_DATABASE_PASSWORD`
`GMAIL_USER_NAME`
`GMAIL_USER_PASSWORD`
`MAIL_RECIPIENTS`

to compile run `mvn package`
to run heroku app local `heroku local -f Procfile.local`

open [http://localhost:8083/](http://localhost:8083/ "Direct link")