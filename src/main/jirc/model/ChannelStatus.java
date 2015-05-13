package jirc.model;

/**
 * Created with IntelliJ IDEA.
 * User: troels
 * Date: 10/8/12
 * Time: 7:57 PM
 * To change this template use File | Settings | File Templates.
 */
public enum ChannelStatus {

    OPERATOR,
    HALFOPERATOR,
    VOICE,
    NORMAL;

    public int getRank() {
        switch (this) {
            case OPERATOR:
                return 3;
            case HALFOPERATOR:
                return 2;
            case VOICE:
                return 1;
            case NORMAL:
                return 0;
            default:
                return 0;
        }
    }
}
