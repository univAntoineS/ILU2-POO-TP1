package histoire;
import personnages.Gaulois;
import villagegaulois.*;

public class ScenarioCasDegrade {

	public static void main(String[] args) {
		
		Etal etal2 = new Etal();
		etal2.libererEtal();

		
		Etal etal = new Etal();
		Gaulois acheteur = new Gaulois("acheteur",1024);
		Gaulois vendeur = new Gaulois("vendeur",1023);
		
		
		etal.occuperEtal(vendeur, "FLEUR", 12);
		System.out.println(etal.acheterProduit(12, null));
		System.out.println(etal.acheterProduit(0, acheteur));	
		System.out.println(etal2.acheterProduit(12, acheteur));
		
		Village village = new Village("VILLAGEEEEE",3,2);
		village.ajouterHabitant(vendeur);
		village.ajouterHabitant(acheteur);
		
		System.out.println(village.afficherVillageois());
		
		System.out.println("Fin du test");


	}

}
