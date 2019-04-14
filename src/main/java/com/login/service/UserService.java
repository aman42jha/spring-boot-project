package com.login.service;

import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoOperations;
import static org.springframework.data.mongodb.core.query.Query.*;
import static org.springframework.data.mongodb.core.query.Criteria.*;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.*;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.login.model.Counter;
import com.login.model.Login;
import com.login.model.QLogin;
import com.login.model.pojo.AcknolegmentPojo;
import com.login.model.pojo.DataPojo;
import com.login.model.pojo.UserBriefDetailsPojo;
import com.login.model.pojo.UsersDataPojo;
import com.login.model.user.GetUsersResponse;
import com.login.model.user.LoginRequest;
import com.login.model.user.RegisterLoginResponse;
import com.login.model.user.RegisterRequest;
import com.login.model.user.VarifyResponse;
import com.login.repository.CounterRepository;
import com.login.repository.UserRepository;
import com.querydsl.core.types.dsl.BooleanExpression;

@Service
public class UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CounterRepository counterRepository;
	@Autowired 
	private MongoOperations mongo;
	
	 public int getNextSequence(String seqName) {
	     Counter counter = this.mongo.findAndModify(query(where("_id").is(seqName)), 
	    		 new Update().inc("seq", 1), 
	    		 options().returnNew(true),
	      Counter.class);
	     if(Objects.isNull(counter)) {
	    	 counter = new Counter();
	    	 counter.setId(seqName);
	    	 counter.setSeq(1);
		     this.counterRepository.save(counter);
	     }
	    return counter.getSeq();
	}

	public RegisterLoginResponse saveUser(RegisterRequest request) {
		if (request == null) {
			return new RegisterLoginResponse(new AcknolegmentPojo("Request is null", false), null);
		}
		RegisterLoginResponse response = null;
		try {
			Login login = new Login(this.getNextSequence("user_seq"), request.getUserName(), request.getPassword(), request.getGender());
			Login save = this.userRepository.save(login);
			UserBriefDetailsPojo userBriefDetailsPojo = new UserBriefDetailsPojo(save.getId(), save.getUserName(),
					save.getGender());
			DataPojo dataPojo = new DataPojo(save.getUserName(), userBriefDetailsPojo);
			response = new RegisterLoginResponse(new AcknolegmentPojo("User register successfully", true), dataPojo);
		} catch (Exception e) {
			response = new RegisterLoginResponse(new AcknolegmentPojo("Server error, unable to register user", false),
					null);
			logger.error("Error occure while register user", e);
		}
		return response;
	}

	public RegisterLoginResponse findUser(LoginRequest request) {
		if (request == null) {
			return new RegisterLoginResponse(new AcknolegmentPojo("Request is null", false), null);
		}
		RegisterLoginResponse response = null;
		try {
			// create query class (QLogin)
			QLogin query = new QLogin("login");
			// using the query class we can create filter
			BooleanExpression filterByUsername = query.userName.eq(request.getUserName());
			BooleanExpression filterByPassword = query.password.eq(request.getPassword());
			Optional<Login> findOne = this.userRepository.findOne(filterByUsername.and(filterByPassword));
			logger.info("userLogin findOne [" + findOne + "]");
			if (findOne.isPresent()) {
				Login login = findOne.get();
				UserBriefDetailsPojo user = new UserBriefDetailsPojo(login.getId(), login.getUserName(),
						login.getGender());
				DataPojo dataPojo = new DataPojo(login.getUserName(), user);
				response = new RegisterLoginResponse(new AcknolegmentPojo("User login successfully", true), dataPojo);
			}else {
				response = new RegisterLoginResponse(new AcknolegmentPojo("Server error, unable to find user", false), null);
			}
		} catch (Exception e) {
			response = new RegisterLoginResponse(new AcknolegmentPojo("Server error, unable to find user", false),
					null);
			logger.error("Error occure while finding user", e);
		}
		return response;
	}

	public GetUsersResponse getUsers(int page, int size) {
		if (page < 0 || size < 0) {
			return new GetUsersResponse(new AcknolegmentPojo("incorrect data", false), null);
		}
		GetUsersResponse response = null;
		try {
			Page<Login> userData = this.userRepository.findAll(PageRequest.of(page, size));
			logger.info("getuser controller userData " + userData);
			UsersDataPojo data = new UsersDataPojo(userData.getNumber(), userData.getSize(), userData.getTotalPages(),
					userData.getTotalElements(), userData.getContent());
			response = new GetUsersResponse(new AcknolegmentPojo("Found Sucessfull", true), data);
		} catch (Exception e) {
			response = new GetUsersResponse(new AcknolegmentPojo("Server error, unable to find user data", false),
					null);
			logger.error("Error occure while finding users details controller ", e);
		}
		return response;
	}

	public VarifyResponse verify(String token) {
		if (Strings.isNullOrEmpty(token)) {
			return new VarifyResponse(new AcknolegmentPojo("Wrong token", false), null);
		}
		VarifyResponse response = null;
		try {
			// create query class (QLogin)
			QLogin query = new QLogin("login");
			// using the query class we can create filter
			BooleanExpression filterByUsername = query.userName.eq(token);
			Optional<Login> findOne = this.userRepository.findOne(filterByUsername);
			if (findOne.isPresent()) {
				Login login = findOne.get();
				UserBriefDetailsPojo userBriefDetailsPojo = new UserBriefDetailsPojo(login.getId(), login.getUserName(),
						login.getGender());
				response = new VarifyResponse(new AcknolegmentPojo("Found user with token", true),
						userBriefDetailsPojo);
			}
		} catch (Exception e) {
			response = new VarifyResponse(new AcknolegmentPojo("Server error, unable to find user", false), null);
			logger.error("Error occure while finding users by token controller ", e);
		}
		return response;
	}
}
