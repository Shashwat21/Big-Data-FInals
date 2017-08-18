library("dplyr")
library("XML")
library("stringr")
library("rvest")
library("audio")
library("aws.s3")

#Creating a connection with S3 AWS
Sys.setenv("AWS_ACCESS_KEY_ID" = "AKIAJM2RRWHOTKTD3KDA","AWS_SECRET_ACCESS_KEY" = "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")

#Remove all white space
trim <- function (x) gsub("^\\s+|\\s+$", "", x)
#Function to scrape Amazon Reviews
scrapeReviews <- function(doc){
  revAuthor = html_nodes(doc, ".review-byline .author") %>% html_text() 
  revText   = html_nodes(doc,"#cm_cr-review_list .review-text") %>% html_text()
  revRating = doc %>% html_nodes("#cm_cr-review_list  .review-rating") %>% html_text() %>% str_extract("\\d") %>% as.numeric()
  df = data.frame(revAuthor,revText,revRating,stringsAsFactors = F)
  return(df)
}
#Product Code
#productCode = "B00WK47VEW" #Bose-SoundLinkMini
#productCode = "B01LWWY3E2" #Beats-Solo-3
#productCode = "B01E3SNO1G" #Bose Quiet Comfort QC-35
productCode = "B00ZV9PXP2" #AmazonKindle
prodURL = paste0("https://www.amazon.com/dp/",productCode)
readURL = read_html(prodURL)
#Fetching Product Name from Amazon
productName = readURL %>% html_nodes("#productTitle")%>% html_text() %>% gsub("\n", "", .)%>% trim()
#Scraping Customer Name, Review and Ratings
allReviews = NULL
for(pageNum in 1:60){
  url = paste0("https://www.amazon.com/product-review/", productCode,"/?pageNumber=",pageNum)
  document =read_html(url)
  amazonRev = scrapeReviews(document)
  allReviews = rbind(allReviews,cbind(productName,amazonRev))
}
write.csv(allReviews, file = "ProductReviews.csv",row.names=FALSE)
#write.table(allReviews,"ProductReviews-New.csv", row.names=F,na="NA",append=T, quote= FALSE, sep=",", col.names=F)
put_object(file = "ProductReviews.csv",bucket = "s3://bookreviews21/")
system("/usr/local/bin/hadoop-2.8.0/bin/hadoop fs -put /Users/varshacholennavar/Desktop/AmazonReviews/AmazonScraping/ProductReviews.csv /SentimentAnalysis/")