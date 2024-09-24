//
// Decompiled by Procyon v0.6.0
//

package com.dsgames.combat;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

class ActiveObject extends Actor {
    int curRegion;
    final TextureRegion[] regions;

    ActiveObject(final float n, final float n2, final float n3, final float n4,
            final TextureRegion[] regions) {
        this.curRegion = 0;
        this.setBounds(n, n2, n3, n4);
        this.regions = regions;
    }

    @Override
    public final void draw(final Batch batch, final float n) {
        batch.draw(this.regions[this.curRegion], this.getX(), this.getY(), this.getWidth(),
                this.getHeight());
    }
}
