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

public class Mapper2 extends Mapper<Object,Text,Text,Text>{
    private Text word = new Text();
	
    public void map(Object key,Text value,Context context) throws IOException, InterruptedException{
        String[] data = value.toString().split("\t");
        Text locISBNKey = new Text(data[0]);
        Text userIDVal = new Text(data[1]);
    	context.write(locISBNKey,userIDVal);
    }
}
