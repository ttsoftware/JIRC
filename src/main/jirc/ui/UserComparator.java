package jirc.ui;

import jirc.model.ChannelStatus;
import jirc.service.ChannelService;

import java.util.Comparator;

public class UserComparator implements Comparator<String> {

    @Override
    public int compare(String username1, String username2) {

        return compareString(
                username1,
                username2
        );
    }

    private int compareString(String username1, String username2) {
        ChannelStatus status1 = ChannelService.getChannelStatus(username1);
        ChannelStatus status2 = ChannelService.getChannelStatus(username2);

        if (status1.getRank() > status2.getRank()) {
            return 0;
        }
        else if (status1.getRank() == status2.getRank()) {
            if (username1.compareToIgnoreCase(username2) < 0) {
                return 0;
            }
        }
        return 1;
    }
}
