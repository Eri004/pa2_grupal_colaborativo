package uce.edu.ec.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.ec.domain.model.Cliente;
import uce.edu.ec.infraestructure.repository.ClienteRepositoryImpl;

@ApplicationScoped
@Transactional
public class ClienteService {
    @Inject
    private ClienteRepositoryImpl cri;

    public Cliente buscarPorCedula(String cedula){
        return this.cri.buscarPorCedula(cedula);
    }

    public boolean validarCliente(String cedula){
        if(this.buscarPorCedula(cedula) == null){
            System.out.println("No se encontro al cliente con la cedula: " + cedula);
            return false;
        }else{
            return true;
        }
    }
    public void guardar(Cliente cliente){
        this.cri.persist(cliente);
    }

    public void actualizar(String cedula, Cliente cliente){
        Cliente clienteBase = this.buscarPorCedula(cedula);
        clienteBase.setCiudad(cliente.getCiudad());
        clienteBase.setCorreo(cliente.getCorreo());
        clienteBase.setVendedor(cliente.getVendedor());
        clienteBase.setReservas(cliente.getReservas());
        clienteBase.setNombre(cliente.getNombre());
    }
    public void eliminar(Integer id){
        this.cri.deleteById(id);
    }


}
