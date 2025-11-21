package org.gattolfo.engen.system;

public final class SystemPriorities {
    private SystemPriorities() {}

    // Valori di default (sovrascrivibili)
    public static int TRANSFORM = 1;
    public static int PHYSICS = 2;
    public static int UPDATE = 3;
    public static int RENDER = 4;
    public static int ANIMATION = 5;
    public static int PARTICLES = 6;
}
