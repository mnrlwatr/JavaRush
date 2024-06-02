package JavaRush.tasks.Quest3.task32.task3209.actions;

import JavaRush.tasks.Quest3.task32.task3209.View;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * .
 */
public class RedoAction extends AbstractAction {
    private View view;

    public RedoAction(View view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        view.redo();
    }
}
