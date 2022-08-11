public class karyawan_kontrak extends induk_karyawan{
	public int upah_harian;
	public int hari;
	public int anak;
	public int total; 
	public int upah;

	public karyawan_kontrak(){

	}

	public karyawan_kontrak(int pokok, int harian, int tunjangan_anak, int upah_harian, int hari, int anak, int total, int upah){
		super(pokok, harian, tunjangan_anak);
		setTunjanganAnak(anak);
		setTotal(total, upah_harian, hari, upah);
	}

	public void setTunjanganAnak(int anak){
		this.anak = anak; 
	}

	public int getTunjanganAnak(){
		return super.tunjangan_anak*anak;
	}

	public void setTotal(int total, int upah_harian, int hari, int upah){
		int tunjang = getTunjanganAnak();
		upah = upah_harian*hari;
		total = (upah_harian*hari) + tunjang;
		this.total = total;
		this.upah = upah;
	}

	public int getTotal(){
		return total;
	}

	public int getUpah(){
		return upah;
	}
}