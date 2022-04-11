package org.acme;

import java.lang.reflect.Type;
import javax.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Message;
import io.smallrye.reactive.messaging.MessageConverter;
import io.smallrye.reactive.messaging.amqp.IncomingAmqpMetadata;
import io.vertx.core.json.JsonObject;

@ApplicationScoped
public class JsonToObjectConverter implements MessageConverter {

  @Override
  public boolean canConvert(final Message<?> in, final Type target) {
    return in.getMetadata(IncomingAmqpMetadata.class)
        .map(meta -> "application/json".equals(meta.getContentType()) && target instanceof Class)
        .orElse(false);
  }

  @Override
  public Message<?> convert(final Message<?> in, final Type target) {
    return in.withPayload(((JsonObject) in.getPayload()).mapTo((Class<?>) target));
  }

}
