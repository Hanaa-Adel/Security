
package contestsecurity;

import java.util.*;

/**
 *
 * @author hanaa
 */
public class Vernam {
   
   static Character[]alphaa={'A','B','C','D','E','F','G','H','I','J','K','L',
                        'M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'
    };
  
    public static String Vigenere(String p,char x){
        String cipher1="";
        for(char l:p.toCharArray()){
          int bit=0,s=0;
           for(int i=0;i<26;i++){
               if(alphaa[i]==l){
                   bit=i;
               }
           }
           for (int k=0;k<26;k++){
               if (alphaa[k]==x){
                   s=k;
               }
           }
           int index=bit+s;
           if (index>25)index=index%26;
           char pl=alphaa[index];
           cipher1+=pl;
           break;
        }
        return cipher1;
    }
    public static String AT(String p,char x){
        String cipher2="";
        for(char l:p.toCharArray()){
            int hK=(int)x;
            int hP=(int)l;
          int c= hK^hP;
          cipher2+=String.format("%02X", c);
            break;
        }
        return cipher2;
    }
    public static void main(String[] args) {
        Scanner input1 = new Scanner (System.in);
        String key= input1.next();
        String key1=key;
        Scanner input2 = new Scanner (System.in);
        String plain= input2.next();
        String cipher1="",cipher2="";
        if (key.length()<plain.length()){
            int l1=plain.length()-key.length();
            key=key+(key.substring(0, l1));
        }
        int k=0;
        for(char x:key.toCharArray()){
            
            cipher1 += Vigenere(plain.substring(k,plain.length()),x);
            cipher2 +=AT(plain.substring(k,plain.length()),x);
            k++;
        }
        System.out.println("Vigenere: "+cipher1);
        System.out.println("Vernam: "+cipher2);
        if (key1.length()==plain.length()){
            System.out.println("One-Time Pad: "+"YES");
        }
        else if(key1.length()<plain.length()){
            System.out.println("One-Time Pad: "+"NO");
        }
    }
}
