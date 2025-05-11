package com.nilknow.temp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {
    private Robot robot;
    private App app;
    private TextArea textArea;

    @BeforeEach
    void beforeAll() throws AWTException {
        System.out.println("before all");
        this.app = new App();
        app.run();
        textArea = app.textArea;

        robot = new Robot();
        robot.delay(50);
    }

    @Test
    void test() throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeLater(() -> textArea.requestFocus());
        robot.waitForIdle();

        String text = "hello";
        for (char c : text.toCharArray()) {
            int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
            if (KeyEvent.CHAR_UNDEFINED == keyCode) {
                throw new IllegalArgumentException("Character not supported: " + c);
            }
            robot.keyPress(keyCode);
            robot.keyRelease(keyCode);
        }

        Thread.sleep(1000);

        final String[] actualOutput = {""};
        SwingUtilities.invokeAndWait(() -> actualOutput[0] = textArea.getText());
        assertEquals("hello", actualOutput[0]);
    }
}