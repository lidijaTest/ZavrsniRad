import org.dyn4j.geometry.Polygon;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class GraphicsPaint extends JPanel {

    public static List<Double> xVrhovi = new ArrayList<>();
    public static List<Double> yVrhovi = new ArrayList<>();

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.setBackground(Color.black);

        Graphics2D g2D = (Graphics2D) g;

        GeneralPath polygon = new GeneralPath(GeneralPath.WIND_EVEN_ODD,xVrhovi.size());
        GeneralPath dijagonala = new GeneralPath();

        polygon.moveTo(xVrhovi.get(0),yVrhovi.get(0));
        int brojVrhova = xVrhovi.size();

        for(int i=1; i<brojVrhova;i++){
            polygon.lineTo(xVrhovi.get(i),yVrhovi.get(i));
        }


        g2D.setColor(Color.GREEN);
        AffineTransform at = new AffineTransform(5.,0.,0.,5.,5.,5.);
        g2D.setTransform(at);
        polygon.closePath();
        g2D.draw(polygon);
        /*g2D.setColor(Color.MAGENTA);
        for(int i = 0; i<Poligon.pronadiPreokretniVrh().size(); i++){
            g2D.drawRect((int)Poligon.pronadiPreokretniVrh().get(i).getX(), (int)Poligon.pronadiPreokretniVrh().get(i).getY(), 1,1);
        }*/
        g2D.setColor(Color.YELLOW);
        for(int i = 0; i<Dijagonala.size; i++){
            dijagonala.moveTo(Dijagonala.dijagonale[i][0].getX(), Dijagonala.dijagonale[i][0].getY());
            for(int j = 0; j<2; j++){
                dijagonala.lineTo(Dijagonala.dijagonale[i][1].getX(), Dijagonala.dijagonale[i][1].getY());
            }
            g2D.draw(dijagonala);
        }

    }

    public static void popuniListeVrhova(List<Point2D> lista){
        Poligon.dohvatiListuVrhova(lista);
        for(int i =0; i<lista.size(); i++){
            xVrhovi.add(lista.get(i).getX());
            yVrhovi.add(lista.get(i).getY());
        }
    }

}
