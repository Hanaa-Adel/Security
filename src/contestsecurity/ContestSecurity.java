
package contestsecurity;
import java.util.*;

/**
 *
 * @author hanaa
 */
public class ContestSecurity {
  public static void main(String[] args) {
       Scanner input1 = new Scanner(System.in);
       long key = input1.nextLong();
       Scanner input2 = new Scanner (System.in);
       String plain= input2.next();

       Character x;
       String cipher="";
       
       long substitude;
       
       if (key>26) key=key%26;
       for(char y:plain.toCharArray())
       {
           int a= (int)y;
     
               substitude =a+key;
           if (substitude>90){
               long k=substitude-90;
                substitude =64+k;
           }
           x=(char)substitude;
          cipher+=x;
          
       }
       System.out.println(cipher);
       
   }
}
