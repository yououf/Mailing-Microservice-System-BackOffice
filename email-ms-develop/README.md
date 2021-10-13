Hello World !!

Welcome to Novelis Mailing MS - API REST 

########## Once the tables are generated

# Run following SQL insert statements
# INSERT INTO roles(name) VALUES('ROLE_USER');
# INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
# INSERT INTO roles(name) VALUES('ROLE_ADMIN');

#######################################################

server back-end port 8080

db port 3308

server front-end port 3000 

############################# About Service of Novelis MS ##########################################

the service consumed by the other microservices is that of the MailDTOController 

which serves as a startup and boot point to intercept the dto mail received and trigger the process

####################################################################################################

### change the server port if you don't want run the application on this port number ,

### but if you change the port number please make you sychronize between front end and back end port numbers

server.port=8080

spring.jackson.serialization.FAIL_ON_EMPTY_BEANS=false

############################## -->Begin Spring JPA Hibernate Configuration ##########################

spring.datasource.driver-class-name=com.mysql.jdbc.Driver

spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect

# after creating tables and the beans factory is on you can comment create line

spring.jpa.hibernate.ddl-auto=update

# for the first time running make create line active

#spring.jpa.hibernate.ddl-auto=create

spring.jpa.show-sql=true

spring.datasource.url=jdbc:mysql://localhost:3308/pfa2020_database?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC

spring.datasource.username=root

spring.datasource.password=


#spring.resources.add-mappings=true

#spring.jpa.properties.javax.persistence.schema-generation.scripts.action=create

#spring.jpa.properties.javax.persistence.schema-generation.scripts.create-target=create.sql

#spring.jpa.properties.javax.persistence.schema-generation.scripts.create-source=metadata


#javax.persistence.schema-generation.create-source=metadata

#javax.persistence.schema-generation.drop-source=metadata


############################## -->End Spring JPA Hibernate Configuration ############################

############################## -->Begin Spring Mail Configuration ###################################

#Make sure less secure App is enabled at link https://myaccount.google.com/lesssecureapps

spring.mail.host = smtp.gmail.com

spring.mail.username = mailing.ms.novelis.broadcaster@gmail.com

spring.mail.password = ******************


#Below Properties are needed for maintaining the session.

#Two Step verification should not be enabled for gmaill.


spring.mail.properties.mail.smtp.auth = true

spring.mail.properties.mail.smtp.socketFactory.port = 465

spring.mail.properties.mail.smtp.socketFactory.class = javax.net.ssl.SSLSocketFactory

spring.mail.properties.mail.smtp.socketFactory.fallback = false

spring.mail.properties.mail.smtp.ssl.enable=true

spring.mail.port=465

spring.mail.properties.mail.smtp.starttls.enable=true


#### Important don't forget disable your Antivirus !!!! in my case i'm disabling Avast and it's working Perfect :)

############################## -->End Spring Mail Configuration ###################################

############################## --->Begin JWT Authentification parameters and properties #################

# App Properties

novelis.ms.app.jwtSecret= NovelisMSSecretKey

novelis.ms.app.jwtExpirationMs= 86400000


############################## --->End JWT Authentification parameters and properties #################


i will add all routes here i'm waiting just to finalize the project ....

