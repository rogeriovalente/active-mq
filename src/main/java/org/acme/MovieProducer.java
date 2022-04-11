package org.acme;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@ApplicationScoped
public class MovieProducer {

  @Inject
  @Channel("movies-out")
  Emitter<Movie> emitter;

  public void send(final Movie movie) {
    emitter.send(movie);
  }
}
