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
    private FloatWritable result = new FloatWritable();
    public void reduce(Text key, Iterable<IntWritable> values,Context context) 
    		throws IOException, InterruptedException {
        int count = 0;
        for (IntWritable val : values) {            
            count++;
        }
        context.write(key, new IntWritable(count));
    }
}
