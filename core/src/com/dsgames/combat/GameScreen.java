//
// Decompiled by Procyon v0.6.0
//

package com.dsgames.combat;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;

class GameScreen extends Stage implements Screen {
    private boolean anim;
    private TextureRegion[] btns;
    BitmapFont font;
    private int frS1;
    private int frS2;
    private int frames1;
    private int frames2;
    final MyGdxGame game;
    boolean ingame;
    private int manaPlus;
    private int maxHalfPlus;
    final PlayerState p1;
    final PlayerState p2;
    private TextureRegion play1;
    private TextureRegion play1L;
    private TextureRegion play1LM;
    private TextureRegion play1M;
    private TextureRegion play1S;
    private TextureRegion play1SM;
    private TextureRegion play2;
    private TextureRegion play2L;
    private TextureRegion play2LM;
    private TextureRegion play2M;
    private TextureRegion play2S;
    private TextureRegion play2SM;
    ShapeRenderer renderer;
    boolean rightTurn;
    private boolean soundPlus;
    private int t1;
    private int t2;
    private long time;
    TextureRegion[] types;

    GameScreen(final MyGdxGame game) {
        super(new StretchViewport(1600.0f, 900.0f), game.batch);
        this.t1 = 0;
        this.t2 = -1;
        this.anim = false;
        this.soundPlus = false;
        this.ingame = true;
        this.manaPlus = 0;
        this.maxHalfPlus = 0;
        this.rightTurn = false;
        this.types = new TextureRegion[] {new TextureRegion(game.types, 0, 0, 64, 64),
                new TextureRegion(game.types, 64, 0, 64, 64),
                new TextureRegion(game.types, 0, 64, 64, 64),
                new TextureRegion(game.types, 64, 64, 64, 64)};
        this.renderer = game.renderer;
        this.frames1 = 0;
        this.frames2 = 0;
        this.frS1 = 0;
        this.frS2 = 0;
        this.p1 = new PlayerState(this, false);
        this.p2 = new PlayerState(this, true);
        this.btns = new TextureRegion[41];
        int n = 0;
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 5; ++j) {
                final TextureRegion[] btns = this.btns;
                final Texture btns2 = game.btns;
                int n2;
                if (j < 4) {
                    n2 = 0;
                } else {
                    n2 = 64;
                }
                btns[n] = new TextureRegion(btns2, n2 + j * 64, i * 64, 64, 64);
                ++n;
            }
        }
        this.btns[20] = new TextureRegion(game.btns, 256, 0, 64, 64);
        int n3 = 21;
        for (int k = 4; k < 8; ++k) {
            for (int l = 0; l < 5; ++l) {
                final TextureRegion[] btns3 = this.btns;
                final Texture btns4 = game.btns;
                int n4;
                if (l < 4) {
                    n4 = 0;
                } else {
                    n4 = 64;
                }
                btns3[n3] = new TextureRegion(btns4, n4 + l * 64, k * 64, 64, 64);
                ++n3;
            }
        }
        final TextureRegion textureRegion = new TextureRegion(game.leftRight, 0, 0, 64, 64);
        final TextureRegion textureRegion2 = new TextureRegion(game.leftRight, 64, 0, 64, 64);
        final TextureRegion textureRegion3 = new TextureRegion(game.leftRight, 0, 64, 64, 64);
        final TextureRegion textureRegion4 = new TextureRegion(game.leftRight, 64, 64, 64, 64);
        this.game = game;
        this.addActor(new ButtonActive(700.0f, 700.0f, 200.0f, 200.0f,
                new TextureRegion[] {textureRegion, textureRegion2, textureRegion3, textureRegion4},
                0, 2) {
            final GameScreen this_0 = GameScreen.this;

            @Override
            void click() {
                if (this.this_0.rightTurn) {
                    this.this_0.p2.doubleAttack = 1;
                    this.this_0.p1.forHalf = 1;
                    if (this.this_0.p2.maxMana < this.this_0.p2.maxMaxMana) {
                        final PlayerState p2 = this.this_0.p2;
                        ++p2.maxMana;
                    }
                    this.this_0.p2.mana = this.this_0.p2.maxMana;
                    this.this_0.rightTurn = false;
                    this.cur = 0;
                    this.curDown = 2;
                    this.this_0.t1++;
                    if (this.this_0.maxHalfPlus < 4 && this.this_0.t1 == 2) {
                        this.this_0.t1 = 0;
                        final PlayerState p3 = this.this_0.p1;
                        p3.maxHalf *= 2;
                        this.this_0.time = TimeUtils.nanoTime();
                        this.this_0.anim = true;
                        Gdx.input.setInputProcessor(null);
                        this.this_0.maxHalfPlus++;
                        this.this_0.soundPlus = true;
                    }
                } else {
                    this.this_0.p1.doubleAttack = 1;
                    this.this_0.p2.forHalf = 1;
                    if (this.this_0.p1.maxMana < this.this_0.p1.maxMaxMana) {
                        final PlayerState p4 = this.this_0.p1;
                        ++p4.maxMana;
                    }
                    this.this_0.p1.mana = this.this_0.p1.maxMana;
                    this.this_0.rightTurn = true;
                    this.cur = 1;
                    this.curDown = 3;
                    this.this_0.t2++;
                    if (this.this_0.maxHalfPlus < 4 && this.this_0.t2 == 2) {
                        this.this_0.t2 = 0;
                        final PlayerState p5 = this.this_0.p2;
                        p5.maxHalf *= 2;
                        this.this_0.time = TimeUtils.nanoTime();
                        this.this_0.anim = true;
                        Gdx.input.setInputProcessor(null);
                        this.this_0.maxHalfPlus++;
                        this.this_0.soundPlus = true;
                    }
                }
                this.this_0.manaPlus = 0;
            }
        });
        this.addActor(new ButtonActive(0.0f, 310.0f, 160.0f, 160.0f, this.btns, 0, 21) {
            final GameScreen this_0 = GameScreen.this;
            final MyGdxGame val_game = game;

            @Override
            void click() {
                if (this.this_0.rightTurn) {
                    if (this.this_0.p2.attack < this.this_0.p2.maxAttack
                            && this.this_0.p2.mana >= 3) {
                        final PlayerState p2 = this.this_0.p2;
                        p2.mana -= 3;
                        final PlayerState p3 = this.this_0.p2;
                        ++p3.attack;
                        if (this.val_game.pref.getBoolean("sound")) {
                            this.val_game.hp.play();
                        }
                        this.this_0.frS1 = MathUtils.random(25, 75);
                    }
                } else if (this.this_0.p1.attack < this.this_0.p1.maxAttack
                        && this.this_0.p1.mana >= 3) {
                    final PlayerState p4 = this.this_0.p1;
                    p4.mana -= 3;
                    final PlayerState p5 = this.this_0.p1;
                    ++p5.attack;
                    if (this.val_game.pref.getBoolean("sound")) {
                        this.val_game.hp.play();
                    }
                    this.this_0.frS2 = MathUtils.random(25, 75);
                }
            }
        });
        this.addActor(new ButtonActive(160.0f, 310.0f, 160.0f, 160.0f, this.btns, 1, 22) {
            final GameScreen this_0 = GameScreen.this;
            final MyGdxGame val_game = game;

            @Override
            void click() {
                if (this.this_0.rightTurn) {
                    if (this.this_0.p2.hp < this.this_0.p2.superHp && this.this_0.p2.mana >= 3) {
                        final PlayerState p2 = this.this_0.p2;
                        p2.mana -= 3;
                        final PlayerState p3 = this.this_0.p2;
                        ++p3.hp;
                        if (this.val_game.pref.getBoolean("sound")) {
                            this.val_game.hp.play();
                        }
                        this.this_0.frS1 = MathUtils.random(25, 75);
                    }
                } else if (this.this_0.p1.hp < this.this_0.p1.superHp && this.this_0.p1.mana >= 3) {
                    final PlayerState p4 = this.this_0.p1;
                    p4.mana -= 3;
                    final PlayerState p5 = this.this_0.p1;
                    ++p5.hp;
                    if (this.val_game.pref.getBoolean("sound")) {
                        this.val_game.hp.play();
                    }
                    this.this_0.frS2 = MathUtils.random(25, 75);
                }
            }
        });
        this.addActor(new ButtonActive(320.0f, 310.0f, 160.0f, 160.0f, this.btns, 2, 23) {
            final GameScreen this_0 = GameScreen.this;
            final MyGdxGame val_game = game;

            @Override
            void click() {
                if (this.this_0.rightTurn) {
                    if (this.this_0.p2.mana >= 2) {
                        final PlayerState p2 = this.this_0.p2;
                        p2.mana -= 2;
                        final PlayerState p3 = this.this_0.p1;
                        p3.hp -= this.this_0.p2.doubleAttack * this.this_0.p2.attack
                                / this.this_0.p1.forHalf;
                        if (this.val_game.pref.getBoolean("sound")) {
                            this.val_game.atk.play();
                        }
                        this.this_0.frS1 = MathUtils.random(25, 75);
                    }
                } else if (this.this_0.p1.mana >= 2) {
                    final PlayerState p4 = this.this_0.p1;
                    p4.mana -= 2;
                    final PlayerState p5 = this.this_0.p2;
                    p5.hp -= this.this_0.p1.doubleAttack * this.this_0.p1.attack
                            / this.this_0.p2.forHalf;
                    if (this.val_game.pref.getBoolean("sound")) {
                        this.val_game.atk.play();
                    }
                    this.this_0.frS2 = MathUtils.random(25, 75);
                }
            }
        });
        this.addActor(new ButtonActive(480.0f, 310.0f, 160.0f, 160.0f, this.btns, 3, 24) {
            final GameScreen this_0 = GameScreen.this;

            @Override
            void click() {
                if (this.this_0.rightTurn) {
                    if (this.this_0.p2.forHalf < this.this_0.p2.maxHalf
                            && this.this_0.p2.mana >= 2) {
                        final PlayerState p2 = this.this_0.p2;
                        p2.mana -= 2;
                        final PlayerState p3 = this.this_0.p2;
                        p3.forHalf *= 2;
                        this.this_0.frS1 = MathUtils.random(25, 75);
                    }
                } else if (this.this_0.p1.forHalf < this.this_0.p1.maxHalf
                        && this.this_0.p1.mana >= 2) {
                    final PlayerState p4 = this.this_0.p1;
                    p4.mana -= 2;
                    final PlayerState p5 = this.this_0.p1;
                    p5.forHalf *= 2;
                    this.this_0.frS2 = MathUtils.random(25, 75);
                }
            }
        });
        this.addActor(new ButtonActive(640.0f, 310.0f, 160.0f, 160.0f, this.btns, 5, 26) {
            final GameScreen this_0 = GameScreen.this;
            final MyGdxGame val_game = game;

            @Override
            void click() {
                if (this.this_0.manaPlus < 5) {
                    if (this.this_0.rightTurn) {
                        if (this.this_0.p2.mana < this.this_0.p2.maxMana
                                && this.this_0.p2.hp >= 2) {
                            final PlayerState p2 = this.this_0.p2;
                            p2.hp -= 2;
                            final PlayerState p3 = this.this_0.p2;
                            ++p3.mana;
                            if (this.val_game.pref.getBoolean("sound")) {
                                this.val_game.hp.play();
                            }
                            this.this_0.manaPlus++;
                            this.this_0.frS1 = MathUtils.random(25, 75);
                        }
                    } else if (this.this_0.p1.mana < this.this_0.p1.maxMana
                            && this.this_0.p1.hp >= 2) {
                        final PlayerState p4 = this.this_0.p1;
                        p4.hp -= 2;
                        final PlayerState p5 = this.this_0.p1;
                        ++p5.mana;
                        if (this.val_game.pref.getBoolean("sound")) {
                            this.val_game.hp.play();
                        }
                        this.this_0.manaPlus++;
                        this.this_0.frS2 = MathUtils.random(25, 75);
                    }
                }
            }
        });
        this.addActor(new ButtonActive(800.0f, 310.0f, 160.0f, 160.0f, this.btns, 6, 27) {
            final GameScreen this_0 = GameScreen.this;
            final MyGdxGame val_game = game;

            @Override
            void click() {
                if (this.this_0.rightTurn) {
                    if (this.this_0.p2.attack < this.this_0.p2.maxAttack
                            && this.this_0.p2.mana >= 5) {
                        final PlayerState p2 = this.this_0.p2;
                        p2.mana -= 5;
                        final PlayerState p3 = this.this_0.p2;
                        p3.attack += 2;
                        if (this.val_game.pref.getBoolean("sound")) {
                            this.val_game.hp.play();
                        }
                        this.this_0.frS1 = MathUtils.random(25, 75);
                    }
                } else if (this.this_0.p1.attack < this.this_0.p1.maxAttack
                        && this.this_0.p1.mana >= 5) {
                    final PlayerState p4 = this.this_0.p1;
                    p4.mana -= 5;
                    final PlayerState p5 = this.this_0.p1;
                    p5.attack += 2;
                    if (this.val_game.pref.getBoolean("sound")) {
                        this.val_game.hp.play();
                    }
                    this.this_0.frS2 = MathUtils.random(25, 75);
                }
            }
        });
        this.addActor(new ButtonActive(960.0f, 310.0f, 160.0f, 160.0f, this.btns, 7, 28) {
            final GameScreen this_0 = GameScreen.this;
            final MyGdxGame val_game = game;

            @Override
            void click() {
                if (this.this_0.rightTurn) {
                    if (this.this_0.p2.hp < this.this_0.p2.superHp && this.this_0.p2.mana >= 4) {
                        final PlayerState p2 = this.this_0.p2;
                        p2.mana -= 4;
                        final PlayerState p3 = this.this_0.p2;
                        p3.hp += 3;
                        if (this.val_game.pref.getBoolean("sound")) {
                            this.val_game.hp.play();
                        }
                        this.this_0.frS1 = MathUtils.random(25, 75);
                    }
                } else if (this.this_0.p1.hp < this.this_0.p1.superHp && this.this_0.p1.mana >= 4) {
                    final PlayerState p4 = this.this_0.p1;
                    p4.mana -= 4;
                    final PlayerState p5 = this.this_0.p1;
                    p5.hp += 3;
                    if (this.val_game.pref.getBoolean("sound")) {
                        this.val_game.hp.play();
                    }
                    this.this_0.frS2 = MathUtils.random(25, 75);
                }
            }
        });
        this.addActor(new ButtonActive(1120.0f, 310.0f, 160.0f, 160.0f, this.btns, 8, 29) {
            final GameScreen this_0 = GameScreen.this;
            final MyGdxGame val_game = game;

            @Override
            void click() {
                if (this.this_0.rightTurn) {
                    if ((this.this_0.p2.attack < this.this_0.p2.maxAttack
                            || this.this_0.p2.hp < this.this_0.p2.superHp
                            || this.this_0.p2.forHalf < this.this_0.p2.maxHalf)
                            && this.this_0.p2.mana >= 10) {
                        final PlayerState p2 = this.this_0.p2;
                        p2.mana -= 10;
                        if (this.this_0.p2.attack < this.this_0.p2.maxAttack) {
                            final PlayerState p3 = this.this_0.p2;
                            p3.attack += 3;
                        }
                        if (this.this_0.p2.hp < this.this_0.p2.superHp) {
                            final PlayerState p4 = this.this_0.p2;
                            p4.hp += 5;
                        }
                        if (this.this_0.p2.forHalf < this.this_0.p2.maxHalf) {
                            final PlayerState p5 = this.this_0.p2;
                            p5.forHalf *= 2;
                        }
                        final PlayerState p6 = this.this_0.p1;
                        p6.hp -= this.this_0.p2.doubleAttack * this.this_0.p2.attack
                                / this.this_0.p1.forHalf;
                        if (this.val_game.pref.getBoolean("sound")) {
                            this.val_game.hp.play();
                            this.val_game.atk.play();
                        }
                        this.this_0.frS1 = MathUtils.random(25, 75);
                    }
                } else if ((this.this_0.p1.attack < this.this_0.p1.maxAttack
                        || this.this_0.p1.hp < this.this_0.p1.superHp
                        || this.this_0.p1.forHalf < this.this_0.p1.maxHalf)
                        && this.this_0.p1.mana >= 10) {
                    final PlayerState p7 = this.this_0.p1;
                    p7.mana -= 10;
                    if (this.this_0.p1.attack < this.this_0.p1.maxAttack) {
                        final PlayerState p8 = this.this_0.p1;
                        p8.attack += 3;
                    }
                    if (this.this_0.p1.hp < this.this_0.p1.superHp) {
                        final PlayerState p9 = this.this_0.p1;
                        p9.hp += 5;
                    }
                    if (this.this_0.p1.forHalf < this.this_0.p1.maxHalf) {
                        final PlayerState p10 = this.this_0.p1;
                        p10.forHalf *= 2;
                    }
                    final PlayerState p11 = this.this_0.p2;
                    p11.hp -= this.this_0.p1.doubleAttack * this.this_0.p1.attack
                            / this.this_0.p2.forHalf;
                    if (this.val_game.pref.getBoolean("sound")) {
                        this.val_game.hp.play();
                        this.val_game.atk.play();
                    }
                    this.this_0.frS2 = MathUtils.random(25, 75);
                }
            }
        });
        this.addActor(new ButtonActive(0.0f, 100.0f, 160.0f, 160.0f, this.btns, 10, 31) {
            final GameScreen this_0 = GameScreen.this;

            @Override
            void click() {
                if (this.this_0.rightTurn) {
                    if (this.this_0.p2.maxAttack < this.this_0.p2.superAtk
                            && this.this_0.p2.mana >= 6) {
                        final PlayerState p2 = this.this_0.p2;
                        p2.mana -= 6;
                        final PlayerState p3 = this.this_0.p2;
                        ++p3.maxAttack;
                        this.this_0.frS1 = MathUtils.random(25, 75);
                    }
                } else if (this.this_0.p1.maxAttack < this.this_0.p1.superAtk
                        && this.this_0.p1.mana >= 6) {
                    final PlayerState p4 = this.this_0.p1;
                    p4.mana -= 6;
                    final PlayerState p5 = this.this_0.p1;
                    ++p5.maxAttack;
                    this.this_0.frS2 = MathUtils.random(25, 75);
                }
            }
        });
        this.addActor(new ButtonActive(160.0f, 100.0f, 160.0f, 160.0f, this.btns, 11, 32) {
            final GameScreen this_0 = GameScreen.this;

            @Override
            void click() {
                if (this.this_0.rightTurn) {
                    if (this.this_0.p2.maxMaxMana < this.this_0.p2.superMana
                            && this.this_0.p2.mana >= 6) {
                        final PlayerState p2 = this.this_0.p2;
                        p2.mana -= 6;
                        final PlayerState p3 = this.this_0.p2;
                        ++p3.maxMaxMana;
                        this.this_0.frS1 = MathUtils.random(25, 75);
                    }
                } else if (this.this_0.p1.maxMaxMana < this.this_0.p2.superMana
                        && this.this_0.p1.mana >= 6) {
                    final PlayerState p4 = this.this_0.p1;
                    p4.mana -= 6;
                    final PlayerState p5 = this.this_0.p1;
                    ++p5.maxMaxMana;
                    this.this_0.frS2 = MathUtils.random(25, 75);
                }
            }
        });
        this.addActor(new ButtonActive(320.0f, 100.0f, 160.0f, 160.0f, this.btns, 12, 33) {
            final GameScreen this_0 = GameScreen.this;

            @Override
            void click() {
                if (this.this_0.rightTurn) {
                    if (this.this_0.p2.doubleAttack < 2 && this.this_0.p2.mana >= 6) {
                        final PlayerState p2 = this.this_0.p2;
                        p2.mana -= 6;
                        final PlayerState p3 = this.this_0.p2;
                        p3.doubleAttack *= 2;
                        this.this_0.frS1 = MathUtils.random(25, 75);
                    }
                } else if (this.this_0.p1.doubleAttack < 2 && this.this_0.p1.mana >= 6) {
                    final PlayerState p4 = this.this_0.p1;
                    p4.mana -= 6;
                    final PlayerState p5 = this.this_0.p1;
                    p5.doubleAttack *= 2;
                    this.this_0.frS2 = MathUtils.random(25, 75);
                }
            }
        });
        this.addActor(new ButtonActive(480.0f, 100.0f, 160.0f, 160.0f, this.btns, 13, 34) {
            final GameScreen this_0 = GameScreen.this;
            final MyGdxGame val_game = game;

            @Override
            void click() {
                if (this.this_0.rightTurn) {
                    if (this.this_0.p2.hp < this.this_0.p2.superHp && this.this_0.p2.mana >= 8) {
                        final PlayerState p2 = this.this_0.p2;
                        p2.mana -= 8;
                        final PlayerState p3 = this.this_0.p2;
                        p3.hp *= 2;
                        if (this.val_game.pref.getBoolean("sound")) {
                            this.val_game.hp.play();
                        }
                        this.this_0.frS1 = MathUtils.random(25, 75);
                    }
                } else if (this.this_0.p1.hp < this.this_0.p1.superHp && this.this_0.p1.mana >= 8) {
                    final PlayerState p4 = this.this_0.p1;
                    p4.mana -= 8;
                    final PlayerState p5 = this.this_0.p1;
                    p5.hp *= 2;
                    if (this.val_game.pref.getBoolean("sound")) {
                        this.val_game.hp.play();
                    }
                    this.this_0.frS2 = MathUtils.random(25, 75);
                }
            }
        });
        this.addActor(new ButtonActive(640.0f, 100.0f, 160.0f, 160.0f, this.btns, 15, 36) {
            final GameScreen this_0 = GameScreen.this;

            @Override
            void click() {
                if (this.this_0.rightTurn) {
                    if (this.this_0.p2.doubleAttack < 3 && this.this_0.p2.mana >= 14) {
                        final PlayerState p2 = this.this_0.p2;
                        p2.mana -= 14;
                        this.this_0.p2.doubleAttack = 3;
                        this.this_0.frS1 = MathUtils.random(25, 75);
                    }
                } else if (this.this_0.p1.doubleAttack < 3 && this.this_0.p1.mana >= 14) {
                    final PlayerState p3 = this.this_0.p1;
                    p3.mana -= 14;
                    this.this_0.p1.doubleAttack = 3;
                    this.this_0.frS2 = MathUtils.random(25, 75);
                }
            }
        });
        this.addActor(new ButtonActive(800.0f, 100.0f, 160.0f, 160.0f, this.btns, 16, 37) {
            final GameScreen this_0 = GameScreen.this;
            final MyGdxGame val_game = game;

            @Override
            void click() {
                if (this.this_0.rightTurn) {
                    if (this.this_0.p2.mana < this.this_0.p2.maxMana && this.this_0.p2.attack >= 5
                            && this.this_0.p2.hp >= 5) {
                        final PlayerState p2 = this.this_0.p2;
                        p2.hp -= 5;
                        final PlayerState p3 = this.this_0.p2;
                        p3.attack -= 5;
                        final PlayerState p4 = this.this_0.p2;
                        p4.mana += 10;
                        if (this.val_game.pref.getBoolean("sound")) {
                            this.val_game.hp.play();
                        }
                        this.this_0.frS1 = MathUtils.random(25, 75);
                    }
                } else if (this.this_0.p1.mana < this.this_0.p1.maxMana
                        && this.this_0.p1.attack >= 5 && this.this_0.p1.hp >= 5) {
                    final PlayerState p5 = this.this_0.p1;
                    p5.hp -= 5;
                    final PlayerState p6 = this.this_0.p1;
                    p6.attack -= 5;
                    final PlayerState p7 = this.this_0.p1;
                    p7.mana += 10;
                    if (this.val_game.pref.getBoolean("sound")) {
                        this.val_game.hp.play();
                    }
                    this.this_0.frS2 = MathUtils.random(25, 75);
                }
            }
        });
        this.addActor(new ButtonActive(960.0f, 100.0f, 160.0f, 160.0f, this.btns, 17, 38) {
            final GameScreen this_0 = GameScreen.this;
            final MyGdxGame val_game = game;

            @Override
            void click() {
                if (this.this_0.rightTurn) {
                    if ((this.this_0.p2.hp < this.this_0.p2.superHp
                            || this.this_0.p2.forHalf < this.this_0.p2.maxHalf)
                            && this.this_0.p2.mana >= 6) {
                        final PlayerState p2 = this.this_0.p2;
                        p2.mana -= 6;
                        if (this.this_0.p2.hp < this.this_0.p2.superHp) {
                            final PlayerState p3 = this.this_0.p2;
                            p3.hp += 5;
                        }
                        if (this.this_0.p2.forHalf < this.this_0.p2.maxHalf) {
                            final PlayerState p4 = this.this_0.p2;
                            p4.forHalf *= 2;
                        }
                        if (this.this_0.p2.forHalf < this.this_0.p2.maxHalf) {
                            final PlayerState p5 = this.this_0.p2;
                            p5.forHalf *= 2;
                        }
                        if (this.val_game.pref.getBoolean("sound")) {
                            this.val_game.hp.play();
                        }
                        this.this_0.frS1 = MathUtils.random(25, 75);
                    }
                } else if ((this.this_0.p1.hp < this.this_0.p1.superHp
                        || this.this_0.p1.forHalf < this.this_0.p1.maxHalf)
                        && this.this_0.p1.mana >= 6) {
                    final PlayerState p6 = this.this_0.p1;
                    p6.mana -= 6;
                    if (this.this_0.p1.hp < this.this_0.p1.superHp) {
                        final PlayerState p7 = this.this_0.p1;
                        p7.hp += 5;
                    }
                    if (this.this_0.p1.forHalf < this.this_0.p1.maxHalf) {
                        final PlayerState p8 = this.this_0.p1;
                        p8.forHalf *= 2;
                    }
                    if (this.this_0.p1.forHalf < this.this_0.p1.maxHalf) {
                        final PlayerState p9 = this.this_0.p1;
                        p9.forHalf *= 2;
                    }
                    if (this.val_game.pref.getBoolean("sound")) {
                        this.val_game.hp.play();
                    }
                    this.this_0.frS2 = MathUtils.random(25, 75);
                }
            }
        });
        this.addActor(new ButtonActive(1120.0f, 100.0f, 160.0f, 160.0f, this.btns, 18, 39) {
            final GameScreen this_0 = GameScreen.this;
            final MyGdxGame val_game = game;

            @Override
            void click() {
                if (this.this_0.rightTurn) {
                    if ((this.this_0.p2.attack < this.this_0.p2.maxAttack
                            || this.this_0.p2.hp < this.this_0.p2.superHp
                            || this.this_0.p2.forHalf < this.this_0.p2.maxHalf)
                            && this.this_0.p2.mana >= 20) {
                        final PlayerState p2 = this.this_0.p2;
                        p2.mana -= 20;
                        if (this.this_0.p2.attack < this.this_0.p2.maxAttack) {
                            final PlayerState p3 = this.this_0.p2;
                            p3.attack += 5;
                        }
                        if (this.this_0.p2.hp < this.this_0.p2.superHp) {
                            final PlayerState p4 = this.this_0.p2;
                            p4.hp += 3;
                        }
                        if (this.this_0.p2.doubleAttack < 2) {
                            this.this_0.p2.doubleAttack = 2;
                        }
                        if (this.this_0.p2.forHalf < this.this_0.p2.maxHalf) {
                            final PlayerState p5 = this.this_0.p2;
                            p5.forHalf *= 2;
                        }
                        final PlayerState p6 = this.this_0.p1;
                        p6.hp -= this.this_0.p2.doubleAttack * this.this_0.p2.attack
                                / this.this_0.p1.forHalf;
                        if (this.val_game.pref.getBoolean("sound")) {
                            this.val_game.hp.play();
                            this.val_game.atk.play();
                        }
                        this.this_0.frS1 = MathUtils.random(25, 75);
                    }
                } else if ((this.this_0.p1.attack < this.this_0.p1.maxAttack
                        || this.this_0.p1.hp < this.this_0.p1.superHp
                        || this.this_0.p1.forHalf < this.this_0.p1.maxHalf)
                        && this.this_0.p1.mana >= 20) {
                    final PlayerState p7 = this.this_0.p1;
                    p7.mana -= 20;
                    if (this.this_0.p1.attack < this.this_0.p1.maxAttack) {
                        final PlayerState p8 = this.this_0.p1;
                        p8.attack += 5;
                    }
                    if (this.this_0.p1.hp < this.this_0.p1.superHp) {
                        final PlayerState p9 = this.this_0.p1;
                        p9.hp += 3;
                    }
                    if (this.this_0.p1.doubleAttack < 2) {
                        this.this_0.p1.doubleAttack = 2;
                    }
                    if (this.this_0.p1.forHalf < this.this_0.p1.maxHalf) {
                        final PlayerState p10 = this.this_0.p1;
                        p10.forHalf *= 2;
                    }
                    final PlayerState p11 = this.this_0.p2;
                    p11.hp -= this.this_0.p1.doubleAttack * this.this_0.p1.attack
                            / this.this_0.p2.forHalf;
                    if (this.val_game.pref.getBoolean("sound")) {
                        this.val_game.hp.play();
                        this.val_game.atk.play();
                    }
                    this.this_0.frS2 = MathUtils.random(25, 75);
                }
            }
        });
        this.addActor(new ButtonActive(1280.0f, 100.0f, 160.0f, 160.0f, this.btns, 4, 25) {
            final GameScreen this_0 = GameScreen.this;
            final MyGdxGame val_game = game;

            @Override
            void click() {
                if (this.this_0.rightTurn) {
                    if (this.this_0.p2.mana >= 10 && this.this_0.p2.bMana < 10) {
                        final PlayerState p2 = this.this_0.p2;
                        ++p2.bMana;
                        final PlayerState p3 = this.this_0.p1;
                        p3.mana -= 10;
                        if (this.val_game.pref.getBoolean("sound")) {
                            this.val_game.hp.play();
                        }
                    }
                } else if (this.this_0.p1.mana >= 10 && this.this_0.p1.bMana < 10) {
                    final PlayerState p4 = this.this_0.p1;
                    ++p4.bMana;
                    final PlayerState p5 = this.this_0.p1;
                    p5.mana -= 10;
                    if (this.val_game.pref.getBoolean("sound")) {
                        this.val_game.hp.play();
                    }
                }
            }
        });
        this.addActor(new ButtonActive(1440.0f, 100.0f, 160.0f, 160.0f, this.btns, 9, 30) {
            final GameScreen this_0 = GameScreen.this;
            final MyGdxGame val_game = game;

            @Override
            void click() {
                if (this.this_0.rightTurn) {
                    if (this.this_0.p2.bMana >= 2) {
                        final PlayerState p2 = this.this_0.p2;
                        p2.superHp += 3;
                        final PlayerState p3 = this.this_0.p2;
                        p3.bMana -= 2;
                        if (this.val_game.pref.getBoolean("sound")) {
                            this.val_game.hp.play();
                        }
                    }
                } else if (this.this_0.p1.bMana >= 2) {
                    final PlayerState p4 = this.this_0.p2;
                    p4.bMana -= 2;
                    final PlayerState p5 = this.this_0.p2;
                    p5.superHp += 3;
                    if (this.val_game.pref.getBoolean("sound")) {
                        this.val_game.hp.play();
                    }
                }
            }
        });
        this.addActor(new ButtonActive(1280.0f, 310.0f, 160.0f, 160.0f, this.btns, 14, 35) {
            final GameScreen this_0 = GameScreen.this;
            final MyGdxGame val_game = game;

            @Override
            void click() {
                if (this.this_0.rightTurn) {
                    if (this.this_0.p2.bMana >= 3) {
                        final PlayerState p2 = this.this_0.p2;
                        p2.bMana -= 3;
                        final PlayerState p3 = this.this_0.p2;
                        p3.superAtk += 2;
                        if (this.val_game.pref.getBoolean("sound")) {
                            this.val_game.hp.play();
                        }
                    }
                } else if (this.this_0.p1.bMana >= 3) {
                    final PlayerState p4 = this.this_0.p1;
                    p4.bMana -= 3;
                    final PlayerState p5 = this.this_0.p1;
                    p5.superAtk += 2;
                    if (this.val_game.pref.getBoolean("sound")) {
                        this.val_game.hp.play();
                    }
                }
            }
        });
        this.addActor(new ButtonActive(1440.0f, 310.0f, 160.0f, 160.0f, this.btns, 19, 40) {
            final GameScreen this_0 = GameScreen.this;
            final MyGdxGame val_game = game;

            @Override
            void click() {
                if (this.this_0.rightTurn) {
                    if (this.this_0.p2.bMana >= 4) {
                        final PlayerState p2 = this.this_0.p2;
                        p2.bMana -= 4;
                        final PlayerState p3 = this.this_0.p2;
                        ++p3.superMana;
                        if (this.val_game.pref.getBoolean("sound")) {
                            this.val_game.hp.play();
                        }
                    }
                } else if (this.this_0.p1.bMana >= 4) {
                    final PlayerState p4 = this.this_0.p1;
                    p4.bMana -= 4;
                    final PlayerState p5 = this.this_0.p1;
                    ++p5.superMana;
                    if (this.val_game.pref.getBoolean("sound")) {
                        this.val_game.hp.play();
                    }
                }
            }
        });
        this.font = game.font;
        this.addActor(this.p1);
        this.addActor(this.p2);
        this.play1 = new TextureRegion(game.humans, 0, 0, 128, 256);
        this.play2 = new TextureRegion(game.humans, 128, 0, 128, 256);
        this.play1M = new TextureRegion(game.humans, 256, 0, 128, 256);
        this.play2M = new TextureRegion(game.humans, 384, 0, 128, 256);
        this.play1L = new TextureRegion(game.humans, 0, 256, 128, 256);
        this.play2L = new TextureRegion(game.humans, 128, 256, 128, 256);
        this.play1LM = new TextureRegion(game.humans, 256, 256, 128, 256);
        this.play2LM = new TextureRegion(game.humans, 384, 256, 128, 256);
        this.play1S = new TextureRegion(game.humans, 0, 512, 128, 256);
        this.play2S = new TextureRegion(game.humans, 128, 512, 128, 256);
        this.play1SM = new TextureRegion(game.humans, 256, 512, 128, 256);
        this.play2SM = new TextureRegion(game.humans, 384, 512, 128, 256);
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    void gameOver(final boolean b) {
        this.game.setScreen(new GameOverScreen(this.game, b));
        this.ingame = false;
        this.dispose();
    }

    @Override
    public void hide() {}

    @Override
    public boolean keyDown(final int n) {
        if (n == 4 || n == 131) {
            this.game.setScreen(new MainMenuScreen(this.game));
            this.dispose();
        }
        return false;
    }

    @Override
    public void pause() {}

    @Override
    public void render(final float n) {
        Gdx.gl.glClearColor(0.0f, 86.0f, 1.0f, 1.0f);
        Gdx.gl.glClear(16384);
        this.renderer.begin(ShapeRenderer.ShapeType.Filled);
        this.renderer.setColor(0.0f, 0.0f, 0.0f, 1.0f);
        this.renderer.rect(0.0f, 600.0f, 1600.0f, 10.0f);
        this.renderer.setColor(0.0f, 1.0f, 0.0f, 1.0f);
        this.renderer.rect(0.0f, 0.0f, 1600.0f, 600.0f);
        this.renderer.end();
        super.act();
        super.draw();
        this.game.batch.begin();
        this.font.draw(this.game.batch, "3 M", 25.0f, 310.0f);
        this.font.draw(this.game.batch, "3 M", 185.0f, 310.0f);
        this.font.draw(this.game.batch, "2 M", 345.0f, 310.0f);
        this.font.draw(this.game.batch, "2 M", 505.0f, 310.0f);
        this.font.draw(this.game.batch, "2 HP", 650.0f, 310.0f);
        this.font.draw(this.game.batch, "5 M", 825.0f, 310.0f);
        this.font.draw(this.game.batch, "4 M", 985.0f, 310.0f);
        this.font.draw(this.game.batch, "10 M", 1130.0f, 310.0f);
        this.font.draw(this.game.batch, "3 M", 1290.0f, 310.0f);
        this.font.draw(this.game.batch, "4 BM", 1450.0f, 310.0f);
        this.font.draw(this.game.batch, "6 M", 25.0f, 100.0f);
        this.font.draw(this.game.batch, "6 M", 185.0f, 100.0f);
        this.font.draw(this.game.batch, "6 M", 345.0f, 100.0f);
        this.font.draw(this.game.batch, "8 M", 505.0f, 100.0f);
        this.font.draw(this.game.batch, "14 M", 650.0f, 100.0f);
        this.font.draw(this.game.batch, "5 HP", 810.0f, 100.0f);
        this.font.draw(this.game.batch, "5 ATK", 790.0f, 50.0f);
        this.font.draw(this.game.batch, "6 M", 985.0f, 100.0f);
        this.font.draw(this.game.batch, "20 M", 1130.0f, 100.0f);
        this.font.draw(this.game.batch, "10 M", 1290.0f, 100.0f);
        this.font.draw(this.game.batch, "2 BM", 1450.0f, 100.0f);
        if (this.frames1 == 0 && MathUtils.random(1, 100) == 100) {
            this.frames1 = MathUtils.random(10, 100);
        }
        if (this.frames2 == 0 && MathUtils.random(1, 100) == 100) {
            this.frames2 = MathUtils.random(10, 100);
        }
        if (this.frames1 > 0) {
            --this.frames1;
            if (this.p1.hp < 5 || (this.p1.attack < 2 && this.p1.maxMana > 3)
                    || this.p2.attack > this.p1.hp) {
                final SpriteBatch batch = this.game.batch;
                TextureRegion textureRegion;
                if (this.frS2 > 0) {
                    textureRegion = this.play1SM;
                } else {
                    textureRegion = this.play1LM;
                }
                batch.draw(textureRegion, 475.0f, 500.0f, 200.0f, 400.0f);
            } else {
                final SpriteBatch batch2 = this.game.batch;
                TextureRegion textureRegion2;
                if (this.frS2 > 0) {
                    textureRegion2 = this.play1SM;
                } else {
                    textureRegion2 = this.play1M;
                }
                batch2.draw(textureRegion2, 475.0f, 500.0f, 200.0f, 400.0f);
            }
        } else if (this.p1.hp < 5 || (this.p1.attack < 2 && this.p1.maxMana > 3)
                || this.p2.attack > this.p1.hp) {
            final SpriteBatch batch3 = this.game.batch;
            TextureRegion textureRegion3;
            if (this.frS2 > 0) {
                textureRegion3 = this.play1S;
            } else {
                textureRegion3 = this.play1L;
            }
            batch3.draw(textureRegion3, 475.0f, 500.0f, 200.0f, 400.0f);
        } else {
            final SpriteBatch batch4 = this.game.batch;
            TextureRegion textureRegion4;
            if (this.frS2 > 0) {
                textureRegion4 = this.play1S;
            } else {
                textureRegion4 = this.play1;
            }
            batch4.draw(textureRegion4, 475.0f, 500.0f, 200.0f, 400.0f);
        }
        if (this.frames2 > 0) {
            --this.frames2;
            if (this.p2.hp < 5 || (this.p2.attack < 2 && this.p2.maxMana > 3)
                    || this.p1.attack > this.p2.hp) {
                final SpriteBatch batch5 = this.game.batch;
                TextureRegion textureRegion5;
                if (this.frS1 > 0) {
                    textureRegion5 = this.play2SM;
                } else {
                    textureRegion5 = this.play2LM;
                }
                batch5.draw(textureRegion5, 1000.0f, 500.0f, 200.0f, 400.0f);
            } else {
                final SpriteBatch batch6 = this.game.batch;
                TextureRegion textureRegion6;
                if (this.frS1 > 0) {
                    textureRegion6 = this.play2SM;
                } else {
                    textureRegion6 = this.play2M;
                }
                batch6.draw(textureRegion6, 1000.0f, 500.0f, 200.0f, 400.0f);
            }
        } else if (this.p2.hp < 5 || (this.p2.attack < 2 && this.p2.maxMana > 3)
                || this.p1.attack > this.p2.hp) {
            final SpriteBatch batch7 = this.game.batch;
            TextureRegion textureRegion7;
            if (this.frS1 > 0) {
                textureRegion7 = this.play2S;
            } else {
                textureRegion7 = this.play2L;
            }
            batch7.draw(textureRegion7, 1000.0f, 500.0f, 200.0f, 400.0f);
        } else {
            final SpriteBatch batch8 = this.game.batch;
            TextureRegion textureRegion8;
            if (this.frS1 > 0) {
                textureRegion8 = this.play2S;
            } else {
                textureRegion8 = this.play2;
            }
            batch8.draw(textureRegion8, 1000.0f, 500.0f, 200.0f, 400.0f);
        }
        if (this.frS1 > 0) {
            --this.frS1;
        }
        if (this.frS2 > 0) {
            --this.frS2;
        }
        if (this.anim) {
            final long n2 = TimeUtils.nanoTime() - this.time;
            if (n2 <= 2000000000L) {
                this.game.batch.draw(this.btns[20], (float) (800L - 450L * n2 / 2000000000L),
                        (float) (450L - 450L * n2 / 2000000000L), (float) (900L * n2 / 2000000000L),
                        (float) (900L * n2 / 2000000000L));
            } else if (n2 > 4000000000L) {
                this.game.batch.draw(this.btns[20],
                        (float) (350L + 450L * (n2 - 4000000000L) / 2000000000L),
                        (float) (450L * (n2 - 4000000000L) / 2000000000L),
                        (float) (900L - 900L * (n2 - 4000000000L) / 2000000000L),
                        (float) (900L - 900L * (n2 - 4000000000L) / 2000000000L));
            } else {
                this.game.batch.draw(this.btns[20], 350.0f, 0.0f, 900.0f, 900.0f);
                if (this.soundPlus) {
                    this.game.hp.play();
                    this.soundPlus = false;
                }
            }
            if (n2 > 6000000000L) {
                Gdx.input.setInputProcessor(this);
                this.anim = false;
            }
        }
        this.game.batch.end();
    }

    @Override
    public void resize(final int n, final int n2) {}

    @Override
    public void resume() {}

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }
}
