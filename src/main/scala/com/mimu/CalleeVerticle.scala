package com.mimu

/**
 * Created by mm on 13/11/2014.
 */

import java.util.concurrent.atomic.AtomicLong
import org.vertx.scala.platform.Verticle
import org.vertx.scala.core.http.{HttpServer,HttpServerRequest}


class CalleeVerticle extends Verticle {

  val LISTENING_PORT:Int = 9199;
  val counter:AtomicLong = new AtomicLong(0L);

  override def start(): Unit = {

    val server: HttpServer = vertx.createHttpServer

    server.requestHandler{ req:HttpServerRequest => {
        req.response().setChunked(true);
        req.response().setStatusCode(200);
        req.response().write("counter=" + counter.incrementAndGet());
        req.response().end();
    }}

    server.listen(LISTENING_PORT);

  }

}
