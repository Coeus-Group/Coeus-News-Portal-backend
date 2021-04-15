package com.coeusnews;

import java.util.*;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.coeusnews.model.Article;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class GetCoeusnewsHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

	private static final Logger LOG = LogManager.getLogger(GetCoeusnewsHandler.class);

	@Override
	public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {
		LOG.info("received: {}");

		String category = request.getPathParameters().get("category");

		String requestMethod = request.getHttpMethod();



		List<Article> articles = new ArrayList<>();

		if(category.equals("entertainment")){
			Article a1 = new Article(1163, 0, "ITV Wales presenter's 'bully' ex-husband jailed for three years - BBC News", "entertainment", "Jonathan Wignall harassed and stalked Ruth Dodsworth over nine years, a court has heard.", " ", "https://ichef.bbci.co.uk/news/1024/branded_news/119D2/production/_118064127_de27-10.jpg", "https://www.bbc.co.uk/news/uk-wales-56753460","2021-04-15T07:49:15Z", "2021-04-15T07:49:15Z", "published", " ", " " );
			Article a2 = new Article(1164, 0, "ITV Wales presenter's 'bully' ex-husband jailed for three years - BBC News", "entertainment", "Jonathan Wignall harassed and stalked Ruth Dodsworth over nine years, a court has heard.", " ","https://ichef.bbci.co.uk/news/1024/branded_news/119D2/production/_118064127_de27-10.jpg", "https://www.bbc.co.uk/news/uk-wales-56753460","2021-04-15T07:49:15Z", "2021-04-15T07:49:15Z", "published", " ", " " );
			Article a3 = new Article(1165, 0, "ITV Wales presenter's 'bully' ex-husband jailed for three years - BBC News", "entertainment", "Jonathan Wignall harassed and stalked Ruth Dodsworth over nine years, a court has heard.", " ","https://ichef.bbci.co.uk/news/1024/branded_news/119D2/production/_118064127_de27-10.jpg", "https://www.bbc.co.uk/news/uk-wales-56753460","2021-04-15T07:49:15Z", "2021-04-15T07:49:15Z", "published", " ", " " );
			Article a4 = new Article(1166, 0, "ITV Wales presenter's 'bully' ex-husband jailed for three years - BBC News", "entertainment", "Jonathan Wignall harassed and stalked Ruth Dodsworth over nine years, a court has heard.", " ","https://ichef.bbci.co.uk/news/1024/branded_news/119D2/production/_118064127_de27-10.jpg", "https://www.bbc.co.uk/news/uk-wales-56753460","2021-04-15T07:49:15Z", "2021-04-15T07:49:15Z", "published", " ", " " );
			articles.add(a1);
			articles.add(a2);
			articles.add(a3);
			articles.add(a4);
		}
		else{
			Article a5 = new Article(1167, 0, "ITV Wales presenter's 'bully' ex-husband jailed for three years - BBC News", "science", "Jonathan Wignall harassed and stalked Ruth Dodsworth over nine years, a court has heard.", " ","https://ichef.bbci.co.uk/news/1024/branded_news/119D2/production/_118064127_de27-10.jpg", "https://www.bbc.co.uk/news/uk-wales-56753460","2021-04-15T07:49:15Z", "2021-04-15T07:49:15Z", "published", " ", " " );
			Article a6 = new Article(1167, 0, "ITV Wales presenter's 'bully' ex-husband jailed for three years - BBC News", "sports", "Jonathan Wignall harassed and stalked Ruth Dodsworth over nine years, a court has heard.", " ","https://ichef.bbci.co.uk/news/1024/branded_news/119D2/production/_118064127_de27-10.jpg", "https://www.bbc.co.uk/news/uk-wales-56753460","2021-04-15T07:49:15Z", "2021-04-15T07:49:15Z", "published", " ", " " );
			articles.add(a5);
			articles.add(a6);
		}

		APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
		response.setStatusCode(200);

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
}
