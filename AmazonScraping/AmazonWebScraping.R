library("dplyr")
library("XML")
library("stringr")
library("rvest")
library("audio")
#Remove all white space
trim <- function (x) gsub("^\\s+|\\s+$", "", x)
#Function to scrape Amazon Reviews
scrapeReviews <- function(doc){
  revAuthor = html_nodes(doc, ".author") %>% html_text() 
  revText   = html_nodes(doc,".review-text") %>% html_text()
  revRating = doc %>% html_nodes("#cm_cr-review_list  .review-rating") %>% html_text() %>% str_extract("\\d") %>% as.numeric()
  df = data.frame(revAuthor,revText,revRating,stringsAsFactors = F)
  return(df)
}
#Product Code
productCode = "B00WK47VEW"
prodURL = paste0("https://www.amazon.com/dp/",productCode)
readURL = read_html(prodURL)
#Fetching Product Name from Amazon
productName = readURL %>% html_nodes("#productTitle")%>% html_text() %>% gsub("\n", "", .)%>% trim()
#Scraping Customer Name, Review and Ratings
allReviews = NULL
for(pageNum in 1:3){
  url = paste0("https://www.amazon.com/product-review/", productCode,"/?pageNumber=",pageNum)
  doc =read_html(url)
  amazonRev = scrapeReviews(doc)
  allReviews = rbind(allReviews,cbind(productName,amazonRev))
}
write.csv(allReviews, file = "ProductReviews.csv",row.names=FALSE)
