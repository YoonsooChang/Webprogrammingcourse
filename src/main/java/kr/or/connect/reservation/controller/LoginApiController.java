package kr.or.connect.reservation.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginApiController {
	static final int VALID_TIME = 180;

	@PostMapping(path = "/login")
	public String login(@RequestParam(name = "email", required = true)
	String email,
		HttpSession session) {
		session.setAttribute("registered", "true");
		session.setAttribute("user", email);
		session.setMaxInactiveInterval(VALID_TIME);

		return "redirect:/myreservation";
	}
}
