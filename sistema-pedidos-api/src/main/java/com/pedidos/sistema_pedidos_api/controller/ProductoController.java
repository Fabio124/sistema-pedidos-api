package com.pedidos.sistema_pedidos_api.controller;


import com.pedidos.sistema_pedidos_api.model.Producto;
import com.pedidos.sistema_pedidos_api.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/producto")
public class ProductoController {

    private ProductoService productoService;
    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // // GET /api/cliente -> listar todas read
    @GetMapping
    public ResponseEntity<List<Producto>>listarTodo(){
        List<Producto>productos=productoService.listarTodo();

        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    // GET /api/producto/{id} -> buscar una
    @GetMapping("/{id}")
    public ResponseEntity<?>buscarid(@PathVariable Long id){
        Producto producto = productoService.buscarPorId(id);
        if (producto== null){
            return new ResponseEntity<>("Producto no encontrado", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(producto,HttpStatus.OK);


    }

    // POST /api/herramientas -> crear
    @PostMapping
    public  ResponseEntity<?>crear(@Valid @RequestBody Producto producto){

        Producto nuevoProducto = productoService.crear(producto);
        if(nuevoProducto== null){
            return new ResponseEntity<>("Datos invalidos", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);

    }

    // PUT /api/herramientas/{id} -> actualizar
    @PutMapping("/{id}")
    public  ResponseEntity<?>acualizar(@PathVariable Long id,
                                       @RequestBody Producto producto){
        // 1. Llamamos al servicio para que intente actualizar

        Producto actualizarProducto= productoService.actualizar(id, producto);

        // 2. Si el servicio devuelve null (o puedes usar excepciones), respondemos que no existe
        if(actualizarProducto==null) {
            return  new ResponseEntity<>("Producto no encontrado con ID: "+id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(actualizarProducto, HttpStatus.OK);
    }

    // DELETE /empleados/A001 → elimina
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>eliminar(@PathVariable Long id){
        boolean eliminado= productoService.eliminar(id);

        if (!eliminado) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
