# CRUD

## Descripción

Esta es una aplicación de escritorio desarrollada en **Java** utilizando el patrón **MVC** (Modelo-Vista-Controlador) para gestionar operaciones de **Crear**, **Leer**, **Actualizar** y **Eliminar** (CRUD) sobre un conjunto de datos almacenados en una base de datos **SQL**.

La interfaz gráfica de usuario (GUI) está implementada con **Swing**, lo que permite una experiencia interactiva para gestionar datos de personas.

## Características

- Crear nuevos registros en la base de datos.
- Leer y visualizar registros existentes en una tabla.
- Actualizar información de registros seleccionados.
- Eliminar registros seleccionados.
- Validaciones en los campos de entrada para garantizar integridad de datos.
- Uso de patrones de diseño:
  - **DAO** (Data Access Object): Para gestionar las operaciones de acceso a la base de datos.
  - **MVC** (Modelo-Vista-Controlador): Para separar responsabilidades entre lógica de negocio, interfaz y control.
  - **Singleton**: Para garantizar una unica instancia de la conexion a la base de datos.

## Tecnologías utilizadas

- **Java SE** (Swing para la GUI)
- **JDBC** (Java Database Connectivity) para la conexión con la base de datos.
- **MySQL** como sistema de gestión de base de datos.
- **Git** para el control de versiones.
 
