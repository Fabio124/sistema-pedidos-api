package com.pedidos.sistema_pedidos_api.service;

import com.pedidos.sistema_pedidos_api.model.Cliente;
import com.pedidos.sistema_pedidos_api.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }


    // Crear una nueva
    public Cliente crear(Cliente cliente){

        if(cliente== null || !validarDatos(cliente)){
            return null;
        }
        return clienteRepository.save(cliente);

    }
    // Listar todas
    public List<Cliente>listarTodo(){

        return clienteRepository.findAll();
    }

    // Buscar  por id
    public  Cliente buscarPorId(Long id){

        return clienteRepository.findById(id).orElse(null);
    }
    // Actualizar una existente
    public  Cliente actualizar (Long id, Cliente datosActualizados){
        if(!validarDatos(datosActualizados)){
            return null;
        }
        //buscar si existe el product
        Cliente clientes =clienteRepository.findById(id).orElse(null);
        //verificr que existe
        if (clientes !=null){

            //actualizamos los datos
            clientes.setNombre(datosActualizados.getNombre());
            clientes.setCedula(datosActualizados.getCedula());
            clientes.setCorreo(datosActualizados.getCorreo());
            clientes.setTelefono(datosActualizados.getTelefono());

            return clienteRepository.save(clientes);

        }
        return null;
    }

    public boolean eliminar(Long id){
        Cliente cliente=buscarPorId(id);

        if(cliente==null){
            return false;
        }
        clienteRepository.deleteById(id);
        return true;
    }
    public boolean validarDatos(Cliente cliente){
        if (cliente.getNombre()== null || cliente.getNombre().isBlank()){
            return false;
        }
        if (cliente.getCedula()== null || cliente.getCedula().isBlank()){
            return  false;
        }
        if (cliente.getCorreo()==null || cliente.getCorreo().isBlank()){
            return false;
        }
        if (cliente.getTelefono()==null || cliente.getTelefono().isBlank()){
            return  false;
        }
        return true;

    }

}
