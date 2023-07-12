package com.SpringBoot.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBoot.Model.ResponseStructure;
import com.SpringBoot.Model.Tweet;
import com.SpringBoot.Model.User;
import com.SpringBoot.Service.TweetService;
import com.SpringBoot.Service.UserService;

@RestController
public class UserController {
	
	@Autowired
	TweetService tweetService;
	
	@Autowired 
	UserService userService;
	
	@GetMapping("/")
	public String msg() {
		return "Hello World";
	}
	
	@PostMapping("/createUser")
	public ResponseStructure<User> createUser(@RequestBody User userRequest) {
		return userService.saveUser(userRequest);
    } 
	
	@PostMapping("/createTweet")
    public ResponseStructure<Tweet> createTweet(@RequestBody Tweet tweetRequest) {
		return tweetService.saveTweet(tweetRequest);
        
    }
	
	@GetMapping("/getTweetsByUser/{id}")
	public ResponseStructure<List<Tweet>> getTweets(@PathVariable Long id) {
		return tweetService.getAllTweetsByUserId(id);
    }
	
	@GetMapping("/getTweets/{id}")
	public ResponseStructure<Tweet> getTweetById(@PathVariable Long id) {
		return tweetService.getTweetsById(id);
    }
	
	@PutMapping("/updateTweet/{id}")
	public ResponseStructure<Tweet> updateTweet(@PathVariable Long id, @RequestBody Tweet tweetRequest){
		return tweetService.tweetUpdate(id, tweetRequest);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseStructure<Tweet> delete(@PathVariable Long id){
		return tweetService.deleteTweet(id);
	}
}