package com.neu.book;

import java.net.UnknownHostException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mongodb.MongoException;
import com.neu.DAO.recomDAO;
import com.neu.DAO.usersDAO;
import com.neu.pojo.RecomPOJO;
import com.neu.pojo.UsersPOJO;


@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	public usersDAO daoObj;
	
	@Autowired
	public recomDAO recObj;
	
	@RequestMapping(value = "/displayUsers.htm", method = RequestMethod.POST)
	public String updateCustomer(Model model,@RequestParam("title")String searchCrit) throws UnknownHostException, MongoException {
		System.out.println("I am inside function" +searchCrit);
		ArrayList<UsersPOJO> userPOJO = null; 
		userPOJO = daoObj.searchByKeyword(searchCrit);
		model.addAttribute("UsersPOJO", userPOJO);
        return "home";
    }	
	@RequestMapping(value = "/viewRecommendations.htm", method = RequestMethod.POST)
	public String viewRecommendation(Model model,@RequestParam("userID") String userID) throws UnknownHostException, MongoException{
		System.out.println("I am inside recommendation:" +userID);
		ArrayList<RecomPOJO> recomPOJO = null;
		recomPOJO = recObj.getRecommendation(userID);
		model.addAttribute("RecomPOJO",recomPOJO);
		return "insert";
	}
}
