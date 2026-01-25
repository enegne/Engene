package org.gattolfo.engen.component.physics;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Represents a 2D physics component associated with an entity.
 * This component encapsulates a physics body, enabling the entity to interact
 * and behave according to the rules of a 2D physics simulation.
 *
 */
public class PhysicsComponent2D implements Component {
    /**
     * Represents the physical body associated with this physics component.
     * This variable holds the Body instance that defines the physical properties
     * and behavior of the component in a 2D physics simulation.
     * The body is immutable once it is set during object construction.
     */
    private final Body body;
    /**
     * Constructs a new PhysicsComponent2D that wraps a physics body for 2D simulations.
     *
     * @param body the physics body to associate with this component
     */
    public PhysicsComponent2D(Body body){
        this.body = body;
    }

    /**
     * Retrieves the physics body associated with this component.
     *
     * @return the physics body of this component
     */
    public Body getBody() {
        return body;
    }
}
