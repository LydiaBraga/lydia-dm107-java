package br.inatel.dm107.api;

import java.sql.SQLException;
import java.util.Objects;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import br.inatel.dm107.dao.EntregaDao;
import br.inatel.dm107.model.Entrega;

@Path("/entrega")
public class EntregaService {

	private EntregaDao entregaDao = new EntregaDao();
	
	@Context
	private UriInfo uriInfo;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addEntrega(Entrega entrega) {
		if (Objects.isNull(entrega.getIdCliente()) || Objects.isNull(entrega.getNumeroPedido())) {
			String errorMessage = "Os campos id do cliente e número do pedido são obrigatórios!";
			
			return Response.status(Status.BAD_REQUEST).entity(errorMessage).build();
		}
		
		try {
			entregaDao.inserirEntrega(entrega);
			
			return Response
					.created(uriInfo.getAbsolutePathBuilder().path(String.valueOf(entrega.getNumeroPedido())).build())
					.entity(entrega)
					.build();
		} catch (SQLException e) {
			String errorMessage = "Erro ao adicionar entrega!";
			
			return Response.serverError().entity(errorMessage).build();
		}
	}

	@GET
	@Path("{numeroPedido}")
	public Response getEntregaByNumeroPedido(@PathParam("numeroPedido") int numeroPedido) {
		try {
			Entrega entrega = entregaDao.selectEntregaByNumeroPedido(numeroPedido);
			
			if (Objects.isNull(entrega)) {
				String errorMessage = "Entrega não encontrada para número de pedido " + numeroPedido;
				
				return Response.status(Status.NOT_FOUND).entity(errorMessage).build();
			}
			
			return Response
					.ok(entrega)
					.build();
		} catch (SQLException e) {
			String errorMessage = "Erro inesperado ao buscar entrega!";
			
			return Response.serverError().entity(errorMessage).build();
		}
	
	}

}
