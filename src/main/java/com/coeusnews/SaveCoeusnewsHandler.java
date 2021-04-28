package com.coeusnews;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.coeusnews.model.Article;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.*;
import java.util.*;

public class SaveCoeusnewsHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

	private static final Logger LOG = LogManager.getLogger(SaveCoeusnewsHandler.class);
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	@Override
	public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {
		LOG.info("received the request");

		String title = request.getPathParameters().get("title");
		String requestBody = request.getBody();

		ObjectMapper objMapper = new ObjectMapper();
		APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
		response.setStatusCode(200);
		Map<String, String> headers = new HashMap<>();
		headers.put("Access-Control-Allow-Origin", "*");
		response.setHeaders(headers);


		try{
			Article article = objMapper.readValue(requestBody, Article.class);
			Class.forName("com.mysql.jdbc.Driver");

			connection = DriverManager.getConnection(String.format("jdbc:mysql://%s/%s?user=%s&password=%s",
					System.getenv("DB_HOST"),
					System.getenv("DB_NAME"),
					System.getenv("DB_USER"),
					System.getenv("DB_PASSWORD")));

			preparedStatement = connection.prepareStatement("INSERT INTO coeusnews.article_post(id, title, description, article_text, location, category, article_image_URL, article_URL, author_name,published_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,)");
			preparedStatement.setString(1, UUID.randomUUID().toString());
			preparedStatement.setString(2, title);
			preparedStatement.setString(3, article.getDescription());
			preparedStatement.setString(4, article.getArticle_text());
			preparedStatement.setString(5, article.getArticle_location());
			preparedStatement.setString(6, article.getCategory());
			preparedStatement.setString(7, article.getArticle_image_URL());
			preparedStatement.setString(8, article.getArticle_URL());
			preparedStatement.setString(9, article.getAuthor_name());
			preparedStatement.setString(10, article.getPublished_at());

			preparedStatement.execute();

			connection.close();
		} catch (IOException e) {
			LOG.error("Unable to unmarshal JSON for adding a task", e);
		} catch (ClassNotFoundException e) {
			LOG.error("ClassNotFoundException", e);
		} catch (SQLException throwables) {
			LOG.error("SQL Exception", throwables);
		}
		finally {
			closeConnection();
		}
		return response;
	}
	private void closeConnection() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (connection != null) {
				connection.close();
			}
		}
		catch (Exception e) {
			LOG.error("Unable to close connections to MySQL - {}", e.getMessage());
		}
	}
}
