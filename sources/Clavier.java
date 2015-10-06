import java.io.*;

class Clavier
{

        final private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	/**
	*	Méthode qui permet de lire une valeur entière entrée par l'utilisateur
	*/
	public static int readInt()
	{
		int resultat = 0;
		try {
         		resultat = Integer.parseInt(br.readLine()) ;
        	} catch (IOException ioe ) {}
        	return resultat;
	}

	/**
	*	Méthode qui permet de lire une valeur de type long entrée par l'utilisateur
	*/
	public static long readLong()
	{
		long resultat = 0;
		try {
         		resultat = Long.parseLong(br.readLine()) ;
        	} catch (IOException ioe ) {}
        	return resultat;
	}

	/**
	*	Méthode qui permet de lire une valeur décimale entrée par l'utilisateur
	*/
	public static float readFloat()
	{
		float resultat = 0;
		try {
         		resultat = Float.parseFloat(br.readLine()) ;
        	} catch (IOException ioe ) {}
        	return resultat;
	}

	/**
	*	Méthode qui permet de lire une valeur décimale entrée par l'utilisateur
	*/
	public static double readDouble()
	{
		double resultat = 0;
		try {
         		resultat = Double.parseDouble(br.readLine()) ;
        	} catch (IOException ioe ) {}
        	return resultat;
	}

	/**
	*	Méthode qui permet de lire un caractère entré par l'utilisateur
	*/
	public static char readChar()
	{
		String resultat = "";
		try {
         		resultat = br.readLine();
        } catch (IOException ioe ) {}
		if (resultat.length() > 1){
			return resultat.charAt(0);
		}
		return resultat.charAt(0);
	}

	/**
	*	Méthode qui permet de lire une chaine de caractère entrée par l'utilisateur
	*/
	public static String readString()
	{
		String resultat = "";
		try {
         		resultat = br.readLine();
        	} catch (IOException ioe ) {}
        	return resultat;
	}
}
