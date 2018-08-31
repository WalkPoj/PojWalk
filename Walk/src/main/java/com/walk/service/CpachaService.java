package com.walk.service;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public interface CpachaService {
    /**
     * 验证码生成
     * @param session
     * @param response
     * @throws IOException
     */
    void generateLoginCpacha(HttpSession session, HttpServletResponse response) throws IOException;
}
