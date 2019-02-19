/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contestsecurity;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author hanaa
 */
public class GCD {
    static String[]bin={"0000","0001","0010","0011","0100","0101","0110","0111","1000","1001","1010","1011","1100","1101","1110","1111"};
    static Character[]hex={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
    static ArrayList<String>mul_List=new ArrayList();
    public static long find_GCD(long B,long A){
        long temp;
        while (A!=0){
            temp=B;
            B=A;
            A=temp%A;
        }
       // System.out.println(B);
       return B;
    }
    ////////////////////////////////////////////////////////////
    public static void extended_Euclidean(long M,long N){
       long A1,A2,A3 ,B1,B2,B3 , Q ,T1,T2,T3;long result;
        if(N==0){
            System.out.println("IMPOSSIBLE");
        }
        else if(N==1){
            result=add_Inverse(M, N);
            System.out.println(result+" " +"1");
        }
        else if(N<0){
            while (N<0){
               N=N+M;
            }
            result=add_Inverse(M, N);
            A1=1;A2=0;A3=M;    B1=0;B2=1;B3=N;
            while(B3!=1&&B3!=0){
                Q=A3/B3;
                T1=A1-(Q*B1);T2=A2-(Q*B2);T3=A3-(Q*B3);
                A1=B1;A2=B2;A3=B3;
                B1=T1;B2=T2;B3=T3;
            }
            if (B3==0) System.out.println("IMPOSSIBLE");
            else if (B2<0){B2=B2+M;
                System.out.println(result+" "+B2);
            }
            else System.out.println(result+" "+B2);
        }
        else if (N>M){
            while (N>M){
                N=N-M;
            }
            if(N==0){
            System.out.println("IMPOSSIBLE");
            }
            else{
                result=add_Inverse(M, N);
                A1=1;A2=0;A3=M;    B1=0;B2=1;B3=N;
                while(B3!=1&&B3!=0){
                    Q=A3/B3;
                    T1=A1-(Q*B1);T2=A2-(Q*B2);T3=A3-(Q*B3);
                    A1=B1;A2=B2;A3=B3;
                    B1=T1;B2=T2;B3=T3;
                }
                if (B3==0) System.out.println("IMPOSSIBLE");
                else if (B2<0){B2=B2+M;
                    System.out.println(result+" "+B2);
                }
                else System.out.println(result+" "+B2);
            }
        }
        else{
            result=add_Inverse(M, N);
           A1=1;A2=0;A3=M;    B1=0;B2=1;B3=N;
         while(B3!=1&&B3!=0){
             Q=A3/B3;
             T1=A1-(Q*B1);T2=A2-(Q*B2);T3=A3-(Q*B3);
             A1=B1;A2=B2;A3=B3;
             B1=T1;B2=T2;B3=T3;
         }
         if (B3==0) System.out.println("IMPOSSIBLE");
         else if (B2<0){B2=B2+M;
         System.out.println(result+" "+B2);
         }
         else System.out.println(result+" "+B2);
        }
    }
    public static long add_Inverse(long M,long N){
        long ordinaryInv,addInv=0;
        ordinaryInv=-N;
        while(addInv<=0){
        addInv=ordinaryInv+M;
        }
        return addInv;
    }
    ////////////////////////
    public static String xoring(String binary1,String binary2,String a){
        String binary="",binaryKey="";
        String hexOut="";
//        for(char x:input1.toCharArray()){
//            for(int i=0;i<16;i++){
//                if(x==hex[i]){
//                    binary+=bin[i];
//                    break;
//                }
//            }
//        }//System.out.println(binary);
//        for(char x:input2.toCharArray()){
//            for(int i=0;i<16;i++){
//                if(x==hex[i]){
//                    binaryKey+=bin[i];
//                    break;
//                }
//            }
//        }//System.out.println(binaryKey);
        BigInteger b1 = new BigInteger(binary1, 2);
        BigInteger b2 = new BigInteger(binary2, 2);
        hexOut=b1.xor(b2).toString(16).toUpperCase();
        //System.out.println(b1.xor(b2).toString(16));
        if(hexOut.length()<a.length()){
            int r=a.length()-hexOut.length();
            for(int h=0;h<r;h++){
                hexOut="0"+hexOut;
            }
        }
        return hexOut;
    }
    public static String multiply(String fx,String gx,String a){
         String shifted=fx;String bit="";
         mul_List.add(fx);
        for (int i=1;i<8;i++){
            bit=shifted.substring(0, 1);
             shifted=(shifted.substring(1)+"0");
            if (bit.equals("1")){
                 String GF,shifted_bin="";
                 GF =xoring(shifted,"00011011", a);
                for(char x:GF.toCharArray()){
                    for(int j=0;j<16;j++){
                        if(x==hex[j]){
                         shifted_bin+=bin[j];
                         break;
                        }
                    }shifted=shifted_bin;
                }
            }
            mul_List.add(shifted);
        } 
        String temp="00000000";String temp2,temp1,mul_bin="";
        for(int i=0;i<gx.length();i++){
            if(gx.charAt(i)=='1'){
                temp1=mul_List.get(7-i);
                temp2=xoring(temp1, temp, a);
                mul_bin="";
                for(char x:temp2.toCharArray()){
                    for(int j=0;j<16;j++){
                        if(x==hex[j]){
                         mul_bin+=bin[j];
                         break;
                        }
                    }
                }temp=mul_bin;
            }
        }
        String hexout=xoring(temp,"00000000", a);
        return hexout;
    }
    public static void field(String a, String b){
        String hexIn_1="";String hexIn_2="";String hexOut_add="",hexOut_mul="";
        String binary1="",binary2="";
        String hexOut="";
        for(char x:a.toCharArray()){
            for(int i=0;i<16;i++){
                if(x==hex[i]){
                    binary1+=bin[i];
                    break;
                }
            }
        }
        for(char x:b.toCharArray()){
            for(int i=0;i<16;i++){
                if(x==hex[i]){
                    binary2+=bin[i];
                    break;
                }
            }
        }
       hexOut_add=xoring(binary1,binary2,a);
       hexOut_mul= multiply(binary1,binary2,a);
       System.out.println(hexOut_add+" "+hexOut_mul);
    }
     //////////////////////////////////////////////////
    static String binary_String="";
    public static void binary(long b){
        if (b<=1){
            //System.out.println(b);
            binary_String+=b;
            return;
        }
        long remainder;
        remainder=b%2;
        b=b/2;
        binary(b);
        //System.out.println(remainder);
        binary_String+=remainder;
    }
    public static long powerFn(long b,long p){
        long result=1;
        for (int i=0;i<p;i++){
            result =result*b;
        }
        return result;
    }

     public static void power1(long a, long b, long c){
        binary(b);long number=0;long r=0,result1=0;
          long k=0; ArrayList<Long>powerOf2=new ArrayList();ArrayList<Long>modPower=new ArrayList();ArrayList<Long>twoOnly=new ArrayList();
          ArrayList<Long>found_1=new ArrayList();
          for(int i =binary_String.length();i>0;i--){
              if(binary_String.charAt(i-1)=='1'){
                  k=binary_String.length()-i;
                  found_1.add(k);// System.out.println(k);
                  number= (long)Math.pow(2,k);
                  powerOf2.add(number);     // System.out.println(number);
              }
          }
          for(long i=1;i<=binary_String.length();i++){
              if(i==1){
               r =(long)Math.pow(a,i);
//               long q=r/c;
//               result1=r-(q*c);
                 result1=r;
                while(result1>c){
                long q=r/c;
               result1=r-(q*c);}
               //result1=r;
               twoOnly.add(result1);
              }
              else
              {
                  
                //r=(long)Math.pow(result1, 2);
                long q=(result1*result1)/c;
                
                result1=(result1*result1)-(q*c);
                //result1=r;
//                while(result1>c){
//                 q=result1/c;
//               result1=result1-(q*c);}
                //result1=r%c;}
                twoOnly.add(result1);
              }
            // System.out.println(result1);
            }   //System.out.println(result1);
           for (long j=0;j<found_1.size();j++){
                  long last =found_1.get((int)j);
                  long finish =twoOnly.get((int)last);
                      modPower.add(finish);//System.out.println(finish);
                  
            }
        
          
          long finaly=modPower.get(0);//System.out.println(finaly);
           
        
          for(long i=1;i<modPower.size();i++){
              long ffinal=modPower.get((int)i);//System.out.println(ffinal);
            long q= (ffinal*finaly)/c;//System.out.println(q);
            
               finaly=(ffinal*finaly)-(q*c);//System.out.println(finaly);
             // finaly=(ffinal*finaly)%c;System.out.println(finaly);
//               while(finaly>c){
//                   finaly =finaly%c;
//              }
          }
          System.out.println(finaly);

    }
    public static void main(String[] args){
        Scanner reader =new Scanner(System.in);
        long a=reader.nextLong();
        long b=reader.nextLong();
        long c=reader.nextLong();
        
//       System.out.println(a);
//        System.out.println(b);
        //extended_Euclidean(M,N);
         //System.out.println(c);
        //find_GCD(B,A);
        power1(a,b,c);
        //field(a,b);
       // binary(b);
        //System.out.println(binary_String);
    }
    
}
