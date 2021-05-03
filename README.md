# Coeus-News-Portal-backend functions

This repo creates the backend API for connection to an AWS RDS database, to be consumed by the Coeus News portal front-end React application, available here.

The hosted version of the application is available here: https://github.com/Coeus-Group/coeus-news-portal-reactfrontend

Technology
This project used the following:

- Java 8
- Maven
- Serverless Framework
- Terraform
- AWS Lambda and API Gateway
- AWS RDS (MySQL 5.7)

Setup

Download or clone this repo. The codebase uses Maven for dependency management and Serverless Framework for deploying to AWS Lambda.

To build the application:

- mvn clean install 

To deploy the Lambda functions to AWS:

- serverless deploy

Configuration

AWS keys set up for your Serverless installation to be referenced from the project serverless.yml file.

Create a config.dev.json file at the project level containing the following name/value pairs:

{
 - "DB_HOST" : "<database_host_name>",
 - "DB_NAME" : "<database_name>",
 - "DB_USER" : "<database_user>",
 - "DB_PASSWORD" : "<database_password>"
 
} 

Deployment
To deploy the Lambda functions run:

serverless deploy 

This will provision the required API Gateway and upload the JAR file to AWS Lambda.

REST API

The definition of the REST API can be found here.

Lambda HTTP Functions
# GET Articles
  getarticles-coeusnews-api:
  
   handler: com.coeusnews.GetArticlesCoeusnewsHandler   
   
    events: 
    
      - http:     
       
          path: /getArticles
          
          method: get 
          
          cors: true
          

# GET articles based on category  
  getcategory-coeusnews-api:
  
    handler: com.coeusnews.GetCategoryCoeusnewsHandler
    
    events:
    
      - http:
      
          path: /getCategories/{category}
          
          method: get
          
          cors: true
          

# GET articles based on location  
  getlocation-coeusnews-api:
  
    handler: com.coeusnews.GetLocationCoeusnewsHandler
    
    events:
    
      - http:
      
          path: /getLocations/{location}
          
          method: get
          
          cors: true
          
          
# POST
  save-coeusnews-api:
  
    handler: com.coeusnews.SaveCoeusnewsHandler
    
    events:
    
      - http:
      - 
          path: /coeusnews/{title}/saveArticle
          
          method: post
          
          cors: true
              
Display the version number of serverless framework:

- serverless version

Deploy from the current folder:

- serverless deploy 

check log file for a function (e.g. function name getlocation-coeusnews-api):

- serverless logs -f getlocation-coeusnews-api

Remove everything:

- serverless remove
