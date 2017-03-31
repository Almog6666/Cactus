package com.thalmic.android.sample.helloworld;

import java.util.ArrayList;

public class TextToMorse {
	char letter;
	ArrayList<Code> morse_code;
	public TextToMorse(char a){
		letter = a;
		morse_code = new ArrayList<Code>();
	}
	
	public ArrayList<Code> getMorseCode(){
		morse_code.clear();
		switch(letter){
		case 'a':
			morse_code.add(Code.DOT);morse_code.add(Code.LINE);
			break;
		case 'b':
			morse_code.add(Code.LINE);morse_code.add(Code.DOT);
			morse_code.add(Code.DOT);morse_code.add(Code.DOT);
			break;
		case 'c':
			morse_code.add(Code.LINE);morse_code.add(Code.DOT);
			morse_code.add(Code.LINE);morse_code.add(Code.DOT);
			break;
		case 'd':
			morse_code.add(Code.LINE);morse_code.add(Code.DOT);morse_code.add(Code.DOT);
			break;
		case 'e':
			morse_code.add(Code.DOT);
			break;
		case 'f':
			morse_code.add(Code.DOT);morse_code.add(Code.DOT);
			morse_code.add(Code.LINE);morse_code.add(Code.DOT);
			break;
		case 'g':
			morse_code.add(Code.LINE);morse_code.add(Code.LINE);morse_code.add(Code.DOT);
			break;
		case 'h':
			morse_code.add(Code.DOT);morse_code.add(Code.DOT);morse_code.add(Code.DOT);
			morse_code.add(Code.DOT);
			break;
		case 'i':
			morse_code.add(Code.DOT);morse_code.add(Code.DOT);
			break;
		case 'j':
			morse_code.add(Code.DOT);morse_code.add(Code.LINE);morse_code.add(Code.LINE);
			morse_code.add(Code.LINE);
			break;
		case 'k':
			morse_code.add(Code.LINE);morse_code.add(Code.DOT);morse_code.add(Code.LINE);
			break;
		case 'l':
			morse_code.add(Code.DOT);morse_code.add(Code.LINE);morse_code.add(Code.DOT);morse_code.add(Code.DOT);
			break;
		case 'm':
			morse_code.add(Code.LINE);morse_code.add(Code.LINE);
			break;
		case 'n':
			morse_code.add(Code.LINE);morse_code.add(Code.DOT);
			break;
		case 'o':
			morse_code.add(Code.LINE);morse_code.add(Code.LINE);morse_code.add(Code.LINE);
			break;
		case 'p':
			morse_code.add(Code.DOT);morse_code.add(Code.LINE);morse_code.add(Code.LINE);morse_code.add(Code.DOT);
			break;
		case 'q':
			morse_code.add(Code.LINE);morse_code.add(Code.LINE);morse_code.add(Code.DOT);
			morse_code.add(Code.LINE);
			break;
		case 'r':
			morse_code.add(Code.DOT);morse_code.add(Code.LINE);morse_code.add(Code.DOT);
			break;
		case 's':
			morse_code.add(Code.DOT);morse_code.add(Code.DOT);morse_code.add(Code.DOT);
			break;
		case 't':
			morse_code.add(Code.LINE);
			break;
		case 'u':
			morse_code.add(Code.DOT);morse_code.add(Code.DOT);morse_code.add(Code.LINE);
			break;
		case 'v':
			morse_code.add(Code.DOT);morse_code.add(Code.DOT);morse_code.add(Code.DOT);morse_code.add(Code.LINE);
			break;
		case 'w':
			morse_code.add(Code.DOT);morse_code.add(Code.LINE);morse_code.add(Code.LINE);
			break;
		case 'x':
			morse_code.add(Code.LINE);morse_code.add(Code.DOT);
			morse_code.add(Code.DOT);morse_code.add(Code.LINE);
			break;
		case 'y':
			morse_code.add(Code.LINE);morse_code.add(Code.DOT);morse_code.add(Code.LINE);
			morse_code.add(Code.LINE);
			break;
		case 'z':
			morse_code.add(Code.LINE);morse_code.add(Code.LINE);morse_code.add(Code.DOT);
			morse_code.add(Code.DOT);
			break;
		}
		return morse_code;
	}
	
}
