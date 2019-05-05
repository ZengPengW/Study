import java.util.Scanner;
 
public class Encryption {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String str=sc.next();
		System.out.println(EncryptChar(str));;
	}

	private static String EncryptChar(String str) {
		StringBuilder sb=new StringBuilder();
		char ch;
		for (int i = 0; i < str.length(); i++) {
			ch=str.charAt(i);
			ch=change(ch);
			sb.append(ch);
		}
		return sb.toString();
	}

	private static char change(char ch) {
		if(ch=='Z')return 'a';
		if(ch=='z')return 'A';
		if((ch<'A'||ch>'z'))return ch;
		return (char)(((int)ch)+1);
	}

	
 
}
