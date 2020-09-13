import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String args[]){

        myFrame myFrame = new myFrame();


        GraphicsPaint.popuniListeVrhova(primjerPoligona());

        podjeliPoligonNaMonotoneDijelove();

    }



    private static List<Point2D> primjerPoligona(){
        List<Point2D> D = new ArrayList<Point2D>();

        D.add(new Point(61, 10));
        D.add(new Point(85, 44));
        D.add(new Point(101, 40));
        D.add(new Point(105, 54));
        D.add(new Point(151, 23));
        D.add(new Point(188, 90));
        D.add(new Point(130, 105));
        D.add(new Point(106, 149));
        D.add(new Point(53, 150));
        D.add(new Point(60, 111));
        D.add(new Point(30,80));

        return D;
    }

    public static void podjeliPoligonNaMonotoneDijelove(){
        Poligon.pronadiNajvisiVrhPoligona();
        Poligon.pronadiNajniziVrhPoligona();
        Poligon.sortirajListuPremaNajvisemVrhu();
        Poligon.napraviLanace();
        //Poligon.pronadiPreokretniVrh();
        Poligon.triangulirajPoligon();
        int broj = Dijagonala.dijagonale.length;
        //Poligon.nacrtajDijagonale();
        //Point2D vrh1 = Poligon.pronadiPreokretniVrh().get(1);

        //Point2D vrh2 = new Point.Double(0,vrh1.getY());
        //Poligon.kolikoPutaLinijaSjeceRubovePoligona(vrh1,vrh2);
    }


}
