package $!{projectPackage}.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexAction {
	
	@RequestMapping("/_index")
	public ModelAndView top(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mv= new ModelAndView("index");
		return mv;
	}

}
