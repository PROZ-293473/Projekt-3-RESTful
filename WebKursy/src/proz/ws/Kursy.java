package proz.ws;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ExchangeRatesSeries")
@XmlAccessorType (XmlAccessType.FIELD)
public class Kursy {
	 	private String Table;
	    private String Currency;
	    private String Code;
	    
	    @XmlElementWrapper(name="Rates")
	    @XmlElement(name="Rate")
	    private List<Kurs> Rates;

		public String getTable() {
			return Table;
		}

		public void setTable(String table) {
			Table = table;
		}

		public String getCurrency() {
			return Currency;
		}

		public void setCurrency(String currency) {
			Currency = currency;
		}

		public String getCode() {
			return Code;
		}

		public void setCode(String code) {
			Code = code;
		}

		public List<Kurs> getRates() {
			return Rates;
		}

		public void setRates(List<Kurs> rates) {
			Rates = rates;
		}
		
		public Srednia buildSrednia () {
			double licznik=0;
			int mianownik=0;
			for(Kurs kk : Rates) {
				licznik+=kk.getMid();
				mianownik++;
			}
			if(mianownik==0) {
				return null;
			}
			Srednia srednia = new Srednia();
			srednia.setWartosc(licznik/mianownik);
			return srednia;
		}
}
