import java.util.Scanner;

/**
*	@autor Julien LELEU, Thomas FERRO
*/
public class NoeudArbre {
	
	private String contenu;		//contenu du noeud : Question ou animal
	private NoeudArbre gauche;	//Noeud gauche : correspond à NON
	private NoeudArbre droit;	//Noeud droit : correspond à OUI
	
	/**
	* Constructeur de la classe
	*/
	public NoeudArbre(){}
	
	/**
	* Constructeur de la classe
	* @param contenu Le contenu du noeud : Une question ou un animal
	*/
	public NoeudArbre(String contenu) {
		this.contenu = contenu;
		gauche = null;
		droit = null;
	}
	
	/**
	* Constructeur de la classe
	*
	* @param contenu Le contenu du noeud : Une question ou un animal
	* @param gauche Le sous arbre gauche
	* @param droit Le sous arbre droit
	*/
	public NoeudArbre(String contenu, NoeudArbre gauche, NoeudArbre droit) {
		this(contenu);
		this.gauche = gauche;
		this.droit = droit;
	}
	
	/**
	* Méthode qui permet de savoir si le noeud courant est une feuille ou non
	*
	* @return true si c'est une feuille, false si c'est un noeud
	*/
	public boolean estFeuille() {
		return gauche == null && droit == null;
	}
	
	/**
	* Méthode qui permet de mettre en forme la question
	*
	* @return La question si c'est un noeud, l'animal sous forme de question si c'est une feuille 
	*/
	public String afficherQuestion() {
		if (estFeuille()) {
			return "Est-ce " + contenu + " ?";
		}
		return contenu;
	}
	
	/**
	* Méthode qui gère l'intéraction avec l'utilisateur en lui posant notamment des questions
	*/
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
	
	/**
	* Méthode qui permet d'apprendre un nouvel animal avec sa question
	*
	* @param contenuCourant Le nouveau contenu du noeud actuel
	* @param contenuGauche Le nouveau contenu du noeud actuel gauche
	* @param contenuDroit Le nouveau contenu du noeud actuel droit
	*/
	public void apprendre(String contenuCourant, String contenuGauche, String contenuDroit) {
		if (estFeuille()) {
			this.contenu = contenuCourant;
			this.gauche = new NoeudArbre(contenuGauche);
			this.droit = new NoeudArbre(contenuDroit);
		}
	}
	
	/**
	* Méthode qui permet de connaître les réponses aux questions pour trouver l'animal passé en paramètres
	*
	* @param animal L'animal à chercher
	*
	* @return Le chemin des questions/réponses
	*/
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
	
	/**
	* Méthode qui permet de créer un arbre à partir d'un affichage préfixe du contenu
	*
	* @param args[] Arbre sous forme préfixe
	* 
	* @return NoeudArbre L'arbre généré
	*/
	public static NoeudArbre creerArbre(String args[]) {
		int i = 0;			// On commence à 2 car le premier argument correspond à l'argument "--definir", et le 2e a l'animal recherché
		NoeudArbre tmp = null;
		if (i < args.length) {
			tmp = creerArbre(args, i);
		}
		return tmp;
	}
	
	private static NoeudArbre creerArbre(String args[], int cursor) {
		NoeudArbre tmp = null;
		if (cursor < args.length) {
			if (!isQuestion(args[cursor])) {	//Si c'est une feuille
				tmp = new NoeudArbre(args[cursor]);
			}
			else {								//Sinon c'est un noeud
				tmp = new NoeudArbre(args[cursor]);
				tmp.setNoeudGauche(creerArbre(args,++cursor));
				tmp.setNoeudDroit(creerArbre(args,++cursor));
			}
		}
		return tmp;
	}
	
	/**
	* Méthode qui permet de savoir si la chaine passée en paramètre est une question
	* 
	* @param str Chaine à tester
	*
	* @return true si c'est une question, false si c'est un animal
	*/
	public static boolean isQuestion(String str) {
		return str.substring(str.length()-1).equals("?");
	}
	
	/**
	* Getter qui retourne le contenu du noeud courant
	* @return Le contenu du noeud
	*/
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
	
	/**
	* Méthode qui retourne sous forme préfixe l'arbre courant
	*
	* @return L'arbre sous forme préfixe
	*/
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
		if (args.length > 0) {				//On vérifie qu'il y ait un seul argument
			if (args.length == 3 && !args[0].equals("--definir")) {			//Pour la question 5
				arbre = new NoeudArbre(args[0]);
				NoeudArbre noeudGauche = new NoeudArbre(args[1]);
				NoeudArbre noeudDroit = new NoeudArbre(args[2]);
				arbre.setNoeudGauche(noeudGauche);
				arbre.setNoeudDroit(noeudDroit);
			}
			else if(args[0].equals("--definir") && args.length > 2) {		//Pour la question 6
				String arbrePrefixe[] = new String[args.length-2];
				for (int i = 2; i < args.length; i++) {
					arbrePrefixe[i-2] = args[i];
				}
				arbre = NoeudArbre.creerArbre(arbrePrefixe);
				System.out.println(arbre.definir(args[1]));
			}
			else {															//Pour la question 7
				String arbrePrefixe[] = new String[args.length-2];
				for (int i = 2; i < args.length; i++) {
					arbrePrefixe[i-2] = args[i];
				}
				arbre = NoeudArbre.creerArbre(args);
			}
		}
		else {																//Par défaut on créé l'arbre ci-dessous
			arbre = new NoeudArbre("Est-ce un mamifère ?");
			arbre.setNoeudGauche(new NoeudArbre("Un crocodile"));
			arbre.setNoeudDroit(new NoeudArbre("Est-ce qu'il aboie ?"));
			arbre.getNoeudDroit().setNoeudGauche(new NoeudArbre("Un cheval"));
			arbre.getNoeudDroit().setNoeudDroit(new NoeudArbre("Un chien"));
		}
		System.err.println(arbre);											//On affiche l'arbre actuel
		String reponse;
		boolean gameOver = false;
		
	while (!gameOver) {
			arbre.rechercherAnimal();
			System.err.println(arbre);										//On affiche l'arbre final
			System.out.println("Voulez-vous rejouer ?");
			reponse = Clavier.readString();
			if (reponse.equals("non")) {
				gameOver = true;
			}
		}
	}
}
