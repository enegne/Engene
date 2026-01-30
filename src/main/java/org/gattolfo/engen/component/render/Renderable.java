package org.gattolfo.engen.component.render;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.gattolfo.engen.component.TransformComponent;

/**
 * Represents an entity that can be rendered in a 2D environment.
 * A {@code Renderable} defines the contract for rendering graphical
 * content using a {@code SpriteBatch} and applying transformations
 * specified by a {@code TransformComponent}.
 *
 * The {@code render} method is responsible for drawing the graphical content
 * of the {@code Renderable} instance, based on the current state of the world
 * or application (e.g., position, scale, rotation).
 *
 * Implementations of this interface typically handle specific types of rendering,
 * such as rendering sprites, animations, or UI elements, and will customize
 * how the {@code SpriteBatch} and {@code TransformComponent} are used.
 */
public interface Renderable {
    /**
     * Renders graphical content using the supplied {@code SpriteBatch} and transformation
     * data provided by the {@code TransformComponent}. The method also accounts for the
     * elapsed time since the last frame to ensure smooth and consistent rendering updates.
     *
     * @param batch the {@code SpriteBatch} used for rendering. This must be initialized
     *              and prepared before being passed to this method.
     * @param transform the {@code TransformComponent} containing positional, rotational,
     *                  and scaling information to be applied during rendering.
     * @param deltaTime the time elapsed since the last frame, in seconds, used for animations
     *                  or other time-dependent computations during rendering.
     */
    void render(Camera camera, SpriteBatch batch, TransformComponent transform, float deltaTime);

    public int getBatchIndex();

}
