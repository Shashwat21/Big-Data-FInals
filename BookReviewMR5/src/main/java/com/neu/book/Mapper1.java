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

/**
 *
 * @author varshacholennavar
 */
public class Mapper1 extends Mapper<Object,Text,Text,IntWritable>{
    private Text word = new Text();
    public void map(Object key,Text value,Context context) 
    		throws IOException, InterruptedException{
        String line = value.toString();
        line = line.replace("\";\"", "\"æ\"");
        String data[] = line.split("æ");
        String isbn = new String(data[0]);
        String yearOfPublish = new String(data[3]);
        String authorName = new String(data[2]);
        yearOfPublish = yearOfPublish.replace("\"","");        
        if (value.toString().contains("ISBN")) {
        } else {
        	int yearPub = Integer.parseInt(yearOfPublish);
        	if(yearPub != 0){
        		String keyToSend = yearOfPublish+"--"+authorName;
        		context.write(new Text(keyToSend), new IntWritable(1));
        	}
        }
    }
}
