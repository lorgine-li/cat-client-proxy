package com.dianping.cat.proxy.internals;

import com.dianping.cat.proxy.internals.cat.CatMessageProducer;
import com.dianping.cat.proxy.spi.MessageProducer;
import com.dianping.cat.proxy.spi.MessageProducerManager;
/**
 * @author Jason Song(song_s@ctrip.com)
 */
public class DefaultMessageProducerManager implements MessageProducerManager {
  private static MessageProducer producer;
  String CAT_CLASS = "com.dianping.cat.Cat";

  public DefaultMessageProducerManager() {
    if (isClassPresent(CAT_CLASS)) {
      producer = new CatMessageProducer();
    } else {
      producer = new NullMessageProducerManager().getProducer();
    }
  }
  public static boolean isClassPresent(String className) {
    try {
      Class.forName(className);
      return true;
    } catch (ClassNotFoundException ex) {
      // ignore expected exception
      return false;
    } catch (LinkageError ex) {
      // unexpected error, need to let the user know the actual error
      return false;
    }
  }

  @Override
  public MessageProducer getProducer() {
    return producer;
  }
}
