public class Kondisi{
	public static void main(String[] args) {
		char nilai = 'd';
		String predikat;
		if(nilai=='a'){
			predikat="Excellent";
		}else if(nilai=='b'){
			predikat="Good";
		}else if(nilai=='c'){
			predikat="Fair";
		}else if(nilai=='d'){
			predikat="Dafug";
		}else if(nilai=='e'){
			predikat="Fail";
		}else{
			predikat="No such grade";
		}
		System.out.println("Nilai : "+nilai);
		System.out.println("Predikat : "+predikat);
	}
}
