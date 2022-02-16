package retrait;


public class RetraitBean {
	
	private Long id;
	private String numCompte ;
	private String numCheque ;
	private String nomPrenoms ;
	private Long montantRetrait ;
	private String date;
	
	public RetraitBean(Long id, String numCompte, String numCheque, String nomPrenoms, Long montantRetrait,
			String date) {
		super();
		this.id = id;
		this.numCompte = numCompte;
		this.numCheque = numCheque;
		this.nomPrenoms = nomPrenoms;
		this.montantRetrait = montantRetrait;
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

	public String getNumCheque() {
		return numCheque;
	}

	public void setNumCheque(String numCheque) {
		this.numCheque = numCheque;
	}

	public String getNomPrenoms() {
		return nomPrenoms;
	}

	public void setNomPrenoms(String nomPrenoms) {
		this.nomPrenoms = nomPrenoms;
	}

	public Long getMontantRetrait() {
		return montantRetrait;
	}

	public void setMontantRetrait(Long montantRetrait) {
		this.montantRetrait = montantRetrait;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	
	
}
