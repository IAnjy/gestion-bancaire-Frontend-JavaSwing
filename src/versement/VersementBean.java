package versement;


public class VersementBean {
	
	private Long id;
	private String numCompte ;
	private String nomPrenoms ;
	private Long montantVersement ;
	private String date;
	
	
	public VersementBean(Long id, String numCompte, String nomPrenoms, Long solde, String date) {
		super();
		this.id = id;
		this.numCompte = numCompte;
		this.nomPrenoms = nomPrenoms;
		this.montantVersement = solde;
		this.date = date;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNumCompte() {
		return numCompte;
	}


	public void setNumCompte(String numCompte) {
		this.numCompte = numCompte;
	}


	public String getNomPrenoms() {
		return nomPrenoms;
	}


	public void setNomPrenoms(String nomPrenoms) {
		this.nomPrenoms = nomPrenoms;
	}


	public Long getMontantVersement() {
		return montantVersement;
	}


	public void setMontantVersement(Long solde) {
		this.montantVersement = solde;
	}


	
	
	
}
