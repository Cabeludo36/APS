package com.cabeludo.ambiente.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.cabeludo.ambiente.MainGame;

public class Leny extends Sprite{
    public World world;
    public Body b2body;

    public Leny(World world){
        this.world = world;
        defineLeny();
    }

    public void defineLeny() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(32/MainGame.PPM, 32/MainGame.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        //define a forma da hitBox
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(7f/MainGame.PPM, 14f/MainGame.PPM);
        /* CircleShape shape = new CircleShape();
        shape.setRadius(5); */

        fdef.shape = shape;
        b2body.createFixture(fdef);
    }
    
}
