package com.algorithm;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class StringCalculation {

    public static void main(String[] args) throws ScriptException {
        String s = "1+23*2+3";
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        Object result = engine.eval(s);
        System.out.println(result);
    }

    public static void calculate(String s){
        char[] chars = s.toCharArray();

    }

}
