package cage_the_spire.patches.com.megacrit.cardcrawl.screens.custom.CustomModeScreen;

import cage_the_spire.CageTheSpire;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.screens.custom.CustomMod;
import com.megacrit.cardcrawl.screens.custom.CustomModeScreen;

import java.lang.reflect.Field;
import java.util.List;

public class CustomModeScreenHooks {

    @SpirePatch(
            cls = "com.megacrit.cardcrawl.screens.custom.CustomModeScreen",
            method = "initializeMods"
    )
    public static class PostInitializeModsHook {
        public static void Postfix(CustomModeScreen _instance) {

            try {
                Field modList = CustomModeScreen.class.getDeclaredField("modList");
                modList.setAccessible(true);
                List<CustomMod> l = (List<CustomMod>) modList.get(_instance);
                l.add(0, new CustomMod(CageTheSpire.CAGE_COSMETIC_ID, "b", true));
                l.add(1, new CustomMod(CageTheSpire.CAGE_FULL_EFFECT_ID, "r", true));
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
                System.out.println("Could not initialize Nicolas Cage custom mod");
            }
        }
    }
}
