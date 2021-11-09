package cn.shinema.app.port.adapter.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.shinema.core.restful.Response;

@RestController
public class indexAgent {
	private static final Logger LOGGER = LoggerFactory.getLogger(indexAgent.class);

	@RequestMapping(value = "/index", method = { RequestMethod.GET }, produces = "application/json;charset=utf-8")
	public ResponseEntity<Response<String>> index() {
		LOGGER.info("index");
		Response<String> result = Response.success("index");
		return new ResponseEntity<Response<String>>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/home", method = { RequestMethod.GET }, produces = "application/json;charset=utf-8")
	public ResponseEntity<Response<String>> home() {
		LOGGER.info("home");
		Response<String> result = Response.success("home");
		return new ResponseEntity<Response<String>>(result, HttpStatus.OK);
	}

}
