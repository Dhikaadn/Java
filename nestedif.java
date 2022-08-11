import java.util.Scanner;

public class nestedif{
	public void kondisi(){
		System.out.println("Harga barang : 100000");
		Scanner input = new Scanner(System.in);
		System.out.print("Masukkan uang anda : ");
		int uang = input.nextInt();
		if (uang<=100000){
			if (uang<100000){
				System.out.print("Maaf uang anda kurang");
			}
			else if (uang==100000){
				System.out.print("Pembayaran berhasil");
			}
		}

		else if (uang>100000){
			uang = uang-100000;
			System.out.print("Pembayaran berhasil, kembalian anda "+uang);
		}

		else{
			System.out.print("Tidak valid");
		}
	}
}