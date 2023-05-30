package com.matchgorithm;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

import org.fusesource.jansi.AnsiConsole;
import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

class ChatTest{
    public static void main(String[] args) {
        Ansi a =  ansi();
        a.fgYellow().a("Hello ");
        a.fgRed().a("how ");
        //a.saveCursorPosition();
        a.fgGreen().a("are ");
        a.cursor(0, 0);
        //a.restoreCursorPosition().a("replace");
        a.a("\033[2J");
        AnsiConsole.out().println(a);

    }



}

