package ec.edu.uce.application.service;

import ec.edu.uce.domain.model.Cliente;
import ec.edu.uce.infrastructure.repository.ClienteRepositoryImpl;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class ClienteService {

    @Inject
    private ClienteRepositoryImpl clr;

    public void guardar(Cliente c) {

        this.clr.persist(c);

    }

    public Cliente actualizar(Integer id, Cliente c) {

        Cliente nuevo = this.clr.findById(id);

        nuevo.setNombre(c.getNombre());

        return nuevo;

    }

    public Cliente buscarPorId(Integer id) {

        return this.clr.findById(id);

    }

    public void eliminar(Integer id) {

        this.clr.deleteById(id);

    }

    public Uni<Cliente> buscarPorIdReactivo(Integer id) {

        return Uni.createFrom().item(() -> this.clr.findById(id));

    }

    public Uni<Cliente> guardarClienteReactivo(Cliente c) {

        return Uni.createFrom().item(() -> {
            this.guardar(c);
            return c;
        });

    }

}
