class kondisi2{
	int nilai;
	String predikat;
	void koreksi(int nilai){
		if((nilai<=100)&&(nilai>=90)){
			predikat="Excellent";
		}else if((nilai<=89)&&(nilai>=80)) {
			predikat="Good";
		}else if((nilai<=79)&&(nilai>=70)){
			predikat="Fair";
		}else if((nilai<=69)&&(nilai>=50)){
			predikat="Dafug";
		}else if((nilai<=49)&&(nilai>=0)){
			predikat="Fail";
		}else{
			predikat="No such grade";
		}
		System.out.println("Nilai : "+nilai);
		System.out.println("Predikat : "+predikat);
	}
}


	