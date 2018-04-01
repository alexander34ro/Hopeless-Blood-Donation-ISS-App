package Models;

public class Medic implements IHasId, IUser, IHasName {

	public Medic() {
	}

	private Integer id;
	private String username;
	private String parola;
	private String nume;
	private String prenume;
	private Integer spital;

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getParola() {
		return parola;
	}

	@Override
	public void setParola(String parola) {
		this.parola = parola;
	}

	@Override
	public String getNume() {
		return nume;
	}

	@Override
	public void setNume(String nume) {
		this.nume = nume;
	}

	@Override
	public String getPrenume() {
		return prenume;
	}

	@Override
	public void setPrenume(String prenume) {
		this.prenume = prenume;
	}

	public Integer getSpital() {
		return spital;
	}

	public void setSpital(Integer spital) {
		this.spital = spital;
	}

}