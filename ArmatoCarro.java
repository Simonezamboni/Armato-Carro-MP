import javax.swing.JOptionPane;
import java.util.Timer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.TimerTask;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ArmatoCarro{


    class MyCanvas extends JPanel{

        public void disegnaBandiera (Graphics g)
        {
            g.setColor(new Color(255,0,0));
            g.fillRect(xBand1, yBand1, 15, 15);
            g.setColor(new Color(100,255,255));
            g.drawRect(xBand2, yBand2, 15, 15);
        }

        // Da qui si decide come disegnare il carro
        public void disegnaCarro(Graphics g, int x, int y){
            // Questi due comandi disegnano le ruote di raggio 25 pixel...
            // provate a cambiare il raggio
            g.drawOval(x+20, y+70, 50,50);
            g.drawOval(x+120, y+70, 50,50);
            g.drawOval(x+70, y+70, 50,50);
            // Così si imposta il colore del carro armato. Il colore è indicato
            // da tre numeri: la quantità di rosso, di verde e di blu
            // Il valore minimo di ogni componente è 0, il massimo 255.
            g.setColor(new Color(100,255,255));
            // Qui vengono disegnati i due pezzi rettangolari gli ultimi due numeri sono
            // larghezza e altezza
            g.drawRect(x,y+40,200,40);
            g.drawRect(x+30,y,140,40);
            // Colore del cannone
            g.setColor(new Color(100,255,255));
            // Disegna il cannone, lungo 70pixel e alto 10pixel
            g.drawRect(x+170,y,70,10);
        }

        // Qui disegna il bersaglio
        public void disegnaTarget(Graphics g){
            g.setColor(new Color(255,0,0));
            g.fillOval(xTarget, yTarget, 15,15);
            g.setColor(new Color(255,0,0));
            g.fillOval(xTarget+2, yTarget, 10,10);
        }


        public void paint(Graphics g){
            // Qui si decide il colore dello sfondo...ora è grigio (tutti i
            // numeri uguali fanno tipi di grigio)
            g.setColor(new Color(0,0,50));
            g.fillRect(0, 0, this.getWidth() , this.getHeight());
            g.setColor(new Color(255,255,255));
            disegnaCarro(g,x,y);
            g.setColor(new Color(100,255,50));
            g.fillRect (0, 756, this.getWidth() , this.getHeight());
            g.setColor(new Color(100,255,255));
            if(shoot)
                g.fillRect(xBullet,yBullet,20,10);
            disegnaTarget(g);
        }
    }

    class gestoreEventi  extends MouseAdapter implements KeyListener{

        public boolean up , down , left , right , screen_shot;

        public void     keyPressed(KeyEvent e){}

        public void     keyReleased(KeyEvent e){}

        public void     keyTyped(KeyEvent e){System.out.println(e.getKeyChar());
            // Qui si decide quali tasti servono
            // La D sposta la coordinatax(il carro si sposta in avanti)
            if (e.getKeyChar()=='d' || e.getKeyChar()=='D')
            {
               x+= 5;
            // La A diminuisce la coordinata x(il carro si sposta indietro)
            }
            if (e.getKeyChar()=='a' || e.getKeyChar()=='A')
            {
               x-= 5;
            // La W diminuisce la coordinatay(il carro si sposta in alto)
            }
            if (e.getKeyChar()=='w' || e.getKeyChar()=='W')
            {
               y-= 5;
            // La S aumenta la coordinatay(il carro si sposta in basso)
            }
            if (e.getKeyChar()=='s' || e.getKeyChar()=='S')
            {
               y+= 5;
            }

            //Gestione dei movimenti del bersaglio

            //La I sposta il bersaglio in alto
            if (e.getKeyChar()=='i' || e.getKeyChar()=='D')
            {
                yTarget -= 5;
            }

            //La L sposta il bersaglio avanti
            else if (e.getKeyChar()=='l' || e.getKeyChar()=='L')
            {
                xTarget += 5;
            }

            //La J sposta il bersaglio a sinistra
            else if(e.getKeyChar()=='j' || e.getKeyChar()=='J')
            {
                xTarget -= 5;
            }

            //La K sposta il bersaglio in basso
            else if(e.getKeyChar()=='k' || e.getKeyChar()=='K')
            {
                yTarget+= 5;
            // Lo spazio spara
            }
            else if((e.getKeyChar()==' ')&&(shoot == false))
            {
                xBullet = x+200;
                yBullet = y+20;
                shoot = true;
            }
            canvas.repaint();
        }
    }


    MyCanvas canvas;
    gestoreEventi gestEv;
    float zoomFactor = 10f;
    TimerTask timeEvent;
    boolean direction = true;
    boolean shoot = false;

    int yTarget = 500;
    int xTarget = 100;
    int xBullet,yBullet;
    int CarroWinCount;
    int TargetWinCount;
    int xBand2=200;
    int yBand2=600;
    int xBand1=200;
    int yBand1=100;
    int x;
    int y;

    private void refreshTimer() {
        Timer autoUpdate = new Timer();
        autoUpdate.scheduleAtFixedRate(new TimerTask() {
              @Override
              public void run()
              {
                  if(((Math.abs(y-yBand2)<20)||(Math.abs(yBand2-y)<20))&&((Math.abs(x-xBand2)<20)||(Math.abs(xBand2-x)<20)))
                  {
                      CarroWinCount+=1;
                      JOptionPane.showMessageDialog(null,"Carro Wins!!!");
                  }
                  if (shoot==true){
                      xBullet +=30;
                      // Qui controlla se è stato colpito il bersaglio...ora colpisce il bersaglio
                      // in un area di 10x10. Se volete renderlo più semplice...aumentate il numero
                      if(Math.abs(xBullet-1500)<10 && (Math.abs(yBullet-yTarget))<20)
                      {
                          shoot = false;
                          CarroWinCount+=1;
                          JOptionPane.showMessageDialog(null,"Carro Wins!!!");
                      }
                      if(xBullet>1500)
                      {
                          shoot = false;
                      }
                  }
                  canvas.repaint();
              }
        }, 0, 50);
    }


    public ArmatoCarro(){
       x= 100;y= 100;
        gestEv = new gestoreEventi();
        canvas = new MyCanvas();

        JFrame f = new JFrame("Grafica");
        f.setSize(500, 200);
        canvas.addMouseListener(gestEv);
        //canvas.setDoubleBuffered(true);
        f.addKeyListener(gestEv);

        f.add(canvas);
        f.setVisible(true);
        refreshTimer();
    }

    public static void main(String[] s)
    {
        new ArmatoCarro();
    }
}
