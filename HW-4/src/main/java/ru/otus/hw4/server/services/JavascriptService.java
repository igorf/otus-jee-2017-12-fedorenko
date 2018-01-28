package ru.otus.hw4.server.services;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class JavascriptService {
    private static JavascriptService ourInstance = new JavascriptService();

    public static JavascriptService getInstance() {
        return ourInstance;
    }

    private JavascriptService() {
        super();
    }

    public Object run(String javascript) throws ScriptException {
        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine engine = factory.getEngineByName("JavaScript");
        return engine.eval(javascript);
    }
}
