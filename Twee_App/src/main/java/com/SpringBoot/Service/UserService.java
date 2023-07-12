package com.SpringBoot.Service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.SpringBoot.DbUtils.DBUtils;
import com.SpringBoot.Model.ResponseStructure;
import com.SpringBoot.Model.Tweet;
import com.SpringBoot.Model.User;


@Service
public class UserService {
	
	@Autowired
	private DBUtils dbUtils;
	
	public ResponseStructure<User> saveUser(User user) {
		ResponseStructure<User> responseStructure = new ResponseStructure<User>();
		String sql = "INSERT INTO User (name) VALUES (?);";
		int executeQuery = 0;
		try {
			executeQuery = dbUtils.executeUpdate(sql, user.getName());
			if (executeQuery > 0) {
				responseStructure.setMessage("User saved successfully");
				responseStructure.setStatusCode(HttpStatus.CREATED.value());
			} else {
				responseStructure.setStatusCode(HttpStatus.EXPECTATION_FAILED.value());
				responseStructure.setMessage("User has failed to save");
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		
		return responseStructure;
	}

}
