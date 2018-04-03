package Models;

public class CentruTransfuzii implements IHasId, IHasLocation {

	public CentruTransfuzii() {
	}

	private Integer id;
	private String nume;
	private Regiune regiune;
	private Oras oras;

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	@Override
	public Regiune getRegiune() {
		return regiune;
	}

	@Override
	public void setRegiune(Regiune regiune) {
		this.regiune = regiune;
	}

	@Override
	public Oras getOras() {
		return oras;
	}

	@Override
	public void setOras(Oras oras) {
		this.oras = oras;
	}

}