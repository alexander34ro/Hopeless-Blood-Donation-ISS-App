package Models;

public class Pacient implements IHasId, IHasName {

	public Pacient() {
	}

	private Integer id;
	private String nume;
	private String prenume;
	private Boolean prioritate;
	private Integer medic;

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
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

	public Boolean getPrioritate() {
		return prioritate;
	}

	public void setPrioritate(Boolean prioritate) {
		this.prioritate = prioritate;
	}

	public Integer getMedic() {
		return medic;
	}

	public void setMedic(Integer medic) {
		this.medic = medic;
	}

}