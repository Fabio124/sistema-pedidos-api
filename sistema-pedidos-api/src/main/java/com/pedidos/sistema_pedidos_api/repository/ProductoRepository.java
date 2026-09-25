package com.pedidos.sistema_pedidos_api.repository;

import com.pedidos.sistema_pedidos_api.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Long> {
}
