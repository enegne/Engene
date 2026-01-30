package org.gattolfo.engen.component.render;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import org.gattolfo.engen.component.TransformComponent;

public class TileMapRenderable implements Renderable{
    public TiledMap map;
    public OrthogonalTiledMapRenderer renderer;

    public TileMapRenderable(TiledMap map, OrthogonalTiledMapRenderer renderer) {
        this.map = map;
        this.renderer = renderer;
    }

    @Override
    public void render(Camera camera, SpriteBatch batch, TransformComponent transform, float deltaTime) {
        renderer.setView((OrthographicCamera) camera);
        renderer.render();
    }

    @Override
    public int getBatchIndex() {
        return 0;
    }


}