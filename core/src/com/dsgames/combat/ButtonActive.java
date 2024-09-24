//
// Decompiled by Procyon v0.6.0
//

package com.dsgames.combat;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

abstract class ButtonActive extends ActiveObject {
    int cur;
    int curDown;

    ButtonActive(final float n, final float n2, final float n3, final float n4,
            final TextureRegion[] array, final int n5, final int curDown) {
        super(n, n2, n3, n4, array);
        this.curDown = curDown;
        this.cur = n5;
        this.curRegion = n5;
        this.addListener(new ClickListener() {
            final ButtonActive this_0 = ButtonActive.this;
            final ButtonActive val_gett = ButtonActive.this;

            @Override
            public boolean touchDown(final InputEvent inputEvent, final float n, final float n2,
                    final int n3, final int n4) {
                boolean b;
                if (n3 != 0) {
                    b = false;
                } else {
                    this.this_0.curRegion = this.val_gett.curDown;
                    b = true;
                }
                return b;
            }

            @Override
            public void touchUp(final InputEvent inputEvent, final float n, final float n2,
                    final int n3, final int n4) {
                if (n3 == 0 && n < this.this_0.getWidth() && n > 0.0f && n2 > 0.0f
                        && n2 < this.this_0.getHeight()) {
                    this.this_0.click();
                }
                this.this_0.curRegion = this.val_gett.cur;
            }
        });
    }

    abstract void click();
}
