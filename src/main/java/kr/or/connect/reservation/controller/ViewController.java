package kr.or.connect.reservation.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
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
		int displayInfoId,
		RedirectAttributes redirectAttr) {
		if (user == null) {
			redirectAttr.addFlashAttribute("id", displayInfoId);
			return "redirect:/bookinglogin";
		}
		return "reserve";
	}

	@GetMapping("/bookinglogin")
	public String setViewLoginPage(HttpServletRequest request) {
		Map<String, ?> redirectMap = RequestContextUtils.getInputFlashMap(request);
		if (redirectMap != null) {
			request.setAttribute("id", (Integer)redirectMap.get("id"));
		}
		return "bookinglogin";
	}

	@GetMapping("/myreservation")
	public String setViewMyReservationPage() {
		return "myreservation";
	}

	@GetMapping("/reviewWrite")
	public String setViewReviewWritePage(@RequestParam("user")
	String user,
		@RequestParam("reservation")
		Integer reservationId,
		@RequestParam("product")
		Integer productId,
		@SessionAttribute(name = "user", required = false)
		String sessionUser,
		HttpServletRequest request,
		HttpServletResponse response) throws IOException {
		if (user == null || !user.equals(sessionUser)) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return null;
		}
		request.setAttribute("reservation", reservationId);
		request.setAttribute("product", productId);
		return "reviewWrite";
	}
}
