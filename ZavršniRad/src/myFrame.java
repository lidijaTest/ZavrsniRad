import javax.swing.*;

public class myFrame extends JFrame {

    GraphicsPaint graf = new GraphicsPaint();

    public myFrame(){
        this.setSize(1000,1000);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(graf);
        this.setVisible(true);
    }
}
