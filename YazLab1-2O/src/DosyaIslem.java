
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Omer
 */
public class DosyaIslem  {
        
        
       public void DYaz(String [][]sDizi )
       {
            Scanner sc;
            String [][] st = new String [2][5];
           try {
              
               
               
               sc = new Scanner(new File("dizi.txt"));
               int sayac=0;
                 while(sc.hasNextLine())
             {
                 for (int i = 0; i < sDizi.length; i++) {
                     String [] line = sc.nextLine().trim().split("");
                     sayac=0;
                     for (int j = 0; j < line.length; j++) {

                             sDizi[i][j] = line[j];

//                           if(i<=5)
//                         {
//                             if(j==9 || j==10 || j==11)
//                             {
//                                 sDizi[i][9] =null;
//                             sDizi[i][10] =null;
//                             sDizi[i][11] =null;
//                             }
//                             else{
//                                  sDizi[i][j] = line[sayac];
//                                  sayac++;
//                             }
//                         }
//                         
//                         else if(i>=15)
//                         {
//                              if(j==9 || j==10 || j==11)
//                             {
//                                 sDizi[i][9] =null;
//                             sDizi[i][10] =null;
//                             sDizi[i][11] =null;
//                             }
//                             else{
//                                  sDizi[i][j] = line[sayac];
//                                  sayac++;
//                             }
//                         }
//                         else  {
//                             sDizi[i][j] = line[sayac];
//                             sayac++;
//                             
//                         }
                        
                     }
                 }
               
                    
             }
                   
                 System.out.println("Gol");
                 sc.close();
                 
           } catch (FileNotFoundException ex) {
               Logger.getLogger(DosyaIslem.class.getName()).log(Level.SEVERE, null, ex);
               
           }
       }
       
       public void DosyaSil()
       {
              FileWriter fileWriter = null;
           try {
               File file = new File("adimlar.txt");
               if (!file.exists()) {
                   file.createNewFile();
               }      fileWriter = new FileWriter(file, false);
               BufferedWriter bWriter = new BufferedWriter(fileWriter);
               bWriter.write("  ----- ADIMLAR ----");
               bWriter.newLine();
               bWriter.close();
           } catch (IOException ex) {
               Logger.getLogger(DosyaIslem.class.getName()).log(Level.SEVERE, null, ex);
           } finally {
               try {
                   fileWriter.close();
               } catch (IOException ex) {
                   Logger.getLogger(DosyaIslem.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
       }
public void DosyaYaz(String str)
{
    FileWriter fileWriter = null;
           try {
               File file = new File("adimlar.txt");
               if (!file.exists()) {
                   file.createNewFile();
               }      fileWriter = new FileWriter(file, true);
               BufferedWriter bWriter = new BufferedWriter(fileWriter);
               bWriter.write(str);
               bWriter.newLine();
               bWriter.close();
           } catch (IOException ex) {
               Logger.getLogger(DosyaIslem.class.getName()).log(Level.SEVERE, null, ex);
           } finally {
               try {
                   fileWriter.close();
               } catch (IOException ex) {
                   Logger.getLogger(DosyaIslem.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
}
   public String YazDostum(){
       String str=null;
		
		
	
		try{
			
			
			FileInputStream fstream= new FileInputStream("dizi.txt");
			DataInputStream dstream = new DataInputStream(fstream);
			BufferedReader breader = new  BufferedReader(new InputStreamReader(dstream));
			
			str= breader.readLine();
			
			dstream.close();
		}catch(Exception e){
			System.err.println("Hata "+e.getMessage());
			
		}
		
		return str;
   }
    
}
