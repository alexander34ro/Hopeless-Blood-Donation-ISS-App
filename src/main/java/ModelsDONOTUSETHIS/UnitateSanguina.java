package ModelsDONOTUSETHIS;

import java.util.Date;

public class UnitateSanguina implements IHasId {

	public UnitateSanguina() {
	}

	private Integer id;
	private CategorieSanguina categorie;
	private TipSange tipSange;
	private Date expiraLa;
	private Integer centruTransfuzii;


	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public CategorieSanguina getCategorie() {
		return categorie;
	}

	public void setCategorie(CategorieSanguina categorie) {
		this.categorie = categorie;
	}

	public TipSange getTipSange() {
		return tipSange;
	}

	public void setTipSange(TipSange tipSange) {
		this.tipSange = tipSange;
	}

	public Date getExpiraLa() {
		return expiraLa;
	}

	public void setExpiraLa(Date expiraLa) {
		this.expiraLa = expiraLa;
	}

	public Integer getCentruTransfuzii() {
		return centruTransfuzii;
	}

	public void setCentruTransfuzii(Integer centruTransfuzii) {
		this.centruTransfuzii = centruTransfuzii;
	}

}