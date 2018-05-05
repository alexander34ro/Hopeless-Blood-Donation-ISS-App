package ModelsDONOTUSETHIS;

public class Notificare implements IHasId {

	public Notificare() {
	}

	private Integer id;
	private Integer asistent;
	private Integer donator;
	private String mesaj;


	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAsistent() {
		return asistent;
	}

	public void setAsistent(Integer asistent) {
		this.asistent = asistent;
	}

	public Integer getDonator() {
		return donator;
	}

	public void setDonator(Integer donator) {
		this.donator = donator;
	}

	public String getMesaj() {
		return mesaj;
	}

	public void setMesaj(String mesaj) {
		this.mesaj = mesaj;
	}

}