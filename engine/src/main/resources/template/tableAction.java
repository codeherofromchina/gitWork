package $!{projectPackage}.action;

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
import $!{projectPackage}.domain.$!{beanName};
import $!{projectPackage}.service.$!{beanName}Service;
import $!{projectPackage}.utils.PageBean;

@Controller
@RequestMapping("/_$!{tableName}")
public class $!{beanName}Action {
	private static final Log LOGGER = LogFactory.getLog($!{beanName}Action.class);
	private static final int DEFAULT_PAGESIZE = 10 ;
	@Autowired
	private $!{beanName}Service $!{beanName}Service;
	
	@RequestMapping("/list")
	public ModelAndView list(Integer page) {
		int currentPage = page == null ? 1 : page.intValue();
		PageBean<$!{beanName}> pageBean = $!{beanName}Service.queryForPage(DEFAULT_PAGESIZE, currentPage);
		
		ModelAndView mv = new ModelAndView("$!{tableName}");
		mv.addObject("pageBean", pageBean);
		return mv;
	}
	
	@RequestMapping("/addSubmit")
	public ModelAndView addSubmit(#foreach ($column in $tableMetaData.columnMetaDatas) #if ($column.fieldName != 'id') String $!{column.fieldName}, #end #end HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("$!{tableName}_edit");
		mv.addObject("doFlag", "add");
		Map<String, Object> paramMap = WebUtils.getParametersStartingWith(request, null);
		mv.addObject("bean", paramMap);
		
		String errMsg = null;
		#foreach ($column in $tableMetaData.columnMetaDatas)
			#if ($column.fieldName != 'id')
				if (StringUtils.isEmpty($!{column.fieldName})) {
					errMsg = "$!{column.fieldName}字段不能为空";
				}
			#end
		#end
		
		if (errMsg == null) {
			$!{beanName} $!{tableName} = new $!{beanName}();
			#foreach ($column in $tableMetaData.columnMetaDatas)
				#if ($column.fieldName != 'id')
					$!{tableName}.set$!{column.fieldName}($!{column.fieldName});
				#end
			#end
			try {
				$!{beanName}Service.save($!{tableName});
				mv.addObject("success", true);
			} catch(Exception e) {
				LOGGER.error("保存数据错误。[$!{tableName}:"+$!{tableName}+"]" , e);
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
		ModelAndView mv = new ModelAndView("$!{tableName}_edit");
		mv.addObject("doFlag", "add"); // 因为增加和修改使用同一个页面，这里标志位控制页面各个元素
		return mv;
	}
	
	@RequestMapping("/editSubmit")
	public ModelAndView editSubmit(HttpServletRequest request,#foreach ($column in $tableMetaData.columnMetaDatas) #if ($column.fieldName != 'id') String $!{column.fieldName}, #end #end Long id) {
		ModelAndView mv = new ModelAndView("$!{tableName}_edit");
		mv.addObject("doFlag", "edit");
		Map<String, Object> paramMap = WebUtils.getParametersStartingWith(request, null);
		mv.addObject("bean", paramMap);
		
		String errMsg = null;
		if (id == null) { // 主键判断
			errMsg = "系统标识丢失，更新失败";
		} 
		#foreach ($column in $tableMetaData.columnMetaDatas) 
			#if ($column.fieldName != 'id')
				else if (StringUtils.isBlank($!{column.fieldName})) { // 不可空字段判断
					errMsg = "$!{column.fieldName}字段不可为空";
				}
			#end 
		#end
		
		
		if (errMsg == null) {
			$!{beanName} $!{tableName} = new $!{beanName}();
			#foreach ($column in $tableMetaData.columnMetaDatas)
				$!{tableName}.set$!{column.fieldName}($!{column.fieldName});
			#end
			boolean success = $!{beanName}Service.updateByPK($!{tableName});
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
		ModelAndView mv = new ModelAndView("$!{tableName}_edit");
		mv.addObject("doFlag", "edit"); // 因为增加和修改使用同一个页面，这里标志位控制页面各个元素
		$!{beanName} $!{tableName} = $!{beanName}Service.findByPK(id);
		mv.addObject("bean", $!{tableName});
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
				$!{beanName}Service.delete(id);
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
