public class buku{
	public String judul;
	public String pengarang;
	public String penerbit;
	public int tahun;

	public void cetakBuku(String judul, String pengarang, String penerbit, int tahun){
		System.out.print(judul+", "+pengarang+", "+penerbit+", "+tahun);
	}
}