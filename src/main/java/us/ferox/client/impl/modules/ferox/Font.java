package us.ferox.client.impl.modules.ferox;

import us.ferox.client.api.module.Category;
import us.ferox.client.api.module.Module;
import us.ferox.client.api.module.ModuleInfo;
import us.ferox.client.api.setting.Setting;

@ModuleInfo(name = "Font", description = "Changes the font that Ferox uses", category = Category.FEROX)
public class Font extends Module {
    public static Setting<Enum> font = new Setting("Font", Fonts.Lato);
    public static Setting<Boolean> shadow = new Setting("Shadow", true);
    public static Setting<Boolean> lowercase = new Setting("Lowercase", false);

    public Font() {
        this.addSetting(font);
        this.addSetting(shadow);
        this.addSetting(lowercase);

        this.setEnabled(true);
    }

    @Override
    public void onDisable() {
        this.setEnabled(true);
    }

    public enum Fonts {
        Lato,
        Ubuntu,
        Verdana,
        Comfortaa,
        Subtitle,
        Minecraft
    }
}
