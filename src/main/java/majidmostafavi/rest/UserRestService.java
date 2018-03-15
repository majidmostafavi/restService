package majidmostafavi.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import majidmostafavi.entity.Personnel;
import majidmostafavi.entity.Users;
import majidmostafavi.service.PersonnelService;
import majidmostafavi.service.UsersService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;

@Path("users")
public class UserRestService  {


	@Inject
	UsersService usersService;
	@Inject
	PersonnelService personnelService;


	@GET
	@Path("{id}")
	public Response getUserById(@PathParam("id") String id) {

		return Response.status(200).entity("getUserById is called, id : " + id)
				.build();
	}

	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public String getUserName(
			@FormParam("email") String username,
			@FormParam("password")  String password){

		Users user = usersService.findUser(username,password) ;
		Personnel p = new Personnel();
		JsonObject json = new JsonObject();
		if (user!=null){
			p=	usersService.findPersonnel(user.getUsername());

			json.addProperty("error",false);
			json.addProperty("user",String.valueOf(user.getId()));
			json.addProperty("email",p.getEmail());
			json.addProperty("name",p.getFirstName());
			json.addProperty("created_at", String.valueOf(new Date()));
			return json.toString();
		}
		json.addProperty("error",true);
		return json.toString();
	}

	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public Response createUser(
			@FormParam("name") String name,
			@FormParam("email") String email,
			@FormParam("password") String password){

		Personnel personnel = new Personnel();
		personnelService.create(personnel);
		Users user= new Users();
		user.setUsername(personnel.getEmail());
		user.setPassword(personnel.getFirstName());
		user.setPersonnel(personnel);
		usersService.create(user) ;

		return Response.status(200)
				.entity("user is called, userName : "+ user.getId() )
				.build();
	}

//	@GET
//	@Path("{username}")
//	public Response getPersonnel(
//			@PathParam("username") String username){
//
//	//	Personnel personnel = usersService.findPersonnel(username) ;
//
//		return Response.status(200)
//				.entity("Personnel  is called, personnelName : "  + "personnelLast"  )
//				.build();
//
//	}
	
}