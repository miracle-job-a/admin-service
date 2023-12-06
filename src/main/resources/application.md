spring:
  datasource:
    url: ${DB_url}
    username: ${DB_username}
    password: ${DB_password}
    driver-class-name: com.mysql.cj.jdbc.Driver

  application:
    name: adminService

  jpa:
    hibernate:
      ddl-auto: validate
      show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQLDialect
        format_sql: true

server:
  port: 60003