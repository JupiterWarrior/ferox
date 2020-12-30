package us.ferox.client.impl.gui.component;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import us.ferox.client.api.module.Module;
import us.ferox.client.api.util.font.FontUtil;
import us.ferox.client.impl.gui.Component;

import java.awt.*;
import java.util.ArrayList;

public class ModuleButton extends Component {
    private ArrayList<Component> subcomponents;
    public Module mod;
    public Panel parent;
    public int offset;
    private boolean open;
    private boolean hovered;

    public ModuleButton(Module mod, Panel parent, int offset) {
        this.subcomponents = new ArrayList<>();
        this.mod = mod;
        this.parent = parent;
        this.offset = offset;
        this.open = false;
        this.hovered = false;
    }

    @Override
    public void setOff(final int newOff) {
        offset = newOff;

        int opY = this.offset + 16;

        for (Component component : this.subcomponents) {
            component.setOff(opY);
            opY += 16;
        }
    }

    @Override
    public void renderComponent() {
        Gui.drawRect(parent.getX(), parent.getY() + offset, parent.getX() + parent.getWidth(), parent.getY() + 16 + offset, mod.isEnabled() ? new Color(10, 10, 10, 10).getRGB() : new Color(100, 100, 100, 100).getRGB());

        FontUtil.drawText(mod.getName(), parent.getX() + 2, parent.getY() + offset + 2, -1);

        if (subcomponents.size() > 1) {
            FontUtil.drawText("...", parent.getX() + parent.getWidth() - 10, (parent.getY() + offset + 2), -1);
        }

        if (hovered == true) {
            FontUtil.drawText(mod.getDescription(), 2, (new ScaledResolution(mc).getScaledHeight() - FontUtil.getStringHeight(mod.getDescription()) - 2), -1);
        }

        if (open && !subcomponents.isEmpty()) {
            for (Component comp : subcomponents) {
                comp.renderComponent();
            }
        }
    }

    @Override
    public void closeAllSub() {
        this.open = false;
    }

    @Override
    public int getHeight() {
        if (open) {
            return 16 * (subcomponents.size() + 1);
        }

        return 16;
    }

    @Override
    public void updateComponent(int mouseX, int mouseY) {
        hovered = isMouseOnButton(mouseX, mouseY);

        if (!subcomponents.isEmpty()) {
            for (Component comp : subcomponents) {
                comp.updateComponent(mouseX, mouseY);
            }
        }
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int button) {
        if (isMouseOnButton(mouseX, mouseY) && button == 0) {
            mod.toggle();
        }

        if (isMouseOnButton(mouseX, mouseY) && button == 1) {
            if (!isOpen()) {
                parent.closeAllSetting();
                setOpen(true);
            } else {
                setOpen(false);
            }
            parent.refresh();
        }

        for (Component comp : subcomponents) {
            comp.mouseClicked(mouseX, mouseY, button);
        }
    }

    @Override
    public void keyTyped(char typedChar, int key) {
        for (Component comp : subcomponents) {
            comp.keyTyped(typedChar, key);
        }
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int mouseButton) {
        for (Component comp : this.subcomponents) {
            comp.mouseReleased(mouseX, mouseY, mouseButton);
        }
    }

    public boolean isMouseOnButton(int x, int y) {
        return (x > parent.getX() && x < parent.getX() + 100 && y > this.parent.getY() + this.offset && y < this.parent.getY() + 16 + this.offset);
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}
