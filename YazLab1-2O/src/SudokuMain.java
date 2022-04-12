
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author omer
 */
public class SudokuMain {
    static int [][] mdizi1= new int[9][9];
    static int [][] mdizi2= new int[9][9];
    static int [][] mdizi3= new int[9][9];
    static int [][] mdizi4= new int[9][9];
    static int [][] mdizi5= new int[9][9];
  static  String [][] orj = new String [21][21];
    static String [][] sdiz = new String [21][21];
   
      static int [][] diz = new int[21][21]; // asıl 21 integerli dizi
       public static ArrayList<ArrayList<String>> arraym = new ArrayList();
    
    private static final int GRID_SIZE = 9;
    public static JButton[][] dizi = new JButton[9][9];
    public static JTextField[][] dizi_21 = new JTextField[21][21];
    
    public static JButton[][] dizi2 = new JButton[9][9];


    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocation(0, 0);

        // frame.setLayout(new GridLayout(9,9));

        YirmiBirPanel fpanel = new YirmiBirPanel();
        
        //frame.add(panel);
        YeniPanel yeni = new YeniPanel();
           JPanel menu = new JPanel();
        menu.setLayout(new GridBagLayout());
        GridBagConstraints menuConstraints = new GridBagConstraints();

//      frame.add(yeni);
        JButton bt = new JButton("5 Threadli Çözüm");
        Font font1s = new Font("SansSerif", Font.BOLD, 20);
                bt.setFont(font1s);
       // yeni.add(bt);
       menu.add(bt);
        DiziYap();
        bt.addActionListener(new ActionListener() { //BUTONA TIKLAMA EVENTİ
            @Override
            public void actionPerformed(ActionEvent e) {
                /*  if (sudokuCoz(sudokDizi)) {
      System.out.println("Solved successfully!");
    }
    else {
      System.out.println("Unsolvable sudokDizi :(");
    }
    
     SudokuBas(sudokDizi,dizi);
     SudokuBas(sudokDizi,dizi2);*/
                // printBoard(sudokDizi);
                long baslangic =  System.nanoTime();
                DosyaIslem sil = new DosyaIslem();
                sil.DosyaSil();

//                ThIslem i1 = new ThIslem(mdizi1, dizi_21, 0,0,0,6,6,0,0,true,1);
//                Thread t1 = new Thread(i1);
//                t1.start();
//                   
//                  
//
//                
//                ThIslem i2 = new ThIslem(mdizi3, dizi_21, 0,12,0,0,6,6,0,true,3);
//                Thread t2 = new Thread(i2);
//                System.out.println(" --> " + t2.isAlive());
//                t2.start();
//                
//                  ThIslem i3 = new ThIslem(mdizi2, dizi_21, 0,0,12,6,0,0,6,true,2);
//                Thread t3 = new Thread(i3);
//                System.out.println(" --> " + t3.isAlive());
//                t3.start();
//                
////                
//                 ThIslem i4 = new ThIslem(mdizi4, dizi_21, 0,12,12,0,0,6,6,true,4);
//                Thread t4 = new Thread(i4);
//                System.out.println(" --> " + t4.isAlive());
//                t4.start();
//                
//                
//                ThIslem2 i5 = new ThIslem2(mdizi5, dizi_21, 0,6,6,0,0,0,0,false,5);
//                Thread t5 = new Thread(i5);
//                System.out.println(" --> " + t5.isAlive());
//                t5.start();
              
                ExecutorService executor = Executors.newFixedThreadPool(5);
                executor.execute(new ThIslem(mdizi1, dizi_21, 0,0,0,6,6,0,0,true,1));
                executor.execute(new ThIslem(mdizi3, dizi_21, 0,12,0,0,6,6,0,true,3));
                executor.execute(new ThIslem(mdizi2, dizi_21, 0,0,12,6,0,0,6,true,2));
                
                executor.execute(new ThIslem(mdizi4, dizi_21, 0,12,12,0,0,6,6,true,4));
                executor.execute(new ThIslem2(mdizi5, dizi_21, 0,6,6,0,0,0,0,false,5));
               executor.shutdown();
                try {
                    boolean finished = executor.awaitTermination(1, TimeUnit.MINUTES);
                    
                    long sonlanma = System.nanoTime();
long gecenZaman = sonlanma - baslangic;
System.out.println(gecenZaman);
double seconds = (double)gecenZaman/1000000000;
        System.out.println("Saniye --> " + seconds );
                    System.out.println("Toplam çözülen kare sayısı : " + ThIslem.kare);
                    ZamanGrafik z = new ZamanGrafik(seconds,ThIslem.kare);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SudokuMain.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });


        for (int i = 0; i < 21; i++) {
            for (int j = 0; j < 21; j++) {
                JTextField buts = new JTextField(2);
                Font font1 = new Font("SansSerif", Font.BOLD, 20);
                buts.setHorizontalAlignment(JTextField.CENTER);
                buts.setFont(font1);
               // buts.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                buts.setSize(500,50);
                dizi_21[i][j] = buts;
                // buts.setVisible(false);
                fpanel.add(buts);

            }
        }

        forlar(); // samurai sudokuoluşması için

        

        SudokuBas(mdizi1, dizi_21, 0, 0);
        SudokuBas(mdizi2, dizi_21, 0, 12);
        SudokuBas(mdizi3, dizi_21, 12, 0);
        SudokuBas(mdizi4, dizi_21, 12, 12);
       SudokuBas(mdizi5, dizi_21, 6, 6);



        Container con = frame.getContentPane();
        con.setLayout(new BorderLayout());

        con.add(fpanel, BorderLayout.SOUTH);

        //con.add(yeni);
        con.add(menu);

        frame.pack();
        frame.setVisible(true);

       
    }
    public static void dosyadan()
    {
        DosyaIslem t = new DosyaIslem();
        t.DYaz(sdiz);
        
         for (int i = 0; i < 21; i++) {
            ArrayList<String> sahte = new ArrayList();
            for (int j = 0; j < 21; j++) {
                sahte.add(sdiz[i][j]);
            }
            arraym.add(sahte);
        }
     
         
              for (int i = 0; i <=5 ; i++) {
            arraym.get(i).add(9, "-1");
        arraym.get(i).add(10, "-1");
         arraym.get(i).add(11, "-1");
         
         
             arraym.get(i+15).add(9, "-1");
        arraym.get(i+15).add(10, "-1");
         arraym.get(i+15).add(11, "-1");
        }
         
         for (int i = 9; i <= 11; i++) {
             for (int j = 0; j <=5; j++) {
                 arraym.get(i).add(j, "-1");
             }
        }
         
          for (int i = 0; i < 21; i++) {
            for (int j = 0; j < 21; j++) {
                
                if(arraym.get(i).get(j)==null)
                {
                    diz[i][j]=0;
                }
                
                else {
                   if(arraym.get(i).get(j).equals("*"))
                {
                    diz[i][j]=0;
                   // intArr.get(i).add(0);
                }
                   else{
                        diz[i][j]=Integer.parseInt(arraym.get(i).get(j));
                   }
                    
                   
                }
            }
        }
        
    }
    public static void forlar() {
        for (int i = 0; i < 6; i++) {               // silinen ilk dalga
            for (int j = 9; j <= 11; j++) {
                dizi_21[i][j].setVisible(false);
            }
        }

        for (int i = 9; i <= 11; i++) {               // silinen ikinci dalga
            for (int j = 0; j < 6; j++) {
                dizi_21[i][j].setVisible(false);
            }

        }

        for (int i = 9; i <= 11; i++) {               // silinen ikinci dalga
            for (int j = 15; j <= 20; j++) {
                dizi_21[i][j].setVisible(false);
            }

        }

        for (int i = 15; i <= 20; i++) {               // silinen ikinci dalga
            for (int j = 9; j <= 11; j++) {
                dizi_21[i][j].setVisible(false);
            }

        }

    } // forlar sonu
public static void DiziYap()
{
    dosyadan();
    for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 9; j++) {
           mdizi1[i][j]=diz[i][j]; 
        }
    }
    
    
    
    for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 9; j++) {
           mdizi2[i][j]=diz[i][j+12]; 
        }
    }
    
    
    
    for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 9; j++) {
           mdizi3[i][j]=diz[i+12][j]; 
        }
    }
    
    
    
    
    
    for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 9; j++) {
           mdizi4[i][j]=diz[i+12][j+12]; 
        }
    }
    
    
    for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 9; j++) {
           mdizi5[i][j]=diz[i+6][j+6]; 
        }
    }
    
    
}
    public static void SudokuBas(int[][] board, JTextField[][] dizim, int o, int s) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    dizim[i + o][j + s].setText("0");
                } else if (board[i][j] == 1) {
                    dizim[i + o][j + s].setText("1");
                } else if (board[i][j] == 2) {
                    dizim[i + o][j + s].setText("2");
                } else if (board[i][j] == 3) {
                    dizim[i + o][j + s].setText("3");
                } else if (board[i][j] == 4) {
                    dizim[i + o][j + s].setText("4");
                } else if (board[i][j] == 5) {
                    dizim[i + o][j + s].setText("5");
                } else if (board[i][j] == 6) {
                    dizim[i + o][j + s].setText("6");
                } else if (board[i][j] == 7) {
                    dizim[i + o][j + s].setText("7");
                } else if (board[i][j] == 8) {
                    dizim[i + o][j + s].setText("8");
                } else if (board[i][j] == 9) {
                    dizim[i + o][j + s].setText("9");
                }

            }
        }
    }

    /**
     * *****
     */

   

   



}
