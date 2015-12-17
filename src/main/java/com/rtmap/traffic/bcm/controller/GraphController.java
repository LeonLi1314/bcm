package com.rtmap.traffic.bcm.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rtmap.traffic.bcm.domain.DimensionAnalyzeDto;
import com.rtmap.traffic.bcm.domain.MultiDimensionAnalyzeDto;
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
	
	@RequestMapping("totalDriverWork")
	public String totalDriverWork(Model model){
		model.addAttribute("preDay", preDateStr);
		return "/graph/totalDriverWork";
	}
	
	@ResponseBody
	@RequestMapping("/getTotalDriverWork.do")
	public List<MultiDimensionAnalyzeDto> getTotalDriverWorkByCond(HttpServletRequest request) {
		PassCond cond = paramUtils.convertRptPassCond(request);
		List<MultiDimensionAnalyzeDto> list = graphService.getTotalDriverWork(cond);
		return list;
	}

	@RequestMapping("passFlowVolume")
	public String passFlowVolume(Model model){
		model.addAttribute("preDay", preDateStr);
		return "/graph/passFlowVolume";
	}
	
	@ResponseBody
	@RequestMapping("/getPassFlowVolume.do")
	public List<DimensionAnalyzeDto> getPassFlowVolume(HttpServletRequest request) {
		PassCond cond = paramUtils.convertRptPassCond(request);
		List<DimensionAnalyzeDto> list = graphService.getPassFlowVolume(cond);
		return list;
	}

	@RequestMapping("passSubtotal")
	public String passSubtotal(Model model){
		model.addAttribute("preDay", preDateStr);
		return "/graph/passSubtotal";
	}
	
	@ResponseBody
	@RequestMapping("/getPassSubtotal.do")
	public List<DimensionAnalyzeDto> getPassSubtotal(HttpServletRequest request) {
		PassCond cond = paramUtils.convertRptPassCond(request);
		List<DimensionAnalyzeDto> list = graphService.getPassSubtotal(cond);
		return list;
	}
}
