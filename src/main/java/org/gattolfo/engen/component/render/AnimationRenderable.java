package org.gattolfo.engen.component.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import org.gattolfo.engen.component.TransformComponent;

/**
 * A class responsible for rendering animations in a 2D game environment.
 * The AnimationRenderable class allows the usage of a frame-by-frame animation system using
 * libGDX's {@link Animation} and {@link TextureRegion}. It implements the {@link Renderable} interface,
 * enabling it to be rendered with a {@link SpriteBatch}.
 *
 * This class provides constructors to create an instance directly from an existing animation
 * or to build one using a texture atlas sheet split into regions with specified frame duration and play mode.
 *
 * The rendering method adjusts the animation states over time, applying transformations such as position,
 * rotation, and scaling at runtime.
 */
public class AnimationRenderable implements Renderable {

    /**
     * Represents an animation consisting of multiple frames, allowing for time-based transitions
     * between frames to create smooth animation effects. This variable holds a libGDX
     * {@link Animation} object that manages the sequence of {@link TextureRegion} frames
     * and their associated timing and play modes.
     *
     * The animation system is driven over time by updating the state time, which determines
     * the current frame of the animation to render. This provides flexibility in controlling
     * animation playback, including features like looping, ping-pong, or playing the animation
     * a single time.
     *
     * This field is primarily intended for use within the {@link AnimationRenderable} class to
     * render animated textures, adjusted for position, scaling, and rotation in a 2D game world.
     *
     * Key Features:
     * - Stores a collection of {@link TextureRegion} objects for animation frames.
     * - Supports time-based playback using libGDX's {@link Animation} logic.
     * - Plays well with various play modes, such as looping or ping-pong.
     */
    private Animation<TextureRegion> animation;
    /**
     * Constructs an AnimationRenderable instance using the given animation.
     * The animation defines the sequence of frames to be rendered
     * and their behavior over time.
     *
     * @param animation the {@link Animation} object containing the {@link TextureRegion} frames
     *                  and playback configuration (e.g., frame durations and play mode)
     *                  to be used for rendering.
     */
    public AnimationRenderable(Animation<TextureRegion> animation){
        this.animation =animation;
    }

    /**
     * Constructs an {@code AnimationRenderable} using a texture sheet, dividing it into animation frames
     * arranged in a grid. The animation is created with the specified frame duration and playback mode.
     *
     * @param sheet         the texture sheet containing the animation frames. The sheet should be organized
     *                      in a grid layout, where each cell represents a frame.
     * @param frameCols     the number of columns in the texture sheet grid.
     * @param frameRows     the number of rows in the texture sheet grid.
     * @param frameDuration the duration (in seconds) for each frame in the animation.
     * @param playMode      the playback mode of the animation, which determines how the animation loops
     *                      or plays, defined by {@link Animation.PlayMode}.
     */
    public AnimationRenderable(Texture sheet,int frameCols, int frameRows,float frameDuration,Animation.PlayMode playMode){
        TextureRegion[][] tmp = TextureRegion.split(
            sheet,
    sheet.getWidth()/frameCols,
    sheet.getHeight()/frameRows
        );

        Array<TextureRegion> frames = new Array<>();

        for (int i = 0; i < frameRows; i++) {
            for (int j = 0; j < frameCols; j++) {
                frames.add(tmp[i][j]);
            }
        }

        animation = new Animation<>(frameDuration, frames, playMode);
    }

    /**
     * Tracks the elapsed time for the current animation state.
     *
     * The {@code stateTime} variable is used to determine the progress of the animation
     * based on the time elapsed since it started playing. It is typically incremented
     * by the frame time delta on each render or update cycle, enabling time-based
     * calculations for frame selection in an animation sequence.
     *
     * Typically, {@code stateTime} starts at zero when the animation begins and
     * increases as the animation progresses.
     */
    float stateTime = 0f;


    /**
     * Renders the current frame of the animation using the specified {@link SpriteBatch}.
     * The rendering process takes into account the position, rotation, and scale
     * defined by the given {@link TransformComponent}, as well as the delta time
     * to update the animation state.
     *
     * @param batch      the {@link SpriteBatch} used to draw the current frame of the animation.
     * @param transform  the {@link TransformComponent} that provides the position, rotation, and scale
     *                   for rendering the animation in the world.
     * @param deltaTime  the time elapsed (in seconds) since the last frame, used to update animation timing.
     */
    @Override
    public void render(SpriteBatch batch, TransformComponent transform, float deltaTime) {
        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = animation.getKeyFrame(stateTime);


        Vector3 pos = transform.getWorldPosition();
        float rotation = transform.getWorldRotation().getAngleAround(Vector3.Z);
        Vector3 scale = transform.getWorldScale();


        float originX = currentFrame.getRegionWidth() / 2f;
        float originY = currentFrame.getRegionHeight() / 2f;


        batch.draw(
                currentFrame,
                pos.x - originX,          // x position of bottom-left
                pos.y - originY,          // y position of bottom-left
                originX,                  // originX for rotation & scale
                originY,                  // originY for rotation & scale
                currentFrame.getRegionWidth(),  // width
                currentFrame.getRegionHeight(), // height
                scale.x,                  // scaleX
                scale.y,                  // scaleY
                rotation                  // rotation in degrees,
        );

    }
}
