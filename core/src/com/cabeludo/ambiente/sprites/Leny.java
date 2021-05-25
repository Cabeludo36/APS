package com.cabeludo.ambiente.sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.cabeludo.ambiente.MainGame;
import com.cabeludo.ambiente.telas.playScreen;

public class Leny extends Sprite{
    public enum State {CAINDO, PULANDO, CORRENDO, PARADO};
    public State atualState;
    public State anteriorState;

    public World world;
    public Body b2body;

    private Animation<TextureRegion> lenyParado;
    private Animation<TextureRegion> lenyCorre;
    private Animation<TextureRegion> lenyPula;
    private float stateTimer;
    private boolean correndoDireita;

    public Leny(World world, playScreen screen){
        this.world = world;
        defineLeny();

        atualState = State.PARADO;
        anteriorState = State.PARADO;
        stateTimer = 0;
        correndoDireita = true;

        Array<TextureRegion> frames = new Array<TextureRegion>();

        for(int i = 0; i < 18; i++)
            frames.add(new TextureRegion(screen.getAtlas().findRegion("idle sheet-Sheet"), i * 80, 0, 32, 32));
        lenyParado = new Animation<TextureRegion>(0.1f, frames);
        frames.clear();
        for(int i = 0; i < 24; i++)
            frames.add(new TextureRegion(screen.getAtlas().findRegion("itch run-Sheet sheet"), i * 80, 0, 32, 37));
        lenyCorre = new Animation<TextureRegion>(0.07f, frames);
        frames.clear();
        for(int i = 0; i < 6; i++)
            frames.add(new TextureRegion(screen.getAtlas().findRegion("itch jump sheet-Sheet"), i * 80, 0, 32, 37));
        lenyPula = new Animation<TextureRegion>(0.1f, frames);
        frames.clear();

        setBounds(0, 0, 32 / MainGame.PPM, 32 / MainGame.PPM);
    }

    public void update(float dt) {
        setPosition(b2body.getPosition().x - getWidth()/2, b2body.getPosition().y - getHeight()/2);
        setRegion(getFrame(dt));
    }

    public TextureRegion getFrame(float dt) {
        atualState = getState();

        TextureRegion region;
        switch (atualState) {
            case PULANDO:
                region = lenyPula.getKeyFrame(stateTimer);
                break;
            case CORRENDO:
                region = lenyCorre.getKeyFrame(stateTimer, true);
                break;
            case CAINDO:
            case PARADO:
            default:
                region = lenyParado.getKeyFrame(stateTimer, true);
                break;
        }

        if ((b2body.getLinearVelocity().x < 0 || !correndoDireita) && !region.isFlipX()) {
            region.flip(true, false);
            correndoDireita = false;
        }else if((b2body.getLinearVelocity().x > 0 || correndoDireita)&& region.isFlipX()){
            region.flip(true,false);
            correndoDireita = true;
        }

        stateTimer = atualState == anteriorState ? stateTimer + dt : 0;
        anteriorState = atualState;
        return region;
    }

    public State getState() {
        if (b2body.getLinearVelocity().y > 0 || (b2body.getLinearVelocity().y < 0 && anteriorState == State.PULANDO)) {
            return State.PULANDO;
        }
        else if (b2body.getLinearVelocity().y < 0) {
            return State.CAINDO;
        }
        else if (b2body.getLinearVelocity().x != 0) {
            return State.CORRENDO;
        } else {
            return State.PARADO;
        }
    }

    public void jump(){
        if ( atualState != State.PULANDO ) {
            b2body.applyLinearImpulse(new Vector2(0, 4f), b2body.getWorldCenter(), true);
            atualState = State.PULANDO;
        }
    }

    public void defineLeny() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(170/MainGame.PPM, 25/MainGame.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        //define a forma
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
