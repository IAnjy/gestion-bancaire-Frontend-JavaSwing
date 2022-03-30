package transfert;

public class TransfertBean {
	
	private Long id;
	private Long montantTransfert ;
	private String numCompteExpediteur ;
	private String numCompteDestinataire ;
	private String date;
	
	
	public TransfertBean(Long id, Long montantTransfert, String numCompteExpediteur, String numCompteDestinataire,
			String date) {
		super();
		this.id = id;
		this.montantTransfert = montantTransfert;
		this.numCompteExpediteur = numCompteExpediteur;
		this.numCompteDestinataire = numCompteDestinataire;
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


	public Long getMontantTransfert() {
		return montantTransfert;
	}


	public void setMontantTransfert(Long montantTransfert) {
		this.montantTransfert = montantTransfert;
	}


	public String getNumCompteExpediteur() {
		return numCompteExpediteur;
	}


	public void setNumCompteExpediteur(String numCompteExpediteur) {
		this.numCompteExpediteur = numCompteExpediteur;
	}


	public String getNumCompteDestinataire() {
		return numCompteDestinataire;
	}


	public void setNumCompteDestinataire(String numCompteDestinataire) {
		this.numCompteDestinataire = numCompteDestinataire;
	}
	
	
	

}
