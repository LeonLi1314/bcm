package com.rtmap.traffic.bcm.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rtmap.traffic.bcm.domain.DimensionAnalyzeDto;
import com.rtmap.traffic.bcm.domain.PassCond;
import com.rtmap.traffic.bcm.service.IGraphService;

@Controller
@RequestMapping("graph")
public class GraphController {
	@Resource
	private ParamUtils paramUtils;
	@Resource
	private IGraphService graphService;

	// mytest 只有12月1号有数据
	private String preDateStr = "2015-12-01";
	
	@RequestMapping("driverWork")
	public String driverWork(Model model){
		model.addAttribute("preDay", preDateStr);
		return "/graph/driverWork";
	}
	
	@ResponseBody
	@RequestMapping("/getTotalDriverWork.do")
	public List<DimensionAnalyzeDto> getTotalDriverWorkByCond(HttpServletRequest request) {
		PassCond cond = paramUtils.convertRptPassCond(request);
		List<DimensionAnalyzeDto> list = graphService.getTotalDriverWork(cond);
		return list;
	}
}
