package lv.accenture.bootcamp.network;

public class EffectiveString {

	
	public static void main(String[]args) {
		String s = "";
		s += "Hello";
		s += " ";
		s += "world";
		s += "!";
		
		System.out.println(s);
		
		StringBuilder str = new StringBuilder();
		str.append("Hello");
		str.append(" ");
		str.append("world");
		str.append("!");
		
		String s2 = str.toString();
		System.out.println(s2);
	}
}
