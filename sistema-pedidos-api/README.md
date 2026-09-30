# Sistema de Pedidos - Spring Boot

API REST hecha con Spring Boot para manejar pedidos de una tienda: clientes, productos, y pedidos que relacionan varios productos con sus cantidades. La hice como práctica para seguir reforzando Spring Boot, esta vez con relaciones entre tablas, DTOs y JPA más a fondo.

## De qué se trata

El sistema permite registrar clientes y productos, y crear pedidos asociando un cliente con varios productos y sus cantidades. El total de cada pedido se calcula a partir del precio y la cantidad de cada producto, no se guarda fijo en la base de datos.

## Tecnologías

- Java 24
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Docker (para la base de datos)
- Maven
- Bean Validation
- Postman para probar los endpoints

## Arquitectura

Controller -> Service -> Repository -> Base de datos

Cuatro entidades: Cliente, Producto, Pedido y DetallePedido. Un Pedido pertenece a un Cliente, y tiene varios DetallePedido, cada uno apuntando a un Producto con su cantidad.

## Modelo

Cliente: nombre, cedula, telefono, correo

Producto: nombre, marca, categoria, precio, cantidadDisponible, descripcion

Pedido: fecha, estado, cliente (relación)

DetallePedido: cantidad, pedido (relación), producto (relación)

## Endpoints

Cliente:
- POST /api/cliente - crea un cliente
- GET /api/cliente - lista todos
- GET /api/cliente/{id} - trae uno por id
- PUT /api/cliente/{id} - actualiza uno
- DELETE /api/cliente/{id} - elimina uno

Producto:
- POST /api/producto - crea un producto
- GET /api/producto - lista todos
- GET /api/producto/{id} - trae uno por id
- PUT /api/producto/{id} - actualiza uno
- DELETE /api/producto/{id} - elimina uno

Pedido:
- POST /api/pedido - crea un pedido completo (con cliente y productos)
- GET /api/pedido - lista todos
- GET /api/pedido/{id} - trae uno por id

Un pedido no se edita ni se elimina por ahora, solo se crea y se consulta.

Ejemplo de lo que se manda para crear un pedido:

```json
{
    "clienteId": 1,
    "detalles": [
        { "productoId": 1, "cantidad": 3 },
        { "productoId": 2, "cantidad": 2 }
    ]
}
```

El clienteId y los productoId tienen que ser de clientes y productos que ya existan (creados antes con sus respectivos endpoints).

## Cómo correrlo

Necesitas Java 24, Maven y Docker (para la base de datos).

1. Clona el repo:
```bash
git clone https://github.com/Fabio124/sistema-pedidos-api
cd sistema-pedidos-api
```

2. Levanta PostgreSQL con Docker y crea una base de datos llamada pedidos_db.

3. Revisa que el application.properties tenga la conexión bien puesta:
   spring.datasource.url=jdbc:postgresql://localhost:5432/ferreteria_db
   spring.datasource.username=admin
   spring.datasource.password=admin123
   (son credenciales de desarrollo local, solo para probar el proyecto)
4. Corre la aplicación:
```bash
./mvnw spring-boot:run
```

5. Queda corriendo en http://localhost:8080

## Notas

Las tablas se crean solas cuando corres la app por primera vez, gracias a Hibernate (ddl-auto=update).

No usé DTOs para todo, solo para crear el pedido, que es donde tenía sentido separar lo que se recibe de la API de cómo se guarda en la base de datos.

## Autor

Fabio Castillo Barrera

GitHub: https://github.com/Fabio124
LinkedIn: https://linkedin.com/in/fabio-castillo-barrera/