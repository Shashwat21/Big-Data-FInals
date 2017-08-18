/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.book;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class BookReviewMR2 {
    public static void main(String[] args)throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
		Job job = Job.getInstance(conf,"BookReviewsMR2");
		job.setJarByClass(BookReviewMR2.class);
		job.setMapperClass(Mapper1.class);
		job.setReducerClass(Reducer1.class);
		//Path Setting
		Path inputPath = new Path(args[0]);
		Path outputDir = new Path(args[1]);
		Path second_input_path = outputDir;
		Path final_output = new Path(args[2]);
		
		//Setting output key and value class types
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
				
		//Setting up input and output directories
		FileInputFormat.addInputPath(job, inputPath);
		FileOutputFormat.setOutputPath(job, outputDir);
		
		//Second JOB
		boolean complete = job.waitForCompletion(true);

		Configuration conf2 = new Configuration();
		Job job2 = Job.getInstance(conf2, "chaining");

		//For Mapper2 and Reducer2, second job
		if (complete) {
			job2.setJarByClass(BookReviewMR2.class);
			job2.setMapperClass(Mapper2.class);
			job2.setMapOutputKeyClass(Text.class);
			job2.setMapOutputValueClass(Text.class);

			job2.setReducerClass(Reducer2.class);
			job2.setOutputValueClass(NullWritable.class);
			job2.setOutputKeyClass(Text.class);

			FileInputFormat.addInputPath(job2, second_input_path);
			FileOutputFormat.setOutputPath(job2, final_output);

			System.exit(job2.waitForCompletion(true) ? 0 : 1);
		}		
		//Run the job and wait till its completed
		//System.exit(job.waitForCompletion(true) ? 0 :1);
    }    
}
