
public class Chambre {

	private  int idChambre ;
	private int categorie;
	
	public Chambre(int idChambre, int categorie) {
		super();
		this.idChambre = idChambre;
		this.categorie = categorie;
	}
	
	
	public int getIdChambre() {
		return idChambre;
	}

	public int getCategorie() {
		return categorie;
	}

}
