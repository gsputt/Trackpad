package Trackpad.relics;

import Trackpad.trackpad;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

public class Trackpad extends CustomRelic {

    // ID, images, text.
    public static final String ID = trackpad.makeID("Trackpad");
    public static final String IMG = trackpad.makePath(trackpad.TRACKPAD);
    public static final String OUTLINE = trackpad.makePath(trackpad.TRACKPAD_OUTLINE);


    public Trackpad() {
        super(ID, new Texture(IMG), new Texture(OUTLINE), RelicTier.BOSS, LandingSound.CLINK);
    }

    @Override
    public void update() {
        super.update();
        if (AbstractDungeon.player != null && AbstractDungeon.currMapNode != null && AbstractDungeon.getCurrRoom() != null && Display.isActive() && AbstractDungeon.screen != null && AbstractDungeon.screen != AbstractDungeon.CurrentScreen.SETTINGS && AbstractDungeon.screen != AbstractDungeon.CurrentScreen.INPUT_SETTINGS && AbstractDungeon.player.hasRelic(ID)) {
            Mouse.setCursorPosition(Mouse.getX() + (int)(MathUtils.random(-10, 10) * Settings.scale), Mouse.getY() + (int)(MathUtils.random(-10, 10) * Settings.scale));
        }

    }

    @Override
    public void onEquip() {
        ++AbstractDungeon.player.energy.energyMaster;
    }

    @Override
    public void onUnequip() {
        --AbstractDungeon.player.energy.energyMaster;
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];

    }

    // Which relic to return on making a copy of this relic.
    @Override
    public AbstractRelic makeCopy() {
        return new Trackpad();
    }
}
