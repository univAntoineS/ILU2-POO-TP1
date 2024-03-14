package villagegaulois;

import personnages.Gaulois;

public class Etal {
	private Gaulois vendeur;
	private String produit;
	private int quantiteDebutMarche;
	private int quantite;
	private boolean etalOccupe = false;

	public boolean isEtalOccupe() {
		return etalOccupe;
	}

	public Gaulois getVendeur() {
		return vendeur;
	}

	public void occuperEtal(Gaulois vendeur, String produit, int quantite) {
		this.vendeur = vendeur;
		this.produit = produit;
		this.quantite = quantite;
		quantiteDebutMarche = quantite;
		etalOccupe = true;
	}

	public String libererEtal() {
		if (!this.etalOccupe) {
			throw new NullPointerException("L'étal n'est pas occupé.");
		}

		String nomVendeur;
		try {
			nomVendeur = vendeur.getNom();
		} catch (java.lang.NullPointerException e) {
			e.printStackTrace();
			return "";
		}

		StringBuilder chaine = new StringBuilder();
		etalOccupe = false;
		chaine.append("Le vendeur " + nomVendeur + " quitte son étal, ");
		int produitVendu = quantiteDebutMarche - quantite;
		if (produitVendu > 0) {
			chaine.append("il a vendu " + produitVendu + " parmi " + produit + ".\n");
		} else {
			chaine.append("il n'a malheureusement rien vendu.\n");
		}
		return chaine.toString();

	}

	public String afficherEtal() {
		if (etalOccupe) {
			return "L'étal de " + vendeur.getNom() + " est garni de " + quantite + " " + produit + "\n";
		}
		return "L'étal est libre";
	}

	public String acheterProduit(int quantiteAcheter, Gaulois acheteur) {

		if (quantiteAcheter < 1) {
			throw new IllegalArgumentException("la quantité à acheter est inférieur à 1.");
		}
		if (!this.etalOccupe) {
			throw new IllegalStateException("L'étal n'est pas occupé.");
		}

		StringBuilder chaine = new StringBuilder();

		String nomAcheteur;
		String nomVendeur;

		try {
			nomAcheteur = acheteur.getNom();
			nomVendeur = vendeur.getNom();

		} catch (java.lang.NullPointerException e) {
			e.printStackTrace();
			return "";
		}
		chaine.append(nomAcheteur + " veut acheter " + quantiteAcheter + " " + produit + " à " + nomVendeur);

		if (quantite == 0) {
			chaine.append(", malheureusement il n'y en a plus !");
			quantiteAcheter = 0;
		}
		if (quantiteAcheter > quantite) {
			chaine.append(", comme il n'y en a plus que " + quantite + ", " + nomAcheteur + " vide l'étal de "
					+ nomVendeur + ".\n");
			quantiteAcheter = quantite;
			quantite = 0;
		}
		if (quantite != 0) {
			quantite -= quantiteAcheter;
			chaine.append(". " + nomAcheteur + ", est ravi de tout trouver sur l'étal de " + nomVendeur + "\n");
		}
		return chaine.toString();

	}

	public boolean contientProduit(String produit) {
		return this.produit.equals(produit);
	}

}
