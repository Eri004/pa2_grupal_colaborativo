package ec.edu.uce.application.service;

import java.util.List;

import ec.edu.uce.domain.model.Cliente;
import ec.edu.uce.infrastructure.repository.ClienteRepositoryImpl;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.infrastructure.Infrastructure;
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

    public Cliente buscarPorIdCli(Integer id) {
        return this.clienteRepositoryImpl.findById(id);
    }

    public Cliente buscarPorCedulaCli(String cedulaCli) {
        return this.clienteRepositoryImpl.buscarPorCedula(cedulaCli);
    }

    public List<Cliente> buscarTodosCls() {
        return this.clienteRepositoryImpl.findAll().list();
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

    public String buscarPorIdReactivo(Integer id) {
        Uni.createFrom().item(() -> this.clienteRepositoryImpl.findById(id))
                .runSubscriptionOn(Infrastructure.getDefaultWorkerPool());
        return "Encontrado";
    }

    public Uni<Cliente> guardarClienteReactivo(Cliente cliente) {
        return Uni.createFrom().item(() -> {
            this.guardarCli(cliente);
            return cliente;
        }).runSubscriptionOn(Infrastructure.getDefaultWorkerPool());
    }

}
