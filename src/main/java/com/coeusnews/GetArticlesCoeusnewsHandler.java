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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetArticlesCoeusnewsHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

	private static final Logger LOG = LogManager.getLogger(GetArticlesCoeusnewsHandler.class);

	private Connection conn = null;
	private PreparedStatement prepStatement= null;
	private ResultSet rs = null;

	@Override
	public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {
		LOG.info("GetArticlesCoeusnewsHandler - Started");

		//String category = request.getPathParameters().get("category");
			List<Article> articles = new ArrayList<>();

		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					String.format("jdbc:mysql://%s/%s?user=%s&password=%s",
							System.getenv("DB_HOST"),
							System.getenv("DB_NAME"),
							System.getenv("DB_USER"),
							System.getenv("DB_PASSWORD")));
			prepStatement = conn.prepareStatement("SELECT * FROM article_location WHERE trim(article_location) IS NOT NULL ORDER BY counter DESC");
			//prepStatement.setString(1, category);
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
													rs.getString("article_approved_by"),
													rs.getInt("counter"));

					articles.add(article);
			}
		}
		catch(Exception e){
			LOG.error(("Unable to query db for articles"), e);
		}
		finally {
			closeConnection();
		}
		
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
