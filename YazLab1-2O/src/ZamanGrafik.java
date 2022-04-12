
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Omer
 */
public class ZamanGrafik extends JFrame {
public double saniye;
public double kare;
public final int uzunluk=600;
    public ZamanGrafik(double saniye,double kare) {
        this.saniye = saniye/5.0;
        this.kare=kare/10000.0;
        setBounds(800, 0, 700, 800);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    @Override
    public void paint (Graphics g)
    {
        super.paint(g);
//       Graphics2D graf = (Graphics2D)g;
//       graf.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//       Shape cizgi = new Line2D.Float(20,90,55,250);
            Graphics2D g2d = (Graphics2D) g.create();
           // g.setColor(Color.red);
            g2d.drawLine(50, 50,50, uzunluk);
            g2d.drawLine(50, uzunluk,1050, uzunluk);
            int k=0;
            for (int i = uzunluk; i > 50; i-=50) {
                
            String str = Integer.toString(k);
                g2d.drawString(str, 30, i);
                k+=10000;
        }
            k=0;
            for (int i = 50; i < 850; i+=50) {
                
            String str = Integer.toString(k);
                g2d.drawString(str, i, uzunluk+20);
                g2d.drawOval(i, uzunluk, 5, 5);
                k+=5;
        }
            g2d.setStroke(new BasicStroke(4));
            Shape cizgi = new Line2D.Double(50, uzunluk, (saniye*50)+50, (double) (uzunluk-(kare*50)));
            g2d.draw(cizgi);

            
            
            
            g2d.setFont( new Font("SansSerif", Font.BOLD, 20));
            g2d.setColor(Color.RED);
            g2d.drawString("Çözülen Kare Sayısı : " +(int)(kare*10000)+ " \n" , 320, 100);
            g2d.drawString("Geçen Süre : " +saniye*5+ " saniye" , 320, 140);
            
            
            
            
            
             // Create a copy of the Graphics instance
  

  // Set the stroke of the copy, not the original 
  Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,0, new float[]{4}, 0);
  
  g2d.setStroke(dashed);

//  
//  g2d.drawLine(50, (int)(uzunluk-(saniye*28)), (kare*28)+50,(int)(uzunluk-(saniye*28)) );
//  g2d.drawLine((kare*28)+50, uzunluk, (kare*28)+50, (double)(uzunluk-(saniye*28)));
  
  Shape cizgim = new Line2D.Double(50, (uzunluk-(kare*50)), (saniye*50)+50,(uzunluk-(kare*50)) );
     g2d.draw(cizgim);
     Shape cizgim2 = new Line2D.Double((saniye*50)+50, uzunluk, (saniye*50)+50, (uzunluk-(kare*50)));
     g2d.draw(cizgim2);

  // Get rid of the copy
  g2d.dispose();
            
    }
}
