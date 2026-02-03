package org.gattolfo.engen;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.gattolfo.engen.updater.Resizable;
import org.gattolfo.engen.updater.ResizeUpdater;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;


/**
 * Engene class, this is where it all starts
 */
public class Engene    {

    private AssetManager assetManager;

    private static Engene INSTANCE;


    private Scene currentScene;
    private Map<String, Scene> scenes;


    private Engene(){
        assetManager = new AssetManager();
        scenes = new HashMap<>();
    }

    public Engene get_instance(){
        if(INSTANCE==null)
            INSTANCE = new Engene();

        return INSTANCE;
    }

    public void registerScene(String name, Scene scene) {
        scenes.put(name, scene);
    }

    public void setScene(String name) {
        if (currentScene != null) {
            currentScene.hide();
            currentScene.unloadAssets(assetManager);
        }

        currentScene = scenes.get(name);
        if (currentScene != null) {
            currentScene.loadAssets(assetManager);
            assetManager.finishLoading(); // O usa loading asincrono
            currentScene.show();
        }
    }


    public void update(float delta){
        if (currentScene != null) {
            currentScene.update(delta);
        }
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public void dispose() {
        for (Scene scene : scenes.values()) {
            scene.dispose();
        }
        assetManager.dispose();
    }


}
