package com.buselorest.controller.command;

import com.buselorest.controller.command.Command;
import com.buselorest.controller.command.impl.EmptyCommand;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {
    public Command defineCommand(HttpServletRequest request){
        Command current = new EmptyCommand();
        String action = request.getParameter("command");
        if (action == null || action.isEmpty()){
            return current;
        }
        try {
            CommandList command = CommandList.valueOf(action.toUpperCase());
            current = command.getCommand();
        }catch (IllegalArgumentException e){

        }
        return current;
    }
}
