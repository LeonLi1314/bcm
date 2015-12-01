package my.web;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class TokenManager {
	private static final String TOKEN_KEY = "_synchronizerToken";

	public static synchronized boolean isTokenValid(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String sessionToken = (String) session.getAttribute(getTokenKey());
		String requestToken = request.getParameter(getTokenKey());
		if (requestToken == null) {
			throw new TokenException("Missing synchronizer token in request");
		}
		if (sessionToken == null) {
			throw new TokenException("Missing synchronizer token in session");
		}
		if (sessionToken.equals(requestToken)) {
			session.setAttribute(getTokenKey(), nextToken());
			return true;
		}
		return false;
	}

	public static String nextToken() {
		long seed = System.currentTimeMillis();
		Random r = new Random();
		r.setSeed(seed);
		return Long.toString(seed) + Long.toString(Math.abs(r.nextLong()));
	}

	public static String getTokenKey() {
		return TOKEN_KEY;
	}
}
