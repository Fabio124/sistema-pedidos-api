package com.pedidos.sistema_pedidos_api.service;

import com.pedidos.sistema_pedidos_api.dto.DetalleRequestDTO;
import com.pedidos.sistema_pedidos_api.dto.PedidoRequestDTO;
import com.pedidos.sistema_pedidos_api.model.Cliente;
import com.pedidos.sistema_pedidos_api.model.DetallePedido;
import com.pedidos.sistema_pedidos_api.model.Pedido;
import com.pedidos.sistema_pedidos_api.model.Producto;
import com.pedidos.sistema_pedidos_api.repository.ClienteRepository;
import com.pedidos.sistema_pedidos_api.repository.DetallePedidoRepository;
import com.pedidos.sistema_pedidos_api.repository.PedidoRepository;
import com.pedidos.sistema_pedidos_api.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PedidoService {
    private  final PedidoRepository pedidoRepository;
    private  final DetallePedidoRepository detallePedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProductoRepository productoRepository;

    @Autowired

    public PedidoService(PedidoRepository pedidoRepository, DetallePedidoRepository
                                     detallePedidoRepository,
                                    ClienteRepository clienteRepository,
                                    ProductoRepository productoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.detallePedidoRepository = detallePedidoRepository;
        this.clienteRepository = clienteRepository;
        this.productoRepository = productoRepository;


    }
    public Pedido crear(PedidoRequestDTO pedidoRequestDTO){

        // Paso 1: buscar el cliente, verificar que existe
        Cliente cliente =clienteRepository.findById(pedidoRequestDTO.getClienteId()).orElse(null);
        if (cliente==null){
            return  null;
        }
        // Paso 2: crear el Pedido (todavía sin guardar)
        Pedido pedido= new Pedido();
        pedido.setFecha(LocalDate.now());
        pedido.setCliente(cliente);
        pedido.setEstado("Pendiente");
        // Paso 3: guardar el Pedido primero, para que tenga un id

        Pedido pedidoGuardado = pedidoRepository.save(pedido);

        // Paso 4: recorrer cada detalle que llegó en el DTO
        for(DetalleRequestDTO detalleDTO : pedidoRequestDTO.getDetalles()){

            // buscar el producto correspondiente
            Producto producto = productoRepository.findById(detalleDTO.getProductoId()).orElse(null);
            
            if (producto == null) {
                continue; // si el producto no existe, lo saltamos (podríamos tamb  ién cancelar todo, lo vemos después)
            }

            // crear el detalle y asociarlo al pedido ya guardado
            DetallePedido detalle = new DetallePedido();
            detalle.setPedido(pedidoGuardado);
            detalle.setProducto(producto);
            detalle.setCantidad(detalleDTO.getCantidad());

            detallePedidoRepository.save(detalle);
        }

        return pedidoGuardado;

    }

    public Pedido buscarPorId(Long id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    public List<Pedido> listarTodo(){
        return pedidoRepository.findAll();
    }
}
