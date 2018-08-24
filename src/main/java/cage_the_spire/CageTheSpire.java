package cage_the_spire;

import basemod.BaseMod;
import basemod.interfaces.EditStringsSubscriber;
import com.badlogic.gdx.Gdx;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.localization.RunModStrings;

import java.nio.charset.StandardCharsets;

@SpireInitializer
public class CageTheSpire implements EditStringsSubscriber {

    public CageTheSpire() {
        BaseMod.subscribe(this);
    }

    public static void initialize() {
        new CageTheSpire();
    }

    @Override
    public void receiveEditStrings() {
        String modString = Gdx.files.internal("localization/CageTheSpire-run_mods.json").readString(
                String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(RunModStrings.class, modString);
    }
}
