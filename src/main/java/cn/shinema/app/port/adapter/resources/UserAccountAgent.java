package cn.shinema.app.port.adapter.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.shinema.app.application.UserAccountApplication;
import cn.shinema.app.application.command.LoginCommand;
import cn.shinema.app.application.command.ModifyCommand;
import cn.shinema.app.application.command.RegisterCommand;
import cn.shinema.app.application.representation.UserInfoRepresentation;
import cn.shinema.app.domain.UserAccount;
import cn.shinema.app.exception.NegativeException;
import cn.shinema.core.restful.Response;

@RestController
@RequestMapping(value = "/user")
public class UserAccountAgent {
	private static final Logger logger = LoggerFactory.getLogger(UserAccountAgent.class);

	@Autowired
	private UserAccountApplication userApplicatoin;

	@RequestMapping(value = "/index", method = { RequestMethod.GET })
	public ResponseEntity<Response<String>> index() {
		Response<String> result = Response.success("user index");

		return new ResponseEntity<Response<String>>(result, HttpStatus.OK);
	}

	@ResponseBody
	@RequestMapping(value = "/regist", method = { RequestMethod.POST }, produces = "application/json;charset=utf-8")
	public ResponseEntity<Response<UserInfoRepresentation>> register(
			@RequestParam(value = "cellphone") String cellphone, @RequestParam(value = "password") String password,
			@RequestParam(value = "nickname") String nickname, @RequestParam(value = "age") Integer age,
			@RequestParam(value = "province") String province, @RequestParam(value = "city") String city,
			@RequestParam(value = "area") String area) {
		Response<UserInfoRepresentation> result = null;
		ResponseEntity<Response<UserInfoRepresentation>> respose = null;

		try {
			RegisterCommand command = new RegisterCommand(cellphone, password, nickname, age, province, city, area);
			UserAccount userAccount = userApplicatoin.register(command);
			UserInfoRepresentation activityListRepresentation = new UserInfoRepresentation(userAccount);
			result = Response.success(activityListRepresentation);
			respose = new ResponseEntity<Response<UserInfoRepresentation>>(result, HttpStatus.OK);
		} catch (NegativeException e) {
			logger.error("user login Exception : {}", e.getMessage());
			result = Response.error(e.getStatus(), e.getMessage());
			respose = new ResponseEntity<Response<UserInfoRepresentation>>(result, HttpStatus.SERVICE_UNAVAILABLE);
		}

		return respose;
	}

	@ResponseBody
	@RequestMapping(value = "/login", method = { RequestMethod.POST,
			RequestMethod.GET }, produces = "application/json;charset=utf-8")
	public ResponseEntity<Response<UserInfoRepresentation>> login(@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password) {
		Response<UserInfoRepresentation> result = null;
		ResponseEntity<Response<UserInfoRepresentation>> respose = null;

		try {
			LoginCommand command = new LoginCommand(username, password);
			UserAccount userAccount = userApplicatoin.login(command);
			UserInfoRepresentation activityListRepresentation = new UserInfoRepresentation(userAccount);
			result = Response.success(activityListRepresentation);
			respose = new ResponseEntity<Response<UserInfoRepresentation>>(result, HttpStatus.OK);
		} catch (NegativeException e) {
			logger.error("user login Exception : {}", e.getMessage());
			result = Response.error(e.getStatus(), e.getMessage());
			respose = new ResponseEntity<Response<UserInfoRepresentation>>(result, HttpStatus.SERVICE_UNAVAILABLE);
		}

		return respose;
	}

	@ResponseBody
	@RequestMapping(value = "/modify", method = { RequestMethod.POST }, produces = "application/json;charset=utf-8")
	public ResponseEntity<Response<UserInfoRepresentation>> modify(
			@RequestParam(value = "cellphone", required = true) String cellphone,
			@RequestParam(value = "nickname") String nickname, @RequestParam(value = "age") Integer age) {
		Response<UserInfoRepresentation> result = null;
		ResponseEntity<Response<UserInfoRepresentation>> respose = null;

		try {
			ModifyCommand command = new ModifyCommand(cellphone, nickname, age);
			UserAccount userAccount = userApplicatoin.modify(command);
			UserInfoRepresentation activityListRepresentation = new UserInfoRepresentation(userAccount);
			result = Response.success(activityListRepresentation);
			respose = new ResponseEntity<Response<UserInfoRepresentation>>(result, HttpStatus.OK);
		} catch (NegativeException e) {
			logger.error("user login Exception : {}", e.getMessage());
			result = Response.error(e.getStatus(), e.getMessage());
			respose = new ResponseEntity<Response<UserInfoRepresentation>>(result, HttpStatus.SERVICE_UNAVAILABLE);
		}

		return respose;
	}

}
