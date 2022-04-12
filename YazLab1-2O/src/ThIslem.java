
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JTextField;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author omer
 */
public class ThIslem implements Runnable {

    public static ArrayList<String> dosStr = new ArrayList<>();
    public static ArrayList<Double> gecenSure = new ArrayList<>();
    public int[][] sudokDizi;
    public JTextField[][] dizim;
    public int sleep;
    public int o, s;
    public int samur1, samur2;
    public int son1, son2;
    public boolean besDegil;
    public int id;
    DosyaIslem dosya = new DosyaIslem();
    public int kac1, kac2;
    public static int kare;

    public ThIslem(int[][] sudokDizi, JTextField[][] dizim, int sleep, int o, int s, int samur1, int samur2, int son1, int son2, boolean besDegil, int id) {
        this.sudokDizi = sudokDizi;
        this.dizim = dizim;
        this.sleep = sleep;
        this.o = o;
        this.s = s;
        this.samur1 = samur1;
        this.samur2 = samur2;
        this.son1 = son1;
        this.son2 = son2;
        this.besDegil = besDegil;
        this.id = id;

    }

    @Override
    public void run() {
        try {
            long baslangic = System.nanoTime();
            long toplam = 0;
            
            
            Thread.sleep(sleep);
            //solveBoard();
            if (sudokuCoz()) {

                if (besDegil == true) {
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            SudokuMain.mdizi5[i + son1][j + son2] = sudokDizi[i + samur1][j + samur2];
                        }
                    }
                    // System.out.println("-->>>>>>> " + SudokuMain.board5[0][1]);
                    //System.out.println("-->>>>>>> " + SudokuMain.board5[6][6]);
                }
                SudokuMain.SudokuBas(sudokDizi, dizim, o, s);
                long sonlanma = System.nanoTime();
long gecenZaman = sonlanma - baslangic;
double seconds = (double)gecenZaman/1000000000;
gecenSure.add(seconds);
            } else {
                System.out.println("Çözülemedi :(");
                    long sonlanma = System.nanoTime();
long gecenZaman = sonlanma - baslangic;
double seconds = (double)gecenZaman/1000000000;
            }
            System.out.println("Thread başladı!");
        } catch (InterruptedException ex) {
            Logger.getLogger(ThIslem.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean satirKontrol(int sayi, int satir) {
        for (int i = 0; i < 9; i++) {
            if (sudokDizi[satir][i] == sayi) {
                return true;
            }
        }
        return false;
    } // isnumberrow

    public boolean sutunKontrol(int sayi, int sutun) {
        for (int i = 0; i < 9; i++) {
            if (sudokDizi[i][sutun] == sayi) {
                return true;
            }
        }
        return false;
    }//column

    public boolean kutuKontrol(int sayi, int satir, int sutun) {
        int kutuSatir = satir - satir % 3;
        int kutuSutun = sutun - sutun % 3;

        for (int i = kutuSatir; i < kutuSatir + 3; i++) {
            for (int j = kutuSutun; j < kutuSutun + 3; j++) {
                if (sudokDizi[i][j] == sayi) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean gecerliMi(int sayi, int satir, int sutun) {

        return !satirKontrol(sayi, satir) 
                && !sutunKontrol(sayi, sutun)
                && !kutuKontrol(sayi, satir, sutun);

    }

    public boolean sudokuCoz() {

        for (int satir = 0; satir < 9; satir++) {

            for (int sutun = 0; sutun < 9; sutun++) {

                if (sudokDizi[satir][sutun] == 0) {
                    for (int sayiDene = 1; sayiDene <= 9; sayiDene++) {
                        if (gecerliMi(sayiDene, satir, sutun)) {
                            sudokDizi[satir][sutun] = sayiDene;
                            //System.out.println(this.id+" . tablonun "+satir+" satir " + sutun+ " sütun " +"++>>  " + sudokDizi[satir][sutun]);

//                             for (int i = 0; i <dosStr.size(); i++) {
//                                dosya.DosyaYaz(dosStr.get(i));
//                            }
                              kare++;
                            String str = this.id + " . threadin / tablonun  " + satir + " satir " + sutun + " sütun " + "---->  " + sudokDizi[satir][sutun] + " ";
                            dosya.DosyaYaz(str);
                            if (sudokuCoz()) {
                                // SudokuBas();

                                return true;
                            } else {

                                sudokDizi[satir][sutun] = 0;
                            }
                        }
                    }
                    return false;
                }   // if ===0 
            }
        }
        return true;
    }

    public void SudokuBas() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (this.sudokDizi[i][j] == 0) {
                    this.dizim[i][j].setText("0");
                } else if (this.sudokDizi[i][j] == 1) {
                    this.dizim[i][j].setText("1");
                } else if (this.sudokDizi[i][j] == 2) {
                    this.dizim[i][j].setText("2");
                } else if (this.sudokDizi[i][j] == 3) {
                    this.dizim[i][j].setText("3");
                } else if (this.sudokDizi[i][j] == 4) {
                    this.dizim[i][j].setText("4");
                } else if (this.sudokDizi[i][j] == 5) {
                    this.dizim[i][j].setText("5");
                } else if (this.sudokDizi[i][j] == 6) {
                    this.dizim[i][j].setText("6");
                } else if (this.sudokDizi[i][j] == 7) {
                    this.dizim[i][j].setText("7");
                } else if (this.sudokDizi[i][j] == 8) {
                    this.dizim[i][j].setText("8");
                } else if (this.sudokDizi[i][j] == 9) {
                    this.dizim[i][j].setText("9");

                }

            }
        }
    }

}
