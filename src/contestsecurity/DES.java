
package contestsecurity;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author hanaa
 */
public class DES {
    static String[]bin={"0000","0001","0010","0011","0100","0101","0110","0111","1000","1001","1010","1011","1100","1101","1110","1111"};
    static Character[]hex={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
    static int[]eP= {32, 1, 2, 3, 4, 5, 4, 5, 6, 7, 8, 9, 8, 9, 10, 11, 12, 13, 12, 13, 14, 15, 16, 17, 16, 17, 18,
        19, 20, 21,20, 21, 22, 23, 24, 25, 24, 25, 26, 27, 28, 29, 28, 29, 30, 31, 32, 1};
    static int[]straightP = {16, 7, 20, 21, 29, 12, 28, 17, 1, 15, 23, 26, 5, 18, 31, 10, 2, 8, 24, 14, 32, 27, 3, 9, 19, 13, 30, 6, 22, 11, 4, 25 };  
   static int[] IP = {58, 50, 42, 34, 26, 18, 10, 2, 60, 52, 44, 36, 28, 20, 12, 4, 62, 54, 46, 38, 30, 22, 14, 6, 64, 56, 48, 40, 32, 24, 16, 8, 57, 49, 41, 33, 25, 17, 9, 1, 59, 51, 43, 35, 27, 19, 11, 3, 61, 53, 45, 37, 29, 21, 13, 5, 63, 55, 47, 39, 31, 23, 15, 7};
    static int[] IP_1 = {40, 8, 48, 16, 56, 24, 64, 32, 39, 7, 47, 15, 55, 23, 63, 31, 38, 6, 46, 14, 54, 22, 62, 30, 37, 5, 45, 13, 53, 21, 61, 29,
        36, 4, 44, 12, 52, 20, 60, 28, 35, 3, 43, 11, 51, 19, 59, 27, 34, 2, 42, 10, 50, 18, 58, 26, 33, 1, 41, 9, 49, 17, 57, 25};
    static int[] PC1 = {57, 49, 41, 33, 25, 17, 9, 1, 58, 50, 42, 34, 26, 18, 10, 2, 59, 51, 43, 35, 27, 19, 11, 3, 60,
       52, 44, 36, 63, 55, 47, 39, 31, 23, 15, 7, 62, 54, 46, 38, 30, 22, 14, 6, 61, 53, 45, 37, 29, 21, 13, 5, 28, 20, 12, 4 };
    static int[] PC2 = {14, 17, 11, 24, 1, 5, 3, 28, 15, 6, 21, 10, 23, 19, 12, 4, 26, 8, 16, 7, 27, 20, 13, 2, 41, 52, 31, 37,
       47, 55, 30, 40, 51, 45, 33, 48, 44, 49, 39, 56, 34, 53, 46, 42, 50, 36, 29, 32 };
    static int[]  Rotations = {1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1 };
    static int[][][] s_Boxes={
        {{14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
        {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
        {4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},
        {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13},
        },
        {{15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10},
            {3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5},
            {0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15},
            {13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9},
        },
        {{10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8},
            {13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1},
            {13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7},
            {1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12},
        },
        {{7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15},
            {13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9},
            {10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4},
            {3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14},
        },
        {{2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9},
            {14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6},
            {4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14},
            {11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3},
        },
        {{12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11},
            {10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8},
            {9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6},
            {4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13},
        },
        {{4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1},
            {13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6},
            {1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2},
            {6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12},
        },
        {{13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7},
            {1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2},
            {7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8},
            {2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11},}
  
    }; 
    static ArrayList<String> kTable = new ArrayList<String>();
    static ArrayList<Integer> pTable = new ArrayList<Integer>();
    static ArrayList<Integer> invPTable= new ArrayList<Integer>();
    public static String permutation(int outBits,ArrayList permutationTable,int inputBits,String inPut){
        String outPut="";
        String binary="";
        String hexOut="";
        for(char x:inPut.toCharArray()){
            for(int i=0;i<16;i++){
                if(x==hex[i]){
                    binary+=bin[i];
                    break;
                }
            }
        }
        for(int i=0;i<permutationTable.size();i++){
            int change =(int) permutationTable.get(i);
            outPut+=binary.charAt(change-1);
        }
        int k=0;
        for (int i=0;i<outBits/4;i++){
            String str=outPut.substring(k,k+4);
            for(int j=0;j<16;j++){
                if(str.equals(bin[j])){
                    hexOut+=hex[j];
                    break;
                }
            }
            k=k+4;
        }
        return hexOut;
    } 
    public static void inversePermutation(int inOut,ArrayList pTable){
        int change=0;int k=0;
        for(int i=0; i<inOut;i++){
            change=(int)pTable.get(i);
            if ((int)invPTable.get(change-1)==0)
            invPTable.set(change-1, i+1);
            else {k=1;
            }
        }
        if (k==0){
            for (int i=0;i<inOut;i++){
            System.out.print(invPTable.get(i)+" ");
            }
        }
        else 
            System.out.print("IMPOSSIBLE ");
    }
    public static String totalPermutation(String inPut,int[] permu){
        String outPut="";
        String binary="";
        String hexOut="";
        for(char x:inPut.toCharArray()){
            for(int i=0;i<16;i++){
                if(x==hex[i]){
                    binary+=bin[i];
                    break;
                }
            }
        }
        for(int i=0;i<permu.length;i++){
            int change =permu[i];
            outPut+=binary.charAt(change-1);
        }
        int k=0;
        for (int i=0;i<permu.length/4;i++){
            String str=outPut.substring(k,k+4);
            for(int j=0;j<16;j++){
                if(str.equals(bin[j])){
                    hexOut+=hex[j];
                    break;
                }
            }
            k=k+4;
        }
        return hexOut;
    
    }
    public static String xoring(String input1,String input2){
        String binary="",binaryKey="";
        String hexOut="";
        for(char x:input1.toCharArray()){
            for(int i=0;i<16;i++){
                if(x==hex[i]){
                    binary+=bin[i];
                    break;
                }
            }
        }//System.out.println(binary);
        for(char x:input2.toCharArray()){
            for(int i=0;i<16;i++){
                if(x==hex[i]){
                    binaryKey+=bin[i];
                    break;
                }
            }
        }//System.out.println(binaryKey);
        BigInteger b1 = new BigInteger(binary, 2);
        BigInteger b2 = new BigInteger(binaryKey, 2);
        hexOut=b1.xor(b2).toString(16).toUpperCase();
//System.out.println(b1.xor(b2).toString(16));
        if(hexOut.length()<input1.length()){
            int r=input1.length()-hexOut.length();
            for(int h=0;h<r;h++){
                hexOut="0"+hexOut;
            }
        }
        return hexOut;
    }
    public static void shifting(String key56bit){
        String first28=key56bit.substring(0, 7);
       // System.out.println(first28);
        String second28=key56bit.substring(7, 14);
        //System.out.println(second28);
        String binary="",binarySecond="";
        String hexOut=""; String shifted="";
        for(char x:first28.toCharArray()){
            for(int i=0;i<16;i++){
                if(x==hex[i]){
                    binary+=bin[i];
                    break;
                }
            }
        }//System.out.println(binary);
        for(char x:second28.toCharArray()){
            for(int i=0;i<16;i++){
                if(x==hex[i]){
                    binarySecond+=bin[i];
                    break;
                }
            }
        }//System.out.println(binarySecond);
        String shifted1=binary;String shifted2=binarySecond; 
        for(int i=0;i<16;i++){
            int shift=Rotations[i];
             shifted1 = shifted1.substring(shift)+shifted1.substring(0,shift);
             shifted2 = shifted2.substring(shift)+shifted2.substring(0,shift);
             shifted=(shifted1+shifted2);int k=0;hexOut="";
          for (int j=0;j<14;j++){
            String str=shifted.substring(k,k+4);
            for(int r=0;r<16;r++){
                if(str.equals(bin[r])){
                    hexOut+=hex[r];
                    break;
                }
            }
            k=k+4;
        }
            String out=totalPermutation(hexOut, PC2);
            kTable.add(out);
           // System.out.println(out);
        }
    }
    public static String sBox(String hexIn){
        String outPut="";
        String binary="";
        String hexOut="";
        for(char x:hexIn.toCharArray()){
            for(int i=0;i<16;i++){
                if(x==hex[i]){
                    binary+=bin[i];
                    break;
                }
            }
        } 
        int k=0,sNo=0; String inSBox; 
        for (int i=0;i<8;i++){
          inSBox=binary.substring(k,k+6);
          String row="", column="";
          row+=inSBox.charAt(0);
          row+=inSBox.charAt(5);
          column=inSBox.substring(1,1+4);
          int rowDec = Integer.parseInt(row, 2);
          int colDec = Integer.parseInt(column, 2);
          String g =Integer.toHexString(colDec).toUpperCase();
          for(int sNum=sNo;sNum<8;sNum++){
              for (int rNo=0;rNo<4;rNo++){
                  if(rNo==rowDec){
                    for(int cNo=0;cNo<16;cNo++){
                     if(cNo==colDec){
                         int out=s_Boxes[sNo][rNo][cNo];
                         hexOut+=Integer.toHexString(out).toUpperCase();
                         break;
                     }
                        
                    }
                    break;
                  }
              }
              sNo++;
              break;
          }

          k=k+6;
        }
        return hexOut;
    }
    public static String des(String rPlain32,String key){
        String ePOut=totalPermutation(rPlain32, eP);
        String xorOut=xoring(ePOut, key);
        String sOut=sBox(xorOut);
        String sPOut=totalPermutation(sOut,straightP);
        //System.out.println(sPOut);
        return sPOut;
    }
    public static void keyGeneration(String key){
        String pc1Out=totalPermutation(key, PC1);
        shifting(pc1Out);
    }
    public static void desEncryption(String plain,String key,int roundNo){
        String leftP;String rightP;String desRight="",finalRight="",newPlain="",out="";
        newPlain=plain;
        keyGeneration(key);String newLeft="";
        for(int j=0;j<roundNo;j++){
        newPlain=totalPermutation(newPlain, IP);
        for (int i=0;i<16;i++){
         leftP=newPlain.substring(0,8);
         rightP=newPlain.substring(8);
         desRight=des(rightP,kTable.get(i));

         finalRight=xoring(leftP, desRight);
         newLeft=rightP;
         newPlain=newLeft+finalRight;
                }
        newPlain=finalRight+newLeft;
        newPlain=totalPermutation(newPlain, IP_1);
    }
        System.out.println(newPlain);
    }
    public static void desDecryption(String plain,String key,int roundNo){
        String leftP;String rightP;String desRight="",finalRight="",newPlain="",out="";
        newPlain=plain;
        keyGeneration(key);String newLeft="";
        for(int j=0;j<roundNo;j++){
        newPlain=totalPermutation(newPlain, IP);
        for (int i=0;i<16;i++){
         leftP=newPlain.substring(0,8);
         rightP=newPlain.substring(8);
         desRight=des(rightP,kTable.get(15-i));

         finalRight=xoring(leftP, desRight);
         newLeft=rightP;
         newPlain=newLeft+finalRight;
                }
        newPlain=finalRight+newLeft;
        newPlain=totalPermutation(newPlain, IP_1);
    }
        System.out.println(newPlain);
    }
    public static void generalInvPBox(int in,int out,ArrayList pTable ){
        int change=0;int k=0;
        for(int i=0; i<out;i++){
            change=(int)pTable.get(i);
            
            invPTable.add(change);
            
            
        }
        if (k==0){
            for (int i=0;i<out;i++){
            System.out.print(invPTable.get(i)+" ");
            }
        }
        else 
            System.out.print("IMPOSSIBLE ");
        
    }
    public static void main(String[] args) {
        Scanner reader= new Scanner(System.in);String pBox="";
        int in=reader.nextInt();
        int out=reader.nextInt();
               int count =0;
        while (count<out&& reader.hasNextInt() ){
            pTable.add(reader.nextInt());
            count++;
            invPTable.add(0);
        }
      
        generalInvPBox(in, out, pTable);
       // desDecryption(plain, key, roundNo);
        //desEncryption(plain, key, roundNo);
        //keyGeneration(key,roundNo);
//        int count =0;
//        while (count<outBit&& reader.hasNextInt() ){
//            pTable.add(reader.nextInt());
//            count++;
//            invPTable.add(0);
//        }
//       String outPut= des(plain,key);
//     System.out.println(in);
//     System.out.println(out);
//     System.out.println(pBox);
       //String out= sBox(In);
//        int inBits=reader.nextInt();
//        String input= reader.next();
//      String outPut=permutation(outBit,pTable,inBits,input);
//      inversePermutation(outBit, pTable);
    }
}
