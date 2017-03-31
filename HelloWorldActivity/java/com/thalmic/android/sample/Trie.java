package com.thalmic.android.sample.helloworld;

public class Trie {
	private TrieNode root;
	 
    public Trie() {
        root = new TrieNode();
    }
    
    
    public boolean insert(Code[] word, char val){
    	TrieNode p = root;
    	int i=0;
    	while(i<word.length && word[i] != Code.END){
        	Code c = word[i];
        	if(p.morse.get(c) == null){
        		TrieNode temp = new TrieNode();
            	p.morse.put(c, temp);
            	p=temp;
        	}else{
        		p=p.morse.get(c);
        	}	
            ++i;
    	}
    	if(i== word.length){
    		return false;
    	}
    	else{
    		TrieNode temp = new TrieNode();
        	p.morse.put(word[i], temp);
        	p=temp;
    		p.setLeaf(true);
        	p.setValue(val);
        	return true;
    	}
    }
    
    public char getLetter(Code[] word){
    	TrieNode p = root;
    	int i=0;
    	while(i < word.length && word[i] != Code.END){
    		Code c = word[i];
        	if(p.morse.get(c) == null){
        		return '0';
        	}else{
        		p=p.morse.get(c);
        	}	
            ++i;
    	}
    	if(i== word.length){
    		return '0';
    	}
    	else{
    		p=p.morse.get(word[i]);
    		return p.getValue();
    	}
    }
    
    
    
    
 }
    
    