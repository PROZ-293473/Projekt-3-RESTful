package proz.ws;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Srednia {
	private String mime;
	private Double wartosc;
	
	public String getMime() {
		return mime;
	}
	public void setMime(String mime) {
		this.mime = mime;
	}
	public Double getWartosc() {
		return wartosc;
	}
	public void setWartosc(Double wartosc) {
		wartosc = (double) Math.round(wartosc.doubleValue()*100000)/100000;
		this.wartosc = wartosc;
	}
}
