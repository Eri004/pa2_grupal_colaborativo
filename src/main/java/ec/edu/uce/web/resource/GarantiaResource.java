package ec.edu.uce.web.resource;

import ec.edu.uce.application.service.GarantiaService;
import ec.edu.uce.domain.model.Garantia;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/garantia")
public class GarantiaResource {

    @Inject
    private GarantiaService garantiaService;

    @POST
    @Path("/guardar")
    public void guardarGarantia(Garantia garantia){
        this.garantiaService.guardar(garantia);
    }

}
