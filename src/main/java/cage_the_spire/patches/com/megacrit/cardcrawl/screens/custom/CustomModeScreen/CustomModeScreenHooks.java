package cage_the_spire.patches.com.megacrit.cardcrawl.screens.custom.CustomModeScreen;

import cage_the_spire.NicolasCageCustomMod;
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
                l.add(new NicolasCageCustomMod());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
                System.out.println("Could not initialize Nicolas Cage custom mod");
            }
        }
    }
}
