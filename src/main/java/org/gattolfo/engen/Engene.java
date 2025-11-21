package org.gattolfo.engen;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.gattolfo.engen.updater.Resizable;
import org.gattolfo.engen.updater.ResizeUpdater;
import org.jetbrains.annotations.NotNull;


/**
 * Engene class, this is where it all starts
 */
public class Engene    {




    private Engine engine;


    private final ResizeUpdater resizeUpdater;





    public Engene(){
        engine = new Engine();
        resizeUpdater = new ResizeUpdater();
    }


    public void addEntity(@NotNull Entity entity){
        engine.addEntity(entity);
    }

    public void removeEntity(@NotNull Entity entity){
        engine.removeEntity(entity);
    }

    public void addSystem(@NotNull EntitySystem system){
        engine.addSystem(system);

    }

    public void removeSystem(@NotNull EntitySystem system){
        engine.removeSystem(system);
    }


    public void update(float delta){
        engine.update(delta);
    }

    public ResizeUpdater getResizeUpdater(){
        return  resizeUpdater;
    }


    public void resize(int width, int height){
        resizeUpdater.start_resizing(width,height);
    }

}
