library("dplyr")
library("XML")
library("stringr")
library("rvest")
library("audio")

setwd("/Users/varshacholennavar/Desktop/AmazonReviews/BX-CSV-Dump")
booksData = file("BX-Books.csv", "r", blocking = FALSE)
line = readLines(booksData)
for(i in 2:length(line)){
  p = gsub("&amp;","&",line[i])
  p = gsub("'","",p)
  p = gsub("\\\"","",p)
  p = gsub("\";", "\"æ",p)
  write(p,file="testData.txt",append=TRUE)
}
