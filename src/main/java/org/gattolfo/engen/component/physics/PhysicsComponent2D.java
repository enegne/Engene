package org.gattolfo.engen.component.physics;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.box2d.Body;

public class PhysicsComponent2D implements Component {
    private final Body body;
    public PhysicsComponent2D(Body body){
        this.body = body;
    }

    public Body getBody() {
        return body;
    }
}
