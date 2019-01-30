package com.cyborg.sprite.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.cyborg.base.Sprite;
import com.cyborg.math.Rect;
import com.cyborg.pool.BulletPool;

public class MainShip extends Sprite {

    private Rect worldBounds;

    private final Vector2 v0 = new Vector2(0.5f, 0);
    private Vector2 v = new Vector2();
    private Vector2 move2 = new Vector2();

    private boolean isPressedLeft;
    private boolean isPressedRight;

    private BulletPool bulletPool;

    private TextureRegion bulletRegion;

    public MainShip(TextureAtlas atlas, BulletPool bulletPool) {
        super(atlas.findRegion("main_ship"), 1, 2, 2);
        this.bulletRegion = atlas.findRegion("bulletMainShip");
        this.bulletPool = bulletPool;
        setHeightProportion(0.15f);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        this.worldBounds = worldBounds;
        setBottom(worldBounds.getBottom() + 0.05f);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        pos.mulAdd(v, delta);
        if(this.getRight() >= worldBounds.getRight()) {
            stop();
            setRight(worldBounds.getRight());
        }
        if(this.getLeft() <= worldBounds.getLeft()) {
            stop();
            setLeft(worldBounds.getLeft());
        }
    }

    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                isPressedLeft = true;
                moveLeft();
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                isPressedRight = true;
                moveRight();
                break;
            case Input.Keys.UP:
                shoot();
                break;
        }
        return false;
    }

    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                isPressedLeft = false;
                if (isPressedRight) {
                    moveRight();
                } else {
                    stop();
                }
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                isPressedRight = false;
                if (isPressedLeft) {
                    moveLeft();
                } else {
                    stop();
                }
                break;
        }
        return false;
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        shoot();
        return super.touchDown(touch, pointer);
    }

    private void moveRight() {
        v.set(v0);
    }

    private void moveLeft() {
        v.set(v0).rotate(180);
    }

    private void stop() {
        v.setZero();
    }

    private void shoot() {
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, bulletRegion, pos, new Vector2(0, 0.5f), 0.01f, worldBounds, 1);
    }

    public boolean touchDragged(Vector2 touch, int pointer) {
        System.out.println("TOUCH");
        shoot();
        mouseMove(touch);
        return false;
    }

    public boolean mouseMoved(Vector2 touch) {
        System.out.println("MOVE");
        mouseMove(touch);
        return false;
    }

    private void mouseMove(Vector2 touch) {
        if(touch.x<pos.x) {
            moveLeft();
            return;
        }
        if(touch.x>pos.x) {
            moveRight();
            return;
        }
    }
}
