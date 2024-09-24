//
// Decompiled by Procyon v0.6.0
//

package com.dsgames.combat;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

class PlayerState extends Actor {
    int attack;
    int bMana;
    int doubleAttack;
    int forHalf;
    int hp;
    int mana;
    int maxAttack;
    int maxHalf;
    int maxMana;
    int maxMaxMana;
    private boolean right;
    private GameScreen scr;
    int superAtk;
    int superHp;
    int superMana;

    PlayerState(final GameScreen scr, final boolean right) {
        this.scr = scr;
        this.right = right;
        this.superHp = 60;
        this.superMana = 20;
        this.bMana = 0;
        this.superAtk = 30;
        this.maxHalf = 4;
        this.doubleAttack = 1;
        this.hp = 10;
        this.mana = 2;
        this.maxMana = 2;
        this.maxMaxMana = 10;
        this.maxAttack = 12;
        this.attack = 1;
        this.forHalf = 1;
    }

    @Override
    public void act(final float n) {
        if (this.hp <= 0 && this.scr.ingame) {
            this.scr.gameOver(this.right);
        }
        if (this.hp > this.superHp) {
            this.hp = this.superHp;
        }
        if (this.maxAttack > this.superAtk) {
            this.maxAttack = this.superAtk;
        }
        if (this.attack > this.maxAttack) {
            this.attack = this.maxAttack;
        }
        if (this.maxMaxMana > this.superMana) {
            this.maxMaxMana = this.superMana;
        }
        if (this.maxMana > this.maxMaxMana) {
            this.maxMana = this.maxMaxMana;
        }
        if (this.mana > this.maxMana) {
            this.maxMana = this.maxMaxMana;
            if (this.bMana > 10) {
                this.bMana = 10;
            }
        }
    }

    @Override
    public void draw(final Batch batch, final float n) {
        batch.end();
        if (this.right) {
            this.scr.renderer.begin(ShapeRenderer.ShapeType.Filled);
            this.scr.renderer.setColor(0.0f, 0.0f, 0.0f, 1.0f);
            this.scr.renderer.rect(1300.0f, 850.0f, 300.0f, 50.0f);
            this.scr.renderer.rect(1600.0f, 799.0f, -300.0f * this.maxMaxMana / this.superMana,
                    50.0f);
            this.scr.renderer.rect(1600.0f, 748.0f, -300.0f * this.maxAttack / this.superAtk,
                    50.0f);
            this.scr.renderer.rect(1300.0f, 697.0f, 300.0f, 50.0f);
            this.scr.renderer.setColor(0.0f, 1.0f, 0.0f, 1.0f);
            this.scr.renderer.rect(1600.0f, 850.0f, (float) (this.hp * -300 / this.superHp), 50.0f);
            this.scr.renderer.setColor(1.0f, 0.0f, 1.0f, 1.0f);
            this.scr.renderer.rect(1600.0f, 799.0f, -300.0f * this.maxMana / this.superMana, 50.0f);
            this.scr.renderer.setColor(0.0f, 0.0f, 1.0f, 1.0f);
            this.scr.renderer.rect(1600.0f, 799.0f, -300.0f * this.mana / this.superMana, 50.0f);
            this.scr.renderer.setColor(1.0f, 0.0f, 0.0f, 1.0f);
            this.scr.renderer.rect(1600.0f, 748.0f, -300.0f * this.attack / this.superAtk, 50.0f);
            this.scr.renderer.setColor(0.0f, 0.0f, 0.5f, 1.0f);
            this.scr.renderer.rect(1600.0f, 697.0f, -30.0f * this.bMana, 50.0f);
            this.scr.renderer.end();
        } else {
            this.scr.renderer.begin(ShapeRenderer.ShapeType.Filled);
            this.scr.renderer.setColor(0.0f, 0.0f, 0.0f, 1.0f);
            this.scr.renderer.rect(0.0f, 850.0f, 300.0f, 50.0f);
            this.scr.renderer.rect(0.0f, 799.0f, (float) (this.maxMaxMana * 300 / this.superMana),
                    50.0f);
            this.scr.renderer.rect(0.0f, 748.0f, (float) (this.maxAttack * 300 / this.superAtk),
                    50.0f);
            this.scr.renderer.rect(0.0f, 697.0f, 300.0f, 50.0f);
            this.scr.renderer.setColor(0.0f, 1.0f, 0.0f, 1.0f);
            this.scr.renderer.rect(0.0f, 850.0f, (float) (this.hp * 300 / this.superHp), 50.0f);
            this.scr.renderer.setColor(1.0f, 0.0f, 1.0f, 1.0f);
            this.scr.renderer.rect(0.0f, 799.0f, (float) (this.maxMana * 300 / this.superMana),
                    50.0f);
            this.scr.renderer.setColor(0.0f, 0.0f, 1.0f, 1.0f);
            this.scr.renderer.rect(0.0f, 799.0f, (float) (this.mana * 300 / this.superMana), 50.0f);
            this.scr.renderer.setColor(1.0f, 0.0f, 0.0f, 1.0f);
            this.scr.renderer.rect(0.0f, 748.0f, (float) (this.attack * 300 / this.superAtk),
                    50.0f);
            this.scr.renderer.setColor(0.0f, 0.0f, 0.5f, 1.0f);
            this.scr.renderer.rect(0.0f, 697.0f, 30.0f * this.bMana, 50.0f);
            this.scr.renderer.end();
        }
        batch.begin();
        if (this.right) {
            batch.draw(this.scr.types[0], 1250.0f, 850.0f, 50.0f, 50.0f);
            batch.draw(this.scr.types[1], 1550.0f - this.maxMaxMana * 300.0f / this.superMana,
                    799.0f, 50.0f, 50.0f);
            batch.draw(this.scr.types[2], 1550.0f - this.maxAttack * 300.0f / this.superAtk, 749.0f,
                    50.0f, 50.0f);
            batch.draw(this.scr.types[3], 1250.0f, 697.0f, 50.0f, 50.0f);
        } else {
            batch.draw(this.scr.types[0], 300.0f, 850.0f, 50.0f, 50.0f);
            batch.draw(this.scr.types[1], this.maxMaxMana * 300.0f / this.superMana, 799.0f, 50.0f,
                    50.0f);
            batch.draw(this.scr.types[2], this.maxAttack * 300.0f / this.superAtk, 749.0f, 50.0f,
                    50.0f);
            batch.draw(this.scr.types[3], 300.0f, 697.0f, 50.0f, 50.0f);
        }
        if (this.right) {
            this.scr.font.draw(batch, String.valueOf(this.hp), 1520.0f, 900.0f);
            this.scr.font.draw(batch, String.valueOf(this.mana), 1520.0f, 849.0f);
            this.scr.font.draw(batch, String.valueOf(this.attack), 1520.0f, 798.0f);
            this.scr.font.draw(batch, String.valueOf(this.bMana), 1520.0f, 747.0f);
        } else {
            this.scr.font.draw(batch, String.valueOf(this.hp), 0.0f, 900.0f);
            this.scr.font.draw(batch, String.valueOf(this.mana), 0.0f, 849.0f);
            this.scr.font.draw(batch, String.valueOf(this.attack), 0.0f, 798.0f);
            this.scr.font.draw(batch, String.valueOf(this.bMana), 0.0f, 747.0f);
        }
    }
}
