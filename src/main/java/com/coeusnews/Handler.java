package com.coeusnews;

import java.util.Collections;
import java.util.*;
import java.util.Map;
import java.sql.*;

import com.coeusnews.model.Article;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class Handler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

	private static final Logger LOG = LogManager.getLogger(Handler.class);

	@Override
	public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
		LOG.info("received: {}", input);

		List<Article> articles = new ArrayList<>();

//		try{
//			Class.forName("com.mysql.jdbc.Driver");
//			Connection con=DriverManager.getConnection(
//					"jdbc:mysql://coeusnews-db.cetrdyssmcsb.eu-west-2.rds.amazonaws.com/coeusnews","root","root");
////here sonoo is database name, root is username and password
//			Statement stmt=con.createStatement();
//			ResultSet rs=stmt.executeQuery("select id, author_name, title, category, description, article_text, article_image_URL, article_URL, published_at from coeusnews.article_post");
//			while(rs.next())
//				//System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
//			//employee.setId(result.getInt(COLUMN_EMPLOYEES_ID));
//			{
////				Article(rs.getInt("id"),
////						rs.getString("author_name"),
////						rs.getString("title"),
////						rs.getString("category"),
////						rs.getString("description"),
////						rs.getString("article_text"),
////						rs.getString("article_image_URL"),
////						rs.getString("article_URL"),
////						rs.getString("published_at"));
//
//			}
//
//			con.close();
//		}catch(Exception e){ System.out.println(" ");}
//

	Article a1 = new Article(1163, 0, "ITV Wales presenter's 'bully' ex-husband jailed for three years - BBC News", "entertainment", "Jonathan Wignall harassed and stalked Ruth Dodsworth over nine years, a court has heard.", " ", "https://ichef.bbci.co.uk/news/1024/branded_news/119D2/production/_118064127_de27-10.jpg", "https://www.bbc.co.uk/news/uk-wales-56753460","2021-04-15T07:49:15Z", "2021-04-15T07:49:15Z", "published", " ", " " );
		Article a2 = new Article(1164, 0, "ITV Wales presenter's 'bully' ex-husband jailed for three years - BBC News", "entertainment", "Jonathan Wignall harassed and stalked Ruth Dodsworth over nine years, a court has heard.", " ","https://ichef.bbci.co.uk/news/1024/branded_news/119D2/production/_118064127_de27-10.jpg", "https://www.bbc.co.uk/news/uk-wales-56753460","2021-04-15T07:49:15Z", "2021-04-15T07:49:15Z", "published", " ", " " );
		Article a3 = new Article(1165, 0, "ITV Wales presenter's 'bully' ex-husband jailed for three years - BBC News", "entertainment", "Jonathan Wignall harassed and stalked Ruth Dodsworth over nine years, a court has heard.", " ","https://ichef.bbci.co.uk/news/1024/branded_news/119D2/production/_118064127_de27-10.jpg", "https://www.bbc.co.uk/news/uk-wales-56753460","2021-04-15T07:49:15Z", "2021-04-15T07:49:15Z", "published", " ", " " );
		Article a4 = new Article(1166, 0, "ITV Wales presenter's 'bully' ex-husband jailed for three years - BBC News", "entertainment", "Jonathan Wignall harassed and stalked Ruth Dodsworth over nine years, a court has heard.", " ","https://ichef.bbci.co.uk/news/1024/branded_news/119D2/production/_118064127_de27-10.jpg", "https://www.bbc.co.uk/news/uk-wales-56753460","2021-04-15T07:49:15Z", "2021-04-15T07:49:15Z", "published", " ", " " );
		Article a5 = new Article(1167, 0, "ITV Wales presenter's 'bully' ex-husband jailed for three years - BBC News", "entertainment", "Jonathan Wignall harassed and stalked Ruth Dodsworth over nine years, a court has heard.", " ","https://ichef.bbci.co.uk/news/1024/branded_news/119D2/production/_118064127_de27-10.jpg", "https://www.bbc.co.uk/news/uk-wales-56753460","2021-04-15T07:49:15Z", "2021-04-15T07:49:15Z", "published", " ", " " );


		articles.add(a1);
		articles.add(a2);
		articles.add(a3);
		articles.add(a4);
		articles.add(a5);

		return ApiGatewayResponse.builder()
				.setStatusCode(200)
				.setObjectBody(articles)
				.setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & serverless"))
				.build();
	}
}
