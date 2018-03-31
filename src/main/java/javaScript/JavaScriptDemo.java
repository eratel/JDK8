package javaScript;
import org.junit.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class JavaScriptDemo {
    @Test
    public void test() throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName( "JavaScript" );
        System.out.println(engine.getClass().getName());
        String s = "function f() { return 1; }; f() + 1;";
        System.out.println(engine.eval(s));
    }
}
