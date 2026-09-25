package com.pedidos.sistema_pedidos_api.service;



import com.pedidos.sistema_pedidos_api.model.Producto;
import com.pedidos.sistema_pedidos_api.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductoService {
    private final ProductoRepository productoRepository;

    @Autowired

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    // Crear una nueva
    public Producto crear(Producto producto){
        if (producto==null || !validarDatos(producto)){
            return null;
        }

        return productoRepository.save(producto);

    }
    // Listar todas
    public List<Producto>listarTodo(){
        return productoRepository.findAll();
    }
    // Buscar  por id
    public Producto buscarPorId(Long id){
        return productoRepository.findById(id).orElse(null);
    }
    // Actualizar una existente
    public Producto actualizar(Long id , Producto datosActualizados){
        if (!validarDatos(datosActualizados)){
            return null;
        }
        //buscar si existe el product
        Producto producto =  productoRepository.findById(id).orElse(null);
        //verificr que existe
        if (producto != null){
            //actualizamos los datos

            producto.setNombre(datosActualizados.getNombre());
            producto.setCategoria(datosActualizados.getCategoria());
            producto.setMarca(datosActualizados.getMarca());
            producto.setDescripcion(datosActualizados.getDescripcion());
            producto.setCantidadDisponible(datosActualizados.getCantidadDisponible());
            producto.setPrecio(datosActualizados.getPrecio());
            return productoRepository.save(producto);
        }
        return null;
    }
    public  boolean  eliminar(Long id){
        Producto producto = buscarPorId(id);
        if(producto== null){
            return false;
        }
        productoRepository.deleteById(id);
        return  true;
    }

    public boolean validarDatos(Producto produto){
        if(produto.getNombre()==null || produto.getNombre().isBlank()){
            return  false;
        }
        if (produto.getCategoria()==null || produto.getCategoria().isBlank()){
            return  false;
        }
        if (produto.getMarca()==null || produto.getMarca().isBlank()){
            return  false;
        }
        if (produto.getPrecio()==null || produto.getPrecio().compareTo(BigDecimal.ZERO)<=0){
            return  false;
        }
        return true;

    }
}
