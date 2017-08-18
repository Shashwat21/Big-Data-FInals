/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.book;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class Reducer1 extends Reducer<Text, Text, Text, Text> {
	public void reduce(Text key, Iterable<Text> value, Context context)throws IOException, InterruptedException {
		String yr = "";
		String rank = "";
		String finalRes = "";
		float avgRank = 0;
		int nRanks = 0;

		for (Text x : value) {
			String[] temp = x.toString().split("\t");

			if (temp.length >= 2) {
				if (temp[0].equals("Year")) {
					yr = temp[1];
				}
				if (temp[0].equals("Rank")) {
					try {
						avgRank += Integer.parseInt(temp[1]);
					} catch (NumberFormatException e) {
						System.err.println("## ERROR ## temp[1]: "+ temp[1]);
						e.printStackTrace();
					}
					nRanks++;
				}
			}
		}
		nRanks = nRanks <= 0 ? 1 : nRanks;
		avgRank = avgRank / nRanks;
		rank = String.valueOf((int) Math.round(avgRank));
		finalRes = yr + "\t\"" + rank + "\"";
		context.write(key, new Text(finalRes));
	}
}