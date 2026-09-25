package com.pedidos.sistema_pedidos_api.repository;

import com.pedidos.sistema_pedidos_api.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
