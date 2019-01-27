package com.cyborg.pool;

import com.cyborg.base.SpritesPool;
import com.cyborg.sprite.game.Bullet;

public class BulletPool extends SpritesPool<Bullet> {
    @Override
    protected Bullet newObject() {
        return new Bullet();
    }
}
