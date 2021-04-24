package com.coeusnews;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.coeusnews.model.Article;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class GetLocationCoeusnewsHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

	private static final Logger LOG = LogManager.getLogger(GetLocationCoeusnewsHandler.class);

	private Connection conn = null;
	private PreparedStatement prepStatement= null;
	private ResultSet rs = null;

	@Override
	public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {
		LOG.info("GetLocationCoeusnewsHandler - Started");

		String location = request.getPathParameters().get("location");
			List<Article> articles = new ArrayList<>();

		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					String.format("jdbc:mysql://%s/%s?user=%s&password=%s", "coeusnews-db.cetrdyssmcsb.eu-west-2.rds.amazonaws.com",
					"coeusnews",
					"root",
					"admin123"));
			prepStatement = conn.prepareStatement("SELECT * FROM article_post WHERE lower(article_location) = lower(?)");
			prepStatement.setString(1, location);
			rs = prepStatement.executeQuery();

			while(rs.next())
			{
					Article article = new Article(	rs.getString("id"),
													rs.getString("author_name"),
													rs.getString("title"),
													rs.getString("category"),
													rs.getString("description"),
													rs.getString("article_text"),
													rs.getString("article_image_URL"),
													rs.getString("article_URL"),
													rs.getString("updated_at"),
													rs.getString("published_at"),
													rs.getString("article_status"),
													rs.getString("article_location"),
													rs.getString("article_approved_by"));

					articles.add(article);
			}
		}
		catch(Exception e){
			LOG.error(String.format("Unable to query db for articles of category %s", location), e);
		}
		finally {
			closeConnection();
		}

//		if(category.equals("entertainment")){
//
//			Article a2 = new Article("1164", "0", "ITV Wales presenter's 'bully' ex-husband jailed for three years - BBC News", "entertainment", "Jonathan Wignall harassed and stalked Ruth Dodsworth over nine years, a court has heard.", " ","https://ichef.bbci.co.uk/news/1024/branded_news/119D2/production/_118064127_de27-10.jpg", "https://www.bbc.co.uk/news/uk-wales-56753460","2021-04-15T07:49:15Z", "2021-04-15T07:49:15Z", "published", " ", " " );
//			Article a3 = new Article("1165", "0", "ITV Wales presenter's 'bully' ex-husband jailed for three years - BBC News", "entertainment", "Jonathan Wignall harassed and stalked Ruth Dodsworth over nine years, a court has heard.", " ","https://ichef.bbci.co.uk/news/1024/branded_news/119D2/production/_118064127_de27-10.jpg", "https://www.bbc.co.uk/news/uk-wales-56753460","2021-04-15T07:49:15Z", "2021-04-15T07:49:15Z", "published", " ", " " );
//			Article a4 = new Article("1166", "0", "ITV Wales presenter's 'bully' ex-husband jailed for three years - BBC News", "entertainment", "Jonathan Wignall harassed and stalked Ruth Dodsworth over nine years, a court has heard.", " ","https://ichef.bbci.co.uk/news/1024/branded_news/119D2/production/_118064127_de27-10.jpg", "https://www.bbc.co.uk/news/uk-wales-56753460","2021-04-15T07:49:15Z", "2021-04-15T07:49:15Z", "published", " ", " " );
//			articles.add(a2);
//			articles.add(a3);
//			articles.add(a4);
//		}
//		else{
//			Article a5 = new Article("1167", "0", "ITV Wales presenter's 'bully' ex-husband jailed for three years - BBC News", "science", "Jonathan Wignall harassed and stalked Ruth Dodsworth over nine years, a court has heard.", " ","https://ichef.bbci.co.uk/news/1024/branded_news/119D2/production/_118064127_de27-10.jpg", "https://www.bbc.co.uk/news/uk-wales-56753460","2021-04-15T07:49:15Z", "2021-04-15T07:49:15Z", "published", " ", " " );
//			Article a6 = new Article("1167", "0", "ITV Wales presenter's 'bully' ex-husband jailed for three years - BBC News", "sports", "Jonathan Wignall harassed and stalked Ruth Dodsworth over nine years, a court has heard.", " ","https://ichef.bbci.co.uk/news/1024/branded_news/119D2/production/_118064127_de27-10.jpg", "https://www.bbc.co.uk/news/uk-wales-56753460","2021-04-15T07:49:15Z", "2021-04-15T07:49:15Z", "published", " ", " " );
//			articles.add(a5);
//			articles.add(a6);
//		}

		APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
		response.setStatusCode(200);
		Map<String, String> headers = new HashMap<>();
		headers.put("Access-Control-Allow-Origin", "*");
		response.setHeaders(headers);

		ObjectMapper objectMapper = new ObjectMapper();
		try{
			String responseBody = objectMapper.writeValueAsString(articles);
			response.setBody(responseBody);
		}
		catch (JsonProcessingException e) {
			LOG.error("Unable to marshall articles array!", e);
		} {

		}

		return response;
	}

	private void closeConnection() {
		try {
			if (rs != null){
				rs.close();
			}
			if (prepStatement != null){
				prepStatement.close();
			}
			if (conn != null){
				conn.close();
			}
		} catch (Exception e) {
			LOG.error("Unable to close connections to MySQL", e.getMessage());
		}
	}
}
