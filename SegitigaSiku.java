import java.util.Scanner;

public class SegitigaSiku{
	public static void main(String[] args) {
      Scanner scan = new Scanner(System.in);
      int x;
        
      System.out.print("Masukkan nilai x = ");
      x = scan.nextInt();
        
      System.out.println("\nSegitiga Siku-Siku Bintang");
      System.out.println("==========================");
      for (int i=1;i<=x;i++){
         for(int j=1;j<=i;j++){
            System.out.print("*");
         }
            System.out.println();
      }
   }  
}