package edu.yctc.user.controller;

import java.math.BigDecimal;
import java.util.*;

import edu.yctc.user.entity.UserDO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.util.DateUtils;



/**
 * @author yctc@win10
 * @date 2020/3/21 14:15
 */
@Controller
public class WebController {


	@GetMapping("demo")
	public String web(ModelMap map) {
		List<UserDO> users = new ArrayList<>();
		UserDO user1 = new UserDO("张三", 1);
		UserDO user2 = new UserDO("李四", 2);

		users.add(user1);
		users.add(user2);

		map.put("user1", user1);
		map.put("users", users);

		Map<String, UserDO> userMap = new HashMap<>();
		userMap.put("user1", user1);
		userMap.put("user2", user2);
		map.put("userMap", userMap);

		UserDO[] userArray = new UserDO[]{user1, user2};
		map.put("userArray", userArray);

		//数字对象 放置数组
		Integer[] integers = new Integer[]{200, 211};
		map.put("arr", integers);

		//数字处理 放置list 对象
		List<Integer> integerList = new ArrayList<>();
		integerList.add(500);
		integerList.add(999);
		map.put("list", integerList);

		Set<Integer> set = new HashSet<>();
		set.add(300);
		set.add(9467);
		map.put("set", set);

		BigDecimal value1 = BigDecimal.valueOf(10.992);
		BigDecimal value2 = BigDecimal.valueOf(1299.22);
		//小数数组
		BigDecimal[] bigDecimals = new BigDecimal[]{value1, value2};
		map.put("decimal", bigDecimals);

		//小数List
		List<BigDecimal> bigDecimalList = new ArrayList<>();
		bigDecimalList.add(value1);
		bigDecimalList.add(value2);
		map.put("listDec", bigDecimalList);

		Set<BigDecimal> bigDecimalSet = new HashSet<>();
		bigDecimalSet.add(value1);
		bigDecimalSet.add(value2);
		map.put("setDec", bigDecimalSet);

		return "thymeleaf/demoObject";
	}

	@GetMapping("dealString")
	public String delString(ModelMap map) {
		UserDO userDO = new UserDO("yctc", 1);
		userDO.setBirth(DateUtils.create("1999", "11", "07", "11", "45", "59").getTime());
		map.put("user", userDO);
		return "thymeleaf/dealString";
	}

	@GetMapping("commonSyntax")
	public String commonSyntax(ModelMap map) {
		map.put("value", "yctc");

		List<UserDO> users = new ArrayList<>();
		UserDO user1 = new UserDO("张三", 1);
		UserDO user2 = new UserDO("李四", 2);

		users.add(user1);
		users.add(user2);
		map.put("users", users);
		return "thymeleaf/commonSyntax";
	}

	@GetMapping("fragment")
	public String fragment(ModelMap map) {
		List<UserDO> users = new ArrayList<>();
		UserDO user1 = new UserDO("张三", 1);
		UserDO user2 = new UserDO("李四", 2);

		users.add(user1);
		users.add(user2);
		map.put("users", users);
		return "thymeleaf/fragment";
	}

	@GetMapping("inlining")
	public String inlining(ModelMap map) {
		UserDO user1 = new UserDO("张三", 1);
		map.put("user1", user1);

		List<UserDO> users = new ArrayList<>();
		UserDO user2 = new UserDO("李四", 2);

		users.add(user1);
		users.add(user2);
		map.put("userList", users);

		Map<String, UserDO> userMap = new HashMap<>();
		userMap.put("user1", user1);
		userMap.put("user2", user2);
		map.put("userMap", userMap);

		return "thymeleaf/inlining";
	}

	@GetMapping("management")
	public String management(ModelMap map) {
		return "thymeleaf/management";
	}
}
