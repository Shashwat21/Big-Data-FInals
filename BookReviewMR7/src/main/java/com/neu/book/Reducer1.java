/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.book;

import java.io.IOException;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class Reducer1 extends Reducer<Text, IntWritable, Text, IntWritable> {
	int max = 0;
	Text yr = new Text("");
	
	public void reduce(Text key, Iterable<IntWritable> value, Context context){
		
		int sum = 0;
		for(IntWritable x : value){
			sum += x.get();
		}
		
		if(sum>max){
			max = sum;
			yr.set(key);
		}
	}
	@Override
	protected void cleanup(Context context)throws IOException, InterruptedException{
		context.write(yr, new IntWritable(max));
	}
}
