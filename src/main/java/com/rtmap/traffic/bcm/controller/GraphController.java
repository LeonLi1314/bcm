package com.rtmap.traffic.bcm.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rtmap.traffic.bcm.domain.DimensionAnalyzeDto;
import com.rtmap.traffic.bcm.domain.Location;
import com.rtmap.traffic.bcm.domain.LocationCond;
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
	public String totalDriverWork(Model model) {
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
	public String passFlowVolume(Model model) {
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
	public String passSubtotal(Model model) {
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

	@RequestMapping("pathAnalysis")
	public String pathAnalysis(Model model) {
		String beginTime = "2015-11-26 06:00:00";
		String endTime = "2015-11-26 23:00:00";
		model.addAttribute("beginTime", beginTime);
		model.addAttribute("endTime", endTime);
		return "/graph/pathAnalysis";
	}

	@ResponseBody
	@RequestMapping("/getCoordinates.do")
	public List<Location> getCoordinates(HttpServletRequest request) {
		LocationCond cond = paramUtils.convertLocationCond(request);
		List<Location> list = graphService.getEffectCoordinatesByCond(cond);
		return list;
	}

	@ResponseBody
	@RequestMapping("/getCoordinateArray.do")
	public int[][] getCoordinateArray(HttpServletRequest request) {
		LocationCond cond = paramUtils.convertLocationCond(request);
		return graphService.getEffectCoordinateArrayByCond(cond);
	}
}
