package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {

	public class VillageSansChefException extends Exception
	{
		public VillageSansChefException(String errorMessage) {
	        super(errorMessage);
	    }
	};
	
	public class Marche {

		private Etal[] etals;

		private Marche(int nbEtals) {
			this.etals = new Etal[nbEtals];

			for (int i = 0; i < this.etals.length; i++) {
				this.etals[i] = new Etal();
			}

		}

		private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			if (indiceEtal == -1 || this.etals[indiceEtal].isEtalOccupe()) {
				System.out.println("/!\\Cas Mal géré : utiliserEtal avec indiceEtal incorrect");
			} else {
				this.etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
			}
		}

		private int trouverEtalLibre() {
			// search Etal

			int indiceEtal = 0;
			boolean etalTrouver = false;

			while (indiceEtal < this.etals.length && !etalTrouver) {
				if (!this.etals[indiceEtal].isEtalOccupe()) {
					etalTrouver = true;
				} else {
					indiceEtal++;
				}
			}

			// return index
			if (etalTrouver) {
				return indiceEtal;
			} else {
				return -1;
			}

		}

		private Etal[] trouverEtals(String produit) {
			Etal[] listeEtal = {};
			Etal[] tmpListe;

			for (int i = 0; i < this.etals.length; i++) {
				if (this.etals[i].isEtalOccupe() && (this.etals[i].contientProduit(produit))) {

					tmpListe = new Etal[listeEtal.length + 1];
					System.arraycopy(listeEtal, 0, tmpListe, 0, listeEtal.length);
					tmpListe[listeEtal.length] = this.etals[i];
					listeEtal = tmpListe;

				}
			}
			return listeEtal;
		}

		private Etal trouverVendeur(Gaulois gaulois) {
			for (int i = 0; i < this.etals.length; i++) {
				if (this.etals[i].isEtalOccupe() && (this.etals[i].getVendeur() == gaulois)) {
					return this.etals[i];

				}
			}
			return null;
		}

		public String afficherMarche() {
			int nbEtalVide = 0;
			StringBuilder affichage = new StringBuilder();

			for (int i = 0; i < this.etals.length; i++) {
				affichage.append(this.etals[i].afficherEtal());
				if (!this.etals[i].isEtalOccupe()) {
					nbEtalVide++;
				}
			}

			affichage.append("Il reste " + nbEtalVide + " étals non utilisés dans le marché.\n");
			return affichage.toString();
		}
	}

	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;

	public Village(String nom, int nbVillageoisMaximum, int nbEtal) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		this.marche = new Marche(nbEtal);
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String rechercherVendeursProduit(String produit) {
		Etal[] listeEtal = this.marche.trouverEtals(produit);
		StringBuilder affichage = new StringBuilder();

		if (listeEtal.length == 0) {
			affichage.append("Il n'y a pas de vendeur qui propose des " + produit + " au marché.\n");
		} else if (listeEtal.length == 1) {
			affichage.append("Seul le vendeur " + listeEtal[0].getVendeur().getNom() + " propose des " + produit
					+ " au marché.\n");
		} else {
			affichage.append("Les vendeur qui proposent des " + produit + " sont :\n");

			for (int i = 0; i < listeEtal.length; i++) {
				affichage.append("- " + listeEtal[i].getVendeur().getNom() + "\n");
			}
		}
		return affichage.toString();
	}

	public String afficherVillageois() throws VillageSansChefException {

			if(chef == null)
			{
				throw new VillageSansChefException("Le village n'a pas de chef");
			}
			StringBuilder chaine = new StringBuilder();
			if (nbVillageois < 1) {
				chaine.append("Il n'y a encore aucun habitant au village du chef " + chef.getNom() + ".\n");
			} else {
				chaine.append("Au village du chef " + chef.getNom() + " vivent les légendaires gaulois :\n");
				for (int i = 0; i < nbVillageois; i++) {
					chaine.append("- " + villageois[i].getNom() + "\n");
				}
			}
			return chaine.toString();
	}

	public String installerVendeur(Gaulois gaulois, String produit, int quantite) {

		int indiceEtal = this.marche.trouverEtalLibre();

		StringBuilder affichage = new StringBuilder();
		affichage.append(gaulois.getNom() + " cherche un endroit pour vendre " + quantite + " " + produit + ".\n");
		if (indiceEtal == -1) {
			affichage.append("Aucun étal n'est libre");
		} else {
			this.marche.utiliserEtal(indiceEtal, gaulois, produit, quantite);
			affichage.append("Le vendeur " + gaulois.getNom() + " vend des " + produit + " à l'étal n°"
					+ (indiceEtal + 1) + ".\n");
		}
		return affichage.toString();
	}

	public Etal rechercherEtal(Gaulois gaulois) {
		return this.marche.trouverVendeur(gaulois);
	}

	public String partirVendeur(Gaulois gaulois) {
		Etal etal = this.marche.trouverVendeur(gaulois);
		return etal.libererEtal();
	}

	public String afficherMarche() {
		StringBuilder affichage = new StringBuilder();
		affichage.append("Le marché du village `\"" + this.nom + "\" possède plusieurs étals :\n");
		for (int i = 0; i < this.marche.etals.length; i++) {
			if (this.marche.etals[i].isEtalOccupe()) {
				affichage.append(this.marche.etals[i].afficherEtal());
			}
		}

		return affichage.toString();
	}
}
