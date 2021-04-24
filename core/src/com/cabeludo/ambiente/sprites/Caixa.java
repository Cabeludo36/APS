package com.cabeludo.ambiente.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.cabeludo.ambiente.MainGame;

public class Caixa extends Sprite {
    private World world;
    private Body b2body;
    private float x;
    private float y;


    public Caixa(World world, float x, float y) {
        this.world = world;
        this.x = x;
        this.y = y;
        defineCaixa();
    }

    public void update(float dt) {
        setPosition(b2body.getPosition().x - getWidth()/2, b2body.getPosition().y - getHeight()/2);
    }

    public void defineCaixa() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(x/MainGame.PPM, y/MainGame.PPM);
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

        EdgeShape pe = new EdgeShape();
        pe.set(new Vector2(-2 / MainGame.PPM, -15 / MainGame.PPM), new Vector2(2 / MainGame.PPM, -15 / MainGame.PPM));
        fdef.shape = pe;
        fdef.isSensor = true;

        b2body.createFixture(fdef).setUserData("pe");
    }
    
}
