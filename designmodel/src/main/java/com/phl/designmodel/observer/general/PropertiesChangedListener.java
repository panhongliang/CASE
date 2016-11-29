package com.phl.designmodel.observer.general;

import java.util.EventListener;

/**
 * Created by panhongliang on 16/2/24.
 */
public interface PropertiesChangedListener<T extends  PropertiesChangedEvent>  extends EventListener{

    public void onPropertiesChanged(T event);

}
