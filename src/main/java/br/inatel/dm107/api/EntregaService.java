package br.inatel.dm107.api;

import java.sql.SQLException;
import java.util.Objects;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
	@Produces(MediaType.APPLICATION_JSON)
	public Response addEntrega(Entrega entrega) {
		if (Objects.isNull(entrega.getIdCliente()) || Objects.isNull(entrega.getNumeroPedido())) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		
		try {
			entregaDao.inserirEntrega(entrega);
			
			return Response
					.created(uriInfo.getAbsolutePathBuilder().path(String.valueOf(entrega.getNumeroPedido())).build())
					.build();
		} catch (SQLException e) {
			return Response.serverError().build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{numeroPedido}")
	public Response getEntregaByNumeroPedido(@PathParam("numeroPedido") int numeroPedido) {
		try {
			Entrega entrega = entregaDao.selectEntregaByNumeroPedido(numeroPedido);
			
			if (Objects.isNull(entrega)) {				
				return Response.status(Status.NOT_FOUND).build();
			}
			
			return Response.ok(entrega).build();
		} catch (SQLException e) {
			return Response.serverError().build();
		}
	
	}

}
