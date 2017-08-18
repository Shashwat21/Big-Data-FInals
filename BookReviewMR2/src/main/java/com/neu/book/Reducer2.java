/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.book;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class Reducer2 extends Reducer<Text, Text, Text, NullWritable> {

    public void reduce(Text key, Iterable<Text> values,Context context) throws IOException, InterruptedException {
    	int counter = 0;
    	for (Text value : values) {
    		counter++;
		}
    	String newKey = key.toString();
    	newKey = newKey +"%"+ String.valueOf(counter);
    	context.write(new Text(newKey),null);	
    }
}