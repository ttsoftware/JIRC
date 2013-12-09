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
        ChannelStatus status1 = ChannelService.getUserStatus(username1);
        ChannelStatus status2 = ChannelService.getUserStatus(username2);

        if (status1.getRank() > status2.getRank()) {
            return status1.getRank();
        }
        return status2.getRank();
    }
}
