# SimpleVKApp
Rest server that works with VK API, created as a solution for GPN Intelligence Cup

## How to run
You can run application in two ways: run in IDE or run [Dockerfile](Dockerfile) and open one in the container. To run Dockerfile you need to generate 'out' folder and get 'Application.jar' file. 

If you want to make JUnit tests work, than don't forget to open **'application.properties'** file and write your service token to **'vk.api.config.access_token'** variable.

## Tasks
### Required tasks
- [x] Data validation
- [x] Exceptions handling
- [x] OpenAPI 3.0 [description](restDocs.yaml) for REST-methods
- [x] JUnit tests

### Additional tasks
- [x] Basic auth
- [x] Caching
- [ ] Build and deyployment in cloud (Does Docker container count..?)
## Technologies
- JDK 19
- Spring boot 2.7.5
- Jackson 2.14.0
- Apache HttpClient 4.5.13

