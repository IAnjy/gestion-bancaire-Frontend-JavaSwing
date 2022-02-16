package bank;

public class ClientBean {

	private Long id ;
	private String numCompte ;
	private String nom ;
	private String prenoms ;
	private Long solde ;
	
	
	public ClientBean(Long id,String numCompte, String nom, String prenoms, Long solde) {
		super();
		this.id = id;
		this.numCompte = numCompte;
		this.nom = nom;
		this.prenoms = prenoms;
		this.solde = solde;
	}


	

	public String getNumCompte() {
		return numCompte;
	}


	public void setNumCompte(String numCompte) {
		this.numCompte = numCompte;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenoms() {
		return prenoms;
	}


	public void setPrenoms(String prenoms) {
		this.prenoms = prenoms;
	}




	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public Long getSolde() {
		return solde;
	}




	public void setSolde(Long solde) {
		this.solde = solde;
	}


	
	
	
	
	
}
