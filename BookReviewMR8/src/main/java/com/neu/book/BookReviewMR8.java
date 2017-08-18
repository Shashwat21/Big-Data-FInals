/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.book;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.jobcontrol.JobControl;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.jobcontrol.ControlledJob;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class BookReviewMR8 {
    public static void main(String[] args)throws IOException, ClassNotFoundException, InterruptedException {
    	// First Map Reduce (JOIN)
    			Configuration joinConf = new Configuration();
    			Job joinJob = Job.getInstance(joinConf,"Joining Books and Book-Ratings");
    			
    			joinJob.setJarByClass(BookReviewMR8.class);
    			joinJob.setReducerClass(Reducer1.class);
    			
    			joinJob.setOutputKeyClass(Text.class);
    			joinJob.setOutputValueClass(Text.class);
    			
    			MultipleInputs.addInputPath(joinJob, new Path(args[0]), TextInputFormat.class,Mapper1.class);
    			MultipleInputs.addInputPath(joinJob, new Path(args[1]),TextInputFormat.class,Mapper2.class);

    			joinJob.setOutputFormatClass(TextOutputFormat.class);
    			FileOutputFormat.setOutputPath(joinJob, new Path(args[2]));

    			//Path joinOutputPath = new Path(args[2]);
    			//joinOutputPath.getFileSystem(joinConf).delete(joinOutputPath, true);
    			boolean complete =joinJob.waitForCompletion(true);

    			// Second Map Reduce (Analyze)
    			Configuration analyzeConf = new Configuration();

    			Job job2 = Job.getInstance(analyzeConf,"Number of books in 2002 on the basis of rank");
    			if(complete){
    				job2.setJarByClass(BookReviewMR8.class);
    				job2.setMapperClass(Mapper3.class);
    				job2.setMapOutputKeyClass(Text.class);
    				job2.setMapOutputValueClass(IntWritable.class);

    				job2.setReducerClass(Reducer2.class);
    				job2.setOutputValueClass(IntWritable.class);
    				job2.setOutputKeyClass(Text.class);
        			
        			FileInputFormat.setInputPaths(job2, new Path(args[2] + "/part*"));
        			FileOutputFormat.setOutputPath(job2, new Path(args[3]));
        			
        			System.exit(job2.waitForCompletion(true) ? 0 : 1);
    			}
    }    
}
