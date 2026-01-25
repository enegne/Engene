package org.gattolfo.engen.system;


import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import org.gattolfo.engen.Priority;
import org.gattolfo.engen.component.TransformComponent;
import org.gattolfo.engen.component.physics.PhysicsComponent2D;

public class PhysicsSystem2D extends IteratingSystem {

    private static final float STEP = 1f / 60f;


    private final World world;

    private ComponentMapper<PhysicsComponent2D> pm;
    private ComponentMapper<TransformComponent> tm;


    private float accumulator = 0;

    public PhysicsSystem2D(World world) {
        super(Family.all(PhysicsComponent2D.class, TransformComponent.class).get(), Priority.UPDATE_PHYSICS);
        this.world = world;
        pm = ComponentMapper.getFor(PhysicsComponent2D.class);
        tm = ComponentMapper.getFor(TransformComponent.class);
    }

    @Override
    public void update(float deltaTime) {
        accumulator += deltaTime;

        while (accumulator >= STEP) {

            world.step(STEP, 6, 2);

            accumulator -= STEP;
        }

        super.update(deltaTime);
    }

    @Override
    protected void processEntity(Entity entity, float v) {
        PhysicsComponent2D pc = pm.get(entity);
        TransformComponent tc = tm.get(entity);

        Body body = pc.getBody();

        if (body == null) return;

        Vector2 pos = body.getPosition();
        float angle = body.getAngle();

        tc.getLocalPosition().set(
                pos.x,
                pos.y,
                tc.getLocalPosition().z
        );

        tc.getLocalRotation().setEulerAnglesRad(
                0,
                0,
                angle
        );

        tc.markDirty();
    }
}