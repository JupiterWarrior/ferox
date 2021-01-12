package us.ferox.client.impl.gui.console;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Keyboard;
import us.ferox.client.api.util.colour.RainbowUtil;
import us.ferox.client.api.util.font.FontUtil;
import us.ferox.client.impl.gui.Component;
import us.ferox.client.impl.gui.click.component.Panel;
import us.ferox.client.impl.gui.console.component.InputComponent;

import java.awt.*;
import java.util.ArrayList;

public class Console extends GuiScreen {
    public ArrayList<Component> components;
    private int x;
    private int y;
    private int dragX;
    private int dragY;
    private int width;
    private int height;
    private boolean open;
    private boolean dragging;

    private InputComponent inputComponent;

    public Console() {
        this.components = new ArrayList<>();
        this.x = 2;
        this.y = 2;
        this.dragX = 0;
        this.dragY = 0;
        this.width = 300;
        this.height = 25;
        this.open = true;
        this.dragging = false;

        this.inputComponent = new InputComponent(x, y + height, width, height);
        this.components.add(inputComponent);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        if (dragging) {
            x = mouseX - dragX;
            y = mouseY - dragY;
        }

        inputComponent.setX(x);
        inputComponent.setY(y + height);

        Gui.drawRect(x, y, x + width, y + height, RainbowUtil.getRainbow().getRGB());
        Gui.drawRect(x, y, x + width, y + height, new Color(50, 50, 50, 150).getRGB());

        FontUtil.drawText("Ferox Console", x + 2 + width / 2 - FontUtil.getStringWidth("Ferox Console") / 2, y + height / 2 - FontUtil.getStringHeight("Ferox Console") / 2, -1);

        for (Component component : components) {
            if (open) {
                component.renderComponent();
            }
        }
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (isWithinHeader(mouseX, mouseY) && mouseButton == 0) {
            dragging = true;
            dragX = mouseX - x;
            dragY = mouseY - y;
        }

        if (isWithinHeader(mouseX, mouseY) && mouseButton == 1) {
            open = !open;
        }

        for (Component component : components) {
            component.mouseClicked(mouseX, mouseY, mouseButton);
        }
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) {
        if (keyCode == Keyboard.KEY_ESCAPE) {
            mc.displayGuiScreen(null);
        }

        for (Component component : components) {
            component.keyTyped(typedChar, keyCode);
        }
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {
        dragging = false;

        for (Component component : components) {
            component.mouseReleased(mouseX, mouseY, state);
        }
    }

    public boolean isWithinHeader(int x, int y) {
        return (x >= this.x && x <= this.x + this.width && y >= this.y && y <= this.y + this.height);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
