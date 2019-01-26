package com.cyborg.sprite.menu;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class ExitButton extends ScaledTouchUpButton {

    public ExitButton(TextureAtlas atlas) {
        super(atlas.findRegion("btExit"));
        pos.set(-0.2f,0f);
        setHeightProportion(0.1f);
        if(this.isPressed()) {
            setHeightProportion(0.1f * this.PRESS_SCALE);
        }
    }

    @Override
    public void action() {
        System.out.println("exit");
        System.exit(0);
    }


}
