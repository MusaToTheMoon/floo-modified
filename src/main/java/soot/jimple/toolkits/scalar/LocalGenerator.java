package soot.jimple.toolkits.scalar;

import soot.Body;
import soot.Local;
import soot.Type;
import soot.jimple.Jimple;

/**
 * Compatibility stub for projects that still call `new LocalGenerator(body)`.
 * Soot 4 removed the original helper, so we rebuild the two methods Floo uses.
 */
public class LocalGenerator {

    private final Body body;
    private int counter = 0;

    public LocalGenerator(Body body) {
        this.body = body;
    }

    /** Create a fresh local named tmp$N of the requested type and add it to the body. */
    public Local generateLocal(Type t) {
        Local l = Jimple.v().newLocal("tmp$" + (counter++), t);
        body.getLocals().add(l);
        return l;
    }
}