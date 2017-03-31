package com.thalmic.android.sample.helloworld;

public class Parser {
	public Trie morse_tree;
	
	//1 and above is brile 0 is morse
public Parser(int num){
	morse_tree = new Trie();
	if(num==0){
		morse_tree.insert(parse(Code.DOT,Code.LINE,Code.END), 'a');
		morse_tree.insert(parse(Code.LINE,Code.DOT,Code.DOT,Code.DOT,Code.END), 'b');
		morse_tree.insert(parse(Code.LINE,Code.DOT,Code.LINE,Code.DOT,Code.END), 'c');
		morse_tree.insert(parse(Code.LINE,Code.DOT,Code.DOT,Code.END), 'd');
		morse_tree.insert(parse(Code.DOT,Code.END), 'e');
		morse_tree.insert(parse(Code.DOT,Code.DOT,Code.LINE,Code.DOT,Code.END), 'f');
		morse_tree.insert(parse(Code.LINE,Code.LINE,Code.DOT,Code.END), 'g');
		morse_tree.insert(parse(Code.DOT,Code.DOT,Code.DOT,Code.DOT,Code.END), 'h');
		morse_tree.insert(parse(Code.DOT,Code.DOT,Code.END), 'i');
		morse_tree.insert(parse(Code.DOT,Code.LINE,Code.LINE,Code.LINE,Code.END), 'j');
		morse_tree.insert(parse(Code.LINE,Code.DOT,Code.LINE,Code.END), 'k');
		morse_tree.insert(parse(Code.DOT,Code.LINE,Code.DOT,Code.DOT,Code.END), 'l');
		morse_tree.insert(parse(Code.LINE,Code.LINE,Code.END), 'm');
		morse_tree.insert(parse(Code.LINE,Code.DOT,Code.END), 'n');
		morse_tree.insert(parse(Code.LINE,Code.LINE,Code.LINE,Code.END), 'o');
		morse_tree.insert(parse(Code.DOT,Code.LINE,Code.LINE,Code.DOT,Code.END), 'p');
		morse_tree.insert(parse(Code.LINE,Code.LINE,Code.DOT,Code.LINE,Code.END), 'q');
		morse_tree.insert(parse(Code.DOT,Code.LINE,Code.DOT,Code.END), 'r');
		morse_tree.insert(parse(Code.DOT,Code.DOT,Code.DOT,Code.END), 's');
		morse_tree.insert(parse(Code.LINE,Code.END), 't');
		morse_tree.insert(parse(Code.DOT,Code.DOT,Code.LINE,Code.END), 'u');
		morse_tree.insert(parse(Code.DOT,Code.DOT,Code.DOT,Code.LINE,Code.END), 'v');
		morse_tree.insert(parse(Code.DOT,Code.LINE,Code.LINE,Code.END), 'w');
		morse_tree.insert(parse(Code.LINE,Code.DOT,Code.DOT,Code.LINE,Code.END), 'x');
		morse_tree.insert(parse(Code.LINE,Code.DOT,Code.LINE,Code.LINE,Code.END), 'y');
		morse_tree.insert(parse(Code.LINE,Code.LINE,Code.DOT,Code.DOT,Code.END), 'z');
	}else{
		morse_tree.insert(parse(Code.DOT,Code.END), 'a');
		morse_tree.insert(parse(Code.DOT,Code.BLANK,Code.DOT,Code.END), 'b');
		morse_tree.insert(parse(Code.DOT,Code.DOT,Code.END), 'c');
		morse_tree.insert(parse(Code.DOT,Code.DOT,Code.BLANK,Code.DOT,Code.END), 'd');
		morse_tree.insert(parse(Code.DOT,Code.BLANK,Code.BLANK,Code.DOT,Code.END), 'e');
		morse_tree.insert(parse(Code.DOT,Code.DOT,Code.DOT,Code.END), 'f');
		morse_tree.insert(parse(Code.DOT,Code.DOT,Code.DOT,Code.DOT,Code.END), 'g');
		morse_tree.insert(parse(Code.DOT,Code.BLANK,Code.DOT,Code.DOT,Code.END), 'h');
		morse_tree.insert(parse(Code.BLANK,Code.DOT,Code.DOT,Code.BLANK,Code.END), 'i');
		morse_tree.insert(parse(Code.BLANK,Code.DOT,Code.DOT,Code.DOT,Code.END), 'j');
		morse_tree.insert(parse(Code.DOT,Code.BLANK,Code.BLANK,Code.BLANK,Code.DOT,Code.END), 'k');
		morse_tree.insert(parse(Code.DOT,Code.BLANK,Code.DOT,Code.BLANK,Code.DOT,Code.BLANK,Code.END), 'l');
		morse_tree.insert(parse(Code.DOT,Code.DOT,Code.BLANK,Code.BLANK,Code.DOT,Code.END), 'm');
		morse_tree.insert(parse(Code.DOT,Code.DOT,Code.BLANK,Code.DOT,Code.DOT,Code.END), 'n');
		morse_tree.insert(parse(Code.DOT,Code.BLANK,Code.BLANK,Code.DOT,Code.DOT,Code.END), 'o');
		morse_tree.insert(parse(Code.DOT,Code.DOT,Code.DOT,Code.BLANK,Code.DOT,Code.END), 'p');
		morse_tree.insert(parse(Code.DOT,Code.DOT,Code.DOT,Code.DOT,Code.DOT,Code.END), 'q');
		morse_tree.insert(parse(Code.DOT,Code.BLANK,Code.DOT,Code.DOT,Code.DOT,Code.END), 'r');
		morse_tree.insert(parse(Code.BLANK,Code.DOT,Code.DOT,Code.BLANK,Code.DOT,Code.END), 's');
		morse_tree.insert(parse(Code.BLANK,Code.DOT,Code.DOT,Code.DOT,Code.DOT,Code.END), 't');
		morse_tree.insert(parse(Code.DOT,Code.BLANK,Code.BLANK,Code.BLANK,Code.DOT,Code.DOT,Code.END), 'u');
		morse_tree.insert(parse(Code.DOT,Code.BLANK,Code.DOT,Code.BLANK,Code.DOT,Code.DOT,Code.END), 'v');
		morse_tree.insert(parse(Code.BLANK,Code.DOT,Code.DOT,Code.DOT,Code.BLANK,Code.DOT,Code.END), 'w');
		morse_tree.insert(parse(Code.DOT,Code.DOT,Code.BLANK,Code.BLANK,Code.DOT,Code.DOT,Code.END), 'x');
		morse_tree.insert(parse(Code.DOT,Code.DOT,Code.BLANK,Code.DOT,Code.DOT,Code.DOT,Code.END), 'y');
		morse_tree.insert(parse(Code.DOT,Code.BLANK,Code.BLANK,Code.DOT,Code.DOT,Code.DOT,Code.END), 'z');
	}
}

public Code[] addChar(Code[] a, Code c){
	Code[] word = new Code[a.length+1];
	for(int i=0;i<a.length;++i){
		word[i] = a[i];
	}
	word[a.length] = c;
	return word;
}

public Code[] parse(Code a,Code b){
	Code[] word = new Code[2];
	word[0] = a;
	word[1] = b;
	return word;
}
public Code[] parse(Code a,Code b,Code c){
	Code[] word = new Code[3];
	word[0] = a;
	word[1] = b;
	word[2] = c;
	return word;
}
public Code[] parse(Code a,Code b,Code c,Code d){
	Code[] word = new Code[4];
	word[0] = a;
	word[1] = b;
	word[2] = c;
	word[3] = d;
	return word;
}
public Code[] parse(Code a,Code b,Code c,Code d,Code e){
	Code[] word = new Code[5];
	word[0] = a;
	word[1] = b;
	word[2] = c;
	word[3] = d;
	word[4] = e;
	return word;
}
public Code[] parse(Code a,Code b,Code c,Code d,Code e,Code f){
	Code[] word = new Code[6];
	word[0] = a;
	word[1] = b;
	word[2] = c;
	word[3] = d;
	word[4] = e;
	word[5] = f;
	return word;
}
public Code[] parse(Code a,Code b,Code c,Code d,Code e,Code f,Code g){
	Code[] word = new Code[7];
	word[0] = a;
	word[1] = b;
	word[2] = c;
	word[3] = d;
	word[4] = e;
	word[5] = f;
	word[6] = g;
	return word;
}
}
