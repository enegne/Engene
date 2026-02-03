package org.gattolfo.engen;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.assets.AssetManager;
import org.gattolfo.engen.updater.ResizeUpdater;
import org.jetbrains.annotations.NotNull;

public abstract class Scene{
    protected Engine ashleyEngine; // Engine di Ashley
    private final ResizeUpdater resizeUpdater;

    protected Engene engene;   // Riferimento al tuo engine

    public Scene(){
        this.ashleyEngine = new Engine();
        resizeUpdater = new ResizeUpdater();
    }

    public abstract void loadAssets(AssetManager assetManager);

    // Scarica le risorse quando si cambia scena
    public abstract void unloadAssets(AssetManager assetManager);


    protected abstract void setupSystems();
    protected abstract void createEntities();

    public void update(float delta) {
        ashleyEngine.update(delta);
    }


    public void dispose() {
        // Ashley Engine non ha dispose, ma puoi pulire altre risorse
    }

    public Engine getAshleyEngine() {
        return ashleyEngine;
    }

    public ResizeUpdater getResizeUpdater(){
        return  resizeUpdater;
    }

    public void show() {
        setupSystems();
        createEntities();
    }

    public void hide() {
        ashleyEngine.removeAllEntities();
    }

    public void resize(int width, int height){
        resizeUpdater.start_resizing(width,height);
    }

    public void addSystem(@NotNull EntitySystem system){
        ashleyEngine.addSystem(system);

    }

    public void removeSystem(@NotNull EntitySystem system){
        ashleyEngine.removeSystem(system);
    }


    public void addEntity(@NotNull Entity entity){
        ashleyEngine.addEntity(entity);
    }

    public void removeEntity(@NotNull Entity entity){
        ashleyEngine.removeEntity(entity);
    }





}