package com.example.java8;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Main {

	public static void main(String[] args) throws ScriptException {

		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine engine = mgr.getEngineByName("nashorn");
		String script = "'Hello World'.length";
		Object result = engine.eval(script);
		System.out.println(result);
		
	}

}
