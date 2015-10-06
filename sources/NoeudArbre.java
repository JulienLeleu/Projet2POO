import java.util.Scanner;

public class NoeudArbre {
	
	private String contenu;		//contenu du noeud : Question ou animal
	private NoeudArbre gauche;	//Noeud gauche : correspond à NON
	private NoeudArbre droit;	//Noeud droit : correspond à OUI
	
	public NoeudArbre(){}
	
	public NoeudArbre(String contenu) {
		this.contenu = contenu;
		gauche = null;
		droit = null;
	}
	
	public NoeudArbre(String contenu, NoeudArbre gauche, NoeudArbre droit) {
		this(contenu);
		this.gauche = gauche;
		this.droit = droit;
	}
	
	/**
	* 
	*/
	public boolean estFeuille() {
		return gauche == null && droit == null;
	}
	
	public String afficherQuestion() {
		if (estFeuille()) {
			return "Est-ce " + contenu + " ?";
		}
		return contenu;
	}
	
	public void rechercherAnimal() {
		NoeudArbre tmp = this;
		String reponse = null;
		do {
			System.out.println(tmp.afficherQuestion());
			reponse = Clavier.readString();
			
			if (reponse.equals("oui")) {
				if (tmp.estFeuille()) {
					System.out.println("J'ai trouvé !");
					break;
				}
				else {
					tmp = tmp.droit;
				}
			}
			else if (reponse.equals("non"))  {
				if (tmp.estFeuille()) {
					System.out.println("Qu'est-ce que c'est ?");
					String contenuDroit = Clavier.readString();
					System.out.println("Qu'est ce qui les distingue ?");
					String contenuCourant = Clavier.readString();
					tmp.apprendre(contenuCourant, tmp.getContenu(), contenuDroit);
					break;
				}
				else {
					tmp = tmp.gauche;
				}
			}
		} while (true);
	}
	
	public void apprendre(String contenuCourant, String contenuGauche, String contenuDroit) {
		if (estFeuille()) {
			this.contenu = contenuCourant;
			this.gauche = new NoeudArbre(contenuGauche);
			this.droit = new NoeudArbre(contenuDroit);
		}
	}
	
	public String definir(String animal) {
		return null;
	}
	
	public static NoeudArbre creerArbre(String args[], int cursor) {
		if(args.length > 1 && i < args.length) {
			NoeudArbre arbre = new NoeudArbre(args[1]);
			NoeudArbre tmp = arbre;
			for (int i = 1; i < args.length; i++) {
				if (isQuestion(tmp)) {
					
				}
			}
		}
		return null;
	}
		
	public static boolean isQuestion(String str) {
		return str.substring(str.length()-1).equals("?");
	}
	
	public String getContenu() {
		return contenu;
	}
	
	public NoeudArbre getNoeudGauche() {
		return gauche;
	}
	
	public NoeudArbre getNoeudDroit() {
		return droit;
	}
	
	public void setNoeudGauche(NoeudArbre gauche) {
		this.gauche = gauche;
	}
	
	public void setNoeudDroit(NoeudArbre droit) {
		this.droit = droit;
	}
	
	public String toString() {
		String str = "\"" + contenu + "\"";
		if (gauche != null) {
			str += " " + gauche.toString();
		}
		if (droit != null) {
			str += " " + droit.toString();
		}
		return str;
	}
	
	public static void main(String args[]) {
		NoeudArbre arbre = null;
		if (args.length >= 3) {
			arbre = new NoeudArbre(args[0]);
			NoeudArbre noeudGauche = new NoeudArbre(args[1]);
			NoeudArbre noeudDroit = new NoeudArbre(args[2]);
			arbre.setNoeudGauche(noeudGauche);
			arbre.setNoeudDroit(noeudDroit);
		}
		else if(args[0].equals("--definir") && args.length > 1) {
			//NoeudArbre.creerArbre(args);
			//System.out.println(NoeudArbre.isQuestion("est-ce une question "));
		}
		else {
			arbre = new NoeudArbre("Est-ce un mamifère ?");
			arbre.setNoeudGauche(new NoeudArbre("Un crocodile"));
			arbre.setNoeudDroit(new NoeudArbre("Est-ce qu'il aboie ?"));
			arbre.getNoeudDroit().setNoeudGauche(new NoeudArbre("Un cheval"));
			arbre.getNoeudDroit().setNoeudDroit(new NoeudArbre("Un chien"));
		}
		System.out.println(arbre);
		
		String reponse;
		boolean gameOver = false;
		
		while (!gameOver) {
			arbre.rechercherAnimal();
			System.out.println("Voulez-vous rejouer ?");
			reponse = Clavier.readString();
			if (reponse.equals("non")) {
				gameOver = true;
			}
		}
	}
}

/*
	
*/



