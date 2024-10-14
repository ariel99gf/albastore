package com.albastore.resource;

import com.albastore.domain.Product;
import com.albastore.service.ProductService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {

	@Inject
	ProductService productService;

	@GET
	@RolesAllowed({"admin", "user"})
	public List<Product> getAllProducts() {
		return productService.listAllProducts();
	}

	@GET
	@Path("/{id}")
	@RolesAllowed({"admin", "user"})
	public Product getProductById(@PathParam("id") Long id) {
		return productService.findProductById(id);
	}

	@POST
	@RolesAllowed("admin")
	public Response createProduct(Product product) {
		productService.createProduct(product);
		return Response.status(Response.Status.CREATED).build();
	}

	@PUT
	@Path("/{id}")
	@RolesAllowed("admin")
	public Response updateProduct(@PathParam("id") Long id, Product product) {
		productService.updateProduct(id, product);
		return Response.ok().build();
	}

	@DELETE
	@Path("/{id}")
	@RolesAllowed("admin")
	public Response deleteProduct(@PathParam("id") Long id) {
		productService.deleteProduct(id);
		return Response.noContent().build();
	}
}