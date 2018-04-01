package Models;

import java.util.Date;

public class Donatie implements IHasId {

	public Donatie() {
	}

	private Integer id;
	private Integer donator;
	private Integer centruTransfuzii;
	private Integer greutate;
	private Integer puls;
	private Integer tensiune;
	private Boolean aSuferitInterventii;
	private Boolean insarcinataLauzieMenstruatie;
	private Boolean aConsumatAlcool;
	private Boolean subTratament;
	private Boolean aAvutBoliNedorite;
	private String numePacient;
	private Stadiu stadiu;
	private Boolean respins;
	private Date data;


	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDonator() {
		return donator;
	}

	public void setDonator(Integer donator) {
		this.donator = donator;
	}

	public Integer getCentruTransfuzii() {
		return centruTransfuzii;
	}

	public void setCentruTransfuzii(Integer centruTransfuzii) {
		this.centruTransfuzii = centruTransfuzii;
	}

	public Integer getGreutate() {
		return greutate;
	}

	public void setGreutate(Integer greutate) {
		this.greutate = greutate;
	}

	public Integer getPuls() {
		return puls;
	}

	public void setPuls(Integer puls) {
		this.puls = puls;
	}

	public Integer getTensiune() {
		return tensiune;
	}

	public void setTensiune(Integer tensiune) {
		this.tensiune = tensiune;
	}

	public Boolean getaSuferitInterventii() {
		return aSuferitInterventii;
	}

	public void setaSuferitInterventii(Boolean aSuferitInterventii) {
		this.aSuferitInterventii = aSuferitInterventii;
	}

	public Boolean getInsarcinataLauzieMenstruatie() {
		return insarcinataLauzieMenstruatie;
	}

	public void setInsarcinataLauzieMenstruatie(Boolean insarcinataLauzieMenstruatie) {
		this.insarcinataLauzieMenstruatie = insarcinataLauzieMenstruatie;
	}

	public Boolean getaConsumatAlcool() {
		return aConsumatAlcool;
	}

	public void setaConsumatAlcool(Boolean aConsumatAlcool) {
		this.aConsumatAlcool = aConsumatAlcool;
	}

	public Boolean getSubTratament() {
		return subTratament;
	}

	public void setSubTratament(Boolean subTratament) {
		this.subTratament = subTratament;
	}

	public Boolean getaAvutBoliNedorite() {
		return aAvutBoliNedorite;
	}

	public void setaAvutBoliNedorite(Boolean aAvutBoliNedorite) {
		this.aAvutBoliNedorite = aAvutBoliNedorite;
	}

	public String getNumePacient() {
		return numePacient;
	}

	public void setNumePacient(String numePacient) {
		this.numePacient = numePacient;
	}

	public Stadiu getStadiu() {
		return stadiu;
	}

	public void setStadiu(Stadiu stadiu) {
		this.stadiu = stadiu;
	}

	public Boolean getRespins() {
		return respins;
	}

	public void setRespins(Boolean respins) {
		this.respins = respins;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}