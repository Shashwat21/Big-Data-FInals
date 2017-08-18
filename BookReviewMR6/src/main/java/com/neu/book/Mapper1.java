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
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
public class Mapper1 extends Mapper<LongWritable, Text, Text, IntWritable> {

	public void map(LongWritable key, Text value, Context context)throws IOException, InterruptedException {
		String line = value.toString();
		String data[] = line.split(";");
		String year = data[3];
		year = year.replaceAll("\"","");
		// Setting the YearOfPulbication detail values in 'value'
		value.set(year);
		context.write(value, new IntWritable(1));
	}
}

