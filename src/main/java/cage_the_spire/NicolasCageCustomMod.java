package cage_the_spire;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.screens.custom.CustomMod;

public class NicolasCageCustomMod extends CustomMod {

    private static final String MOD_ID = "NICK_CAGE_MOD";
    private static final String MOD_NAME = "Cage The Spire";
    private static final String MOD_DESCRIPTION = "All cards artwork, title and description are turned into Nicolas Cage";

    public NicolasCageCustomMod() {
        // Calling this super is sketch. Work around for having to call super
        super("One Hit Wonder", "r", false);

        // Actual values that need to be set
        this.color = "r";
        this.ID = MOD_ID;
        this.name = MOD_NAME;
        this.description = MOD_DESCRIPTION;
        this.hb = new Hitbox(1000.0F * Settings.scale, 90.0F * Settings.scale);
        this.isDailyMod = false;
    }
}
