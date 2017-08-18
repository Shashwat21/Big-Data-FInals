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

public class Mapper2 extends Mapper<LongWritable, Text, Text, Text> {
	public void map(LongWritable key, Text value, Context context)throws IOException, InterruptedException {
	String line = value.toString();
	String[] data = line.split(";");
	if (!line.contains("ISBN") && !line.contains("Book-Rating")) {
			String isbn = data[1];
			String rank = data[2];
			rank = rank.replace("\"", "");
			context.write(new Text(isbn), new Text("Rank\t" + rank));
		}
	}
}
