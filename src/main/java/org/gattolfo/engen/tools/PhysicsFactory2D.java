package org.gattolfo.engen.tools;


import com.badlogic.gdx.physics.box2d.*;

public class PhysicsFactory2D {
    private final World world;

    public PhysicsFactory2D(World world) {
        this.world = world;
    }

    public Body createBox(
            float x, float y,
            float w, float h,
            boolean dynamic
    ) {

        BodyDef def = new BodyDef();
        def.position.set(x, y);

        def.type = dynamic ?
                BodyDef.BodyType.DynamicBody :
                BodyDef.BodyType.StaticBody;

        Body body = world.createBody(def);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(w / 2f, h / 2f);

        FixtureDef fix = new FixtureDef();
        fix.shape = shape;
        fix.density = 1f;
        fix.friction = 0.3f;

        body.createFixture(fix);

        shape.dispose();

        return body;
    }

}