package com.cyborg.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import com.cyborg.base.BaseScreen;

public class MenuScreen extends BaseScreen {

    SpriteBatch batch;
    Texture img;
    Texture background;

    Vector2 pos;
    Vector2 target;
    Vector2 v;

    @Override
    public void show() {
        super.show();
        batch = new SpriteBatch();
        background = new Texture("bg.png");
        img = new Texture("badlogic.jpg");
        pos = new Vector2(0, 0);
        target = new Vector2(0, 0);
        v = new Vector2(0,0);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0.5f, 0.2f, 0.3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, 0, 0);
        batch.draw(img, pos.x, pos.y);
        batch.end();
        if (Gdx.graphics.getWidth() - 256 > pos.x && Gdx.graphics.getHeight() - 256 > pos.y) {
            if(Math.abs(target.x - pos.x) >=1 && Math.abs(target.y - pos.y) >= 1) {
                pos.add(v);
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println("touchDown " + screenX + " " + (Gdx.graphics.getHeight() - screenY));
        target.x = screenX;
        target.y = Gdx.graphics.getHeight() - screenY;
        // New logic here
        v = target.sub(pos);
        //v.scl(0.01f);
        return super.touchDown(screenX, screenY, pointer, button);
    }
}
