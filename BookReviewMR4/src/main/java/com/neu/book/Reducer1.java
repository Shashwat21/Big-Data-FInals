/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.book;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class Reducer1 extends Reducer<Text, Text, Text, IntWritable> {

    public void reduce(Text key, Iterable<Text> values,Context context) throws IOException, InterruptedException {
    	Text userID = key;
    	ArrayList<String> arrISBN = new ArrayList<String>();
    	ArrayList<String> arrAge = new ArrayList<String>();
    	String location = "";
    	for(Text val : values) {
    		String var = val.toString();
    		if(var.startsWith("#")){
    			arrISBN.add(var.substring(1));
    		}
    		else{
    			arrAge.add(var);
    		}
    	}
    	if(arrISBN.size() >= 1 && arrAge.size() > 0){
    		for(int i=0; i<arrISBN.size();i++){
    			for(int j=0;j<arrAge.size();j++){
    				int age = Integer.parseInt(arrAge.get(j));
    				String ageGroup = "";
    				if(age <= 19){
    					ageGroup = "Teenager";
    				}
    				else if(age>19 && age <=34){
    					ageGroup = "Millenial";
    				}
    				else if(age>34 && age <=50){
    					ageGroup = "GenX";
    				}
    				else if(age>50 && age <=69){
    					ageGroup = "Boomer";
    				}
    				else{
    					ageGroup = "Silent";
    				}
    				Text keyss = new Text(arrISBN.get(i)+"%%%"+ageGroup);
        			context.write(keyss,new IntWritable(1));
    			}    			
    		}
    	}    		
    }
}