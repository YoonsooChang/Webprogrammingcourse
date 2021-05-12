package kr.or.connect.reservation.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginApiController {
	static final int VALID_TIME = 180;
	static final int NO_BOOKING = 0;

	@PostMapping(path = "api/login")
	public String login(@RequestParam(name = "email", required = true)
	String email, @RequestParam(name = "displayInfoId", required = false)
	int bookingDisplay,
		HttpSession session, HttpServletRequest request) {
		session.setAttribute("registered", "true");
		session.setAttribute("user", email);
		session.setMaxInactiveInterval(VALID_TIME);
		if (bookingDisplay == NO_BOOKING) {
			return "redirect:/myreservation";
		}

		request.setAttribute("id", bookingDisplay);
		return "reserve";
	}
}
