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
	
	/*public static String definir(String animal, NoeudArbre arbre) {
		String chemin ="";
		if (arbre.getContenu().equals(animal)) {
			chemin += "==>" + animal;
		}
		else if (arbre.estFeuille()) {	//Sinon si c'est une feuille sans l'animal
			return null;
		}
		else {							//Si c'est un noeud
			
			
			chemin += definir(animal, arbre.getNoeudGauche(), chemin);
		}
		return chemin;
	}*/
	 public String definir(String animal) {
		if(!isQuestion(contenu)) {			//Si c'est une feuille
			if(!contenu.equals(animal)) {
				return "";
			}
			else {
				return "=> " + animal;
			}
		}
		else {
			String retourG = this.gauche.definir(animal);
			String retourD = this.droit.definir(animal);
			if(!retourG.equals("")) {
				return this.contenu + " -> non; " + retourG; 
			}
			else {
			if(!retourD.equals("")) {
				return this.contenu + " -> oui; " + retourD;
			}
			else
				return "";
			}
		}
    }
	
	public static NoeudArbre creerArbre(String args[]) {
		int i = 2;
		NoeudArbre tmp = null;
		if (i<args.length) {
			tmp = creerArbre(args, i);
		}
		return tmp;
	}
	
	public static NoeudArbre creerArbre(String args[], int cursor) {
		NoeudArbre tmp = null;
		if (cursor < args.length) {
			if (!isQuestion(args[cursor])) {	//Si c'est une feuille
				tmp = new NoeudArbre(args[cursor]);
			}
			else {								//Si c'est un noeud
				tmp = new NoeudArbre(args[cursor]);
				tmp.setNoeudGauche(creerArbre(args,++cursor));
				tmp.setNoeudDroit(creerArbre(args,++cursor));
			}
		}
		return tmp;
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
		if (args.length == 3) {
			arbre = new NoeudArbre(args[0]);
			NoeudArbre noeudGauche = new NoeudArbre(args[1]);
			NoeudArbre noeudDroit = new NoeudArbre(args[2]);
			arbre.setNoeudGauche(noeudGauche);
			arbre.setNoeudDroit(noeudDroit);
		}
		else if(args[0].equals("--definir") && args.length > 1) {
			arbre = NoeudArbre.creerArbre(args);
			System.out.println(arbre.definir(args[1]));
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
