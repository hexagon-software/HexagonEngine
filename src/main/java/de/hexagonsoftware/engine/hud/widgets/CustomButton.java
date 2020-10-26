package de.hexagonsoftware.engine.hud.widgets;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import de.hexagonsoftware.engine.HexagonEngine;

public class CustomButton extends HUDWidget {
	private RenderHandler renderHandler;
	private ActionHandler actionHandler;
	public boolean hovered;
	
	public CustomButton(int x, int y) {
		super(x, y);
	}

	public CustomButton(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	public CustomButton(int x, int y, RenderHandler renderHandler, ActionHandler actionHandler) {
		super(x, y);
	}
	
	public CustomButton(int x, int y, int width, int height, RenderHandler renderHandler, ActionHandler actionHandler) {
		super(x, y, width, height);
	}
	
	@Override
	public void render(Graphics g) {
		renderHandler.render(g);
	}
	
	@Override
	public void update() {
		Rectangle buttonBounds = new Rectangle(x, y, width, height);
		Point     hoverPoint   = new Point(HexagonEngine.HE_MOUSE_INPUT.getLastMouseEvent().getX(), HexagonEngine.HE_MOUSE_INPUT.getLastMouseEvent().getY());
		
		if (buttonBounds.contains(hoverPoint)) {
			hovered = true;
		} else {
			hovered = false;
		}
		
		if (HexagonEngine.HE_MOUSE_INPUT.wasButtonClicked(1)) {
			Rectangle clickBounds  = new Rectangle(HexagonEngine.HE_MOUSE_INPUT.lastMouseEvent.getX(), HexagonEngine.HE_MOUSE_INPUT.lastMouseEvent.getY(), 1, 1);
			
			if (clickBounds.intersects(buttonBounds)) {
				actionHandler.actionPerformed();
			}
		}
	}
}