package cn.shinema.app.port.adapter.resources;

import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.shinema.app.service.GreetingService;
import cn.shinema.core.restful.Response;

@RestController
public class DubboAgent {
	private static final Logger logger = LoggerFactory.getLogger(DubboAgent.class);

	@DubboReference(version = "0.0.1")
	private GreetingService greetingService;

	@RequestMapping(value = "/greeting", method = { RequestMethod.POST, RequestMethod.GET })
	public ResponseEntity<Response<String>> greeting(@RequestParam(value = "name") String name) {
		logger.info("name => {}", name);

		Response<String> result = Response.success("", greetingService.greeting(name));

		return new ResponseEntity<Response<String>>(result, HttpStatus.OK);
	}

}
