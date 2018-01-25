package harjoitustyo;
import java.io.*;
import java.lang.String.*;



/**
 * 
 * @author xx
 */
    class Juoma implements Serializable
    {
        private int tnro, kpl;
        private double alep, ahinta;
        private String tnimi;
        /**
         * 
         * @param nro
         * @param nimi
         * @param hinta
         * @param kappaletta
         * @param alennusprosentti 
         */
        public Juoma (int nro, String nimi,double hinta, int kappaletta,  double alennusprosentti) {
   
        tnro = nro;
        tnimi = nimi;
        ahinta= hinta;
        kpl =kappaletta;
        alep=alennusprosentti;
        
        }
        public int tnro() {
        return ( tnro );   }
        public String tnimi() {
        return ( tnimi );   }
        public double ahinta() {
        return ( ahinta );   }
        public int kpl() {
        return ( kpl );   }
        public double alep() {
        return ( alep );   }
    
    //tulostatiedot metodi
    public void tulosta_tiedot () {
        
   
        System.out.print("\n\t " + tnro + " \t " + tnimi+ "\t " + ahinta + "  \t   " + kpl+ "\t     " + alep +"\n" );

   }    
        
}
    
public class Juomarekisteri {

    /**
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
  
    //MUUTTUJIEN MÄÄRITTELY
    int valinta=0;
    BufferedReader Syote = new BufferedReader (new InputStreamReader (System.in));
    
    
    //Tietuetaulukon määrittely
    Juoma[] taulu = new Juoma[100];
    
    
    
    while(valinta!=9)
    {
    
    System.out.println("****************************");
    System.out.println("       1. Syötä uusi juoma"      );
    System.out.println("       2. Tulostaminen"      );
    System.out.println("       3. Hae numerolla "    );
    System.out.println("       4. Hae juoman nimellä"  );
    System.out.println("       9. Lopeta            ");
    System.out.println("****************************");
    
    System.out.println("       Valitse valikosta:   ");
    
    valinta=Integer.parseInt(Syote.readLine());
       

        switch(valinta)
        {
            case 1: 
                
                //KUTSUTAAN LUETIEDOT ALIOHJELMAA  
                taulu=Aliohjelmat.luetiedot(taulu);
                
            break;
                
            case 2: 
                
               //tulostatiedot aliohjelman kutsu
               Aliohjelmat.tulostatiedot(taulu);
               System.out.print("\n"+"\n"+"\n"+"\n"+"\n"+"\n");
                break;
            case 3: 
               
               //haenumerolla aliohjelman kutsu
               Aliohjelmat.haeNumerolla(taulu); 
              break;
             case 4: 
                 
               //haenimellä aliohjelman kutsu
               Aliohjelmat.haeNimella(taulu); 
              break;   
            case 9: 
                               System.out.print("Ohjelma on suljettu. Kiitos käytöstä. \n");
            break;
        
        }
    }   
        
        
    }
}

