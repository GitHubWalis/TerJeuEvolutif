package moteur;

import static javax.swing.SwingConstants.CENTER;
import static javax.swing.SwingConstants.EAST;
import static javax.swing.SwingConstants.NORTH;
import static javax.swing.SwingConstants.SOUTH;
import static javax.swing.SwingConstants.WEST;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;


public class InterfaceJeu extends JFrame implements MouseListener {
	
	private IObjetsJeu[][] grid; 
    private Graphic p;
    private int border = 8, cell = 75;
	public  static int rayures = 4;
	public  static int cercles = 6;
    private KeyEvent ke;
    private MouseEvent clic;
    private JTextField jtf;
 
 
    private class KEDispatcher implements KeyEventDispatcher {
        public boolean dispatchKeyEvent(KeyEvent e) {
            if (e.getID() == KeyEvent.KEY_PRESSED) {
                if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_PAUSE) ke = e;
              }
              return false;
         }
    }
 
    /** Retourne un Point lorsque l'utilisateur clique sur une case. */
    public synchronized Point clicCase(){
        try{
            this.wait();
        }
        catch(InterruptedException e){}
        ((JComponent) this.clic.getSource()).transferFocusUpCycle();
        return new Point(this.clic.getX()/this.cell,this.clic.getY()/this.cell);
    }
 
    public InterfaceJeu(int largeur, int hauteur){
        super("JeuEvolutif");
        this.grid = new IObjetsJeu[largeur][hauteur];
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[i].length;j++){
                this.grid[i][j] = null;
            }        
        }
        this.p = new Graphic();
        this.p.setPreferredSize(new Dimension(largeur*this.cell, hauteur*this.cell));
        this.p.addMouseListener(this);
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(this.p,BorderLayout.CENTER);
        //this.getContentPane().add(this.p);
        this.jtf = new JTextField(20);
        this.getContentPane().add(this.jtf,BorderLayout.SOUTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        this.pack();
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new KEDispatcher());
    }
 
    /* Renvoie un entier correspondant à la touche appuyee. CENTER:pause, EAST:fleche droite, WEST:fleche gauche, SOUTH:fleche bas, NORTH:fleche haut
    Renvoie -1 si la touche appuyee n'est ni une fleche ni pause. */
    public int toucheAppuyee(){
        if(this.ke == null) return -1;
        else{
            int result = -1;
            if(this.ke.getKeyCode() == KeyEvent.VK_RIGHT) result = EAST;
            if(this.ke.getKeyCode() == KeyEvent.VK_LEFT) result = WEST;
            if(this.ke.getKeyCode() == KeyEvent.VK_DOWN) result = SOUTH;
            if(this.ke.getKeyCode() == KeyEvent.VK_UP) result = NORTH;
            if(this.ke.getKeyCode() == KeyEvent.VK_PAUSE) result = CENTER;
            this.ke = null;
            return result;
        }
    }
 
    /* Efface la case d'abscisse x et d'ordonnee y */
    public void effaceCase(int x, int y){
        this.grid[x][y] = null;
        this.miseAJour();
    }
 
    public void dessinerObjet1(int x, int y, int niveau){
        this.grid[x][y] = new Objet1(niveau);
        this.miseAJour();
    }
 
    public void dessinerObjet2(int x, int y, int niveau){
        this.grid[x][y] = new Objet2(niveau);
        this.miseAJour();
    }
 
    public void dessinerObjet3(int x, int y, int niveau){
        this.grid[x][y] = new Objet3(niveau);
        this.miseAJour();
    }
 
    public void dessinerObjet4(int x, int y, int niveau){
        this.grid[x][y] = new Objet4(niveau);
        this.miseAJour();
    }
 
    public void dessinerObjet5(int x, int y, int niveau){
        this.grid[x][y] = new Objet5(niveau);
        this.miseAJour();
    }
 
    public void dessinerObjet6(int x, int y, int niveau){
        this.grid[x][y] = new Objet6(niveau);
        this.miseAJour();
    }
 
    public void miseAJour(){
        this.p.repaint();
    }
 
    private class Graphic extends JPanel{
        public void paint(Graphics gr){
            Graphics2D gr2D = (Graphics2D) gr;
            gr2D.setColor(Color.WHITE);
            gr2D.fillRect(0,0,this.getWidth(),this.getHeight());
            gr2D.setStroke(new BasicStroke(2.0f));
            //int offset = 2;
            for(int i=0;i<grid.length;i++){
                for(int j=0;j<grid[i].length;j++){
                    if(grid[i][j] != null) grid[i][j].paint(gr2D, new Point(i,j), cell, border);
                }
            }
        }
    }
 
    public void afficheMessage(String m){
        JOptionPane.showMessageDialog(this,m);
    }
 
    public void afficheTexte(String t){
        this.jtf.setText(t);
    }
    
    
    
    /** @deprecated */
    public void mouseClicked(MouseEvent e){
        this.setClic(e);
        synchronized(this){
            this.notify();
        }
    }
 
    /** @deprecated */
    public void mouseEntered(MouseEvent e){}
 
    /** @deprecated */
    public void mouseExited(MouseEvent e){}
 
    /** @deprecated */
    public void mousePressed(MouseEvent e){}
 
    /** @deprecated */
    public void mouseReleased(MouseEvent e){}
 
    /** @deprecated */
    public void setClic(MouseEvent e){
        this.clic = e;
    }
    

}
