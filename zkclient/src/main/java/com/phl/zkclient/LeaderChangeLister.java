package com.phl.zkclient;

/**
 * Created by Administrator on 2017-02-21.
 */
public interface LeaderChangeLister {
    public void onLeaderChang(LeaderEvent event);
}
