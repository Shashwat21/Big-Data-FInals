/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.book;

import java.io.IOException;
import java.util.StringTokenizer;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Mapper1 extends Mapper<Object,Text,Text,Text>{
    private Text word = new Text();
	
    public void map(Object key,Text value,Context context) throws IOException, InterruptedException{
            
        String[] data = value.toString().split(";");
        String userID = new String(data[0]);
        String locISBN = new String(data[1]);
        //Ignoring Header Row
        if (value.toString().contains("ISBN")|| value.toString().contains("Location") || userID == null || userID.isEmpty()) {

        } else {        		
        		userID = userID.replace("\"", "");
        		if(locISBN.matches(".*\\d+.*")){
        			locISBN = "#" + locISBN;
        			context.write(new Text(userID), new Text(locISBN));
        		}
        		else{
        			locISBN = "@" + locISBN;
        			context.write(new Text(userID), new Text(locISBN));
        		}
            	
        }
    }
}
