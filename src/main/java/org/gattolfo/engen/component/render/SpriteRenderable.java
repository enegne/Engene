package org.gattolfo.engen.component.render;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import org.gattolfo.engen.component.TransformComponent;

/**
 * A renderable implementation that uses a Sprite object for rendering. This class
 * represents a renderable entity that can draw a Sprite onto a {@code SpriteBatch},
 * reflecting the world transformation defined by a {@code TransformComponent}.
 */
public class SpriteRenderable implements Renderable {
    /**
     * The Sprite instance used for rendering purposes.
     * Represents a 2D image that can be drawn to the screen, with properties such
     * as position, rotation, scale, and origin all customizable for rendering.
     *
     * This variable is central to {@code SpriteRenderable}, as it holds the sprite
     * data to be rendered and manipulated according to the world transformation
     * defined by the {@code TransformComponent}.
     *
     * It is expected to be fully initialized and configured before rendering.
     */
    private final Sprite sprite;

    /**
     * Constructs a new SpriteRenderable instance using the specified Sprite.
     *
     * @param sprite the Sprite instance to be rendered by this SpriteRenderable
     */
    public SpriteRenderable(Sprite sprite) {
        this.sprite = sprite;
    }

    /**
     * Renders a Sprite using the provided {@code SpriteBatch} and applies the world transformation
     * defined by the given {@code TransformComponent}.
     *
     * The method updates the world transformation of the {@code TransformComponent} if necessary,
     * then adjusts the Sprite's position, rotation, scale, and origin to match the
     * world transformation before drawing the Sprite.
     *
     * @param batch     the {@code SpriteBatch} used to draw the Sprite
     * @param transform the {@code TransformComponent} that provides the world transformation
     *                  (position, rotation, and scale) for the Sprite
     * @param deltaTime the time elapsed since the last frame, in seconds
     */
    @Override
    public void render(SpriteBatch batch, TransformComponent transform, float deltaTime) {
        transform.updateWorldTransformIfNeeded();
        sprite.setOriginCenter();
        sprite.setCenter(transform.getWorldPosition().x, transform.getWorldPosition().y);
        //sprite.setPosition(transform.getWorldPosition().x, transform.getWorldPosition().y);
        sprite.setRotation(transform.getWorldRotation().getAngleAround(Vector3.Z));
        sprite.setScale(transform.getWorldScale().x, transform.getWorldScale().y);

        sprite.draw(batch);
    }
}
