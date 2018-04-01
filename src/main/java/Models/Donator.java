package Models;

public class Donator implements IHasId, IHasName, IHasContactDetails, IHasLocation, IUser {

	public Donator() {
	}

	private Integer id;
	private String username;
	private String parola;
	private String email;
	private String telefon;
	private String nume;
	private String prenume;
	private String dataNastere;
	private Oras oras;
	private Regiune regiune;
	private String adresa;
	private Oras orasResedinta;
	private Regiune regiuneResedinta;
	private String adresaResedinta;
	private TipSange tipSange;

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
	public String getEmail() {
		return email;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String getTelefon() {
		return telefon;
	}

	@Override
	public void setTelefon(String telefon) {
		this.telefon = telefon;
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

	public String getDataNastere() {
		return dataNastere;
	}

	public void setDataNastere(String dataNastere) {
		this.dataNastere = dataNastere;
	}

	@Override
	public Oras getOras() {
		return oras;
	}

	@Override
	public void setOras(Oras oras) {
		this.oras = oras;
	}

	@Override
	public Regiune getRegiune() {
		return regiune;
	}

	@Override
	public void setRegiune(Regiune regiune) {
		this.regiune = regiune;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public Oras getOrasResedinta() {
		return orasResedinta;
	}

	public void setOrasResedinta(Oras orasResedinta) {
		this.orasResedinta = orasResedinta;
	}

	public Regiune getRegiuneResedinta() {
		return regiuneResedinta;
	}

	public void setRegiuneResedinta(Regiune regiuneResedinta) {
		this.regiuneResedinta = regiuneResedinta;
	}

	public String getAdresaResedinta() {
		return adresaResedinta;
	}

	public void setAdresaResedinta(String adresaResedinta) {
		this.adresaResedinta = adresaResedinta;
	}

	public TipSange getTipSange() {
		return tipSange;
	}

	public void setTipSange(TipSange tipSange) {
		this.tipSange = tipSange;
	}

}