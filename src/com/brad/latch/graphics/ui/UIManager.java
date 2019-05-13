package com.brad.latch.graphics.ui;

import com.brad.latch.events.Event;
import com.brad.latch.events.EventDispatcher;
import com.brad.latch.events.types.MouseButtonEvent;
import com.brad.latch.events.types.MousePressedEvent;
import com.brad.latch.events.types.MouseReleasedEvent;
import com.brad.latch.graphics.layers.Layer;
import com.brad.latch.level.Level;
import com.brad.latch.util.MathUtils;
import com.brad.latch.util.Vector2i;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

/**
 * Manager class for all UIPanels.
 * A panel must be added to this manager for
 * it to be updated and rendered. Also, any subclasses
 * of components must be added to panels to be updated
 * and rendered.
 */
public class UIManager extends Layer implements EventListener {

    private List<UIPanel> panels = new ArrayList<>();
    private List<UIComponent> components = new ArrayList<>();
    protected Level level;

    public UIManager(Level level) {
        init(level);
    }

    public void init(Level level) {
        this.level = level;
    }

    /**
     * Adds a panel to the UIManager.
     * @param panel panel to add.
     */
    public void addPanel(UIPanel panel) {
        panels.add(panel);
    }

    public void addComponent(UIComponent component) {
        components.add(component);
    }

    public void update() {
        for (UIPanel panel : panels) {
            panel.update();
        }
        for (UIComponent component : components) {
            component.update();
        }
    }

    public void render(Graphics g) {
        for (UIPanel panel : panels) {
            panel.render(g);
        }
        for (UIComponent component : components) {
            component.render(g);
        }
    }

    @Override
    @SuppressWarnings("Duplicates")
    public void onEvent(Event event) {
        for (UIPanel panel : panels) {
            panel.update();
        }
        for (UIComponent component : components) {
            component.update();
        }
        EventDispatcher dispatcher = new EventDispatcher(event);
        dispatcher.dispatch(Event.Type.MOUSE_PRESSED, event1 -> onMousePressed((MousePressedEvent)event1));
        dispatcher.dispatch(Event.Type.MOUSE_RELEASED, event1 -> onMouseReleased((MouseReleasedEvent)event1));
    }

    public boolean onMousePressed(MousePressedEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1 && MouseButtonInAnyPanel(e)) {
            return true;
        }
        return false;
    }

    public boolean onMouseReleased(MouseReleasedEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1 && MouseButtonInAnyPanel(e)) {
            level.getClientPlayer().setShooting(false);
            return true;
        }
        return false;
    }

    private boolean MouseButtonInAnyPanel(MouseButtonEvent e) {
        Vector2i mousePosition = new Vector2i(e.getX(), e.getY());
        boolean result = false;
        for (UIPanel panel : panels) {
            Rectangle panelRect = new Rectangle(panel.position.x, panel.position.y, panel.size.x, panel.size.y);
            if (MathUtils.containsPoint(mousePosition, panelRect)) {
                result = true;
            }
        }
        return result;
    }

}
