package com.pedidos.sistema_pedidos_api.controller;

import com.pedidos.sistema_pedidos_api.dto.PedidoRequestDTO;
import com.pedidos.sistema_pedidos_api.model.Pedido;
import com.pedidos.sistema_pedidos_api.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedido")
public class PedidoController {


    private PedidoService pedidoService;

    @Autowired
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }
    @GetMapping
    public ResponseEntity<List<Pedido>> listarTodo() {
        List<Pedido> pedidos = pedidoService.listarTodo();
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }

    //buscar por id

    @GetMapping("/{id}")
    public  ResponseEntity<?> buscarPorId(@PathVariable Long id) {

        Pedido pedido = pedidoService.buscarPorId(id);
        if (pedido == null) {
            return new ResponseEntity<>("Pedido no encontrado",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pedido, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<?>crear(@RequestBody PedidoRequestDTO pedidoRequestDTO){
        Pedido nuevoPedido = pedidoService.crear(pedidoRequestDTO);

        if (nuevoPedido == null) {

            return new ResponseEntity<>("Datos invalidos", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(nuevoPedido, HttpStatus.CREATED);
    }
}
