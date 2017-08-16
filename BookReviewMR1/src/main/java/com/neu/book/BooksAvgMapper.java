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
public class BooksAvgMapper extends Mapper<Object,Text,Text,FloatWritable>{
    private Text word = new Text();
	
    public void map(Object key,Text value,Context context) throws IOException, InterruptedException{
            
        //String[] data = value.toString().split(";");
        String line = value.toString();
        line = line.replace("'", "");
        line = line.replace("\\\"","");
        line = line.replace("\";", "\"æ");
        String data[] = line.split("æ");
        Text isbn = new Text(data[1]);
        String ratings = new String(data[2]);
        ratings = ratings.replace("\"","");

        if (value.toString().contains("ISBN")|| value.toString().contains("Book-Rating")) {

        } else {
            Float bookRatings = Float.parseFloat(ratings);
            if(bookRatings != 0)
            	context.write(isbn, new FloatWritable(bookRatings));
        }
    }
}
