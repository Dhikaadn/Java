public class induk_karyawan{
	public int pokok;
	public int harian;
	public int tunjangan_anak = 500000;

	public induk_karyawan(){

	}

	public induk_karyawan(int pokok, int harian, int tunjangan_anak){
		setPokok(pokok);
		setHarian(harian);
		setTunjanganAnak(tunjangan_anak);

	}

	public void setPokok(int pokok){
		this.pokok = pokok;
	}

	public int getPokok(){
		return pokok;
	}

	public void setHarian(int harian){
		this.harian = harian;
	}

	public int getHarian(){
		return harian;
	}

	public void setTunjanganAnak(int tunjangan_anak){
		this.tunjangan_anak = tunjangan_anak;
	}

	public int getTunjanganAnak(){
		return tunjangan_anak;
	}
}