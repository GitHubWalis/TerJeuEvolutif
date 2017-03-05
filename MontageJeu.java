package moteur;

import java.awt.Point;
import moteur.Score;
import javax.swing.JOptionPane;

public class MontageJeu {
	
    static class PartieJeu    // varibale de classe static
    {
        Pierres[][] plateau;
        int points;
    }
 
    static class AlignementPierres  // varibale de classe static
    {
        Point debut,fin;
    }
 
    static PartieJeu creerPartieJeu (int l, int h) // varibale de classe static qui représente la Creation d'une nouvelle PartieJeu 
    {
        PartieJeu p = new PartieJeu();
        p.plateau = new Pierres[h][l];
        for (int i=0 ; i<h; i++)
        {
            for(int j=0 ; j<l ; j++)
            {
                p.plateau[i][j] = new Pierres();
                p.plateau[i][j].type = ((int) (Math.random()*6))+1;
                p.plateau[i][j].niveau = ((int) (Math.random()*3))+1;
 
            }
        }
        return p;
    }
 
    static void affichage (PartieJeu p, InterfaceJeu toto, int h, int l) // Affiche les differents types de Pierress dans une fonction
    {
        for (int i=0 ; i<h; i++)
        {
            for(int j=0 ; j<l ; j++)
            {
                if(p.plateau[i][j].type == 1)
                    toto.dessinerObjet1(i,j,0);
                if(p.plateau[i][j].type  == 2)
                    toto.dessinerObjet2(i,j,0);
                if(p.plateau[i][j].type == 3)
                    toto.dessinerObjet3(i,j,0);
                if(p.plateau[i][j].type == 4)
                    toto.dessinerObjet4(i,j,0);
                if(p.plateau[i][j].type == 5)
                    toto.dessinerObjet5(i,j,0);
                if(p.plateau[i][j].type == 6)
                    toto.dessinerObjet6(i,j,0);
             }
        }
    }
 
    static AlignementPierres AlignementPierresMethode (PartieJeu p) // Fonction qui detecte les AlignementPierress en cours de PartieJeu
    {
        int k;
        p.points = 0; // le paramètre point augmente d'une unité à chaque alignement
        for (int i=0 ; i<p.plateau.length; i++)
        {
            for(int j=0 ; j<p.plateau[i].length; j++)
            {
                k=1;
 
                while(i+k<p.plateau.length && p.plateau[i][j].type == p.plateau[i+k][j].type) // Tant que la case qui suit i est du meme type on continue (AlignementPierres horizontale)
                {
                    k = k+1; //
                }
 
                if(k>2) // Si k est superieur a  2 alors il y a un AlignementPierres
                {
                    AlignementPierres a = new AlignementPierres();
                    a.debut = new Point();
                    a.debut.x = i;
                    a.debut.y = j;
                    a.fin = new Point();
                    a.fin.x = i+k-1;
                    a.fin.y = j;
                    p.points = p.points +1;
                    Score.getScore().incrementScore(1);
                    return a; 
                    
                }
 
                k=1;
                while(j+k<p.plateau[i].length && p.plateau[i][j].type == p.plateau[i][j+k].type)
                {
                    k = k+1;
                }
 
                if(k>2)
                {
                    AlignementPierres a = new AlignementPierres();
                    a.debut = new Point();
                    a.debut.x = i;
                    a.debut.y = j;
                    a.fin = new Point();
                    a.fin.x = i;
                    a.fin.y = j+k-1;
                    p.points = p.points +1;
                    Score.getScore().incrementScore(1);
                    return a;
                }
 
            }
        }
        return null;
    }
    static PartieJeu DecalerPierres (PartieJeu p, AlignementPierres a)
    {
        if(a.debut.y == a.fin.y) //AlignementPierres horizontal
        {
            for(int i = a.debut.x ; i<= a.fin.x ; i++)
            {
                for(int j = a.debut.y ; j>=1 ; j--)
                {
 
                    p.plateau[i][j] = p.plateau[i][j-1];
 
 
                }
                p.plateau[i][0] = new Pierres();
            }
            return p;
        }
        if(a.debut.x == a.fin.x) // AlignementPierres vertical
        {
 
            for(int j = 0 ; a.fin.y-j >= 0 ; j++)
            {
                if(a.debut.y-1-j >= 0)
                {
                    p.plateau[a.debut.x][a.fin.y-j] = p.plateau[a.debut.x][a.debut.y-1-j];
                }
                else
                {
                    p.plateau[a.debut.x][a.fin.y-j] = new Pierres();
                }
            }
        }
        return p;
 
    }
 
    public static void main(String[] args)
    {
    	int compteurCoups= 2;
        int l,h,min,max,temporaire;
        Point p1 = new Point();
        Point p2 = new Point();
        l = 12;
        h = 12;
        min = 1;
        max = 6;
        AlignementPierres a1;
        InterfaceJeu toto = new InterfaceJeu(h,l);
        
        JOptionPane jop = new JOptionPane();
        String nom = jop.showInputDialog(toto, "Veuillez saisir votre nom !", " Bienvenu dans le monde des Gamers", JOptionPane.QUESTION_MESSAGE);
        
        JOptionPane jop1 = new JOptionPane();
        jop1.showMessageDialog(toto, nom + ", tu souhaites jouer au Jeu evolutif. Bien, saches que tu dispose de 25 coups et que ton score sera indiqué à la fin du jeu", "Information au début du Jeu", JOptionPane.INFORMATION_MESSAGE);
        
        PartieJeu p = creerPartieJeu(12,12);
        a1 = AlignementPierresMethode(p);
   
        while (a1 != null){      

        	a1 = AlignementPierresMethode (DecalerPierres(p,a1));
        
        }
       
        while (compteurCoups>0) {
	            affichage(p,toto,h,l);
	            p1 = toto.clicCase();    //Nouveau point afin d'inverser 2 Pierress
	            System.out.println(p1.x + " " + p1.y); //Garde en memoire le p1
	            p2 = toto.clicCase();
	            System.out.println(p2.x + " " + p2.y);
	            //inverse 2 points
	            if((p1.x==p2.x && Math.abs(p1.y-p2.y)==1) || (p1.y==p2.y && Math.abs(p1.x-p2.x)==1)) //
	            { 
	                temporaire = p.plateau[p1.x][p1.y].type; 
	                p.plateau[p1.x][p1.y].type = p.plateau[p2.x][p2.y].type;
	                p.plateau[p2.x][p2.y].type = temporaire;
	                affichage(p,toto,h,l);
	                compteurCoups=compteurCoups-1;
	                AlignementPierres a = AlignementPierresMethode(p);
	                while(a!=null){
	                System.out.println(a.debut.x+","+a.debut.y+"   ->    "+a.fin.x+","+a.fin.y);
	                a = AlignementPierresMethode (DecalerPierres(p,a));
	                }
	            }
        }
        
        JOptionPane jop2 = new JOptionPane();
        jop2.showMessageDialog(toto, "Vous avez épuisé vos 25 Coups. Votre score est:"+ Score.getScore().afficherScore(), "Informtion Fin de partie", JOptionPane.INFORMATION_MESSAGE);
            
        toto.dispose();
        
    }
}
