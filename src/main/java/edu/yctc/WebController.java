package edu.yctc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;



/**
 * @author luna@win10
 * @date 2020/3/21 14:15
 */
@Controller
public class WebController {


	@GetMapping("demo")
	public String web(ModelMap map) {

		return "main";
	}

	@GetMapping("system")
	public String signin(ModelMap map) {

		return "sign-in";
	}

	@GetMapping("signup")
	public String signup(ModelMap map) {

		return "sign-up";
	}
}
