package com.projeto.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

@Component
public class SessionIdentity {

    public static final String USER_ID_SESSION_KEY = "authenticatedUserId";

    public Long getUserId(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return null;
        }

        Object userId = session.getAttribute(USER_ID_SESSION_KEY);
        return userId instanceof Long id ? id : null;
    }

    public void startSession(HttpServletRequest request, Long userId) {
        HttpSession oldSession = request.getSession(false);
        if (oldSession != null) {
            oldSession.invalidate();
        }
        request.getSession(true).setAttribute(USER_ID_SESSION_KEY, userId);
    }
}
