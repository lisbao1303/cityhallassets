package br.com.elisbao.cityhallassets.controller;

import br.com.elisbao.cityhallassets.dto.ConfirmacaoDTO;
import br.com.elisbao.cityhallassets.dto.ItemCadastroDTO;
import br.com.elisbao.cityhallassets.model.Item;
import br.com.elisbao.cityhallassets.service.ItemService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.*;

import java.time.LocalDate;
import java.util.List;

@Path("/patrimonios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ItemResource {

    @Inject
    ItemService itemService;
    // 1. Cadastro de Patrimônios OK
    @POST
    public Response cadastrarPatrimonio(ItemCadastroDTO patrimonio) {
        Item item = itemService.salvar(patrimonio);
        return Response.status(Response.Status.CREATED).entity(item).build();
    }

    // 2. Validação de Patrimônio OK
    @POST
    @Path("/validar/{codigo}")
    public Response validarPatrimonio(@PathParam("codigo") String codigo) {
        boolean validado = itemService.validarItem(codigo);
        if (validado) {
            return Response.ok("{\"mensagem\": \"Sucesso na validação.\"}").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"erro\": \"Patrimônio não encontrado.\"}")
                    .build();
        }
    }

    // 3. Listar Patrimônios Ativos OK
    @GET
    @Path("/ativos")
    public Response listarPatrimoniosAtivos(@QueryParam("id") String id) {
        List<Item> ativos = itemService.listarAtivosPorEmpresa(id);
        return Response.ok(ativos).build();
    }

    // 4. Listar Patrimônios em Validação
    @GET
    @Path("/validando")
    public Response listarPatrimoniosValidando(@QueryParam("usuario") String usuario,
                                               @QueryParam("data") String data) {
        LocalDate dataFiltro = LocalDate.parse(data);
        List<Item> emValidacao = itemService.listarItensEmValidacao(usuario, dataFiltro);
        return Response.ok(emValidacao).build();
    }

    // 5. Confirmar Patrimônio
    @PUT
    @Path("/confirmar")
    public Response confirmarPatrimonio(ConfirmacaoDTO confirmacaoDTO) {
        boolean confirmado = itemService.confirmarItem(
                confirmacaoDTO.getCodigo(),
                confirmacaoDTO.getConfirmacao(),
                confirmacaoDTO.getObservacao()
        );
        if (confirmado) {
            return Response.ok("{\"mensagem\": \"Patrimônio confirmado.\"}").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"erro\": \"Patrimônio não encontrado para confirmação.\"}")
                    .build();
        }
    }
}
