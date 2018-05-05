package ModelsDONOTUSETHIS;

public class Cerere implements IHasId {

	public Cerere() {
	}

	private Integer id;
	private Integer centruTransfuzii;
	private Integer pacient;
	private Integer trombociteNecesare;
	private Integer globuleRosiiNecesare;
	private Integer plasmaNecesara;

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCentruTransfuzii() {
		return centruTransfuzii;
	}

	public void setCentruTransfuzii(Integer centruTransfuzii) {
		this.centruTransfuzii = centruTransfuzii;
	}

	public Integer getPacient() {
		return pacient;
	}

	public void setPacient(Integer pacient) {
		this.pacient = pacient;
	}

	public Integer getTrombociteNecesare() {
		return trombociteNecesare;
	}

	public void setTrombociteNecesare(Integer trombociteNecesare) {
		this.trombociteNecesare = trombociteNecesare;
	}

	public Integer getGlobuleRosiiNecesare() {
		return globuleRosiiNecesare;
	}

	public void setGlobuleRosiiNecesare(Integer globuleRosiiNecesare) {
		this.globuleRosiiNecesare = globuleRosiiNecesare;
	}

	public Integer getPlasmaNecesara() {
		return plasmaNecesara;
	}

	public void setPlasmaNecesara(Integer plasmaNecesara) {
		this.plasmaNecesara = plasmaNecesara;
	}

}