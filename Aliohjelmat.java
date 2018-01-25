package harjoitustyo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 
 * @author xx
 */
class Aliohjelmat{
  
     
    /**
     * 
     * @param taulu
     * @return
     * @throws IOException 
     */
    public static Juoma[] luetiedot(Juoma []taulu) throws IOException {

        //MUUTTUJIEN MÄÄRITTELY
        int i, lkm=0;
        int tnro, kpl;
        double ahinta, alep;
        String nimi;
       
        BufferedReader brSyote = new BufferedReader(new InputStreamReader (System.in));
        //SYÖTTÖTIEDOT
        
        System.out.println("Anna uusien juomien lukumäärä (kokonaislukuna):");
        lkm = Integer.parseInt(brSyote.readLine());
        
        for(i=0;i<lkm;i++)
        {
        
        System.out.println("Syötä juomanumero (kokonaislukuna): " );
        tnro = Integer.parseInt(brSyote.readLine());
        
        System.out.println("Syötä juoman nimi: " );
        nimi = brSyote.readLine();
        
        System.out.println("Syötä juoman hinta (kokonaislukuna): " );
        ahinta = Integer.parseInt(brSyote.readLine());
        
        System.out.println("Syötä juomien kpl-määrä (kokonaislukuna): " );
        kpl = Integer.parseInt(brSyote.readLine());
        
        System.out.println("Syötä alennusprosentti: (kokonaislukuna, ei prosenttimerkkiä)" );
        alep = Integer.parseInt(brSyote.readLine());
        
        taulu[i] = new Juoma( tnro,nimi, ahinta, kpl,alep  );}
        taulu = talleta ( taulu );
        return (taulu);
       
    }
      
    
//----------------------------tulosta-aliohjelma------------------------------//    
/**
 * 
 * @param taulu
 * @throws IOException 
 */    
    public static void tulostatiedot(Juoma []taulu) throws IOException {
        
        int i, lkm=0;
        
        BufferedReader brSyote = new BufferedReader(new InputStreamReader (System.in));
        taulu=luetiedosto(taulu);
        aakkos(taulu);
        System.out.print("\n     numero " + "\t nimi" + "\t hinta"  + "\t kpl " + "        ale%" );
         for(i=0;i<taulu.length;i++)
        {
        if( taulu[i] != null ) {
        taulu[i].tulosta_tiedot();
    }  
        } }  
    
 
  
  /**
   * 
   * @param taulu
   * @return
   * @throws IOException 
   */
  public static Juoma [] talleta ( Juoma [] taulu )throws IOException
  {
                String t=null;
                

                BufferedReader brSyote = new BufferedReader(new InputStreamReader (System.in));
               
                System.out.println("Anna tiedoston nimi: ");
                t = brSyote.readLine();
                 
            try
	{         
                 FileOutputStream tuot=new FileOutputStream(t,true);
		  ObjectOutputStream ohlot=new ObjectOutputStream(tuot);
		  
                  ohlot.writeObject(taulu);
                                    
		  ohlot.flush();
		  ohlot.close();
        }
      catch (FileNotFoundException ex) {
	      System.out.println("Tiedostoon tallentamisessa esiintyi häiriö, tarkista tiedostonimesi ja yritä uudelleen.");}
       catch(IOException e){e.printStackTrace();}
            
      //catch (ClassNotFoundException e){
      //e.printStackTrace(); }
        // palautetaan tieto rekisteristä takaisin pääohjelmaan
     return ( taulu );
   }
  
  /**
   * 
   * @param taulu
   * @throws IOException 
   */
 public static void aakkos ( Juoma [] taulu )throws IOException
   {
        
         int i, scan, min=0;
         Juoma temp;
      
         for ( i = 0; i < taulu.length; i++)
         {
             min = i;       
             for  ( scan = i + 1; scan < taulu.length; scan++ )
             {
               if( taulu[scan]!=null & taulu[min]!=null)  //tutkitaan muuttujia, että eivät ole tyhjiä
               	{
                 if(taulu[scan].tnimi().compareTo( taulu [min].tnimi())<0 )
                  min = scan;
               }
             } // sisempi for

            // vaihdetaan arvoja
            temp = taulu[min];
            taulu[min] = taulu[i];
            taulu[i] = temp;
         } // ulompi for
         	
   }
 
/**
 * 
 * @param taulu
 * @return
 * @throws IOException 
 */
   public static Juoma[] luetiedosto ( Juoma[] taulu )throws IOException
 {
                String t;
                
                BufferedReader brSyote = new BufferedReader(new InputStreamReader (System.in));
                
                System.out.println("Anna tiedoston nimi: ");
                t = brSyote.readLine();
                
          
      try
      {
      FileInputStream luku=new FileInputStream(t);
		  ObjectInputStream oluku=new ObjectInputStream(luku);
		  taulu=(Juoma[]) oluku.readObject();
		  oluku.close();
      }
      catch (IOException e){e.printStackTrace();}
      catch (ClassNotFoundException e){
      e.printStackTrace(); }

      return ( taulu );
     


  }

/**
 * 
 * @param taulu
 * @return
 * @throws IOException 
 */
public static Juoma[] haeNumerolla ( Juoma[] taulu ) throws IOException {

 BufferedReader brSyote = new BufferedReader(new InputStreamReader (System.in));
        int tnro;
        
        int paikka = 0;
       taulu = luetiedosto ( taulu );        //luetaan tiedoston tiedot taulukkoon
        
      System.out.print("\n\nAnna haettavan juoman numero >");
        tnro = Integer.parseInt(brSyote.readLine());

        paikka = haejuoma( taulu, tnro );
        
if ( paikka < 0 ) {

          System.out.println("Juomaa: " + tnro +" ei löydy");

        } else {

          System.out.println("Haettava juoma on:" );
          taulu[paikka].tulosta_tiedot();

        }
return(taulu);
}
/**
 * 
 * @param taulu
 * @param tnro
 * @return 
 */
public static int haejuoma( Juoma[] taulu, int tnro ) {

     int i;

     for( i=0; i < taulu.length; i++) {

      if( taulu[i] != null) {
         if( taulu[i].tnro()==( tnro )){

           return (i); //löytyi
         }

      }
     }
     return(-1); // ei löytynyt
  }
/**
 * 
 * @param taulu
 * @return palauttaa haetun tuotteen nimen perusteella
 * @throws IOException 
 */
   public static Juoma[] haeNimella ( Juoma[] taulu ) throws IOException {
   
       BufferedReader Syote = new BufferedReader (new InputStreamReader (System.in));
        String nimi;
        int paikka = 0;
        taulu = luetiedosto ( taulu );        //luetaan tiedoston tiedot taulukkoon
        System.out.print("\n\nAnna haettavan juoman nimi >");
        nimi = Syote.readLine();
        paikka = haejuomax( taulu, nimi );

        if ( paikka < 0 ) {

          System.out.println("Juomaa: " + nimi +" ei löydy");

        } else {

          System.out.println("Haettavan juoma on:" );
          taulu[paikka].tulosta_tiedot();

        }
        //taulu = talleta (taulu);    //kirjoitetaan taulukon tiedot tiedostoon
        return ( taulu );
     //
 }
   /**
    * 
    * @param taulu
    * @param tnimi
    * @return 
    */
   public static int haejuomax( Juoma[] taulu, String tnimi ) {


     int i;


     for( i=0; i < taulu.length; i++) {

      if( taulu[i] != null) {

           if( taulu[i].tnimi().equalsIgnoreCase( tnimi )){
           return (i);
         }

      }
     }

     return(-1); //jos ei löytynyt
  }
} 
