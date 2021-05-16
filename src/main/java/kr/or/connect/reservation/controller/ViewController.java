package kr.or.connect.reservation.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

@Controller
public class ViewController {
	@GetMapping("/mainpage")
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

	@GetMapping("/reserve/{id}")
	public String setViewReservePage(@SessionAttribute(name = "user", required = false)
	String user,
		@PathVariable("id")
		int displayInfoId, RedirectAttributes redirectAttr) {
		if (user == null) {
			redirectAttr.addFlashAttribute("id", displayInfoId);
			return "redirect:/bookinglogin";
		}
		return "reserve";
	}

	@GetMapping("/bookinglogin")
	public String setViewLoginPage(HttpServletRequest request) {
		Map<String, ?> redirectMap = RequestContextUtils.getInputFlashMap(request);
		request.setAttribute("id", (Integer)redirectMap.get("id"));
		return "bookinglogin";
	}

	@GetMapping("/myreservation")
	public String setViewMyReservationPage() {
		return "myreservation";
	}
}
