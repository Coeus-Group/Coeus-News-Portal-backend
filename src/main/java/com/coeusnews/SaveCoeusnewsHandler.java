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
import java.util.ArrayList;
import java.util.List;

public class SaveCoeusnewsHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

	private static final Logger LOG = LogManager.getLogger(SaveCoeusnewsHandler.class);

	@Override
	public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {
		LOG.info("received the request");

		String category = request.getPathParameters().get("category");
		String requestBody = request.getBody();

		ObjectMapper objectMapper = new ObjectMapper();


		APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
		response.setStatusCode(200);

		ObjectMapper objMapper = new ObjectMapper();
		try{
			Article A = objMapper.readValue(requestBody, Article.class);
			LOG.debug("Saved Task"+A.getTitle());
			response.setBody("Article Saved!");
		}
		catch (IOException e) {
			LOG.error("Unable to marshall JSON for adding a task!");
		}
		{

		}

		return response;
	}
}
