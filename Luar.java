public class Luar{
	int a;
	int b;
	int hasil;
	public void setTambah(int a, int b){
		hasil = a+b;
		System.out.println("Hasil : "+ hasil);
	}

	public void cetakKurang(int a, int b){
		hasil = a-b;
		System.out.println("Hasil : "+ hasil);
	}
	class Tengah{
		void cetakKali(int a, int b){
			hasil = a*b;
			System.out.println("Hasil : "+ hasil);
		}
	}

}

//class Tengah{
//		void cetakTengah(){
//			cetakLuar();
//			System.out.println("cetak tengah....");
//		}
	

//		class Dalam{
//			void cetakDalam(){
//				cetakLuar();
//				cetakTengah();
//				System.out.println("cetak dalam....");

//			}
//		}
//	}