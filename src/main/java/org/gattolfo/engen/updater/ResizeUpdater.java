package org.gattolfo.engen.updater;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ResizeUpdater {

    private final ArrayList<Resizable>  resizables = new ArrayList<>();


    public void start_resizing(int width, int height) {
        for(Resizable resizable : resizables) {
            resizable.resize(width, height);
        }
    }

    public void addResizable(@NotNull Resizable resizable){
        resizables.add(resizable);
    }

    public void removeResizable(@NotNull Resizable resizable){
        resizables.remove(resizable);
    }

}
