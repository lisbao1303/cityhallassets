package br.com.elisbao.cityhallassets.controller;

import br.com.elisbao.cityhallassets.model.Empresa;
import br.com.elisbao.cityhallassets.service.EmpresaService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/empresas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EmpresaResource {

    @Inject
    EmpresaService empresaService;

    @GET
    public Response getEmpresas() {
        List<Empresa> empresas = empresaService.listarTodas();
        return Response.ok(empresas).build();
    }

    @POST
    public Response criarEmpresa(Empresa empresa) {
        Empresa novaEmpresa = empresaService.cadastrar(empresa);
        return Response.status(Response.Status.CREATED).entity(novaEmpresa).build();
    }
}
