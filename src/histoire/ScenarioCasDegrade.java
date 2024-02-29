package histoire;
import personnages.Gaulois;
import villagegaulois.*;

public class ScenarioCasDegrade {

	public static void main(String[] args) {
		Etal etal = new Etal();
		Gaulois acheteur = new Gaulois("acheteur",1024);
		Gaulois vendeur = new Gaulois("vendeur",1023);
		
		
		etal.occuperEtal(vendeur, "FLEUR", 12);
		System.out.println(etal.acheterProduit(12, acheteur));
		System.out.println("Fin du test");


	}

}
