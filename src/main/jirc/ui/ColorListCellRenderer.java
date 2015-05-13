package jirc.ui;

import jirc.model.ChannelStatus;
import jirc.service.ChannelService;

import javax.swing.*;
import java.awt.*;

public class ColorListCellRenderer extends DefaultListCellRenderer {

    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        ChannelStatus status = ChannelService.getChannelStatus((String) value);
        Color fg;

        switch (status) {
            case OPERATOR:
                fg = Color.RED;
                break;
            case HALFOPERATOR:
                fg = Color.YELLOW;
                break;
            case VOICE:
                fg = Color.BLUE;
                break;
            default:
                fg = Color.BLACK;
                break;
        }

        setForeground(fg);
        return this;
    }
}

