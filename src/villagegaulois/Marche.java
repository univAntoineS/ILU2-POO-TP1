package villagegaulois;

import personnages.*;

public class Marche {
	
	
	private int nbEtals;
	private Etal[] etals;
	
	
	public Marche(int nbEtals)
	{
		this.etals = new Etal[nbEtals];
		this.nbEtals = nbEtals;
	}
	
	
	private void utiliserEtal(int indiceEtal, Gaulois vendeur,String produit, int nbProduit)
	{
		this.etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
	}
	
	public int trouverEtalLibre()
	{
		// search Etal
		int indiceEtal = 0;
		boolean etalTrouver = false;
		while(indiceEtal<this.nbEtals && !etalTrouver)
		{
			if(!this.etals[indiceEtal].isEtalOccupe())
			{
				etalTrouver = true;
			}
		}
		
		// return index
		if(etalTrouver)
		{
			return indiceEtal;
		}
		else
		{
			return -1;
		}
		
	}
	
	public Etal[] trouverEtals(String produit)
	{
		Etal[] listeEtal = {};
		Etal[] tmpListe;
		
		for(int i = 0; i< this.nbEtals; i++)
		{
			if (this.etals[i].isEtalOccupe() && (this.etals[i].contientProduit(produit)))
				{
					
					tmpListe = new Etal[listeEtal.length + 1];
					System.arraycopy(listeEtal, 0, tmpListe, 0, listeEtal.length);
					tmpListe[listeEtal.length] = this.etals[i];
					listeEtal = tmpListe;
				
			}
		}
		return listeEtal;
	}
	Etal trouverVendeur(Gaulois gaulois) 
	{
		for(int i = 0; i< this.nbEtals; i++)
		{
			if (this.etals[i].isEtalOccupe() && (this.etals[i].getVendeur() == gaulois))
				{
					return this.etals[i];
				
			}
		}
		return null;
	}
	
	public String afficherMarche()
	{
		int nbEtalVide = 0;
		StringBuilder affichage = new StringBuilder();
		
		for(int i = 0; i < this.nbEtals;i++)
		{
			affichage.append(this.etals[i].afficherEtal());
			if(!this.etals[i].isEtalOccupe())
			{
				nbEtalVide++;
			}
		}
		
		affichage.append("Il reste " + nbEtalVide + " étals non utilisés dans le marché.\n");
		return affichage.toString();
	}
}





















