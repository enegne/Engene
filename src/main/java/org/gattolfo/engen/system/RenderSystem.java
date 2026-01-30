package org.gattolfo.engen.system;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import org.gattolfo.engen.Priority;
import org.gattolfo.engen.component.RenderComponent;
import org.gattolfo.engen.component.TransformComponent;

public class RenderSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;
    private final Array<Entity> sortedEntities = new Array<>();
    private final OrthographicCamera camera;
    private final SpriteBatch[] batches;

    private final ComponentMapper<RenderComponent> rm = ComponentMapper.getFor(RenderComponent.class);
    private final ComponentMapper<TransformComponent> tm = ComponentMapper.getFor(TransformComponent.class);
    public RenderSystem(final OrthographicCamera camera, final SpriteBatch batch) {
        this(camera,batch,Priority.RENDER);
    }

    public RenderSystem(final OrthographicCamera camera, final SpriteBatch batch, int priority) {
        this(camera, new SpriteBatch[]{null, batch}, priority);
    }

    public RenderSystem(final OrthographicCamera camera, final SpriteBatch[] batches, int priority) {
        super(priority);
        this.camera = camera;
        this.batches = batches;
    }
    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(RenderComponent.class, TransformComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        camera.update();

        sortedEntities.clear();
        sortedEntities.addAll(entities.toArray(Entity.class));
        sortedEntities.sort((a, b) -> {
            TransformComponent ta = tm.get(a);
            TransformComponent tb = tm.get(b);

            ta.updateWorldTransformIfNeeded();
            tb.updateWorldTransformIfNeeded();

            float za = ta.getWorldPosition().z;
            float zb = tb.getWorldPosition().z;

            return Float.compare(za, zb); // low Z = behind, high Z = front
        });
        for(int i=1;i< batches.length;i++)
            batches[i].setProjectionMatrix(camera.combined);


        int currentBatchIndex = -1;
        boolean batchIsOpen = false;


        for (Entity entity : sortedEntities) {
            RenderComponent render = rm.get(entity);
            TransformComponent transform = tm.get(entity);
            int requiredBatchIndex = render.renderable.getBatchIndex();

            if(requiredBatchIndex!= currentBatchIndex){
                if (batchIsOpen && currentBatchIndex > 0 && batches[currentBatchIndex] != null) {
                    batches[currentBatchIndex].end();
                    batchIsOpen = false;
                }

                currentBatchIndex = requiredBatchIndex;

                if (currentBatchIndex > 0 && batches[currentBatchIndex] != null) {
                    batches[currentBatchIndex].begin();
                    batchIsOpen = true;
                }


            }
            render.renderable.render(camera, batches[requiredBatchIndex], transform, deltaTime);
        }
        if (batchIsOpen && currentBatchIndex > 0 && batches[currentBatchIndex] != null) {
            batches[currentBatchIndex].end();
        }
    }
}
