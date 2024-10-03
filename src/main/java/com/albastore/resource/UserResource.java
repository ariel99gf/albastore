package com.albastore.resource;

import com.albastore.domain.User;
import com.albastore.dto.LoginDTO;
import com.albastore.service.UserService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

	@Inject
	UserService userService;

	@GET
	public List<User> getAllUsers() {
		return userService.listAllUsers();
	}

	@GET
	@Path("/{id}")
	public User getUserById(@PathParam("id") Long id) {
		return userService.findUserById(id);
	}

	@POST
	public Response createUser(User user) {
		userService.createUser(user);
		return Response.status(Response.Status.CREATED).build();
	}

	@PUT
	@Path("/{id}")
	public Response updateUser(@PathParam("id") Long id, User user) {
		userService.updateUser(id, user);
		return Response.ok().build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteUser(@PathParam("id") Long id) {
		userService.deleteUser(id);
		return Response.noContent().build();
	}

	@POST
	@Path("/login")
	public Response login(LoginDTO loginDTO) {
		User user = userService.authenticate(loginDTO.username, loginDTO.password);
		if (user != null) {
			return Response.ok(user).build();
		} else {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
	}
}