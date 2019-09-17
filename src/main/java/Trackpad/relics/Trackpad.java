package Trackpad.relics;

import Trackpad.trackpad;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
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

    private float time = 0.0F;
    private float targetX = 0.0F;
    private float targetY = 0.0F;
    private float currentX = 0.0F;
    private float currentY = 0.0F;

    private float timeLimit = 0.0F;
    private boolean getCurrentPos = true;


    public Trackpad() {
        super(ID, new Texture(IMG), new Texture(OUTLINE), RelicTier.BOSS, LandingSound.CLINK);
    }

    @Override
    public void update() {
        super.update();
        if (AbstractDungeon.player != null && AbstractDungeon.currMapNode != null && AbstractDungeon.getCurrRoom() != null && Display.isActive() && AbstractDungeon.screen != null && AbstractDungeon.screen != AbstractDungeon.CurrentScreen.SETTINGS && AbstractDungeon.screen != AbstractDungeon.CurrentScreen.INPUT_SETTINGS && AbstractDungeon.player.hasRelic(ID)) {
            float moveToX = Mouse.getX();
            float moveToY = Mouse.getY();
            boolean move = false;
            if(time == 0.0F)
            {
                targetX = MathUtils.random(-400, 400);
                targetY = MathUtils.random(-400, 400);
                timeLimit = MathUtils.random(3.0F, 5.0F);
            }
            time += Gdx.graphics.getRawDeltaTime();
            if(time > timeLimit)
            {
                if(getCurrentPos)
                {
                    getCurrentPos = false;
                    currentX = Mouse.getX();
                    currentY = Mouse.getY();
                }
                moveToX = Interpolation.linear.apply(currentX, currentX + (targetX * Settings.scale), (time - timeLimit) / 0.5F);
                moveToY = Interpolation.linear.apply(currentY, currentY + (targetY * Settings.scale), (time - timeLimit) / 0.5F);
                move = true;
            }
            if(time > timeLimit + 0.5)
            {
                move = false;
                getCurrentPos = true;
                time = 0.0F;
            }
            if(move) {
                Mouse.setCursorPosition((int) moveToX, (int) moveToY);
            }
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
