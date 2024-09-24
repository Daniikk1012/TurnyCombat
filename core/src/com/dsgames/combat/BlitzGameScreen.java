//
// Decompiled by Procyon v0.6.0
//

package com.dsgames.combat;

import com.badlogic.gdx.utils.TimeUtils;

class BlitzGameScreen extends GameScreen {
    private long timemillis;

    BlitzGameScreen(final MyGdxGame myGdxGame) {
        super(myGdxGame);
        this.timemillis = TimeUtils.millis() + 300000L;
    }

    @Override
    public void render(final float n) {
        final boolean b = true;
        super.render(n);
        this.game.batch.begin();
        this.game.font.draw(this.game.batch,
                String.valueOf((int) (-TimeUtils.millis() + this.timemillis) / 60000) + ":"
                        + String.valueOf(
                                (int) (-TimeUtils.millis() + this.timemillis) % 60000 / 1000),
                750.0f, 700.0f);
        this.game.batch.end();
        if (this.timemillis - TimeUtils.millis() < 0L && this.ingame) {
            boolean b2;
            if (this.p1.hp > this.p2.hp) {
                b2 = b;
            } else if (this.p1.hp < this.p2.hp) {
                b2 = false;
            } else {
                b2 = b;
                if (this.p1.attack <= this.p2.attack) {
                    if (this.p1.attack < this.p2.attack) {
                        b2 = false;
                    } else {
                        b2 = b;
                        if (this.p1.bMana <= this.p2.bMana) {
                            if (this.p1.bMana < this.p2.bMana) {
                                b2 = false;
                            } else {
                                b2 = b;
                                if (this.p1.mana <= this.p2.mana) {
                                    if (this.p1.mana < this.p2.mana) {
                                        b2 = false;
                                    } else {
                                        b2 = b;
                                        if (this.rightTurn) {
                                            b2 = false;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            this.gameOver(b2);
        }
    }
}
