package com.cabeludo.ambiente.tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.cabeludo.ambiente.MainGame;
import com.cabeludo.ambiente.sprites.PlacaPrecao;
import com.cabeludo.ambiente.telas.playScreen;

public class B2WorldCreator {
    
    public B2WorldCreator(playScreen screen) {
        World world = screen.getWorld();
        TiledMap map = screen.getMap();

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;
        //cria o chão
        for (MapObject object : map.getLayers().get(0).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject)object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX()+rect.width/2)/MainGame.PPM, (rect.getY()+rect.height/2)/MainGame.PPM);
            body = world.createBody(bdef);

            shape.setAsBox((rect.getWidth()/2)/MainGame.PPM, (rect.getHeight()/2)/MainGame.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }
        //cria caixa
        /* for (MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
            
            
            Rectangle rect = ((RectangleMapObject)object).getRectangle();

            bdef.type = BodyDef.BodyType.DynamicBody;
            bdef.position.set((rect.getX()+rect.width/2)/MainGame.PPM, (rect.getY()+rect.height/2)/MainGame.PPM);
            body = world.createBody(bdef);

            shape.setAsBox((rect.getWidth()/2)/MainGame.PPM, (rect.getHeight()/2)/MainGame.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        } */
        /* //cria barreira
        for (MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject)object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX()+rect.width/2)/MainGame.PPM, (rect.getY()+rect.height/2)/MainGame.PPM);
            body = world.createBody(bdef);

            shape.setAsBox((rect.getWidth()/2)/MainGame.PPM, (rect.getHeight()/2)/MainGame.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        } */
        //cria VARAL
        for (MapObject object : map.getLayers().get(1).getObjects().getByType(RectangleMapObject.class)) {
            new PlacaPrecao(screen, object, 1);
        }
    }
}
