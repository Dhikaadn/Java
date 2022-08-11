public class testLingkar extends Lingkaran{

	public testLingkar(){
	}

	public testLingkar(double radius){
		super(radius);
	}


	@Override
    int getRadius(){
        return super.radius;
    }

	public String toString(){
		return "Lingkaran {Radius     : "+ getRadius() + "}";
	}
	
}

