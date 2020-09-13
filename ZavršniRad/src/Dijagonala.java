import java.awt.geom.Point2D;

public class Dijagonala {

    public static Point2D[][] dijagonale;
    public static int  size;
    public int capacity;

    public Dijagonala(){
        dijagonale = new Point2D[2][2];
        size = 0;
        capacity = 2;
    }

    public void dodajElement(Point2D element1, Point2D element2){
        if(size == capacity){
            omoguciKapacitet(2);
        }

        dijagonale[size][0] = element1;
        dijagonale[size][1] = element2;
        size++;

    }

    public void obrisiElement(int index){
        if(index>=size || index<0){
            System.out.println("No element at this index");
        }else{
            for(int i=index;i<size-1;i++){
                for(int j = 0; j<2; j++){
                    dijagonale[i][j] = dijagonale[i+1][j+1];
                }

            }
            dijagonale[size-1][2]=null;
            size--;
        }
    }

    public void omoguciKapacitet(int minKapacitet){
        Point2D temp[][] = new Point2D[capacity*minKapacitet][2];
        for (int i = 0; i< capacity; i++){
            for(int j = 0; j<2; j++){
                temp[i][j] = dijagonale[i][j];
            }
        }
        dijagonale = temp;
        capacity = capacity * minKapacitet;

    }

    public void trimToSize(){
        Point2D temp[][] = new Point2D[size][2];
        for(int i=0; i<size;i++){
            for(int j = 0; j<2;j++){
                temp[i][j] = dijagonale[i][j];
            }
        }
        dijagonale = temp;
        capacity = dijagonale.length;
    }

    public void remove(int index){
        if(index>=size || index<0){
            System.out.println("No element at this index");
        }else{
            for(int i=index;i<size-1;i++){
                dijagonale[i][0] = dijagonale[i+1][0];
                dijagonale[i][1] = dijagonale[i+1][1];
            }
            dijagonale[size-1][0] = null;
            dijagonale[size-1][1] = null;
            size--;
        }
    }

    public void obrisiDijagonaleKojeSeSjeku(){
        for (int i = 0; i< size; i++){
            for(int k = 0; k<size; k++){
                if(!provjeriDaLiDijagonaleImajuZajednickuTocku(dijagonale[i][0], dijagonale[i][1], dijagonale[k][0], dijagonale[k][1])){
                    Boolean dijagonaleSeSjeku = Poligon.sjekuLiSeLinije(dijagonale[i][0], dijagonale[i][1], dijagonale[k][0], dijagonale[k][1]);
                    if(dijagonaleSeSjeku){
                        if( (dijagonale[i][0].getY()<dijagonale[k][0].getY() || dijagonale[i][0].getY()<dijagonale[k][1].getY() )
                                || (dijagonale[i][1].getY()<dijagonale[k][0].getY() || dijagonale[i][1].getY()<dijagonale[k][1].getY()) ){
                            remove(i);
                        }else{
                            remove(k);
                        }

                    }
                }
            }
        }
    }

    public Boolean provjeriDaLiDijagonaleImajuZajednickuTocku(Point2D vrh1, Point2D vrh2, Point2D vrh3, Point2D vrh4){
        if( ((vrh1 == vrh3) || (vrh1==vrh4)) || ((vrh2==vrh3) || (vrh2==vrh4)) ){
            return true;
        }
        return false;
    }
}
