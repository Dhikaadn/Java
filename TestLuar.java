public class TestLuar{
	public static void main(String[] a){
		Luar l = new Luar();
		Bentuk be = new Bentuk("Kuning", false);
		Lingkaran li = new Lingkaran("merah", true, 3.9);
		PersegiPanjang pe = new PersegiPanjang("biru", false, 20, 10);
		Luar.Tengah t = l.new Tengah();
		//Luar.Tengah.Dalam d = t.new Dalam();

		l.setTambah(20, 10);
		l.cetakKurang(20,10);
		t.cetakKali(3, 4);
		System.out.println(be);
		System.out.println(li);
		System.out.println(pe);
		//System.out.print(li);
		//System.out.println("......");
		//d.cetakDalam();
	}
}