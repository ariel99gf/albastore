package com.albastore.resource;

import com.albastore.domain.Cart;
import com.albastore.service.CartService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

import java.util.List;

@Path("/carts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartResource {

	@Inject
	CartService cartService;

	@Context
	SecurityContext securityContext;

	@GET
	@RolesAllowed("admin")
	public List<Cart> getAllCarts() {
		return cartService.listAllCarts();
	}

	@GET
	@Path("/{id}")
	@RolesAllowed({"user", "admin"})
	public Cart getCartById(@PathParam("id") Long id) {
		return cartService.findCartById(id);
	}

	@POST
	@RolesAllowed({"user", "admin"})
	public Response createCart(Cart cart) {
		cartService.createCart(cart);
		return Response.status(Response.Status.CREATED).build();
	}

	@PUT
	@Path("/{id}")
	@RolesAllowed({"user", "admin"})
	public Response updateCart(@PathParam("id") Long id, Cart cart) {
		cartService.updateCart(id, cart);
		return Response.ok().build();
	}

	@DELETE
	@Path("/{id}")
	@RolesAllowed({"user", "admin"})
	public Response deleteCart(@PathParam("id") Long id) {
		cartService.deleteCart(id);
		return Response.noContent().build();
	}
}