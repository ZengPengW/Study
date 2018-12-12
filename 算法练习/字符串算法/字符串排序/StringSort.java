class A{
	int key;
	String val;
	public A(int key,String val) {
		this.key=key;
		this.val=val;
	}
}
public class StringSort {
	
	
	
	public static void main(String[] args) throws Exception {
	String[] s= {"now","is","the","time","for","all","good","people","to","come","to","the","aid","of"};
	Quick3String(s, 0, s.length-1, 0);
	for (String string : s) {
		System.out.println(string);
	}
		
		//Lsd();
		
	}
	public static void Lsd() {//∞¥’’º¸÷µ≈≈–Ú
		A[] a=new A[20];
		a[0]=new A(2, "Anderson");
		a[1]=new A(3, "Brown");
		a[2]=new A(3, "Davis");
		a[3]=new A(4, "Garcia");
		a[4]=new A(1, "Harris");
		a[5]=new A(3, "Jackson");
		a[6]=new A(4, "Johhson");
		a[7]=new A(3, "Jones");
		a[8]=new A(1, "Martine");
		a[9]=new A(2, "Martinez");
		a[10]=new A(2, "Miller");
		a[11]=new A(1, "Moore");
		a[12]=new A(2, "Robinson");
		a[13]=new A(4, "Smith");
		a[14]=new A(3, "Taylor");
		a[15]=new A(4, "Thomas");
		a[16]=new A(4, "Thompson");
		a[17]=new A(2, "White");
		a[18]=new A(3, "Williams");
		a[19]=new A(6, "Wilson");
		
		int []count=new int [a.length+1];
		for (int i = 0; i < a.length; i++) {
			count[a[i].key]++;
		}
		
		for (int i = 0; i < a.length; i++) {
			count[i+1]+=count[i];
		}
		
		A[] aux=new A[a.length];
		for (int i = 0; i < a.length; i++) {
			aux[count[a[i].key-1]++]=a[i];
		}
		for (int i = 0; i < aux.length; i++) {
			a[i]=aux[i];
		}
		
		for (A aa : a) {
			System.out.println(aa.key+" "+aa.val);
		}
	}
	private static int charAt(String a,int d) {
		if(d<a.length())return a.charAt(d);
		else return -1;
	}
	
	public static void Quick3String(String []s,int lo,int hi,int d) {//∞¥◊÷∑˚¥Û–°≈≈–Ú
		if(hi<=lo)return;
		int lt=lo,gt=hi;
		int v=charAt(s[lo], d);
		int i=lo+1;
		
		while (i<=gt) {
			int t=charAt(s[i], d);
			if(t>v) {
				String temp=s[i];
				s[i]=s[gt];
				s[gt--]=temp;
			}else if (t<v) {
				String temp=s[i];
				s[i++]=s[lt];
				s[lt++]=temp;	
			}else {
				i++;
			}
			
		}
		
		Quick3String(s, lo, lt-1, d);
		if(v>=0)Quick3String(s, lt, gt, d+1);
		Quick3String(s, gt+1, hi, d);
		
		
	}

	
}
