/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.book;

import java.io.IOException;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class BooksAvgReducer extends Reducer<Text, FloatWritable, Text, FloatWritable> {
    private FloatWritable result = new FloatWritable();

    public void reduce(Text key, Iterable<FloatWritable> values,Context context) throws IOException, InterruptedException {
        float sum = 0f;
        int count = 0;
        for (FloatWritable val : values) {
            sum = sum + val.get();
            count++;
        }
        float average = (float)(sum/count);
        result.set(average);
        context.write(key, result);
    }
}
