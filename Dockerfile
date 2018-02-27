FROM openjdk:latest
MAINTAINER Sunil Khobragade (Sunil.Khobragade@cognizant.com)
COPY ./build/libs/spring-music.jar /usr/src/spring-music/
WORKDIR /usr/src/spring-music
EXPOSE 8080
CMD ["java", "-jar", "spring-music.jar"]