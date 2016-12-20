package com.phl.designmodel.memento;

/**
 * Created by Administrator on 2016-12-20.
 * 备忘录接口，窄接口，没有实现方法，具体的实现类impl在备忘录发起人内部。
 * 要实现的备忘逻辑也在impl中实现，这样外部即使持有备忘录，也无法访问备忘录的内容。
 */
public interface Memento {
}
