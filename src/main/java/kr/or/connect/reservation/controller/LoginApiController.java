package kr.or.connect.reservation.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes("user")
@Controller
public class LoginApiController {
	static final int VALID_TIME = 60;
	static final int NO_BOOKING = 0;

	@PostMapping(path = "api/login")
	public String login(
		Model model,
		@RequestParam(name = "user", required = true)
		String userEmail,
		@RequestParam(name = "displayInfoId", required = false)
		int bookingDisplayInfoId,
		HttpSession session) {
		model.addAttribute("user", userEmail);

		session.setMaxInactiveInterval(VALID_TIME);
		if (bookingDisplayInfoId == NO_BOOKING) {
			return "redirect:/myreservation";
		}

		return "redirect:/reserve/" + bookingDisplayInfoId;
	}
}
