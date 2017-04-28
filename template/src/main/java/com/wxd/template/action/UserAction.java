package com.wxd.template.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.google.gson.JsonObject;
import com.wxd.template.domain.User;
import com.wxd.template.service.UserService;
import com.wxd.template.utils.PageBean;

@Controller
@RequestMapping("/_user")
public class UserAction {
	private static final Log LOGGER = LogFactory.getLog(UserAction.class);
	private static final int DEFAULT_PAGESIZE = 10 ;
	@Autowired
	private UserService userService;
	
	@RequestMapping("/list")
	public ModelAndView list(Integer page) {
		int currentPage = page == null ? 1 : page.intValue();
		PageBean<User> pageBean = userService.queryForPage(DEFAULT_PAGESIZE, currentPage);
		
		ModelAndView mv = new ModelAndView("user");
		mv.addObject("pageBean", pageBean);
		return mv;
	}
	
	@RequestMapping("/addSubmit")
	public ModelAndView addSubmit(HttpServletRequest request,String name) {
		ModelAndView mv = new ModelAndView("user_edit");
		mv.addObject("doFlag", "add");
		Map<String, Object> paramMap = WebUtils.getParametersStartingWith(request, null);
		mv.addObject("user", paramMap);
		
		String errMsg = null;
		if (StringUtils.isEmpty(name)) {
			errMsg = "name字段不能为空";
		} 
		
		if (errMsg == null) {
			User user = new User();
			user.setName(name);
			try {
				userService.save(user);
				mv.addObject("success", true);
			} catch(Exception e) {
				LOGGER.error("保存数据错误。[user:"+user+"]" , e);
				mv.addObject("success", false);
				mv.addObject("errMsg", "保存失败，请联系管理员。");
			}
		} else {
			mv.addObject("success", false);
			mv.addObject("errMsg", errMsg);
		}
		
		return mv;
	}
	
	@RequestMapping("/add")
	public ModelAndView add() {
		ModelAndView mv = new ModelAndView("user_edit");
		mv.addObject("doFlag", "add"); // 因为增加和修改使用同一个页面，这里标志位控制页面各个元素
		return mv;
	}
	
	@RequestMapping("/editSubmit")
	public ModelAndView editSubmit(HttpServletRequest request,Long id,String name) {
		ModelAndView mv = new ModelAndView("user_edit");
		mv.addObject("doFlag", "edit");
		Map<String, Object> paramMap = WebUtils.getParametersStartingWith(request, null);
		mv.addObject("user", paramMap);
		
		String errMsg = null;
		if (id == null) { // 主键判断
			errMsg = "系统标识丢失，更新失败";
		} else if (StringUtils.isBlank(name)) { // 不可空字段判断
			errMsg = "name字段不可为空";
		}
		
		if (errMsg == null) {
			User user = new User();
			user.setId(id);
			user.setName(name);
			boolean success = userService.updateByPK(user);
			if (success ) {
				mv.addObject("success", true);
			} else {
				mv.addObject("success", false);
				mv.addObject("errMsg", "更新失败，请联系管理员。");
			}
		} else {
			mv.addObject("success", false);
			mv.addObject("errMsg", errMsg);
		}
		return mv;
	}
	
	@RequestMapping("/edit")
	public ModelAndView edit(Long id) {
		ModelAndView mv = new ModelAndView("user_edit");
		mv.addObject("doFlag", "edit"); // 因为增加和修改使用同一个页面，这里标志位控制页面各个元素
		User user = userService.findByPK(id);
		mv.addObject("user", user);
		return mv;
	}
	
	@RequestMapping(path = "/delete",method=RequestMethod.POST)
	@ResponseBody
	public String delete(Long[] id) {
		JsonObject json = new JsonObject();
		if (id == null || id.length == 0) {
			json.addProperty("success", false);
			json.addProperty("msg", "获取删除实体的标识失败");
		} else {
			try {
				userService.delete(id);
				json.addProperty("success", true);
			}catch (Exception e) {
				LOGGER.error("删除数据错误。[Ids:"+StringUtils.join(id,",")+"]" , e);
				json.addProperty("success", false);
				json.addProperty("msg", "删除失败，请联系管理员。");
			}
		}
		
		return json.toString();
	}
}
