package proz.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("rates/{table}/{code}/{topCount}")
public class SredniaResource {
	@GET
	@Produces( MediaType.APPLICATION_JSON )
	public Response getJSON(@PathParam("table") String table, @PathParam("code") String code, @PathParam("topCount") String topCount) {
		Srednia srednia = uniProcedura(table, code, topCount);
		srednia.setMime(MediaType.APPLICATION_JSON.toString());
		return Response.status(Response.Status.OK).type(MediaType.APPLICATION_JSON).entity(srednia).build();
	}

	@GET
	@Produces( MediaType.TEXT_XML )
	public Response getXML(@PathParam("table") String table, @PathParam("code") String code, @PathParam("topCount") String topCount) {
		Srednia srednia = uniProcedura(table, code, topCount);
		srednia.setMime(MediaType.TEXT_XML.toString());
		return Response.status(Response.Status.OK).type(MediaType.TEXT_XML).entity(srednia).build();
	}

	@GET
	@Produces( MediaType.TEXT_HTML )
	public Response getHTML(@PathParam("table") String table, @PathParam("code") String code, @PathParam("topCount") String topCount) {
		Srednia srednia = uniProcedura(table, code, topCount);
		srednia.setMime(MediaType.TEXT_HTML.toString());
		String htmlResponse = 
				"<html><body>"
				+ "<h1>Wartosc: </h1>"
				+ "<h1>" + Double.toString(srednia.getWartosc()) + "</h1>"
				+ "<h1>Mime: </h1>"
				+ "<h1>" + srednia.getMime() + "</h1>"
				+ "</body></html>";
		return Response.status(Response.Status.OK).type(MediaType.TEXT_HTML).entity(htmlResponse).build();
	}

	private static Srednia uniProcedura(String table, String code, String topCount) {
		Kursy kursy = null;
		Srednia srednia = null;
		try {
			kursy = KursyKlient.pobierzKursy(table, code, topCount);
			srednia = kursy.buildSrednia();
		} catch (Exception ex) {}
		return srednia;
	}
	
	public static void main(String[] args) {
		System.out.println(uniProcedura("A", "GBP", "20").getWartosc());
	}
	
	@GET
	@Path("hello")
	@Produces( MediaType.TEXT_PLAIN )
	public String sayHello() {
		return "HELLO";
	}
}
