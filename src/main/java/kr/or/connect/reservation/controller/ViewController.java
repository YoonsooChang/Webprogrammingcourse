package kr.or.connect.reservation.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ViewController {
	@GetMapping("/main")
	public String setViewMainPage() {
		return "mainpage";
	}

	@GetMapping("/detail/{id}")
	public String setViewDetailPage(@PathVariable("id")
	int displayInfoId, HttpServletRequest request) {
		request.setAttribute("id", displayInfoId);
		return "detail";
	}

	@GetMapping("/review/{id}")
	public String setViewReviewPage(@PathVariable("id")
	int displayInfoId, HttpServletRequest request) {
		request.setAttribute("id", displayInfoId);
		return "review";
	}

}
