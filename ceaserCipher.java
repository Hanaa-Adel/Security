public static void main(String[] args) {
       Scanner input1 = new Scanner(System.in);
       long key = input1.nextLong();
       Scanner input2 = new Scanner (System.in);
       String plain= input2.nextLine();

       System.out.println(plain);
       Character x;
       String cipher="";
       long substitude;
       for(char y:plain.toCharArray())
       {
           int a= (int)y;
           if(a==90) {
               a=65;
                substitude =a+key-1;
           }

           //int k= (int) key;
           else{
               substitude =a+key;
           }

           x=(char)substitude;
          cipher+=x;
       }

      System.out.println(cipher);
   }
