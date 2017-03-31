package com.thalmic.android.sample.helloworld;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
	public Map<Code, TrieNode> morse;
    boolean isEnd;
    char value;
    public TrieNode() {
    	morse = new HashMap<Code, TrieNode>();
    	isEnd =true;
    }
   public void setValue(char value){
    	this.value = value;
    }
   public char getValue(){
	   return value;
   }
   public boolean isLeaf(){
	   return isEnd;
   }
   public void setLeaf(boolean val){
	    isEnd = val;
   }
}
