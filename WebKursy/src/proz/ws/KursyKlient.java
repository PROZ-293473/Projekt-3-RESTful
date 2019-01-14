package proz.ws;

import java.io.StringReader;
import java.net.URI;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBException;

public class KursyKlient {
	public static Kursy pobierzKursy (String table, String code, String topCount ) throws JAXBException {

		Client client = ClientBuilder.newClient();
		URI uri = UriBuilder.fromUri("http://api.nbp.pl/api/exchangerates/rates/" + table + "/" + code + "/last/" + topCount + "/").build();
		WebTarget webTarget = client.target(uri);
		String xmlAnswer = webTarget.request().accept(MediaType.TEXT_XML).get(String.class);
		
		Kursy kursy = JAXB.unmarshal(new StringReader(xmlAnswer), Kursy.class);
		
		return kursy;
	}
}
