package com.cabeludo.ambiente.tools;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.cabeludo.ambiente.MainGame;
import com.cabeludo.ambiente.sprites.InteractiveTileObject;
import com.cabeludo.ambiente.sprites.Leny;

public class WorldContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;

        switch (cDef) {
            case MainGame.LENY_BIT | MainGame.PLACA_BIT:
                if(fixA.getFilterData().categoryBits == MainGame.LENY_BIT)
                    ((InteractiveTileObject) fixB.getUserData()).onPeHit((Leny) fixA.getUserData());
                else
                    ((InteractiveTileObject) fixA.getUserData()).onPeHit((Leny) fixB.getUserData());
                break;
        }
        
    }

    @Override
    public void endContact(Contact contact) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
        // TODO Auto-generated method stub
        
    }

}
