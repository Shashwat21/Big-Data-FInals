package com.neu.book;

import java.io.IOException;
import java.util.regex.Pattern;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Mapper3 extends Mapper<LongWritable, Text, Text, IntWritable> {
public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
	String line = value.toString();
	String yr = "";
	String rank = "";

	String[] arr = line.split("\t");

	if (Pattern.matches("[0-9]{4}", arr[1]))
		yr = arr[1];
	if (Pattern.matches("\"[0-9]{1,2}\"", arr[2]))
		rank = arr[2];

	if (yr.equals("2002")) {
		context.write(new Text(rank.replaceAll("\"", "")),
				new IntWritable(1));
		}
	}
}