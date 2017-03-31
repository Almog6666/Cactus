package com.thalmic.android.sample.helloworld;

public class maind {
	 public static void main(String[] args) {
	        Parser p=new Parser(0);
	        Code[] ch = p.parse(Code.LINE,Code.DOT,Code.LINE,Code.DOT,Code.END);
	        char a = p.morse_tree.getLetter(ch);
	        System.out.println("the charecter is: "+ a);
	        
	        Code[] ch1 = p.parse(Code.LINE,Code.DOT);
	        ch1 = p.addChar(ch1,Code.END);
	        char a1 = p.morse_tree.getLetter(ch1);
	        System.out.println("the charecter is: "+ a1);
	    }
}
