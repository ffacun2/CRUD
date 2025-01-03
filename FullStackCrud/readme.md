# API REST CRUD con Spring Boot y React

Este proyecto es una aplicación CRUD (Crear, Leer, Actualizar, Eliminar) que utiliza Spring Boot para el backend y React para el frontend. La aplicación permite gestionar una lista de personas, almacenando la información en una base de datos MySQL.

## Estructura del Proyecto

El proyecto está dividido en dos partes principales:

- `apiResCrud/`: Contiene el backend desarrollado con Spring Boot.
- `front-ApiRestCrud/`: Contiene el frontend desarrollado con React.

### Backend (apiResCrud)

#### Estructura de Directorios
```
apiResCrud/ 
├── .env 
├── .env.template 
├── .gitattributes 
├── .gitignore 
├── HELP.md 
├── mvnw 
├── mvnw.cmd 
├── pom.xml 
├── src/ 
│ ├── main/ 
│ │ ├── java/ 
│ │ │ ├── com/apiRestCrud/apiResCrud/ 
│ │ │ │ ├── ApiResCrudApplication.java 
│ │ │ ├── controller/ 
│ │ │ │ ├── PersonaController.java 
│ │ │ ├── exception/ 
│ │ │ │ ├── DataBaseException.java 
│ │ │ ├── model/ 
│ │ │ │ ├── Persona.java 
│ │ │ ├── repository/ 
│ │ │ │ ├── PersonaRepository.java 
│ │ ├── resources/ 
│ │ │ ├── application.properties 
│ ├── test/ 
│ │ ├── java/ 
│ │ │ ├── com/apiRestCrud/apiResCrud/ 
│ │ │ │ ├── ApiResCrudApplicationTests.java
```
#### Archivos Principales

- `ApiResCrudApplication.java`: Clase principal que inicia la aplicación Spring Boot.
- `PersonaController.java`: Controlador REST que maneja las operaciones CRUD para las personas.
- `Persona.java`: Entidad JPA que representa una persona en la base de datos.
- `PersonaRepository.java`: Repositorio JPA para acceder a los datos de las personas.
- `application.properties`: Archivo de configuración de Spring Boot.

#### Configuración

1. Clona el repositorio.
2. Configura la base de datos en el archivo `.env`:
3. ```
    SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/crud
    SPRING_DATASOURCE_USERNAME=root
    SPRING_DATASOURCE_PASSWORD=
    SPRING_DATASOURCE_DB=crud
    ```
4. Ejecuta el siguiente comando para iniciar la aplicación:
   ``` ./mvnw spring-boot:run```

### Frontend (front-ApiRestCrud)

#### Estructura de Directorios
```
front-ApiRestCrud/ 
├── .gitignore 
├── eslint.config.js 
├── index.html 
├── package.json 
├── public/ 
├── src/ 
│ ├── asdasd.css 
│ ├── assets/ 
│ ├── components/ 
│ │ ├── BodyComponent.jsx 
│ │ ├── CreateComponent.jsx 
│ │ ├── HeaderComponent.jsx 
│ │ ├── TablaComponent.jsx 
│ ├── main.jsx 
│ ├── services/ 
│ │ ├── ClienteService.js 
├── vite.config.js
```
#### Archivos Principales

- `main.jsx`: Punto de entrada de la aplicación React.
- `HeaderComponent.jsx`: Componente de cabecera.
- `BodyComponent.jsx`: Componente principal que contiene el formulario de creación y la tabla de personas.
- `CreateComponent.jsx`: Componente para crear y editar personas.
- `TablaComponent.jsx`: Componente que muestra la lista de personas en una tabla.
- `ClienteService.js`: Servicio que maneja las llamadas a la API REST.

#### Configuración

1. Clona el repositorio.
2. Instala las dependencias: ```   npm install```
3. Inicia la aplicación: ```   npm run dev```
