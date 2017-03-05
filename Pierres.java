package moteur;

public class Pierres {
	public int type;
	  public int niveau;
	 
	   /* Cr�e une Pierres al�atoire */
	   public Pierres(){
	     this(((int) (Math.random()*6))+1 , ((int) (Math.random()*3))+1);
	   }
	   /* Cr�e une Pierres de type et niveau pr�cis�s */
	   public Pierres(int type, int niveau){
	     this.type=type;
	     this.niveau = niveau;
	   }
	   public int getType(){ return type; }
	   public int getNiveau(){ return niveau; }
}

