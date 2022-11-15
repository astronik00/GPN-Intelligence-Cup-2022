FROM openjdk:19
ADD out/artifacts/SimpleVKApp_jar /home
WORKDIR /home
ENV PORT 8080
EXPOSE 8080
CMD ["java", "-jar", "/home/SimpleVKApp.jar"]