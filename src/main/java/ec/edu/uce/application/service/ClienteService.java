package ec.edu.uce.application.service;

import ec.edu.uce.domain.model.Cliente;
import ec.edu.uce.infrastructure.repository.ClienteRepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class ClienteService {

    @Inject
    private ClienteRepositoryImpl clienteRepositoryImpl;

    public void guardarCli(Cliente cliente){
        this.clienteRepositoryImpl.persist(cliente);
    }

    public Cliente buscarPorIdCli(Integer id){
        return this.clienteRepositoryImpl.findById(id);
    }

    public void actualizarCli(Cliente cli, Integer id){
        
        Cliente nuevo = this.buscarPorIdCli(id);

        if(nuevo != null){
            nuevo.setIdentificaion(cli.getIdentificaion());
            nuevo.setNombre(cli.getNombre());
            nuevo.setTelefono(cli.getTelefono());
        }

    }

    public void eliminarPorId(Integer id){
        this.clienteRepositoryImpl.delete(this.buscarPorIdCli(id));
    }

}
