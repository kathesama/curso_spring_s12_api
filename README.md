# curso_spring_s12_api


3 tips para compilar exitosamente el .jar
1. En el pom se debe agregar el siguiente plugin:
```xml
    <properties>
        <start-class> -> <groupIdName>.<artifactIdName>.<MAIN_class_name> </start-class>
    </properties>
    <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
        <configuration>
            <mainClass>${start-class}</mainClass>
            <layout>JAR</layout>
        </configuration>
        <executions>
            <execution>
                <goals>
                    <goal>repackage</goal>
                </goals>
            </execution>
        </executions>
    </plugin>
```
Esto genera el manifest que será incorporado en el .jar y asi no arroja el error de *no manifest on jar*
2. Al momento de compilar el jar, siempre que el application.properties o .yml tenga parámetros que deben ser seteados
se ejecuta de la siguiente manera:
Supongamos que tenemos el siguiente application.yml que requiere tres parámetros de configuración
```agsl
spring:
  main:
    log-startup-info: false
  application:
    name: curso_spring_s12_web_api_restful
  jackson:
    default-property-inclusion: non_null
    property-naming-strategy: SNAKE_CASE
  datasource:
    url: ${DB_HOST}
    username: ${DB_USER}
    password: ${DB_PASSWORD}
    driver-class-name: com.mysql.cj.jdbc.Driver
  jpa:
    database-platform: org.hibernate.dialect.MySQLDialect
    show-sql: true
    hibernate:
      ddl-auto: update
logging:
  level:
    org.hibernate.SQL: debug
```
Al momento de compilar se ejecuta así, por ejemplo:

`mvn clean package -DDB_HOST=jdbc:mysql://localhost:3306/mytest -DDB_USER=root -DDB_PASSWORD=123456`

3. Luego para ejecutar el .jar se ejecutaría así:

`java -jar .\target\curso_spring_s12_api-0.0.1-SNAPSHOT.jar --DB_HOST=jdbc:mysql://localhost:3306/mytest --DB_USER=root --DB_PASSWORD=123456`

**Nota**: AL momento de conectarte desde windows a una instancia de EC2 en AWS te puede dar error al hacer ssh con la
instancia usando un certificado .pem desde el power shell, ese problema se debe a permisos de escritura de tu
usuario de windows sobre el archivo, para corregirlo haz lo siguiente:

> In windows you can go to the properties of the pem file, and go to the security tab, then to advance button.
remove inheritance and all the permissions. then grant yourself the full control. after all SSL will not give you the same error again.

Para linux hay otras soluciones en el mismo hilo.

Fuente: [unprotected-private-key-file-error-using-ssh-into-amazon-ec2-instance-aws](https://stackoverflow.com/questions/8193768/unprotected-private-key-file-error-using-ssh-into-amazon-ec2-instance-aws)


Y eso sería todo.