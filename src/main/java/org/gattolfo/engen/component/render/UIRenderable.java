package org.gattolfo.engen.component.render;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import org.gattolfo.engen.component.TransformComponent;
import org.gattolfo.engen.updater.Resizable;

public class UIRenderable implements Renderable, Resizable {
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }

    private Stage stage;
    public UIRenderable(Stage stage) {
        this.stage = stage;
    }


    @Override
    public void render(Camera camera, SpriteBatch batch, TransformComponent transform, float deltaTime) {
        stage.getRoot().setPosition(transform.getWorldPosition().x, transform.getWorldPosition().y);
        stage.act(deltaTime);
        stage.draw();

    }

    @Override
    public int getBatchIndex() {
        return 1;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
