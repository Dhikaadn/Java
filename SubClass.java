class SubClass extends SuperClass{
    int d;
    SubClass(int l, int m, int n, int o){ 
        super(l,m,n);d=o;
    }

    void Show(){
        super.Show("Class SuperClass ");
        super.Show();
    }

    void Show(String x){
        System.out.println(x);
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("c = " + c);
        System.out.println("d = " + d);
    }

    public static void main(String args[]){
        SubClass obj = new SubClass(4,7,1,8);
        obj.d=(obj.b*obj.b)-4*obj.a*obj.c;
        obj.Show();
        obj.Show("Class SubClass");
    }
}