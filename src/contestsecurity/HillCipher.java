
package contestsecurity;


import static java.lang.Math.sqrt;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author hanaa
 */
public class HillCipher {
    
    static Character[]alphaa={'A','B','C','D','E','F','G','H','I','J','K','L',
                        'M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'
    };
   static List<Long> keyList = new ArrayList<Long>();
   public static String hill(String p,long n){
       String cipher="";int k=0;
       //for(int k=0; k<n;k=k){
           while(k<(int)n){long s=0;
       for(char l:p.toCharArray()){
           int bit=0;
           for(int i=0;i<26;i++){
               if(alphaa[i]==l){
                   bit=i;
                   break;
               }
           }
           //s=(bit*keyList[k])%26;
           Long r=keyList.get(k);
               s+=(bit*r)%26;
              
               k++;
           }
       cipher+=alphaa[(int)(s%26)];
       }
       
       return cipher;
   }
    public static void main(String[] args) {
        
       Scanner reader= new Scanner(System.in);
       long length=reader.nextLong();
        while(reader.hasNextLong()){
       keyList.add(reader.nextLong());
       }
       String plain= reader.next();
       String cipher="";
       int numL=((int) sqrt((double)length));
       int k=0;
       //if ((plain.length()%numL)!=0){
           while((plain.length()%numL)!=0){
               plain+='X';
           }
      // }
       String letters="";
       
    
       String plain1=plain;
       for (int i=0;i<plain.length()/numL;i++){
           
        //letters=Character.toString(plain1.charAt(k));
           letters=plain1.substring(k, (k+numL));
      cipher+= hill(letters,length);
      k=k+numL; 
       }
       System.out.println(cipher);
    }
}