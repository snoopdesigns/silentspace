package org.snoopdesigns.silentspace.core.mainmenu;

import org.snoopdesigns.silentspace.core.screens.GameScreen;
import org.snoopdesigns.silentspace.core.screens.Screen;

public class MainMenuMapper {

    public static MenuItem mapMenuItem(String text, final Screen screen) {
        MenuItem item;
        if(text.equals(MainMenu.menuText[0])) {
            item = new MenuItem(screen) {
                @Override
                public void click() {
                    System.out.println("Start button pressed");
                    screen.setScreen(new GameScreen());
                }
            };
        } else if(text.equals("Settings")) {
            item = new MenuItem(screen) {
                @Override
                public void click() {
                    System.out.println("button pressed");
                }
            };
        } else if(text.equals("About")) {
            item = new MenuItem(screen) {
                @Override
                public void click() {
                    System.out.println("button pressed");
                }
            };
        } else if(text.equals("Exit")) {
            item = new MenuItem(screen) {
                @Override
                public void click() {
                    System.exit(0);
                }
            };
        } else {
            item = new MenuItem(screen){
                @Override
                public void click() {
                    System.out.println("Oh shit, cant map this menu item");
                }
            };
        }
        return item;
    }
}
