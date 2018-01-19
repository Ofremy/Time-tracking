package com.buselorest.controller.command;

import com.buselorest.controller.command.impl.LogInCommand;
import com.buselorest.controller.command.impl.LogOutCommand;
import com.buselorest.controller.command.impl.RegisterCommand;

public enum  CommandList {
    LOGIN(new LogInCommand()),
    LOGOUT(new LogOutCommand()),
    REGISTER(new RegisterCommand());

    private Command command;
    CommandList(Command command){
        this.command = command;
    }
    public Command getCommand(){
        return this.command;
    }
}
