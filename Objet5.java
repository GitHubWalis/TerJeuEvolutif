package moteur;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.MultipleGradientPaint;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

import moteur.IObjetsJeu;
import moteur.InterfaceJeu;

public class Objet5 implements IObjetsJeu{
	
	Color c = new Color(121, 28, 248); int niveau;
    public Objet5(int niveau){this.niveau = niveau;}
    public void paint(Graphics2D g, Point p, int cell, int border){
        if(niveau==3) g.setColor(Color.WHITE);
        else g.setColor(this.c);
        double d = (cell-2*border)/3.0d;
        GeneralPath tour = new GeneralPath();
        tour.moveTo(p.x*cell+border+d,p.y*cell+border);
        tour.lineTo((p.x+1)*cell-border-d,p.y*cell+border);
        tour.lineTo((p.x+1)*cell-border,p.y*cell+border+d);
        tour.lineTo((p.x+1)*cell-border,(p.y+1)*cell-border-d);
        tour.lineTo((p.x+1)*cell-border-d,(p.y+1)*cell-border);
        tour.lineTo(p.x*cell+border+d,(p.y+1)*cell-border);
        tour.lineTo(p.x*cell+border,(p.y+1)*cell-border-d);
        tour.lineTo(p.x*cell+border,p.y*cell+border+d);
        tour.closePath();
        if(this.niveau == 1){
            Point2D start = new Point2D.Float(0, 0);
            Point2D end = new Point2D.Float(0, cell/InterfaceJeu.rayures);
            float[] dist = {0.45f, 0.5f};
            Color[] colors = {c, Color.WHITE};
            LinearGradientPaint pai = new LinearGradientPaint(start, end, dist, colors, MultipleGradientPaint.CycleMethod.REPEAT);
            g.setPaint(pai);
        }
        if(this.niveau == 2){
            Point2D center = new Point2D.Float(p.x*cell+cell/2.0f,p.y*cell+cell/2.0f);
            float radius = cell/InterfaceJeu.cercles;
            Point2D focus = new Point2D.Float(p.x*cell+cell/2.0f,p.y*cell+cell/2.0f);
            float[] dist = {0.45f, 0.5f};
            Color[] colors = {c, Color.WHITE,};
            RadialGradientPaint pai = new RadialGradientPaint(center,radius,focus,dist,colors, MultipleGradientPaint.CycleMethod.REPEAT);
            g.setPaint(pai);
        }
        g.fill(tour);
    }

}

