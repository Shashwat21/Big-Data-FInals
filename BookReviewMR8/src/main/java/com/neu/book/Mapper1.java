/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.book;

import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Mapper1 extends Mapper<LongWritable, Text, Text, Text> {
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String line = value.toString();
		String[] data = line.split(";");
		String isbn = data[0];
		String yr = data[3];
		
		if (!yr.equals("") || Integer.parseInt(yr)!=0) {
			context.write(new Text(isbn), new Text("Year\t" + yr));
		}
	}    
}
