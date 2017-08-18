/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.book;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class Reducer1 extends Reducer<Text, Text, Text, Text> {

    public void reduce(Text key, Iterable<Text> values,Context context) throws IOException, InterruptedException {
    	Text userID = key;
    	ArrayList<String> arrISBN = new ArrayList<String>();
    	String location = "";
    	for(Text val : values) {
    		String isbnLoc = val.toString();
    		if(isbnLoc.startsWith("#")){
    			arrISBN.add(isbnLoc.substring(1));
    		}
    		else{
    			location = isbnLoc.substring(1);
    		}
    	}
    	if(arrISBN.size() >= 1 && location.length() > 0){
    		for(int i=0; i<arrISBN.size();i++){
    			Text keyss = new Text(arrISBN.get(i)+"%"+location);
    			context.write(keyss,userID);
    		}
    	}    		
    }
}