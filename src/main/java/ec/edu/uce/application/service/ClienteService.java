package ec.edu.uce.application.service;

import java.util.List;
import java.util.stream.Collectors;

import ec.edu.uce.application.interceptor.Notificacion;
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

    public void guardarCli(Cliente cliente) {
        this.clienteRepositoryImpl.persist(cliente);
    }

    @Notificacion
    public Cliente buscarPorIdCli(Integer id) {
        return this.clienteRepositoryImpl.findById(id);
    }

    public Cliente buscarPorCedulaCli(String cedulaCli) {
        return this.clienteRepositoryImpl.buscarPorCedula(cedulaCli);
    }

    @Notificacion
    public List<Cliente> buscarTodosCls() {
        List<Cliente> clientes = this.clienteRepositoryImpl.findAll().list();
        return clientes.parallelStream()
                .filter(c -> c != null)
                .collect(Collectors.toList());
    }

    public Cliente actualizarCli(Cliente cli, Integer id) {

        Cliente nuevo = this.buscarPorIdCli(id);

        if (nuevo != null) {
            nuevo.setCedula(cli.getCedula());
            nuevo.setNombre(cli.getNombre());
            nuevo.setTelefono(cli.getTelefono());
        }
        return nuevo;
    }

    public void eliminarPorId(Integer id) {
        this.clienteRepositoryImpl.delete(this.buscarPorIdCli(id));
    }

}



