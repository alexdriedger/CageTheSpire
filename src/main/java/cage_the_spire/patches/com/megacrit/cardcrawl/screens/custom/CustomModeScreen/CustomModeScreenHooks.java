package cage_the_spire.patches.com.megacrit.cardcrawl.screens.custom.CustomModeScreen;

import cage_the_spire.NicolasCageCustomMod;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import com.megacrit.cardcrawl.localization.RunModStrings;
import com.megacrit.cardcrawl.screens.custom.CustomMod;
import com.megacrit.cardcrawl.screens.custom.CustomModeScreen;
import org.lwjgl.Sys;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

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
//                l.add(new NicolasCageCustomMod());
                l.add(new CustomMod("Cage Cosmetic", "r", false));
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
                System.out.println("Could not initialize Nicolas Cage custom mod");
            }

            Map<String, RunModStrings> s = LocalizedStrings.mod;
            System.out.println("Printing mod strings");
            for (String k : s.keySet()) {
                System.out.println("Key: " + k);
                System.out.println("Value: ");
                System.out.println("NAME: " + s.get(k).NAME);
                System.out.println("DESCRIPTION: " + s.get(k).DESCRIPTION);
            }
        }
    }
}
