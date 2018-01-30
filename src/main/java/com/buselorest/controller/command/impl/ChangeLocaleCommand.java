package com.buselorest.controller.command.impl;

import com.buselorest.controller.command.Command;
import com.buselorest.service.ResourceManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

public class ChangeLocaleCommand implements Command {
    private final static Logger logger = Logger.getLogger(ChangeLocaleCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String locale = request.getParameter("locale");
        ResourceManager.INSTANCE.changeResource(Locale.forLanguageTag(locale));
        request.getSession().setAttribute("locale", locale);
        logger.info("Locale: " + locale);
        return request.getParameter("uri");
    }
}
