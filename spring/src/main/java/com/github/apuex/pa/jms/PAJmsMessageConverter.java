package com.github.apuex.pa.jms;

import com.github.apuex.pa.mhs.PABase;
import com.github.apuex.pa.mhs.PAMessageConverter;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

public class PAJmsMessageConverter implements MessageConverter {
  private PAMessageConverter converter;

  @Override
  public Object fromMessage(Message message) throws JMSException, MessageConversionException {
    return converter.fromBytesMessage((BytesMessage) message);
  }

  @Override
  public Message toMessage(Object payload, Session session) throws JMSException, MessageConversionException {
    return converter.toBytesMessage((PABase) payload, session);
  }

  public void setConverter(PAMessageConverter converter) {
    this.converter = converter;
  }
}
