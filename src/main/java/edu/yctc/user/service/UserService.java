package edu.yctc.user.service;

import java.util.List;

import edu.yctc.user.entity.UserDO;
import edu.yctc.user.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author luna@win10
 * @date 2020/3/24 16:35
 */
@Service("thymeleaf_dict")
public class UserService {

	@Autowired
	private UserDAO userDAO;

	public List<UserDO> getType() {
		return userDAO.getAll();
	}

	public String getMessage() {
		return "html方式调用后台";
	}

	public String getMessageByParam(String msg) {
		return "Service=>" + msg;
	}

}
