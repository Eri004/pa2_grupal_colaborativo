package ec.edu.uce.application.service;

import java.util.List;

import ec.edu.uce.domain.model.Cliente;
import ec.edu.uce.infraestructure.repository.ClienteRepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@Transactional
@ApplicationScoped
public class ClienteService {

    @Inject
    private ClienteRepositoryImpl clientRepo;

    public void guardar(Cliente cliente){
        this.clientRepo.persist(cliente);
    }

    public Cliente buscarPorId(Integer id){
        return this.clientRepo.findById(id);
    }

    public void actualizar(Integer id, Cliente cliente){
        Cliente clien = this.buscarPorId(id);
        if(clien != null){
            clien.setNombre(cliente.getNombre());
            clien.setApellido(cliente.getApellido());
            clien.setEmail(cliente.getEmail());
        }
    }

    public void eliminar(Integer id){
        Cliente cliente = this.buscarPorId(id);
        this.clientRepo.delete(cliente);
    }

    public List<Cliente> buscarTodos(){
        return this.clientRepo.listAll();
    }

    @Transactional
    public void guardarListaClientes(List<Cliente> clientes) {
        System.out.println(">>> [HILO CLIENTES] Guardando " + clientes.size() 
            + " clientes en Hilo: " + Thread.currentThread().getName());
        
        for (Cliente c : clientes) {
            c.persist(); // O tu método guardar individual
        }
    }
}
