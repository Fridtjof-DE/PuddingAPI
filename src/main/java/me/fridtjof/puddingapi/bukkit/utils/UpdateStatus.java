package me.fridtjof.puddingapi.bukkit.utils;

public enum UpdateStatus {
    PENDING(false, false),
    UP_TO_DATE(false, false),
    OUTDATED(true, true),
    DEV_BUILD(true, false),
    NOT_APPROVED(false, false),
    CONNECTION_FAILED(false, false),
    EXCEPTION(false, false);

    private final boolean notifyPlayer;
    private final boolean showResourceLink;

    UpdateStatus(boolean notifyPlayer, boolean showResourceLink) {
        this.notifyPlayer = notifyPlayer;
        this.showResourceLink = showResourceLink;
    }

    public boolean shouldNotifyPlayer() {
        return notifyPlayer;
    }

    public boolean shouldShowResourceLink() {
        return showResourceLink;
    }
}