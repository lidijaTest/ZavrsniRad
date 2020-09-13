import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import math.geom2d.line.*;

import java.util.*;

import static math.geom2d.line.LineSegment2D.intersects;

public class Poligon {

    private static List<Point2D> listaVrhova = new ArrayList<Point2D>();
    private static List<Point2D> listaVrhovaOriginal = new ArrayList<Point2D>();
    private static List<Point2D> poredanaListaVrhova = new ArrayList<Point2D>();
    private static List<Point2D> lijeviLanacVrhova = new ArrayList<Point2D>();
    private static List<Point2D> desniLanacVrhova = new ArrayList<Point2D>();
    public static Point2D najvisiVrh = new Point();
    public static Point2D najniziVrh = new Point();
    public static List<Point2D> preokretniVrhovi = new ArrayList<Point2D>();
    public static List<Line2D> linijeTemp = new ArrayList<Line2D>();
    public static List<Point2D> lijevoOd = new ArrayList<Point2D>();


    public static void dohvatiListuVrhova(List<Point2D> lista){
        listaVrhova = lista;
    }

    public static List<Point2D> vratiListuVrhova(){
        return listaVrhova;
    }

    /*public static List<Point2D> sortirajListuVrhova(List<Point2D> lista){ sortira mi sve liste tako!
        Collections.sort(lista, new Comparator<Point2D>() {
            public int compare(Point2D p1, Point2D p2){
                return (int)(p1.getY() - p2.getY());
            }
        });
        return lista;
    }*/

    public static void bubbleSort(List<Point2D> a){
        boolean sorted = false;
        Point2D temp;
        while(!sorted){
            sorted=true;
            for(int i = 0; i<a.size()-1; i++){
                if(a.get(i).getY()>a.get(i+1).getY()){
                    temp = a.get(i);
                    a.set(i, a.get(i+1));
                    a.set(i+1,temp);
                    sorted = false;
                }
            }
        }
    }

    public static void pronadiNajniziVrhPoligona(){
        Point2D najniziVrhTemp = new Point();

        for(int i = 0; i<listaVrhova.size(); i++){
            if(i == 0){ //ako je vrh prvi u listi, postavi da je najmanji
                najniziVrhTemp = listaVrhova.get(i);
            }else {
                if(listaVrhova.get(i).getY() > najniziVrhTemp.getY()){
                    najniziVrhTemp = listaVrhova.get(i);
                }else if (listaVrhova.get(i).getY() == najniziVrhTemp.getY()){
                    if(najniziVrhTemp.getX()>listaVrhova.get(i).getX()){
                        najniziVrhTemp = listaVrhova.get(i);
                    }
                }
            }

        }

        najniziVrh = najniziVrhTemp;
    }

    public static void pronadiNajvisiVrhPoligona(){
        Point2D najvisiVrhTemp = new Point();

        for(int i = 0; i<listaVrhova.size(); i++){
            if(i == 0){ //ako je vrh prvi u listi, postavi da je najveći
                najvisiVrhTemp = listaVrhova.get(i);
            }else {
                if(listaVrhova.get(i).getY() < najvisiVrhTemp.getY()){
                    najvisiVrhTemp = listaVrhova.get(i);
                }else if (listaVrhova.get(i).getY() == najvisiVrhTemp.getY()){
                    if(najvisiVrhTemp.getX()<listaVrhova.get(i).getX()){
                        najvisiVrhTemp = listaVrhova.get(i);
                    }
                }
            }

        }
        najvisiVrh = najvisiVrhTemp;
    }

    public static void napraviLanace(){
        List<Point2D> desnaLista = new ArrayList<Point2D>();
        List<Point2D> lijevaLista = new ArrayList<Point2D>();
        for(int i = 0; i<listaVrhova.size(); i++){
            if(listaVrhova.get(i).equals(najvisiVrh)){
                for(int j=i; j<listaVrhova.size(); j++){
                    if(listaVrhova.get(j).equals(najniziVrh)){
                        desnaLista.add(listaVrhova.get(j));
                        break;
                    }else{
                        desnaLista.add(listaVrhova.get(j));
                    }
                }
            }
        }
        desniLanacVrhova = desnaLista;

        for (int i = 0; i<listaVrhova.size(); i++){
                if(desnaLista.contains(listaVrhova.get(i))){ //ako desna lista ima vrh
                    if(listaVrhova.get(i).equals(najniziVrh)){ //ako je taj vrh najmanji dodaj ga u lijevu listu
                        lijevaLista.add(listaVrhova.get(i));
                    }
                }
                else{
                    lijevaLista.add(listaVrhova.get(i));
                }
        }

        lijeviLanacVrhova = lijevaLista;
    }

    public static List<Point2D> pronadiPreokretniVrh(){
        List<Point2D> preokretniVrh = new ArrayList<Point2D>();
        for (int i = desniLanacVrhova.size()-1; i>= 0; i--){
            if(i != 0){
                if(desniLanacVrhova.get(i).getY() > desniLanacVrhova.get(i-1).getY() && desniLanacVrhova.get(i)!=najvisiVrh && desniLanacVrhova.get(i)!=najniziVrh){
                    preokretniVrh.add(desniLanacVrhova.get(i));
                }
            }
        }
        for (int i = lijeviLanacVrhova.size()-1; i>= 0; i--){
            if(i != 0){
                if(lijeviLanacVrhova.get(i).getY() < lijeviLanacVrhova.get(i-1).getY() && lijeviLanacVrhova.get(i)!=najvisiVrh && lijeviLanacVrhova.get(i)!=najniziVrh){
                    preokretniVrh.add(lijeviLanacVrhova.get(i));
                }
            }
        }
        preokretniVrhovi = preokretniVrh;
        return preokretniVrh;
    }


    public static void sortirajListuPremaNajvisemVrhu(){
        List<Point2D> listaVrhovaTemp = new ArrayList<Point2D>();
        List<Point2D> listaVrhovaTemp1 = new ArrayList<Point2D>();
        for (int i = 0; i<listaVrhova.size(); i++){
            if(listaVrhova.get(i)==najvisiVrh){
                for(int j = i; j<listaVrhova.size(); j++){
                    listaVrhovaTemp.add(listaVrhova.get(j));
                }
            }
        }
        for(int i =0; i<listaVrhova.size(); i++){
            if(!(listaVrhovaTemp.contains(listaVrhova.get(i)))){
                listaVrhovaTemp.add(listaVrhova.get(i));
            }
        }
        poredanaListaVrhova = listaVrhovaTemp;
    }

    public static Point2D izračunajSjeciste(Point2D s1, Point2D s2, Point2D d1, Point2D d2){
        double a1 = s2.getY() -s1.getY();
        double b1 = s1.getX()  -s2.getX();
        double c1 = a1 * s1.getX() + b1 * s1.getY() ;

        double a2 = d2.getY()  -d1.getY() ;
        double b2 = d1.getX() -d2.getX();
        double c2 = a2 * s1.getX() + b2 * s1.getY() ;

        double delta = a1 * b2 - a2 * b1;

        return new Point2D.Float((float)((b2 * c1 - b1 * c2) / delta), (float) ((a1 * c2 - a2 * c1) / delta));
    }

    public static Point2D nacrtajDijagonale(){
        for(int i = 0; i<preokretniVrhovi.size(); i++){
            Point2D dijagonala = new Point((int)preokretniVrhovi.get(i).getX(),0);
            //izračunajSjeciste(preokretniVrhovi.get(i), dijagonala,
                    //pronadiLinijuSLijeveStrane(preokretniVrhovi.get(i)).get(i),
                    //pronadiLinijuSLijeveStrane(preokretniVrhovi.get(i)).get(i+1))));
        }
        return najvisiVrh;
    }

    public static List<Point2D> pronadiLinijuSLijeveStrane(Point2D preokretniVrh){
        List<Point2D> linija = new ArrayList<>();
        for(int i = 0; i<lijeviLanacVrhova.size(); i++){
            if(lijeviLanacVrhova.get(i).getY() < preokretniVrh.getY() && lijeviLanacVrhova.get(i+1).getY() > preokretniVrh.getY()){

                linija.add(lijeviLanacVrhova.get(i));
                linija.add(lijeviLanacVrhova.get(i+1));
            }
        }
        return  linija;
    }


    public final static double izracunajKutVrha(Point2D P1, Point2D P2, Point2D P3){
        double kut = Math.toDegrees(Math.atan2(P3.getY()-P1.getY(), P3.getX()-P1.getX()) - Math.atan2(P2.getY()-P1.getY(), P2.getX()-P1.getX()));
        return kut;
    }

    public static boolean sjekuLiSeLinije(Point2D vrh1, Point2D vrh2, Point2D vrh3, Point2D vrh4){
        Line2D linija1 = new Line2D.Double(vrh1,vrh2);
        Line2D linija2 = new Line2D.Double(vrh3,vrh4);
        boolean sjekuSe = linija1.intersectsLine(linija2);
        if(sjekuSe) {
            linijeTemp.add(linija2);
        }
        return sjekuSe;
    }

    public static int kolikoPutaLinijaSjeceRubovePoligona(Point2D vrh1, Point2D vrh2){
        int brojac = 0;
        for(int i = 0; i<listaVrhovaOriginal.size()-1; i++) {
            if (((vrh1.equals(listaVrhovaOriginal.get(i)) && vrh2.equals(listaVrhovaOriginal.get(i + 1))) ||
                    (vrh2.equals(listaVrhovaOriginal.get(i)) && vrh1.equals(listaVrhovaOriginal.get(i + 1))))) {
                return brojac;
            }
        }
        for(int j = 0; j<listaVrhovaOriginal.size()-1; j++){
            if(sjekuLiSeLinije(vrh1, vrh2, listaVrhovaOriginal.get(j), listaVrhovaOriginal.get(j+1))){
                brojac ++;
            }
        }
        return brojac;
    }



    public static List<Point2D> lijevoOd(Point2D vrh1, Point2D vrh2){
        for(int i = 0; i<lijeviLanacVrhova.size()-1; i++){
            if(sjekuLiSeLinije(vrh1, vrh2, lijeviLanacVrhova.get(i), lijeviLanacVrhova.get(i+1))){
                lijevoOd.add(lijeviLanacVrhova.get(i));
                lijevoOd.add(lijeviLanacVrhova.get(i+1));
            }
        }
        return lijevoOd;
    }

    public static void triangulirajPoligon(){

        listaVrhovaOriginal = poredanaListaVrhova;
        Stack<Point2D> S = new Stack<Point2D>(); //stack vrhova
        List<Point2D> U = new ArrayList<Point2D>(); //sortirani niz
        List<Point2D> vrhoviTemp = new ArrayList<Point2D>();
        int brojVrhovaKojiSuIzbaceni;
        Dijagonala d = new Dijagonala();
        int sizeTemp = S.size();

        bubbleSort(listaVrhova); //poredaj vrhove prema y-koordinatama
        U = listaVrhova;

        S.push(U.get(0));
        S.push(U.get(1));

        for(int i = 2; i<poredanaListaVrhova.size()-1; i++){
            //provjeri da li su uj i vrh na vrhu niza na različitim lancima
            /*if(desniLanacVrhova.contains(S.firstElement()) && lijeviLanacVrhova.contains(S.firstElement())){
                vrhoviTemp.add(S.firstElement()); //ako se nalazi na oba lanca, vrh je najmanji ili najveći, preskoči
                S.removeElementAt(0);
            }*/
            if((lijeviLanacVrhova.contains(U.get(i)) && desniLanacVrhova.contains(S.firstElement()) ) ||
                    (desniLanacVrhova.contains(U.get(i)) && lijeviLanacVrhova.contains(S.firstElement())) ){
                brojVrhovaKojiSuIzbaceni = 0;
                sizeTemp = S.size();
                for (int j = 0; j<sizeTemp; j++){
                    vrhoviTemp.add(S.pop());
                    brojVrhovaKojiSuIzbaceni++;
                }
                for(int k = 1; k<brojVrhovaKojiSuIzbaceni-1; k++){
                    if(jeLiDijagonalaUnutarPoligona(U.get(i),vrhoviTemp.get(k))){
                        d.dodajElement(U.get(i),vrhoviTemp.get(k));
                    }
                }
                S.push(U.get(i));
                S.push(U.get(i));
                vrhoviTemp.clear();
            }else{
                brojVrhovaKojiSuIzbaceni = 0;
                sizeTemp = S.size();
                /*if(sizeTemp>1) {
                    vrhoviTemp.add(S.pop());
                    sizeTemp = S.size();
                }*/
                vrhoviTemp.add(S.pop());
                sizeTemp = S.size();
                for (int j = sizeTemp-1; j>=0; j--){
                    if(jeLiDijagonalaUnutarPoligona(S.elementAt(j), U.get(i))){
                        d.dodajElement(U.get(i),S.elementAt(j));
                        vrhoviTemp.add(S.pop());
                        brojVrhovaKojiSuIzbaceni++;
                    }
                }
                /*for(int k = 0; k<brojVrhovaKojiSuIzbaceni-1; k++){
                    d.dodajElement(U.get(i),vrhoviTemp.get(k));
                }*/
                S.push(vrhoviTemp.get(vrhoviTemp.size()-1));
                S.push(U.get(i));
                vrhoviTemp.clear();
            }
        }
        sizeTemp = S.size();
        for (int j = 0; j<sizeTemp; j++){
            if(!(j!=0 || j!=sizeTemp-1)){ //ovo provjeri?
                if(jeLiDijagonalaUnutarPoligona(U.get(listaVrhova.size()-1), S.elementAt(j))){
                    d.dodajElement(U.get(listaVrhova.size()-1), S.elementAt(j));
                }
            }
        }
        d.trimToSize();
    }

    public static boolean jeLiDijagonalaUnutarPoligona(Point2D vrh, Point2D drugiVrh){

        int x = (int)(vrh.getX()+drugiVrh.getX())/2;
        int y = (int)(vrh.getY()+drugiVrh.getY())/2;

        Point2D sredinaDijagonale = new Point(x,y);
        Point2D horizontala = new Point(300,(int)sredinaDijagonale.getY());
        //Line2D dijagonala2 = new Line2D.Double(vrh,drugiVrh);

        if(kolikoPutaLinijaSjeceRubovePoligona(vrh,drugiVrh)==0){
            return false; //linija je rub poligona
        }else if(kolikoPutaLinijaSjeceRubovePoligona(sredinaDijagonale, horizontala) % 2 == 0){
            linijeTemp.clear();
            return false; //linija je izvan poligona
        }

        return true;
    }
}
