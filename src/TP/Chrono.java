package TP;

import java.util.Date;

public class Chrono {
	private long tempsDepart=0;
    private long tempsFin=0;
   private long pauseDepart=0;
    private long pauseFin=0;
    private long duree=0;

    public void start()
        {
        tempsDepart=System.currentTimeMillis();
        tempsFin=0;
        pauseDepart=0;
        pauseFin=0;
        duree=0;
        }
    public void stop()
    {
    if(tempsDepart==0) {return;}
    tempsFin=System.currentTimeMillis();
    duree=(tempsFin-tempsDepart) - (pauseFin-pauseDepart);
    tempsDepart=0;
    tempsFin=0;
    pauseDepart=0;
    pauseFin=0;
    }        
    public long getDureeSec()
    {
    return( System.currentTimeMillis()- tempsDepart)/1000;
    }
    public  String nowChrono() {
    	long temps;
      if(tempsDepart==0)temps =0;
      else
     temps =(System.currentTimeMillis()- tempsDepart);
       

        String r="";
      
        Date date = new Date();
        date.setTime(temps);
    r+=  date.getMinutes()+":"+date.getSeconds();
       
        return r;
        }
}
