package com.pedidos.sistema_pedidos_api.controller;

import com.pedidos.sistema_pedidos_api.model.Cliente;
import com.pedidos.sistema_pedidos_api.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/cliente")
public class ClienteController {


    private ClienteService clienteService;
    @Autowired

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // // GET /api/cliente -> listar todas read
    @GetMapping
    public ResponseEntity<List<Cliente>> listarTodo(){
        List<Cliente>cliente=clienteService.listarTodo();
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    // GET /api/cliente/{id} -> buscar una
    @GetMapping("/{id}")
    public ResponseEntity<Cliente>buscarId(@PathVariable Long id){
        Cliente cliente =clienteService.buscarPorId(id);
        if (cliente==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cliente,HttpStatus.OK);
    }

    // POST /api/herramientas -> crear
    @PostMapping
    public  ResponseEntity<?>crear(@Valid @RequestBody Cliente cliente){
    Cliente nuevoCliente = clienteService.crear(cliente);
    if (nuevoCliente==null){
        return new ResponseEntity<>("Datos invalidos", HttpStatus.BAD_REQUEST);
    }
    return  new ResponseEntity<>(nuevoCliente,HttpStatus.CREATED);

    }

    // PUT /api/herramientas/{id} -> actualizar

    @PutMapping("/{id}")
    public ResponseEntity<?>actulizar(@PathVariable Long id,
                                      @RequestBody Cliente cliente) {

        // 1. Llamamos al servicio para que intente actualizar
        Cliente clienteactualizado= clienteService.actualizar(id, cliente);
        // 2. Si el servicio devuelve null (o puedes usar excepciones), respondemos que no existe
        if(clienteactualizado==null) {
            return new  ResponseEntity<>("Cliente no encontrado con el id: "+id , HttpStatus.NOT_FOUND);

        }
        // 3. Si todo sale bien, devolvemos el cliente actualizado con código 200 (OK)
        return new ResponseEntity<>(clienteactualizado, HttpStatus.OK);



    }

    // DELETE /empleados/A001 → elimina
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>eliminar(@PathVariable Long id){
        boolean eliminado= clienteService.eliminar(id);

        if (!eliminado) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
