package com.albastore.resource;

import com.albastore.domain.Cart;
import com.albastore.service.CartService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/carts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartResource {

	@Inject
	CartService cartService;

	@GET
	public List<Cart> getAllCarts() {
		return cartService.listAllCarts();
	}

	@GET
	@Path("/{id}")
	public Cart getCartById(@PathParam("id") Long id) {
		return cartService.findCartById(id);
	}

	@POST
	public Response createCart(Cart cart) {
		cartService.createCart(cart);
		return Response.status(Response.Status.CREATED).build();
	}

	@PUT
	@Path("/{id}")
	public Response updateCart(@PathParam("id") Long id, Cart cart) {
		cartService.updateCart(id, cart);
		return Response.ok().build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteCart(@PathParam("id") Long id) {
		cartService.deleteCart(id);
		return Response.noContent().build();
	}
}