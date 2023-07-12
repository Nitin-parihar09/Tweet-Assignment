package com.SpringBoot.Service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.SpringBoot.DbUtils.DBUtils;
import com.SpringBoot.Model.ResponseStructure;
import com.SpringBoot.Model.Tweet;

@Service
public class TweetService {
	
	@Autowired
	private DBUtils dbUtils;

	public ResponseStructure<Tweet> saveTweet(Tweet tweet) {
		ResponseStructure<Tweet> responseStructure = new ResponseStructure<Tweet>();
		String sql = "INSERT INTO Tweet (content, timestamp, user_id) VALUES (?, NOW(), ?);";
		int executeQuery = 0;
		try {
			executeQuery = dbUtils.executeUpdate(sql, tweet.getContent(), tweet.getUser_id());
			if (executeQuery > 0) {
				responseStructure.setMessage("Tweet saved successfully");
				responseStructure.setStatusCode(HttpStatus.CREATED.value());
			} else {
				responseStructure.setStatusCode(HttpStatus.EXPECTATION_FAILED.value());
				responseStructure.setMessage("Failed TO Save Tweet");
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		
		return responseStructure;
	}

	public ResponseStructure<List<Tweet>> getAllTweetsByUserId(Long userId) {
		ResponseStructure<List<Tweet>> responseStructure = new ResponseStructure<List<Tweet>>();
		String sql = "SELECT * FROM Tweet where user_id = ?";
		try {
			List<Object> executeQuery = dbUtils.executeQuery(sql, new BeanListHandler<>(Tweet.class), userId);
			if(executeQuery.size() > 0) {
				responseStructure.setData(executeQuery);
				responseStructure.setStatusCode(HttpStatus.FOUND.value());
				responseStructure.setMessage("Tweet fetched successfully");
			} else {
				responseStructure.setData(null);
				responseStructure.setStatusCode(HttpStatus.EXPECTATION_FAILED.value());
				responseStructure.setMessage("failed to fetch Tweet");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return responseStructure;
    }
	
	public ResponseStructure<Tweet> getTweetsById(Long Id){
		ResponseStructure<Tweet> response = new ResponseStructure<Tweet>();
		String sql = "SELECT * FROM Tweet WHERE id = ?;";
		try {
			List<Object> executeQuery = dbUtils.executeQuery(sql, new BeanListHandler<>(Tweet.class), Id);
			if (executeQuery.size() > 0 ) {
				response.setMessage("Tweet retrived successfully");
				response.setData(executeQuery);
				response.setStatusCode(HttpStatus.FOUND.value());
			} else {
				response.setData(null);
				response.setStatusCode(HttpStatus.EXPECTATION_FAILED.value());
				response.setMessage("Failed to retrive tweet");
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return response;
	}
	
	public ResponseStructure<Tweet> tweetUpdate(Long Id, Tweet tweet){
		ResponseStructure<Tweet> response = new ResponseStructure<Tweet>();
		String sql = "update Tweet set content = ?, timestamp = now() where id =?;";
		int executeQuery = 0;
		try {
			executeQuery = dbUtils.executeUpdate(sql, tweet.getContent(), tweet.getId());
			if (executeQuery > 0) {
				response.setMessage("Tweet Updated successfully");
				response.setStatusCode(HttpStatus.ACCEPTED.value());
			} else {
				response.setStatusCode(HttpStatus.EXPECTATION_FAILED.value());
				response.setMessage("Tweet has failed to Update");
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return response;
	}
	
	public ResponseStructure<Tweet> deleteTweet(Long id){
		ResponseStructure<Tweet> response = new ResponseStructure<Tweet>();
		String sql = "DELETE FROM Tweet WHERE id = ?";
		int executeQuery = 0;
		try {
			executeQuery = dbUtils.executeUpdate(sql, id);
			if (executeQuery > 0) {
				response.setMessage("Tweet Deleted Successfully");
				response.setStatusCode(HttpStatus.OK.value());
			} else {
				response.setStatusCode(HttpStatus.EXPECTATION_FAILED.value());
				response.setMessage("Failed To Delete Tweet");
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return response;
	}
}
